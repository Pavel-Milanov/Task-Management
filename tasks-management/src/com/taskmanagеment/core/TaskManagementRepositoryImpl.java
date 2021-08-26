package com.taskmanagеment.core;

import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.constants.CoreConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.ElementNotFoundException;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.BoardImpl;
import com.taskmanagеment.models.CommentImpl;
import com.taskmanagеment.models.MemberImpl;
import com.taskmanagеment.models.TeamImpl;
import com.taskmanagеment.models.contracts.*;
import com.taskmanagеment.models.enums.*;
import com.taskmanagеment.models.tasks.BugImpl;
import com.taskmanagеment.models.tasks.FeedBackImpl;
import com.taskmanagеment.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;
import static com.taskmanagеment.constants.CoreConstants.ELEMENT_NOT_FOUND;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private final List<Team> teams;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<Story> stories;
    private final List<Bug> bugs;
    private final List<FeedBack> feedBacks;
    private int nextId;

    public TaskManagementRepositoryImpl() {
        nextId = 0;
        this.teams = new ArrayList<>();
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.feedBacks = new ArrayList<>();

    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<FeedBack> getFeedBacks() {
        return new ArrayList<>(feedBacks);
    }

    @Override
    public Member findMemberByName(String name) {
        return getMembers().stream()
                .filter(member -> name.equals(member.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, name)));

    }

    @Override
    public <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) return element;
        }

        throw new ElementNotFoundException(String.format("No record with ID %d", id));

    }

    @Override
    public void validateMemberIsFromTeam(int taskId, String memberName) {
        WorkingItem workingItem = findElementById(getWorkingItems(), taskId);

        Board board = getBoards().stream()
                .filter(board1 -> board1.getTasks().contains(workingItem)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.TASK_NOT_ATTACHED_TO_BOARD));

        Team team = getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.BOARD_NOT_ATTACHED_TO_TEAM));

        Member member = findMemberByName(memberName);
        if (!member.getTeamList().contains(team)) {
            throw new InvalidUserInputException(String.format(MEMBER_NOT_USER_FROM_TEAM, member, team.getName()));
        }
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public boolean teamExist(String teamName) {
        return getTeams().stream().anyMatch(team -> team.getName().equals(teamName));

    }

    @Override
    public Team findTeamByName(String teamName) {
        return getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, teamName)));
    }

    @Override
    public Board findBoard(String boardName) {
        return getBoards().stream()
                .filter(board -> boardName.equals(board.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, boardName)));

    }

    @Override
    public void addBoardToTeam(Board board, Team team) {
        boolean added = false;
        for (Team team1 : teams) {
            if (team1.getName().equals(team.getName())) {
                team1.addBoard(board);
                added = true;
            }
        }
        if (!added) throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName()));

    }

    @Override
    public boolean memberExist(String memberName) {
        return getMembers().stream().anyMatch(member -> member.getName().equals(memberName));
    }

    @Override
    public Member createMember(String memberName) {
        Member member = new MemberImpl(++nextId, memberName);
        this.members.add(member);
        return member;
    }

    @Override
    public Board findBoardInTeam(String name) {
        return getBoards().stream()
                .filter(board -> name.equals(board.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, name)));
    }

    @Override
    public boolean checkMemberIsFromTeam(String memberName, String teamName) {
        Member member = findMemberByName(memberName);
        Team team = findTeamByName(teamName);

        return member.getTeamList().contains(team);
    }

    @Override
    public Board getBoard(Board board) {
        return getBoards().stream()
                .filter(board1 -> board1.equals(board)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, board.getName())));
    }

    @Override
    public Bug createBug(String name, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        Bug bug = new BugImpl(++nextId, name, description,priority,severity,status, assignee);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String name, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        Story story = new StoryImpl(++nextId, name, description,priority,size,status, assignee);
        this.stories.add(story);
        return story;
    }


    @Override
    public FeedBack createFeedback(String name, String description, int rating,FeedBackStatus feedBackStatus) {
        FeedBack feedback = new FeedBackImpl(++nextId, name, description, rating,feedBackStatus);
        this.feedBacks.add(feedback);
        return feedback;
    }

    @Override
    public void removeComment(Comment comment, WorkingItem workingItem) {
        boolean removed = false;
        for (WorkingItem workingItem1 : getWorkingItems()) {
            if (workingItem1.getName().equals(workingItem.getName())) {
                workingItem1.removeComment(comment);
                removed = true;
            }
        }

        if (!removed)
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, comment.getContent()));

    }

    @Override
    public Member findByMemberName(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new IllegalArgumentException(String.format(MEMBER_NOT_EXISTS, memberName));
    }

    @Override
    public Team findByTeamName(String teamName) {

        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new InvalidUserInputException(String.format(TEAM_NOT_EXISTS, teamName));
    }

    @Override
    public void addMemberToTeam(Member member, Team team) {
        boolean isAdded = false;
        for (Team team1 : teams) {
            if (team1.getName().equals(team.getName())) {
                team1.addMember(member);
                member.addTeam(team1);
                isAdded = true;
            }
        }
        if (!isAdded) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName())); // тук да проверя
        }
    }

    @Override
    public void changeFeedBackStatus(FeedBackStatus feedBackStatus, FeedBack feedBack) {
        if (feedBack.getFeedBackStatus() == feedBackStatus) {
            throw new InvalidUserInputException("The Story status is the same as previous!");
        }

        feedBack.changeFeedbackStatus(feedBackStatus);
    }

    @Override
    public void changeLabelStoryStatus(StoryStatus storyStatus, Story story) {
        if (story.getStoryStatus() == storyStatus) {
            throw new InvalidUserInputException("The Story status is the same as previous!");
        }
        story.changeStoryStatus(storyStatus);
    }

    @Override
    public void changeLabelSize(Size size, Story story) {
        if (story.getSize() == size) {
            throw new InvalidUserInputException("The size is the same as previous!");
        }

        story.changeSize(size);

    }

    @Override
    public void changeLabelStoryPriority(Priority priority, Story story) {
        if (story.getPriority() == priority) {
            throw new InvalidUserInputException("The priority is the same as previous!");
        }

        story.changeStoryPriority(priority);
    }

    @Override
    public void changeLabelRating(int rating, FeedBack feedBack) {
        if (feedBack.getRating() == rating) {
            throw new InvalidUserInputException("The rating is the same as previous!");
        }
        feedBack.changeFeedbackRating(rating);
    }

    @Override
    public void changeLabelSeverityBug(Bug bug, Severity severity) {

        if (bug.getSeverity() == severity) {
            throw new InvalidUserInputException("The severity is the same as previous!");
        }
        bug.changeBugSeverity(severity);
    }

    @Override
    public void changeLabelBugStatus(Bug bug, BugStatus bugStatus) {
        if (bug.getBugStatus() == bugStatus) {
            throw new InvalidUserInputException("The bug status is the same as previous!");
        }

        bug.changeBugStatus(bugStatus);
    }

    @Override
    public void changeLabelPriorityBug(Bug bug, Priority priority) {

        if (bug.getPriority() == priority) {
            throw new InvalidUserInputException("The priority is the same as previous!");
        }
        bug.changeBugPriority(priority);

    }

    @Override
    public boolean boardExist(String boardName) {
        return boards.stream().anyMatch(board -> board.getName().equals(boardName));
    }

    @Override
    public Board createBoard(String boardName) {
        Board board = new BoardImpl(++nextId, boardName);

        if (boardExist(boardName)) {
            throw new InvalidUserInputException(String.format(BOARD_ALREADY_EXISTS, boardName));
        }

        boards.add(board);
        return board;
    }

    @Override
    public Team createTeam(String teamName) {
        Team team = new TeamImpl(teamName);

        teams.add(team);
        return team;
    }





    @Override
    public void removeBoard(String boardName) {

        boards.removeIf(board -> board.getName().equals(boardName));
    }

    @Override
    public void removeTask(WorkingItem workingItem) {
        getWorkingItems().removeIf(task1 -> workingItem.getName().equalsIgnoreCase(workingItem.getName()));

    }

    @Override
    public List<WorkingItem> getWorkingItems(){

        List<WorkingItem> workingItems = new ArrayList<>();

        workingItems.addAll(getBugs());
        workingItems.addAll(getStories());
        workingItems.addAll(getFeedBacks());

        return new ArrayList<>(workingItems);
    }

    @Override
    public List<Task> getTasks(){

        List<Task> tasks = new ArrayList<>();

        tasks.addAll(getBugs());
        tasks.addAll(getStories());


        return new ArrayList<>(tasks);
    }

    @Override
    public boolean validateAssigneeIsMemberOfTeam(Board board, String assignee) {
        Team team = getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CommandConstants.BOARD_IN_TEAM_NOT_EXISTS));

        if (!checkMemberIsFromTeam(assignee, team.getName())) {
            throw new InvalidUserInputException(String.format(CommandConstants.MEMBER_NOT_USER_FROM_TEAM, assignee, team.getName()));
        }
        return true;
    }

    @Override
    public Team getTeam(String teamName) {
        return getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, teamName)));

    }

    @Override
    public boolean titleExist(String title) {

        return getBugs().stream().anyMatch(bug -> bug.getName().equalsIgnoreCase(title));
    }

    @Override
    public boolean assigneeIsExist(String nameAssignee) {


        return getTasks().stream().anyMatch(task -> task.getAssignee().equalsIgnoreCase(nameAssignee));
    }
}

