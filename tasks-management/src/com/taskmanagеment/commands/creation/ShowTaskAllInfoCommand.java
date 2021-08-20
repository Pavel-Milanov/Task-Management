package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ShowTaskAllInfoCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowTaskAllInfoCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return showTaskAllInfo(id);
    }

    private String showTaskAllInfo(int id) {
        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), id);

        StringBuilder output = new StringBuilder();

        output.append(task.getAsString());
        output.append(System.lineSeparator());
        listingParameters(output, task.getComments().isEmpty(), "---COMMENTS---", ListingHelpers.elementsToString(task.getComments()));
        listingSteps(task, output);
        listingParameters(output, task.getActiveHistory().isEmpty(), "---HISTORY---", ListingHelpers.elementsToString(task.getActiveHistory()));

        return output.toString().trim();
    }

    private void listingSteps(Task task, StringBuilder output) {
        if (task.getType().equals(TaskType.BUG)) {
            Bug bug = (Bug) task;
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
