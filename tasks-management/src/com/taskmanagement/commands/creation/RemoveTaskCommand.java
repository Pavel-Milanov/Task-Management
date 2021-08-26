package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;
import static com.taskmanagement.constants.CommandConstants.TASK_REMOVED_SUCCESSFULLY;


public class RemoveTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public RemoveTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        return removeTask(taskIndex);
    }

    private String removeTask(int taskIndex) {

        WorkingItem workingItem = taskManagementRepository.findElementById(taskManagementRepository.getWorkingItems(), taskIndex);

        taskManagementRepository.removeTask(workingItem);


        return String.format(TASK_REMOVED_SUCCESSFULLY, workingItem.getName());
    }
}
