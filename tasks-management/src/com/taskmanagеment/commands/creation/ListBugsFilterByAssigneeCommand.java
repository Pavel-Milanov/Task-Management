package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

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

        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
         String nameAssignee = parameters.get(0);


        return listBugsFilterByAssignee(nameAssignee);
    }

    private String listBugsFilterByAssignee(String nameAssignee) {

        if (taskManagementRepository.getBugs().isEmpty()){
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_BUGS);
        }
     List<Bug> filteredBugs = taskManagementRepository.getBugs()
             .stream().filter(bug -> bug.getAssignee().equalsIgnoreCase(nameAssignee)).collect(Collectors.toList());

     return ListingHelpers.elementsToString(filteredBugs);
    }

    //Ralitsa
}
