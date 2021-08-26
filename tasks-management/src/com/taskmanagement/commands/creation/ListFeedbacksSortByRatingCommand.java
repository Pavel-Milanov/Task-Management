package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListFeedbacksSortByRatingCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ListFeedbacksSortByRatingCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return sortFeedbackByRating();
    }

    private String sortFeedbackByRating() {

        if (taskManagementRepository.getFeedBacks().isEmpty()) {
            throw new InvalidUserInputException(CommandConstants.EMPTY_LIST_FEEDBACKS);
        }

        List<FeedBack> sortedFeedbacksByRating = taskManagementRepository.getFeedBacks()
                .stream().sorted(Comparator.comparing(FeedBack::getRating)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(sortedFeedbacksByRating);
    }
    //Ralitsa
}
