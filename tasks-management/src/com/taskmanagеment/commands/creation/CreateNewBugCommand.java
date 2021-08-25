package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBugCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    private final TaskManagementRepository taskManagementRepository;


    public CreateNewBugCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        try {
            ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        }catch (IllegalArgumentException exception){
            ValidationHelpers.validateArgumentsCount(parameters,(EXPECTED_NUMBER_OF_ARGUMENTS - 1));
        }
        Board board = taskManagementRepository.findBoard(parameters.get(0));
        String name = parameters.get(1);
        String description = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3),Priority.class );
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class );
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(5), BugStatus.class);
        String assignee = parameters.get(6);
        if (EXPECTED_NUMBER_OF_ARGUMENTS != 7) {
            assignee = "";
        }
        taskManagementRepository.validateAssigneeIsMemberOfTeam(board, assignee);
        return createBug(board,name,description,priority,severity,status,assignee);
    }

    private String createBug(Board board, String name, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        Bug bug = taskManagementRepository.createBug(name,description,priority,severity,status,assignee);
        taskManagementRepository.getBoard(board).addTask(bug);
        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, name);
    }


}
