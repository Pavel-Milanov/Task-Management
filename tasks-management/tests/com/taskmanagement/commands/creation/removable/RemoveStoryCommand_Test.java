package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveStoryCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new RemoveStoryCommand(taskManagementRepository);
    }


    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {RemoveStoryCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveStoryCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_removeStory_when_passedValidInput() {
       Story story =   taskManagementRepository.createStory("The program freezes is open","Work on first problem"
               ,Priority.HIGH, Size.LARGE, StoryStatus.INPROGRESS,"Peter");

        Board board = taskManagementRepository.createBoard("Tasks");
        board.addWorkingItem(story);
        command.executeCommand(List.of(String.valueOf(story.getId())));

        Assertions.assertEquals(0, taskManagementRepository.getStories().size());

    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of("1")));
    }
}

