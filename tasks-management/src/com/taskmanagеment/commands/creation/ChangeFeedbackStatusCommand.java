package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeFeedbackStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int feedbackId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        FeedBackStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), FeedBackStatus.class );
        return changeStatus(feedbackId,status);
    }

    private String changeStatus(int feedbackId, FeedBackStatus status) {
        FeedBack feedBack = taskManagementRepository.findElementById(taskManagementRepository.getFeedBacks(),feedbackId);
        feedBack.changeFeedbackStatus(status);
        return String.format(CommandConstants.STATUS_CHANGED_SUCCESSFULLY,feedBack.getName());
    }

    //Pavel
}
