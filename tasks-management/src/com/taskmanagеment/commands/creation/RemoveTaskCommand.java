package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;


public class RemoveTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public RemoveTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        return removeTask(taskIndex);
    }

    private String removeTask(int taskIndex) {

        WorkingItem workingItem = taskManagementRepository.findElementById(taskManagementRepository.getWorkingItems(), taskIndex);

        taskManagementRepository.removeTask(workingItem);


        return String.format(TASK_REMOVED_SUCCESSFULLY, workingItem.getName());
    }
}
