package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.ModelConstants.BUG_STEPS_HEADER;
import static com.taskmanagement.constants.ModelConstants.NO_BUG_STEPS_HEADER;


public class ShowBugStepsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementRepository taskManagementRepository;

    public ShowBugStepsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Bug> bugs = taskManagementRepository.getBugs();


        return showBugSteps(bugs);
    }


    private String showBugSteps(List<Bug> bugs) {

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Bug bug : bugs) {
            sb.append(bug.getAsString());
            sb.append(System.lineSeparator());
            if (bug.getStepsToReproduce().isEmpty()) {
                sb.append(NO_BUG_STEPS_HEADER);
                sb.append(System.lineSeparator());
            } else {
                for (String s : bug.getStepsToReproduce()) {
                    sb.append(BUG_STEPS_HEADER);
                    sb.append(String.format("%d", count++));
                    sb.append(s);
                }
            }


        }
        return sb.toString();
    }


}
