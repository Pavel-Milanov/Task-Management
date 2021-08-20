package com.taskmanagеment.core.contacts;

import com.taskmanagеment.models.contracts.*;
import com.taskmanagеment.models.enums.TaskType;

import java.util.List;

public interface TaskManagementRepository {

    List<Team> getTeams();

    List<Member> getMembers();

    List<Board> getBoards();

    List<Task> getTasks();

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

    Bug createBug(String title, String description, String assignee);

    Story createStory(String title, String description, String assignee);

    FeedBack createFeedback(String title, String description, int rating);

    void removeComment(Comment comment, Task task);

}
