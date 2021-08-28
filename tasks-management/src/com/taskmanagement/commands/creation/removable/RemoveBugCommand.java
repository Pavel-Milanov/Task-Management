package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveBugCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;
    private final TaskManagementRepository taskManagementRepository;

    public RemoveBugCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return removeBug(bugId);
    }

    private String removeBug(int bugId) {
        Bug bug = helperRepository.findElementById(taskManagementRepository.getBugs(), bugId);
        Board board = taskManagementRepository.getBoards().stream()
                .filter(board1 -> board1.getTasks().contains(bug)).findAny().orElseThrow();
        board.removeTask(bug);
        taskManagementRepository.removeBug(bug);
        return String.format(CommandConstants.TASK_REMOVED_SUCCESSFULLY, bug.getName());
    }
}
