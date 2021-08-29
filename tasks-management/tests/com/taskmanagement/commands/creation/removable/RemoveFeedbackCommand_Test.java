package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.creation.CreateNewFeedbackCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveFeedbackCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private Command command1;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new RemoveFeedbackCommand(taskManagementRepository);
        this.command1 = new CreateNewFeedbackCommand(taskManagementRepository);
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
    public void execute_should_removeFeedback_when_passedValidInput() {

        taskManagementRepository.createBoard("board1");

        command1.executeCommand(List.of("1", "feedbacktitle", "descriptionnnn", "10", "new"));

        command.executeCommand(List.of("2"));
        Assertions.assertEquals(0, taskManagementRepository.getFeedBacks().size());

    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of("1")));
    }
}
