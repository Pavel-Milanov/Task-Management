package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.ShowTaskFromBoardIdCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ShowTaskFromBoardIdCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowTaskFromBoardIdCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ShowTaskFromBoardIdCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ShowTaskFromBoardIdCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_registerUser_when_passedValidInput() {

        Board board = taskManagementRepository.createBoard("board111");
        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("1"))),
                () -> Assertions.assertFalse(taskManagementRepository.getBoards().isEmpty()),
                () -> Assertions.assertTrue(taskManagementRepository.getBoard(board).getTasks().isEmpty())
        );
    }
}
