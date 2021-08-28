package com.taskmanagement.commands.creation.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.TEAM_ALREADY_EXISTS;
import static com.taskmanagement.constants.CommandConstants.TEAM_CREATED;


public class CreateNewTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public CreateNewTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);


        return createTeam(teamName);
    }

    private String createTeam(String teamName) {

        if (helperRepository.teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(TEAM_ALREADY_EXISTS, teamName));
        }

        taskManagementRepository.createTeam(teamName);

        return String.format(TEAM_CREATED, teamName);
    }
}

