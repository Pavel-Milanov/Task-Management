package com.taskmanagеment.commands.contracts;

import java.util.List;

public interface Command {

    String executeCommand(List<String> parameters);
}
