package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksFilterByTitleCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListAllTasksFilterByTitleCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        return listAllTaskFilterByTitle(title);
    }

    private String listAllTaskFilterByTitle(String title) {

     //  if (taskManagementRepository.getWorkingItems().isEmpty()){

     //  }
        List<WorkingItem> filteredTasks = taskManagementRepository.getWorkingItems().stream()
                .filter(task -> task.getName().equalsIgnoreCase(title)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredTasks);
    }

    //Ralitsa
}
