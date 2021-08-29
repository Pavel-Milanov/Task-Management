package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

public class ListStoryByAssigneeCommand_Tests {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListStoryByAssigneeCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListStoryByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListStoryByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_filterStoryByAssignee_when_passedValidInput() {

        //Arrange
        taskManagementRepository.createStory("The program freezes is open","Work on first problem",Priority.HIGH, Size.LARGE, StoryStatus.INPROGRESS,"Peter");
        taskManagementRepository.createStory("The task is open","Work on task",Priority.LOW, Size.LARGE,StoryStatus.INPROGRESS,"Peter");

        //Act
        List<Story> listFilteredStoryByAssignee = taskManagementRepository.getStories().stream()
                .filter(story -> story.getAssignee().equals("Peter")).collect(Collectors.toList());

        //Assert
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("peter")));


    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(List.of()));
    }

}
