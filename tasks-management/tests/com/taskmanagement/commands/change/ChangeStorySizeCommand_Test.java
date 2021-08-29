package com.taskmanagement.commands.change;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.commands.creation.change.ChangeStorySizeCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.enums.FeedBackStatus;
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

public class ChangeStorySizeCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeStorySizeCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ChangeStorySizeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeStorySizeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_throwException_when_passInvalidStorySize() {

        // Arrange
        List<String> parameters = List.of(String.valueOf(1), "largest");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }

    @Test
    public void execute_should_throwException_when_passInvalidId() {
        // Arrange
        int storyId = 1;
        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> helperRepository.findElementById(taskManagementRepository.getBugs(), storyId));
    }

    @Test
    public void execute_should_changeStorySize_when_argumentIsValid() {

        //Arrange
        taskManagementRepository.createStory("The program freezes is open","Work on first problem", Priority.HIGH, Size.LARGE, StoryStatus.INPROGRESS,"Peter");
        List<String> parameters = List.of(String.valueOf(1), "medium");
        // Act
        command.executeCommand(parameters);

        //Assert
        Assertions.assertTrue(() -> taskManagementRepository.getStories().get(0).getSize().equals(Size.MEDIUM));

    }
}


