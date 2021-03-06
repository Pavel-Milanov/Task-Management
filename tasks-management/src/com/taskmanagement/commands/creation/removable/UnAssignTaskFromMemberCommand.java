package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.ASSIGNEE_REMOVED;
import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;

public class UnAssignTaskFromMemberCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public UnAssignTaskFromMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        return unAssignee(taskId);
    }

    private String unAssignee(int taskId) {

        Task task = helperRepository.findElementById(helperRepository.getTasks(), taskId);
        task.changeAssignee(CommandConstants.NO_ASSIGNEE);

        return ASSIGNEE_REMOVED;
    }
}
