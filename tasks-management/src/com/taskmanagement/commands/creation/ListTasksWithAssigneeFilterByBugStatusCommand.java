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


public class ListTasksWithAssigneeFilterByBugStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeFilterByBugStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        BugStatus bugStatus = ParsingHelpers.tryParseEnum(parameters.get(0), BugStatus.class);
        return filterByBugStatusAndAssignee(bugStatus);
    }

    private String filterByBugStatusAndAssignee(BugStatus bugStatus) {
        List<Bug> filteredBugs = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getBugStatus().equals(bugStatus) && !bug.getAssignee().equals("")).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredBugs);
    }
}
