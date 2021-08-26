package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.RemoveTaskCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.utils.TestUtilities;
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
        taskManagementRepository.createFeedback("bugtitleee", "bugdescription", 10, FeedBackStatus.NEW);

        command.executeCommand(List.of("1"));

        Assertions.assertEquals(0,taskManagementRepository.getFeedBacks().size());
    }
}
