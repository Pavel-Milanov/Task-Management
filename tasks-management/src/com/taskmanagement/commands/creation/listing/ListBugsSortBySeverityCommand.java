package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

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
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(0), Severity.class);
        return listBugs(severity);
    }

    private String listBugs(Severity severity) {
        List<Bug> bugsFiltered;
        bugsFiltered = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getSeverity().equals(severity)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(bugsFiltered);

    }

}
