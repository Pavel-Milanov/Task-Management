package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesSortByPriorityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ListStoriesSortByPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        return sortedStoriesByPriority();
    }

    private String sortedStoriesByPriority() {

        if (taskManagementRepository.getStories().isEmpty()){
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_STORIES);
        }

        List<Story> sortedStoriesByPriorityList = taskManagementRepository.getStories()
                .stream().sorted(Comparator.comparing(Story::getPriority)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(sortedStoriesByPriorityList);
    }

    //Ralitsa
}
