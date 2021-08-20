package com.taskmanagеment.core.contacts;

import com.taskmanagеment.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);
}
