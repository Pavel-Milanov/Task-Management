package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_BOARD_INDEX;
import static com.taskmanagement.constants.ModelConstants.TASK_HEADER;


public class ShowTaskFromBoardIdCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowTaskFromBoardIdCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_BOARD_INDEX);

        return showTaskFromBoardId(id);
    }

    private String showTaskFromBoardId(int id) {
        StringBuilder sb = new StringBuilder();
        List<WorkingItem> workingItems = new ArrayList<>();


        int count = 1;
        sb.append(TASK_HEADER);
        sb.append(System.lineSeparator());

        List<Board> boards = taskManagementRepository.getBoards();
        for (Board board : boards) {
            if (board.getId() == id) {
                workingItems.addAll(board.getTasks());
            }
        }

        for (WorkingItem workingItem : workingItems) {
            sb.append(String.format("%d: ", count++));
            sb.append(System.lineSeparator());
            sb.append(workingItem.getAsString());
        }

        return sb.toString();
    }
}

