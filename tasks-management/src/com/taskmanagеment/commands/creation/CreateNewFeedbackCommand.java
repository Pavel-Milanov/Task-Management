package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;
import static com.taskmanagеment.utils.ParsingHelpers.*;

public class CreateNewFeedbackCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final TaskManagementRepository taskManagementRepository;


    public CreateNewFeedbackCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String executeCommand(List<String> parameters) {

        Board board = taskManagementRepository.findBoard(parameters.get(0));
        String title = parameters.get(1);
        String description = parameters.get(2);
        int rating = tryParseInt(parameters.get(3), INVALID_RATING);
        FeedBackStatus feedBackStatus = tryParseEnum(parameters.get(4).toUpperCase(),FeedBackStatus.class);

        return createNewFeedBack (board,title,description,rating,feedBackStatus);
    }

    private String createNewFeedBack(Board board, String title, String description, int rating, FeedBackStatus feedBackStatus) {

        FeedBack feedBack = taskManagementRepository.createFeedback(title,description,rating,feedBackStatus);

        taskManagementRepository.getBoard(board).addTask(feedBack);

        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, title);
    }

    //Ralitsa
}
