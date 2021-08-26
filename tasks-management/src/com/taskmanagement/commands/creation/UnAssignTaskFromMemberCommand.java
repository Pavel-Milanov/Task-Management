package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;

public class UnAssignTaskFromMemberCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public UnAssignTaskFromMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0),INVALID_TASK_INDEX);
        return unAssignee(taskId);
    }

    private String unAssignee(int taskId) {

        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskId);
        task.changeAssignee("");

        return ASSIGNEE_REMOVED;
    }
}
