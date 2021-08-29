package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListFeedbacksSortByTitleCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ListFeedbacksSortByTitleCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<FeedBack> feedBacks = taskManagementRepository.getFeedBacks();
        if (feedBacks.isEmpty()) {
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_BUGS);
        }
        feedBacks.sort(Comparator.comparing(o -> o.getName().toUpperCase()));
        return ListingHelpers.elementsToString(feedBacks);
    }


}
