package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListBugsSortBySeverityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsSortBySeverityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(0),Severity.class );
        return listBugs(severity);
    }

    private String listBugs(Severity severity) {
        List<Bug> bugsFiltered;
        bugsFiltered = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getSeverity().equals(severity)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(bugsFiltered);

    }

}
