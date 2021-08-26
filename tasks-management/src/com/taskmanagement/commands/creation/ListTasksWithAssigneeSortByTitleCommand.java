package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeSortByTitleCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeSortByTitleCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String assignee = parameters.get(1);
        return listTasks(assignee);
    }

    private String listTasks(String assignee) {
        List<Task> taskFilter;
        taskFilter = taskManagementRepository.getTasks().stream()
                .filter(task -> task.getAssignee().equals(assignee)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(taskFilter);
    }

}
