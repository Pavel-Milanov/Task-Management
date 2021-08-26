package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_INDEX;

public class ShowActivityTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowActivityTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INDEX);
        return showActivity(id);
    }

    private String showActivity(int id) {

        Team team = taskManagementRepository.findElementById(taskManagementRepository.getTeams(), id);
        return ListingHelpers.elementsToString(team.getActiveHistory());
    }

}
