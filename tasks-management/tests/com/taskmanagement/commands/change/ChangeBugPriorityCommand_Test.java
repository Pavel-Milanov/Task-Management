package com.taskmanagement.commands.change;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.commands.creation.change.ChangeAssigneeCommand;
import com.taskmanagement.commands.creation.change.ChangeBugPriorityCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ChangeBugPriorityCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeBugPriorityCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ChangeBugPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ChangeBugPriorityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_throwException_when_passInvalidPriority() {

        // Arrange
        List<String> parameters = List.of(String.valueOf(1), "Lowest");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }

    @Test
    public void execute_should_throwException_when_passInvalidId() {
        // Arrange
        int bugId = 1;
        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> helperRepository.findElementById(taskManagementRepository.getBugs(), bugId));
    }

    @Test
    public void execute_should_changeBugPriority_when_argumentIsValid() {

        //Arrange
        taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");
        List<String> parameters = List.of(String.valueOf(1), "medium");
        // Act
        command.executeCommand(parameters);

        //Assert
        Assertions.assertTrue(() -> taskManagementRepository.getBugs().get(0).getPriority().equals(Priority.MEDIUM));

    }
}
