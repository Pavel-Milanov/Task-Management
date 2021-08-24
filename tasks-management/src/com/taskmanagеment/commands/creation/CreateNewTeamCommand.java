package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;


public class CreateNewTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);


        return createTeam(teamName);
    }

    private String createTeam(String teamName) {

        if (taskManagementRepository.teamExist(teamName)){
            throw new InvalidUserInputException(String.format(TEAM_ALREADY_EXISTS,teamName));
        }

        taskManagementRepository.createTeam(teamName);

        return String.format(TEAM_CREATED,teamName);
    }
}

