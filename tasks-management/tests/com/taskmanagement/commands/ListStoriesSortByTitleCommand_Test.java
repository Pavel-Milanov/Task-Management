package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.ListBugsSortByTitleCommand;
import com.taskmanagement.commands.creation.ListStoriesSortByTitleCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ListStoriesSortByTitleCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListStoriesSortByTitleCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListBugsSortByTitleCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_registerUser_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createStory("bugtitleeeee","description", Priority.HIGH, Size.LARGE, StoryStatus.NOTDONE,"aaaaa");

        command.executeCommand(List.of());
        Story story =taskManagementRepository.getStories().get(0);
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of()));

    }

    @Test
    public void execute_should_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createStory("bugtitleeeee","description", Priority.HIGH, Size.LARGE, StoryStatus.NOTDONE,"aaaaa");

        command.executeCommand(List.of());
        Story story =taskManagementRepository.getStories().get(0);
        String output = command.executeCommand(List.of());
        Assertions.assertEquals("Story    : id=2, name: 'bugtitleeeee', description: 'description', Status Not Done, Size Large, Priority: High, Assignee: aaaaa",output);
    }
}
