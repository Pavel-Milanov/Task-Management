package com.taskmanagement.commands.listing;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.commands.creation.listing.ListBugsSortBySeverityCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
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
import java.util.stream.Collectors;

public class ListBugsSortBySeverityCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListBugsSortBySeverityCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListBugsSortBySeverityCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListBugsSortBySeverityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_sortBugBySeverity_when_passedValidInput() {

        //Arrange
        taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");
        taskManagementRepository.createBug("The input does not except parameters", "This needs to be fixed quickly!", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Maria");

        //Act
        List<Bug> listFilteredBugsByStatus = taskManagementRepository.getBugs().stream()
                .filter(bug -> bug.getSeverity().equals(Severity.CRITICAL)).collect(Collectors.toList());

        //Assert
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("critical")));


    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(List.of()));
    }

}
