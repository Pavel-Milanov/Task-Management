package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;



public class RemoveBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementRepository taskManagementRepository;
    public RemoveBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }



    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);


        return removeBoard(boardName);
    }

    private String removeBoard(String boardName) {

        if (!taskManagementRepository.boardExist(boardName)){
            throw new InvalidUserInputException(String.format(BOARD_NOT_EXISTS,boardName));
        }

        taskManagementRepository.removeBoard(boardName);

        return String.format(BOARD_REMOVED_SUCCESSFULLY,boardName);
    }
}
