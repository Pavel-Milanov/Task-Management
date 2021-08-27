package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.RemoveBoardCommand;
import com.taskmanagement.commands.creation.RemoveFeedbackCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveFeedbackCommand_Test {

//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new RemoveFeedbackCommand(taskManagementRepository);
//    }
//
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
//    }
//
//    @Test
//    public void execute_should_removeBoard_when_passedValidInput() {
//        FeedBack feedBack = taskManagementRepository.createFeedback("board1","desssssssssccccripton",15, FeedBackStatus.NEW);
//
//        command.executeCommand(List.of("1"));
//        Assertions.assertEquals(0, taskManagementRepository.getFeedBacks().size());
//
//    }
//
//    @Test
//    public void execute_should_throwException_when_listIsEmpty() {
//
//        Assertions.assertThrows(InvalidUserInputException.class,()-> command.executeCommand(List.of("1")));
//    }
}
