package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.creation.CreateNewFeedbackCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.models.tasks.FeedBackImpl;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class CreateNewFeedbackCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewFeedbackCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateNewFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateNewFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_addTaskToBoard_when_passedValidInput() {
        FeedBack feedBack = new FeedBackImpl(1, "feedbacktitile", "feedbackdescription", 10, FeedBackStatus.NEW);
        taskManagementRepository.createBoard("board1");
        command.executeCommand(List.of("board1", "feedbacktitile", "feedbackdescription", "10", "NEW"));


        Assertions.assertEquals(taskManagementRepository.getFeedBacks().get(0).getName(), feedBack.getName());
    }
}
