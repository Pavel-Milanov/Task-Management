package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
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

        String boardName = parameters.get(0);


        return removeBoard(boardName);
    }

    private String removeBoard(String boardName) {

        if (!helperRepository.boardExist(boardName)) {
            throw new InvalidUserInputException(String.format(BOARD_NOT_EXISTS, boardName));
        }

        taskManagementRepository.removeBoard(boardName);

        return String.format(BOARD_REMOVED_SUCCESSFULLY, boardName);
    }
}
