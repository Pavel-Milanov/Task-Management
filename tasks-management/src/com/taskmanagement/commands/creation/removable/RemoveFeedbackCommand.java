package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveFeedbackCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;
    private final TaskManagementRepository taskManagementRepository;

    public RemoveFeedbackCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }
    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedbackId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return removeFeedback(feedbackId);
    }

    private String removeFeedback(int feedbackId) {

        FeedBack feedback = helperRepository.findElementById(taskManagementRepository.getFeedBacks(),feedbackId);

        Board board = taskManagementRepository.getBoards()
                .stream().filter(board1 -> board1.getTasks().contains(feedback)).findAny().orElseThrow();

        board.removeTask(feedback);

        taskManagementRepository.removeFeedback(feedback);

        return String.format(CommandConstants.TASK_REMOVED_SUCCESSFULLY,feedback.getName());
    }
    //Ralitsa
}
