package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public RemoveTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);


        return removeTeam(teamName);
    }

    private String removeTeam(String teamName) {

        if (!helperRepository.teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(CommandConstants.TEAM_NOT_EXISTS, teamName));
        }

        taskManagementRepository.removeTeam(teamName);

        return String.format(CommandConstants.TEAM_REMOVED_SUCCESSFULLY, teamName);
    }
    //Ralitsa
}
