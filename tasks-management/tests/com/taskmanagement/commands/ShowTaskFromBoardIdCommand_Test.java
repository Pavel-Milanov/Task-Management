package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.shown.ShowTaskFromBoardIdCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ShowTaskFromBoardIdCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private TaskManagementHelperRepositoryImpl helperRepository;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowTaskFromBoardIdCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ShowTaskFromBoardIdCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ShowTaskFromBoardIdCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_registerUser_when_passedValidInput() {

        Board board = taskManagementRepository.createBoard("board111");
        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("1"))),
                () -> Assertions.assertFalse(taskManagementRepository.getBoards().isEmpty()),
                () -> Assertions.assertTrue(board.getWorkingItems().isEmpty())
        );
    }
}
