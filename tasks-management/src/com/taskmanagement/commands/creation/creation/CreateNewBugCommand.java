package com.taskmanagement.commands.creation.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBugCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public CreateNewBugCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        try {
            ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        } catch (IllegalArgumentException exception) {
            ValidationHelpers.validateArgumentsCount(parameters, (EXPECTED_NUMBER_OF_ARGUMENTS - 1));
        }
        Board board = helperRepository.findElementById(taskManagementRepository.getBoards(), ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX));
        String name = parameters.get(1);
        String description = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(5), BugStatus.class);
        String assignee = CommandConstants.NO_ASSIGNEE;

        if (parameters.size() == 7) {
            assignee = parameters.get(6);
            helperRepository.validateAssigneeIsMemberOfTeam(board, assignee);
        }

        return createBug(board, name, description, priority, severity, status, assignee);
    }

    private String createBug(Board board, String name, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        Bug bug = taskManagementRepository.createBug(name, description, priority, severity, status, assignee);
        board.addWorkingItem(bug);
        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, name);
    }


}
