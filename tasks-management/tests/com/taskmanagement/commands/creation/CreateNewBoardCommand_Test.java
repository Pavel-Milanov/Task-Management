package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.creation.CreateNewBoardCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class CreateNewBoardCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewBoardCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateNewBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateNewBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_notThrowException_when_addBoard() {

        taskManagementRepository.createBoard("title");


        Assertions.assertAll(
                () -> Assertions.assertEquals("title", taskManagementRepository.getBoards().get(0).getName()),
                () -> Assertions.assertEquals(1, taskManagementRepository.getBoards().size())
        );
    }

    @Test
    public void execute_should_addNewBoard_when_passedValidInput() {

        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("name1"))),
                () -> Assertions.assertFalse(taskManagementRepository.getBoards().isEmpty())
        );
    }
}
