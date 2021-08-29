package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsSortBySeverityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsSortBySeverityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return listBugs();
    }

    private String listBugs() {
        if (taskManagementRepository.getBugs().isEmpty()) {
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_BUGS);
        }

        List<Bug> sortedBugBySeverity = taskManagementRepository.getBugs()
                .stream().sorted(Comparator.comparing(Bug::getSeverity)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(sortedBugBySeverity);

    }

}
