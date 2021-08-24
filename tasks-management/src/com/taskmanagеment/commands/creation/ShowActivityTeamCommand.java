package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.INVALID_INDEX;

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
