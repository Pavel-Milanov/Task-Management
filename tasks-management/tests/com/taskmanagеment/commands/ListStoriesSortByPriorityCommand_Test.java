package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.ListBugsSortByPriorityCommand;
import com.taskmanagеment.commands.creation.ListFeedbacksSortByRatingCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.*;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ListStoriesSortByPriorityCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ListBugsSortByPriorityCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ListBugsSortByPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListBugsSortByPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
//    }
//
//
//    @Test
//    public void execute_should_registerUser_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createStory("bugtitleeeee","description",Priority.HIGH, Size.LARGE, StoryStatus.NOTDONE,"aaaaa");
//
//        command.executeCommand(List.of("high"));
//        Story story =taskManagementRepository.getStories().get(0);
//        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("high")));
//
//    }
//
//    @Test
//    public void execute_should_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createStory("bugtitleeeee","description",Priority.HIGH, Size.LARGE, StoryStatus.NOTDONE,"aaaaa");
//
//        command.executeCommand(List.of("high"));
//        Story story =taskManagementRepository.getStories().get(0);
//        String output = command.executeCommand(List.of("high"));
//        Assertions.assertEquals(output,"Bug      : id=2, title: 'bugtitleeeee', description: 'description', Status Active, Priority High, Severity Critical, Assignee aaaaa");
//    }
}
