package com.taskmanagement.commands.creation.addition;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.BOARD_ATTACHED;
import static com.taskmanagement.constants.CommandConstants.TEAM_NOT_EXISTS;


public class AddNewBoardInTeamCommand implements Command {

    //TODO проверка дали този теам съдържа борд със същото име !!!
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public AddNewBoardInTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Board board = helperRepository.findElementById(taskManagementRepository.getBoards(), ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_BOARD_INDEX));
        String teamName = parameters.get(1);
        return addBoardToTeam(board, teamName);
    }

    private String addBoardToTeam(Board board, String teamName) {
        if (!helperRepository.teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(TEAM_NOT_EXISTS, teamName));
        }

        Team team = helperRepository.findTeamByName(teamName);

        if (team.getBoards().contains(board)) {
            throw new InvalidUserInputException(String.format(BOARD_ATTACHED, board.getName()));
        }

        helperRepository.addBoardToTeam(board, team);

        return String.format(CommandConstants.BOARD_ADDED_TO_TEAM_SUCCESSFULLY, board.getName(), teamName);
    }
}
