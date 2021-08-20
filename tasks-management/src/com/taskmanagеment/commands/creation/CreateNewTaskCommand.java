package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;

import java.util.List;

public class CreateNewTaskCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        return null;
    }
}
