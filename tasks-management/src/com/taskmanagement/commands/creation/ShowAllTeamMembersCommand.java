package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowAllTeamMembersCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamTittle = parameters.get(0);
        return showTeamUsers(teamTittle);
    }

    private String showTeamUsers(String teamTittle) {
        Team team = taskManagementRepository.findTeamByName(teamTittle);

        return ListingHelpers.elementsToString(team.getMembers());
    }
}
