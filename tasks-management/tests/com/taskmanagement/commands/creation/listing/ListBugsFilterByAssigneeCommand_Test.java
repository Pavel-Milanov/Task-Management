package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
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

public class ListBugsFilterByAssigneeCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListBugsFilterByAssigneeCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListBugsFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListBugsFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_registerUser_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createBug("bugtitleeeee", "description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE, "aaaaa");

        command.executeCommand(List.of("aaaaa"));
        Bug bug = taskManagementRepository.getBugs().get(0);
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("aaaaa")));

    }

    @Test
    public void execute_should_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createBug("bugtitleeeee", "description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE, "aaaaa");

        command.executeCommand(List.of("aaaaa"));
        Bug bug = taskManagementRepository.getBugs().get(0);
        String output = command.executeCommand(List.of("aaaaa"));
        Assertions.assertEquals(output, "Bug      : id=2, name: 'bugtitleeeee', description: 'description', Bug Status Active, Severity Critical, Priority: Low, Assignee: aaaaa");
    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {
        taskManagementRepository.createMember("aaaaa");

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of("aaaaa")));
    }
}