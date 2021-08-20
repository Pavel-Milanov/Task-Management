package com.taskmanagеment.core;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.CommandFactory;
import com.taskmanagеment.core.contacts.TaskManagementRepository;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        return null;
    }
}
