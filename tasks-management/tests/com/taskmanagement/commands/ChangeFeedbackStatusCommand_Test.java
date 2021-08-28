package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.commands.creation.change.ChangeFeedbackStatusCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
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

public class ChangeFeedbackStatusCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeFeedbackStatusCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ChangeFeedbackStatusCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeFeedbackStatusCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_throwException_when_passInvalidFeedbackStatus() {

        // Arrange
        List<String> parameters = List.of(String.valueOf(1), "newest");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }

    @Test
    public void execute_should_throwException_when_passInvalidId() {
        // Arrange
        int feedbackId = 1;
        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> helperRepository.findElementById(taskManagementRepository.getBugs(), feedbackId));
    }

    @Test
    public void execute_should_changePriority_when_argumentIsValid() {

        //Arrange
        taskManagementRepository.createFeedback("The program freezes is working on it", "All bugs are fixed!",5, FeedBackStatus.NEW);
        List<String> parameters = List.of(String.valueOf(1), "done");
        // Act
        command.executeCommand(parameters);

        //Assert
        Assertions.assertTrue(() -> taskManagementRepository.getFeedBacks().get(0).getRating()== 5);

    }
}

