package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsFilterByStatusAndAssigneeCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsFilterByStatusAndAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(0),BugStatus.class );
        String assignee = parameters.get(1);
        return listBugs(status,assignee);
    }

    private String listBugs(BugStatus status, String assignee) {
        List<Bug> bugsFiltered ;
        bugsFiltered = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getBugStatus().equals(status) && bug.getAssignee().equals(assignee)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(bugsFiltered);

    }
}
