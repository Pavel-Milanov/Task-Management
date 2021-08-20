package com.taskmanagеment.core;

import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.constants.CoreConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.ElementNotFoundException;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.CommentImpl;
import com.taskmanagеment.models.MemberImpl;
import com.taskmanagеment.models.contracts.*;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.models.tasks.BugImpl;
import com.taskmanagеment.models.tasks.FeedBackImpl;
import com.taskmanagеment.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.NO_SUCH_ENUM;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private final List<Team> teams = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private int nextId;

    public TaskManagementRepositoryImpl() {
        nextId = 0;
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
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Member findMemberByName(String name) {
        return getMembers().stream()
                .filter(member -> name.equals(member.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, name)));

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
        Task task = findElementById(getTasks(), taskId);

        Board board = getBoards().stream()
                .filter(board1 -> board1.getTasks().contains(task)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.TASK_NOT_ATTACHED_TO_BOARD));

        Team team = getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.BOARD_NOT_ATTACHED_TO_TEAM));

        Member member = findMemberByName(memberName);
        if (!member.getTeamList().contains(team)) {
            throw new InvalidUserInputException(String.format(CommandConstants.USER_NOT_MEMBER, member, team.getName()));
        }
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public boolean teamExist(String teamName) {
        return getTasks().stream().anyMatch(task -> task.getName().equals(teamName));
    }

    @Override
    public Team findTeamByName(String teamName) {
        return getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, teamName)));
    }

    @Override
    public Board findBoard(String boardName) {
        return getBoards().stream()
                .filter(board -> boardName.equals(board.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, boardName)));

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
        if (!added) throw new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, team.getName()));

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
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, name)));
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
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, board.getName())));
    }

    @Override
    public Bug createBug(String title, String description, String assignee) {
        findMemberByName(assignee);
        Bug bug = new BugImpl(++nextId, title, description, assignee);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, String assignee) {
        findMemberByName(assignee);
        Story story = new StoryImpl(++nextId, title, description, assignee);
        this.tasks.add(story);
        return story;
    }

    @Override
    public FeedBack createFeedback(String title, String description, int rating) {
        FeedBack feedback = new FeedBackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public void removeComment(Comment comment, Task task) {
        boolean removed = false;
        for (Task task1 : tasks) {
            if (task1.getName().equals(task.getName())) {
                task1.removeComment(comment);
                removed = true;
            }
        }

        if (!removed)
            throw new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, comment.getContent()));

    }


}
