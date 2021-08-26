package com.taskmanagement.core.contacts;

import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {

    List<Team> getTeams();

    List<Member> getMembers();

    List<Board> getBoards();

    List<Story> getStories();

    List<Bug> getBugs();

    List<FeedBack> getFeedBacks();

    Member findMemberByName(String author);

    <T extends Identifiable> T findElementById(List<T> elements, int id);

    void validateMemberIsFromTeam(int taskId, String memberName);

    Comment createComment(String content, String author);

    boolean teamExist(String teamName);

    Team findTeamByName(String teamName);

    Board findBoard(String boardName);

    void addBoardToTeam(Board board, Team team);

    boolean memberExist(String memberName);

    Member createMember(String memberName);

    Board findBoardInTeam(String name);

    boolean checkMemberIsFromTeam(String memberName, String teamName);

    Board getBoard(Board board);

    Bug createBug(String name, String description, Priority priority, Severity severity, BugStatus status, String assignee);

    Story createStory(String name, String description, Priority priority, Size size, StoryStatus status, String assignee);

    FeedBack createFeedback(String name, String description, int rating, FeedBackStatus feedBackStatus);

    void removeComment(Comment comment, WorkingItem workingItem);

    Member findByMemberName(String memberName);

    Team findByTeamName(String teamName);

    void addMemberToTeam(Member member, Team team);

    void changeFeedBackStatus(FeedBackStatus feedBackStatus, FeedBack feedBack);

    void changeLabelStoryStatus(StoryStatus storyStatus, Story story);

    void changeLabelSize(Size size, Story story);

    void changeLabelStoryPriority(Priority priority, Story story);

    void changeLabelRating(int rating, FeedBack feedBack);

    void changeLabelBugStatus(Bug bug, BugStatus bugStatus);

    void changeLabelSeverityBug(Bug bug, Severity severity);

    void changeLabelPriorityBug(Bug bug, Priority priority);

    Board createBoard(String boardName);

    boolean boardExist(String boardName);

    Team createTeam(String teamName);

    void removeBoard(String boardName);

    void removeTask(WorkingItem workingItem);

    List<WorkingItem> getWorkingItems();

    List<Task> getTasks();

    boolean validateAssigneeIsMemberOfTeam(Board board, String assignee);

    Team getTeam(String teamName);

    boolean titleExist(String title);

    boolean assigneeIsExist(String nameAssignee);

    void removeTeam(String teamName);

    List<Bug> getBugsFilteredByBugStatusAndAssignee(BugStatus bugStatus, String nameAssignee);

    List<Story> getStoriesFilteredByStoryStatus(StoryStatus storyStatus);
}
