package com.taskmanagеment.commands;


import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.ChangeStoryPriorityCommand;
import com.taskmanagеment.commands.creation.ChangeStoryStatusCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Size;
import com.taskmanagеment.models.enums.StoryStatus;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ChangeStoryStatusCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ChangeStoryStatusCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ChangeStoryStatusCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeStoryStatusCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
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
//        taskManagementRepository.createStory("bugtitleee", "bugdescription", Priority.HIGH, Size.LARGE, StoryStatus.NOTDONE, "zzzzzz");
//        command.executeCommand(List.of("1", "done"));
//
//        Assertions.assertTrue(() -> taskManagementRepository.getStories().get(0).getStoryStatus().equals(StoryStatus.DONE));
//    }
}
