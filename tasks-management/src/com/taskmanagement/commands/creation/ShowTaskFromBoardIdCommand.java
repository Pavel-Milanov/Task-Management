package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_BOARD_INDEX;
import static com.taskmanagement.constants.ModelConstants.TASK_HEADER;


public class ShowTaskFromBoardIdCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ShowTaskFromBoardIdCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_BOARD_INDEX);

        return showTaskFromBoardId(id);
    }

    private String showTaskFromBoardId(int id) {
        Board board = helperRepository.findElementById(taskManagementRepository.getBoards(), id);
        StringBuilder output = new StringBuilder();
        if (board.getTasks().isEmpty()) {
            output.append(ModelConstants.NO_TASK);
        } else {
            output.append(TASK_HEADER).append(System.lineSeparator());
        }
        return output + ListingHelpers.elementsToString(board.getTasks());
    }
}

