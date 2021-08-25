package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.ListBugsFilterByAssigneeCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ListBugsFilterByAssigneeCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ListBugsFilterByAssigneeCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ListBugsFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListBugsFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(arguments));
//    }
//
//
//    @Test
//    public void execute_should_registerUser_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa");
//
//        command.executeCommand(List.of("aaaaa"));
//        Bug bug =taskManagementRepository.getBugs().get(0);
//        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("aaaaa")));
//
//    }
//
//    @Test
//    public void execute_should_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa")
//
//        command.executeCommand(List.of("aaaaa"));
//        Bug bug = taskManagementRepository.getBugs().get(0);
//        String output = command.executeCommand(List.of("aaaaa"));
//        Assertions.assertEquals(output,"Bug      : id=2, title: 'bugtitleeeee', description: 'description', Status Active, Priority High, Severity Critical, Assignee aaaaa");
//    }
}
