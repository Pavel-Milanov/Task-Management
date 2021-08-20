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

public class UnAssignTaskFromMemberCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public UnAssignTaskFromMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return unAssignee(taskId);
    }

    private String unAssignee(int taskId) {
        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskId);

        if (task.getType().equals(TaskType.FEEDBACK)) {
            throw new InvalidUserInputException(String.format(CommandConstants.INVALID_TASK_TYPE, task.getType()));
        }

        BugStory bugStory = (BugStory) task;
        bugStory.changeAssignee("");

        return CommandConstants.ASSIGNEE_REMOVED;
    }
}
