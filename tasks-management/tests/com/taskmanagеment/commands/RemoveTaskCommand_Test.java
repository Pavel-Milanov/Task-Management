package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.RemoveTaskCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.TestData;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveTaskCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new RemoveTaskCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {RemoveTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_removeTask_when_passedValidInput() {

        Member member = taskManagementRepository.createMember("member1");
        Board board = taskManagementRepository.createBoard("titlee");
        Bug bug = taskManagementRepository.createBug("bugtitleee","bugdescription", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE,"member1");
        board.addTask(bug);

        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("2", "3", "user1"))),
                () -> Assertions.assertTrue(taskManagementRepository.getBoard(board).getTasks().isEmpty())
        );

    }
}
