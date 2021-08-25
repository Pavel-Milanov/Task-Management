package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

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
