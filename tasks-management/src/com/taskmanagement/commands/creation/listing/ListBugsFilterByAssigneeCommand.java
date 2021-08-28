package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListBugsFilterByAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsFilterByAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String nameAssignee = parameters.get(0);


        return listBugsFilterByAssignee(nameAssignee);
    }

    private String listBugsFilterByAssignee(String nameAssignee) {

        if (taskManagementRepository.getBugs().isEmpty()) {
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_BUGS);
        }
        List<Bug> filteredBugs = taskManagementRepository.getBugs()
                .stream().filter(bug -> bug.getAssignee().equalsIgnoreCase(nameAssignee)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredBugs);
    }

    //Ralitsa
}
