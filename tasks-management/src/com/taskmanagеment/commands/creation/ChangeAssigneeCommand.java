package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.BugStory;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ChangeAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        String assignee = parameters.get(1);
        return validateUserFromTeam(taskId, assignee);
    }

    private String validateUserFromTeam(int taskId, String assignee) {
        taskManagementRepository.validateMemberIsFromTeam(taskId, assignee);
        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskId);
        return changeAssignee(task, assignee);
    }

    private String changeAssignee(Task task, String assignee) {
        if (task.getType().equals(TaskType.FEEDBACK)) {
            throw new InvalidUserInputException(String.format(CommandConstants.INVALID_TASK_TYPE, task.getType()));
        }

        BugStory bugStory = (BugStory) task;
        bugStory.changeAssignee(assignee);

        return String.format(CommandConstants.ASSIGNEE_CHANGED, assignee);
    }
}
