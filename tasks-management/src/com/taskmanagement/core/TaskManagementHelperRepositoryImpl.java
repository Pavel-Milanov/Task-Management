package com.taskmanagement.core;

import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.constants.CoreConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;
import static com.taskmanagement.constants.CoreConstants.ELEMENT_NOT_FOUND;

public class TaskManagementHelperRepositoryImpl {

    private final TaskManagementRepository taskManagementHelperRepository;

    public TaskManagementHelperRepositoryImpl(TaskManagementRepository taskManagementHelperRepository) {
        this.taskManagementHelperRepository = taskManagementHelperRepository;
    }

    public List<WorkingItem> getWorkingItems() {

        List<WorkingItem> workingItems = new ArrayList<>();

        workingItems.addAll(taskManagementHelperRepository.getBugs());
        workingItems.addAll(taskManagementHelperRepository.getStories());
        workingItems.addAll(taskManagementHelperRepository.getFeedBacks());

        return new ArrayList<>(workingItems);
    }

    public List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();

        tasks.addAll(taskManagementHelperRepository.getBugs());
        tasks.addAll(taskManagementHelperRepository.getStories());


        return new ArrayList<>(tasks);
    }

    public Member findMemberByName(String name) {
        return taskManagementHelperRepository.getMembers().stream()
                .filter(member -> name.equals(member.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, name)));

    }

    public <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) return element;
        }

        throw new ElementNotFoundException(String.format("No record with ID %d", id));

    }

    public void validateMemberIsFromTeam(int taskId, String memberName) {
        WorkingItem workingItem = findElementById(getWorkingItems(), taskId);

        Board board = taskManagementHelperRepository.getBoards().stream()
                .filter(board1 -> board1.getTasks().contains(workingItem)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.TASK_NOT_ATTACHED_TO_BOARD));

        Team team = taskManagementHelperRepository.getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.BOARD_NOT_ATTACHED_TO_TEAM));

        Member member = findMemberByName(memberName);
        if (!team.getMembers().contains(member)) {
            throw new InvalidUserInputException(String.format(MEMBER_NOT_USER_FROM_TEAM, member, team.getName()));
        }
    }

    public boolean teamExist(String teamName) {
        return taskManagementHelperRepository.getTeams().stream().anyMatch(team -> team.getName().equals(teamName));

    }

    public Team findTeamByName(String teamName) {
        return taskManagementHelperRepository.getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, teamName)));
    }

    public Board findBoard(String boardName) {
        return taskManagementHelperRepository.getBoards().stream()
                .filter(board -> boardName.equals(board.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, boardName)));

    }

    public void addBoardToTeam(Board board, Team team) {
        boolean added = false;
        for (Team team1 : taskManagementHelperRepository.getTeams()) {
            if (team1.getName().equals(team.getName())) {
                team1.addBoard(board);
                added = true;
            }
        }
        if (!added) throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName()));

    }

    public boolean memberExist(String memberName) {
        return taskManagementHelperRepository.getMembers().stream().anyMatch(member -> member.getName().equals(memberName));
    }

    public boolean checkMemberIsFromTeam(String memberName, String teamName) {
        Member member = findMemberByName(memberName);
        Team team = findTeamByName(teamName);

        return team.getMembers().contains(member);
    }

    public Board getBoard(Board board) {
        return taskManagementHelperRepository.getBoards().stream()
                .filter(board1 -> board1.equals(board)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, board.getName())));
    }

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

    public Member findByMemberName(String memberName) {
        for (Member member : taskManagementHelperRepository.getMembers()) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new IllegalArgumentException(String.format(MEMBER_NOT_EXISTS, memberName));
    }

    public Team findByTeamName(String teamName) {

        for (Team team : taskManagementHelperRepository.getTeams()) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new InvalidUserInputException(String.format(TEAM_NOT_EXISTS, teamName));
    }

    public void addMemberToTeam(Member member, Team team) {
        boolean isAdded = false;
        for (Team team1 : taskManagementHelperRepository.getTeams()) {
            if (team1.getName().equals(team.getName())) {
                team1.addMember(member);
                isAdded = true;
            }
        }
        if (!isAdded) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName())); // тук да проверя
        }
    }

    public boolean boardExist(String boardName) {
        return taskManagementHelperRepository.getBoards().stream().anyMatch(board -> board.getName().equals(boardName));
    }

    public void validateAssigneeIsMemberOfTeam(Board board, String assignee) {
        Team team = taskManagementHelperRepository.getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CommandConstants.BOARD_IN_TEAM_NOT_EXISTS));

        if (!checkMemberIsFromTeam(assignee, team.getName())) {
            throw new InvalidUserInputException(String.format(CommandConstants.MEMBER_NOT_USER_FROM_TEAM, assignee, team.getName()));
        }
    }

    public Team getTeam(String teamName) {
        return taskManagementHelperRepository.getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.ELEMENT_NOT_FOUND, teamName)));

    }

    public boolean assigneeIsExist(String nameAssignee) {
        return getTasks().stream().noneMatch(task -> task.getAssignee().equalsIgnoreCase(nameAssignee));
    }

    public boolean assigneeExist(String nameAssignee) {
        return getTasks().stream().noneMatch(task -> task.getAssignee().equalsIgnoreCase(nameAssignee));
    }
}
