package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;

import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;

public class ChangeAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        String assignee = parameters.get(1);
        return validateUserFromTeam(taskId, assignee);
    }

    private String validateUserFromTeam(int taskId, String assignee) {

        taskManagementRepository.validateMemberIsFromTeam(taskId, assignee);
        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskId);
        return changeAssignee(task, assignee);
    }

    private String changeAssignee(Task task, String assignee) {

       

        task.changeAssignee(assignee);

        return String.format(ASSIGNEE_CHANGED, assignee);
    }
}
