package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeSortByTitleCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ListTasksWithAssigneeSortByTitleCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listTasks();
    }

    private String listTasks() {
        List<Task> taskFilter = helperRepository.getTasks();
        taskFilter.sort(Comparator.comparing(o -> o.getName().toUpperCase()));
        List<Task> assigneeTaskOnly = taskFilter.stream().filter(task -> !task.getAssignee().equals("")).collect(Collectors.toList());
        return ListingHelpers.elementsToString(assigneeTaskOnly);
    }

}
