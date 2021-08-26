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

public class ListBugsFilterByStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListBugsFilterByStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(0), BugStatus.class);
        return listBugs(status);
    }

    private String listBugs(BugStatus status) {
        List<Bug> bugsFiltered;
        bugsFiltered = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getBugStatus().equals(status)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(bugsFiltered);

    }

}
