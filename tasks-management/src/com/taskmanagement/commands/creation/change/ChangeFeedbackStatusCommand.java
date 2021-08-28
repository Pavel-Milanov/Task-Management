package com.taskmanagement.commands.creation.change;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeFeedbackStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int feedbackId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        FeedBackStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), FeedBackStatus.class);
        return changeStatus(feedbackId, status);
    }

    private String changeStatus(int feedbackId, FeedBackStatus status) {
        FeedBack feedBack = helperRepository.findElementById(taskManagementRepository.getFeedBacks(), feedbackId);
        feedBack.changeFeedbackStatus(status);
        return String.format(CommandConstants.STATUS_CHANGED_SUCCESSFULLY, feedBack.getName());
    }

    //Pavel
}
