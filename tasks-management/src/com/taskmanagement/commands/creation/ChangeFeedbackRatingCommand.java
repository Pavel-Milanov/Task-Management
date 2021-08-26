package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;

public class ChangeFeedbackRatingCommand implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeFeedbackRatingCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedBackId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        int newRating = ParsingHelpers.tryParseInt(parameters.get(1), INVALID_INPUT_MESSAGE );
        return changeFeedbackRating(feedBackId,newRating);
    }

    private String changeFeedbackRating(int feedBackId,int newRating) {

        FeedBack feedBack = taskManagementRepository.findElementById(taskManagementRepository.getFeedBacks(),feedBackId);

        feedBack.changeFeedbackRating(newRating);

        return LABEL_CHANGED_SUCCESSFULLY;
    }

    //Ralitsa
}
