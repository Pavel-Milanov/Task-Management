package com.taskmanagement.commands.creation.change;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
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

public class ChangeStoryPriorityCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeStoryPriorityCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ChangeStoryPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeStoryPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_changeStoryPriority_when_passedValidInput() {

        taskManagementRepository.createStory("bugtitleee", "bugdescription", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, "zzzzzz");
        command.executeCommand(List.of("1", "low"));

        Assertions.assertTrue(() -> taskManagementRepository.getStories().get(0).getPriority().equals(Priority.LOW));
    }
}
