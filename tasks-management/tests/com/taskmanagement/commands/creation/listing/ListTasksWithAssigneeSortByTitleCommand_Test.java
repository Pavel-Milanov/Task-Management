package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeSortByTitleCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl taskManagementHelperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.taskManagementHelperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
        this.command = new ListTasksWithAssigneeSortByTitleCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListTasksWithAssigneeSortByTitleCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_sortTasksSortByTitle_when_passedValidInput() {

        //Arrange
        taskManagementRepository.createStory("The program freezes is open", "Work on first problem", Priority.HIGH, Size.LARGE, StoryStatus.INPROGRESS, "Peter");
        taskManagementRepository.createStory("The task is open", "Work on task", Priority.LOW, Size.LARGE, StoryStatus.INPROGRESS, "Peter");
        taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");
        taskManagementRepository.createBug("The input does not except parameters", "This needs to be fixed quickly!", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");

        //Act
        List<Task> sortedTasksByTitle = taskManagementHelperRepository.getTasks()
                .stream().sorted(Comparator.comparing(Task::getName)).collect(Collectors.toList());

        //Assert
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of()));


    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of()));
    }

}


