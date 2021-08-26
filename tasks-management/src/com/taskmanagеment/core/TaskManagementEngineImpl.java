package com.taskmanagеment.core;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.CommandFactoryImpl;
import com.taskmanagеment.core.contacts.CommandFactory;
import com.taskmanagеment.core.contacts.TaskManagementEngine;
import com.taskmanagеment.core.contacts.TaskManagementRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementEngineImpl implements TaskManagementEngine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String REPORT_SEPARATOR = "####################";

    private final CommandFactory commandFactory;
    private final TaskManagementRepository taskManagementRepository;

    public TaskManagementEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true){

            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()){
                    print(EMPTY_COMMAND_ERROR);
                    continue;
                }

                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)){
                    break;
                }
                processCommand(inputLine);
            }catch (Exception ex){
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()){
                    print(ex.getMessage());
                }else {
                    print(ex.toString());
                }
            }
        }

    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        List<String> parameters = extractCommandParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName,taskManagementRepository);
        String executionResult = command.executeCommand(parameters);
        print(executionResult);




    }
    /**
     * Receives a full line and extracts the parameters that are needed for the command to execute.
     * For example, if the input line is "RegisterMember Mark",
     * the method will return a list of ["Mark"].
     *
     * @param inputLine A complete input line
     * @return A list of the parameters needed to execute the command
     */
    private List<String> extractCommandParameters(String inputLine) {
        String [] commandParts = inputLine.split("~");

        return new ArrayList<>(Arrays.asList(commandParts).subList(1, commandParts.length));
    }

    /**
     * Receives a full input line and extracts the command that should be executed
     * For examle, if the input line is "RegisterMember Mark", the method will return "RegisterMember"
     *
     * @param inputLine A complete input line
     * @return The name of command to be executed
     */
    private String extractCommandName(String inputLine) {

        return inputLine.split("~")[0];
    }

    private void print(String result) {

        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}

