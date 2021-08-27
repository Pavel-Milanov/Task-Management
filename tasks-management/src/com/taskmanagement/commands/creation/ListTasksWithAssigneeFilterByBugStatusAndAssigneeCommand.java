package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        BugStatus bugStatus = ParsingHelpers.tryParseEnum(parameters.get(0).toUpperCase(), BugStatus.class);
        String nameAssignee = parameters.get(1);


        return filterByBugStatusAndAssignee(bugStatus, nameAssignee);
    }

    private String filterByBugStatusAndAssignee(BugStatus bugStatus, String nameAssignee) {
        List<Bug> filteredBugs = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getAssignee().equals(nameAssignee) && bug.getBugStatus().equals(bugStatus))
                .collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredBugs);
    }
    //Ralitsa
}
