package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;

import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;

public class ShowTaskAllInfoCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowTaskAllInfoCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        return showTaskAllInfo(id);
    }

    private String showTaskAllInfo(int id) {
        WorkingItem workingItem = taskManagementRepository.findElementById(taskManagementRepository.getWorkingItems(), id);

        StringBuilder output = new StringBuilder();

        output.append(workingItem.getAsString());
        output.append(System.lineSeparator());
        listingParameters(output, workingItem.getComments().isEmpty(), "---COMMENTS---", ListingHelpers.elementsToString(workingItem.getComments()));
        listingSteps(workingItem, output);
        listingParameters(output, workingItem.getActivityHistory().isEmpty(), "---HISTORY---", ListingHelpers.elementsToString(workingItem.getActivityHistory()));

        return output.toString().trim();
    }

    private void listingSteps(WorkingItem workingItem, StringBuilder output) {
        if (workingItem.getType().equals(TaskType.BUG)) {
            Bug bug = (Bug) workingItem;
            if (!bug.getStepsToReproduce().isEmpty()) {
                output.append(ListingHelpers.stepAsString(bug.getStepsToReproduce())).append(System.lineSeparator());
            }
        }
    }

    private void listingParameters(StringBuilder output, boolean empty, String s, String description) {
        if (!empty) {
            output.append(s).append(System.lineSeparator());
            output.append(description).append(System.lineSeparator());
        }
    }
}
