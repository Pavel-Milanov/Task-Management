package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveBoardCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new RemoveBoardCommand(taskManagementRepository);
    }


    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_removeBoard_when_passedValidInput() {
        Board board = taskManagementRepository.createBoard("board1");

        command.executeCommand(List.of("1"));
        Assertions.assertEquals(0, taskManagementRepository.getBoards().size());

    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of("1")));
    }
}
