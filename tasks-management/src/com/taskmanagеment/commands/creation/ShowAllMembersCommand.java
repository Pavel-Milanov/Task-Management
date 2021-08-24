package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ShowAllMembersCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;


    public ShowAllMembersCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return ListingHelpers.elementsToString(taskManagementRepository.getMembers());

    }
}
