package com.taskmanagement.commands.creation.shown;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.ModelConstants.BUG_STEPS_HEADER;
import static com.taskmanagement.constants.ModelConstants.NO_BUG_STEPS_HEADER;


public class ShowBugStepsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ShowBugStepsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return showBugSteps(bugId);
    }

    private String showBugSteps(int bugId) {
        Bug bug = helperRepository.findElementById(taskManagementRepository.getBugs(), bugId);
        StringBuilder output = new StringBuilder();
        if (bug.getStepsToReproduce().isEmpty()) {
            output.append(NO_BUG_STEPS_HEADER);
        } else {
            output.append(BUG_STEPS_HEADER).append(System.lineSeparator());
        }

        return output + ListingHelpers.stepAsString(bug.getStepsToReproduce());
    }
}
