package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllTasksCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListAllTasksCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String filterType = parameters.get(0).toUpperCase();
        return parseParam(filterType);

    }

    private String parseParam(String filterType) {
        List<Task> taskList = taskManagementRepository.getTasks();

        if (filterType.equals("SORT")) {
            taskList.sort(Comparator.comparing(o -> o.getName().toUpperCase()));
        } else {
            taskList.stream().filter(task -> task.getName().toUpperCase().equals(filterType)).findAny()
                    .orElseThrow(() -> new InvalidUserInputException(String.format(CommandConstants.INVALID_TASK_NAME, filterType)));
        }

        return ListingHelpers.elementsToString(taskList);
    }
}
