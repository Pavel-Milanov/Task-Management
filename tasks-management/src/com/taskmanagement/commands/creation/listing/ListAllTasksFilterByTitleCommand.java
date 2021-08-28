package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksFilterByTitleCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ListAllTasksFilterByTitleCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        return listAllTaskFilterByTitle(title);
    }

    private String listAllTaskFilterByTitle(String title) {

        List<WorkingItem> filteredTasks = helperRepository.getWorkingItems().stream()
                .filter(task -> task.getName().equalsIgnoreCase(title)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredTasks);
    }

    //Ralitsa
}
