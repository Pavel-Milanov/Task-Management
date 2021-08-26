package com.taskmanagement.core.contacts;

import com.taskmanagement.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);
}
