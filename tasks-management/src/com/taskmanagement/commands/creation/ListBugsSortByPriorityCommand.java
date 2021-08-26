package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsSortByPriorityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsSortByPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);



        return listBugsSortByPriority();
    }

    private String listBugsSortByPriority() {

        if (taskManagementRepository.getStories().isEmpty()){
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_BUGS);
        }

        List<Story> sortedStoryByPriority = taskManagementRepository.getStories()
                .stream().sorted(Comparator.comparing(Story::getPriority)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(sortedStoryByPriority);
    }

    //Ralitsa
}
