package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeFilterByAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeFilterByAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String nameAssignee = parameters.get(0);

        return filterByAssignee(nameAssignee);
    }

    private String filterByAssignee(String nameAssignee) {

        if (taskManagementRepository.assigneeIsExist(nameAssignee)) {
            throw new InvalidUserInputException(String.format(CommandConstants.ASSIGNEE_NOT_EXIST, nameAssignee));
        }

        List<Task> filteredByAssignee = taskManagementRepository.getTasks()
                .stream().filter(task -> task.getAssignee().equalsIgnoreCase(nameAssignee)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredByAssignee);
    }
//Ralitsa
}
