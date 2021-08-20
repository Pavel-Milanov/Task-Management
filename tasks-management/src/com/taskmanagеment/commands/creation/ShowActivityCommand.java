package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ShowActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ShowActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeActivity = parameters.get(0).toUpperCase();
        int id = ParsingHelpers.tryParseInt(parameters.get(1), CommandConstants.INVALID_INDEX);
        return showActivity(typeActivity, id);
    }

    private String showActivity(String typeActivity, int id) {
        switch (typeActivity) {
            case "MEMBER":
                Member member = taskManagementRepository.findElementById(taskManagementRepository.getMembers(), id);
                return ListingHelpers.elementsToString(member.getActiveHistory());
            case "TEAM":
                Team team = taskManagementRepository.findElementById(taskManagementRepository.getTeams(), id);
                return ListingHelpers.elementsToString(team.getActiveHistory());
            case "BOARD":
                Board board = taskManagementRepository.findElementById(taskManagementRepository.getBoards(), id);
                return ListingHelpers.elementsToString(board.getActiveHistory());
            case "TASKS":
                Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), id);
                return ListingHelpers.elementsToString(task.getActiveHistory());
        }
        throw new InvalidUserInputException(CommandConstants.INVALID_INDEX);
    }
}
