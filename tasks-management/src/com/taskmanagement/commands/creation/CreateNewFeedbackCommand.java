package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_RATING;
import static com.taskmanagement.utils.ParsingHelpers.tryParseEnum;
import static com.taskmanagement.utils.ParsingHelpers.tryParseInt;

public class CreateNewFeedbackCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;


    public CreateNewFeedbackCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {

        Board board = helperRepository.findBoard(parameters.get(0));
        String title = parameters.get(1);
        String description = parameters.get(2);
        int rating = tryParseInt(parameters.get(3), INVALID_RATING);
        FeedBackStatus feedBackStatus = tryParseEnum(parameters.get(4).toUpperCase(), FeedBackStatus.class);

        return createNewFeedBack(board, title, description, rating, feedBackStatus);
    }

    private String createNewFeedBack(Board board, String title, String description, int rating, FeedBackStatus feedBackStatus) {

        FeedBack feedBack = taskManagementRepository.createFeedback(title, description, rating, feedBackStatus);

        helperRepository.getBoard(board).addTask(feedBack);

        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, title);
    }

    //Ralitsa
}
