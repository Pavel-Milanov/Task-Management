package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CoreConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsMembersCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;


    public ShowAllTeamsMembersCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String type = parameters.get(0).toUpperCase();
        return showAll(type);
    }

    private String showAll(String type) {
        switch (type) {
            case "MEMBER":
                return ListingHelpers.elementsToString(taskManagementRepository.getMembers());
            case "TEAM":
                return ListingHelpers.elementsToString(taskManagementRepository.getTeams());
        }
        throw new InvalidUserInputException(String.format(CoreConstants.INVALID_COMMAND, type));
    }
}
