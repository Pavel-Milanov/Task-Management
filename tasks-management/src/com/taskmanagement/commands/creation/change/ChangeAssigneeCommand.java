package com.taskmanagement.commands.creation.change;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.ASSIGNEE_CHANGED;
import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;

public class ChangeAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        String assignee = parameters.get(1);
        return validateUserFromTeam(taskId, assignee);
    }

    private String validateUserFromTeam(int taskId, String assignee) {

        helperRepository.validateMemberIsFromTeam(taskId, assignee);
        Task task = helperRepository.findElementById(helperRepository.getTasks(), taskId);
        return changeAssignee(task, assignee);
    }

    private String changeAssignee(Task task, String assignee) {


        task.changeAssignee(assignee);

        return String.format(ASSIGNEE_CHANGED, assignee);
    }
}
