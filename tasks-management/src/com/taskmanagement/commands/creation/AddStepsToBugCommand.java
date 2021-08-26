package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;


public class AddStepsToBugCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public AddStepsToBugCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        String memberName = parameters.get(1);

       List<String> steps = Arrays.asList(parameters.get(2));



        return addStepToBug(taskId,memberName,steps);
    }

    private String addStepToBug(int taskId,String memberName, List<String> steps ) {

        Bug bug = taskManagementRepository.findElementById(taskManagementRepository.getBugs(),taskId);

        if (!bug.getAssignee().equalsIgnoreCase(memberName)){
            throw new InvalidUserInputException(String.format(MEMBER_NOT_EXISTS,memberName));
        }

        bug.addStepToReproduce(steps);

        return STEP_ADD_TO_BUG;
    }
}
