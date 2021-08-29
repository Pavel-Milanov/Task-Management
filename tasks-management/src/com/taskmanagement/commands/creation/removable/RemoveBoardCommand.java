package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.BOARD_NOT_EXISTS;
import static com.taskmanagement.constants.CommandConstants.BOARD_REMOVED_SUCCESSFULLY;

public class RemoveBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public RemoveBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int boardId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_BOARD_INDEX);

        return removeBoard(boardId);
    }

    private String removeBoard(int boardId) {
        Board board = helperRepository.findElementById(taskManagementRepository.getBoards(), boardId);

        if (!helperRepository.isBoardExist(board.getName())) {
            throw new InvalidUserInputException(String.format(BOARD_NOT_EXISTS, board.getName()));
        }
        taskManagementRepository.removeBoard(board);
        return String.format(BOARD_REMOVED_SUCCESSFULLY, board.getName());
    }
}