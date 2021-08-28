package com.taskmanagement.commands.creation.shown;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;

public class ShowTaskAllInfoCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ShowTaskAllInfoCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        return showTaskAllInfo(id);
    }

    private String showTaskAllInfo(int id) {
        WorkingItem workingItem = helperRepository.findElementById(helperRepository.getWorkingItems(), id);

        StringBuilder output = new StringBuilder();

        output.append(workingItem.getAsString());
        output.append(System.lineSeparator());
        listingParameters(output, workingItem.getComments().isEmpty(), "---COMMENTS---", ListingHelpers.elementsToString(workingItem.getComments()));
        listingParameters(output, workingItem.getActivityHistory().isEmpty(), "---HISTORY---", ListingHelpers.elementsToString(workingItem.getActivityHistory()));

        return output.toString().trim();
    }

    private void listingParameters(StringBuilder output, boolean empty, String s, String description) {
        if (!empty) {
            output.append(s).append(System.lineSeparator());
            output.append(description).append(System.lineSeparator());
        }
    }
}
