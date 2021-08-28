package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.listing.ListFeedbacksSortByRatingCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.FeedBack;
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

public class ListFeedbacksSortByRatingCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListFeedbacksSortByRatingCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListFeedbacksSortByRatingCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_registerUser_when_passedValidInput1() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createFeedback("bugtitleeeee", "description", 15, FeedBackStatus.NEW);

        command.executeCommand(List.of());
        FeedBack feedBack = taskManagementRepository.getFeedBacks().get(0);
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of()));

    }

    @Test
    public void execute_should_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createBug("bugtitleeeee", "description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE, "aaaaa");
        taskManagementRepository.createFeedback("bugtitleeeee", "description", 15, FeedBackStatus.NEW);

        command.executeCommand(List.of());
        FeedBack feedBack = taskManagementRepository.getFeedBacks().get(0);
        String output = command.executeCommand(List.of());
        Assertions.assertEquals("Feedback : id=3, name: 'bugtitleeeee', description: 'description' : , Status New, Rating 15", output);
    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of()));
    }
}
