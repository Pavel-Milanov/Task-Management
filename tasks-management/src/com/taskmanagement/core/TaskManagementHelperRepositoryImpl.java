package com.taskmanagement.core;

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

    public <T extends Identifiable> T findElementById(List<T> elements, int id) {
        return elements.stream().filter(element -> element.getId() == id).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CoreConstants.INVALID_ID, id)));
    }

    public void validateAssigneeIsMemberOfTeam(Board board, String assignee) {

        checkMemberFromTeam(assignee, board);

    }

    public void validateMemberIsFromTeam(int taskId, String memberName) {
        WorkingItem workingItem = findElementById(getWorkingItems(), taskId);

        Board board = taskManagementHelperRepository.getBoards().stream()
                .filter(board1 -> board1.getWorkingItems().contains(workingItem)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.TASK_NOT_ATTACHED_TO_BOARD));

        checkMemberFromTeam(memberName, board);
    }

    public void checkMemberFromTeam(String memberName, Board board) {
        Member member = findMemberByName(memberName);

        Team team = taskManagementHelperRepository.getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(CoreConstants.BOARD_NOT_ATTACHED_TO_TEAM));

        if (!team.getMembers().contains(member)) {
            throw new InvalidUserInputException(String.format(MEMBER_NOT_USER_FROM_TEAM, memberName, team.getName()));
        }
    }

    public boolean isTeamExist(String teamName) {
        return taskManagementHelperRepository.getTeams().stream().anyMatch(team -> team.getName().equals(teamName));

    }

    public boolean isBoardExist(String boardName) {
        return taskManagementHelperRepository.getBoards().stream().anyMatch(board -> board.getName().equals(boardName));
    }

    public boolean isAssigneeExist(String nameAssignee) {
        return getTasks().stream().noneMatch(task -> task.getAssignee().equalsIgnoreCase(nameAssignee));
    }

    public boolean isMemberExist(String memberName) {
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
