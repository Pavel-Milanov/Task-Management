package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class AddNewBoardInTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public AddNewBoardInTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        return addBoardToTeam(boardName, teamName);
    }

    private String addBoardToTeam(String boardTittle, String teamTittle) {
        if (!taskManagementRepository.teamExist(teamTittle)) {
            throw new InvalidUserInputException(String.format(CommandConstants.TEAM_NOT_EXISTS, teamTittle));
        }

        Team team = taskManagementRepository.findTeamByName(teamTittle);
        Board board = taskManagementRepository.findBoard(boardTittle);

        if (validateBoardNotAttached(board)) {
            throw new InvalidUserInputException(String.format(CommandConstants.BOARD_ATTACHED, board.getName()));
        }

        taskManagementRepository.addBoardToTeam(board, team);

        return String.format(CommandConstants.BOARD_ADDED_TO_TEAM_SUCCESSFULLY, boardTittle, teamTittle);
    }

    private boolean validateBoardNotAttached(Board board) {
        return taskManagementRepository.getTeams().stream()
                .anyMatch(team -> team.getBoards().contains(board));
    }
}
