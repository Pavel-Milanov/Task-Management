package com.taskmanagement.commands.creation.shown;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ShowAllTeamBoardsCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        return showTeamBoards(teamName);
    }

    private String showTeamBoards(String teamName) {

        Team team = helperRepository.findTeamByName(teamName);

        return ListingHelpers.elementsToString(team.getBoards());
    }

//Ralitsa
}
