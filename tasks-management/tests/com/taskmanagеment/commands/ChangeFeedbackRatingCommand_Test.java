package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.ChangeBugSeverityCommand;
import com.taskmanagеment.commands.creation.ChangeFeedbackRatingCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ChangeFeedbackRatingCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ChangeFeedbackRatingCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ChangeFeedbackRatingCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeFeedbackRatingCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(arguments));
//    }
//
//    @Test
//    public void execute_should_addBugStep_when_passedValidInput() {
//
//        taskManagementRepository.createFeedback("bugtitleee", "bugdescription","10", FeedBackStatus.NEW);
//        command.executeCommand(List.of("1", "15"));
//
//        Assertions.assertTrue(() -> taskManagementRepository.getFeedBacks().get(0).getRating() == 15);
//    }
}
