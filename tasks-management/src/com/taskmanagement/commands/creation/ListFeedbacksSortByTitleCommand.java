package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
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
        feedBacks.sort(Comparator.comparing(o -> o.getName().toUpperCase()));
        return ListingHelpers.elementsToString(feedBacks);
    }


}
