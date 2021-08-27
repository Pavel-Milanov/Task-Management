package com.taskmanagement.core;

import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.constants.CoreConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.CommandConstants.MEMBER_NOT_USER_FROM_TEAM;
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

    public Board getBoard(Board board) {
        return taskManagementHelperRepository.getBoards().stream()
                .filter(board1 -> board1.equals(board)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, board.getName())));
    }

    public Member findMemberByName(String name) {
        return taskManagementHelperRepository.getMembers().stream()
                .filter(member -> name.equals(member.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, name)));

    }

    public Team findTeamByName(String teamName) {
        return taskManagementHelperRepository.getTeams().stream()
                .filter(team -> teamName.equals(team.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, teamName)));
    }

    public Board findBoardByName(String boardName) {
        return taskManagementHelperRepository.getBoards().stream()
                .filter(board -> boardName.equals(board.getName())).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, boardName)));

    }

    public <T extends Identifiable> T findElementById(List<T> elements, int id) {
        return elements.stream().filter(element -> element.getId() == id).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format("No record with ID %d", id)));
    }

    public void validateAssigneeIsMemberOfTeam(Board board, String assignee) {
        Team team = taskManagementHelperRepository.getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CommandConstants.BOARD_IN_TEAM_NOT_EXISTS));

        if (!validateMemberIsFromTeam(assignee, team.getName())) {
            throw new InvalidUserInputException(String.format(CommandConstants.MEMBER_NOT_USER_FROM_TEAM, assignee, team.getName()));
        }
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

    public boolean validateMemberIsFromTeam(String memberName, String teamName) {
        Member member = findMemberByName(memberName);
        Team team = findTeamByName(teamName);

        return team.getMembers().contains(member);
    }

    public boolean teamExist(String teamName) {
        return taskManagementHelperRepository.getTeams().stream().anyMatch(team -> team.getName().equals(teamName));

    }

    public boolean boardExist(String boardName) {
        return taskManagementHelperRepository.getBoards().stream().anyMatch(board -> board.getName().equals(boardName));
    }

    public boolean assigneeExist(String nameAssignee) {
        return getTasks().stream().noneMatch(task -> task.getAssignee().equalsIgnoreCase(nameAssignee));
    }

    public boolean memberExist(String memberName) {
        return taskManagementHelperRepository.getMembers().stream().anyMatch(member -> member.getName().equals(memberName));
    }

    public void addBoardToTeam(Board board, Team team) {
        boolean added = false;
        for (Team team1 : taskManagementHelperRepository.getTeams()) {
            if (team1.getName().equals(team.getName())) {
                team1.addBoard(board);
                added = true;
            }
        }
        if (!added) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName()));
        }
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
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, team.getName()));
        }
    }
}
