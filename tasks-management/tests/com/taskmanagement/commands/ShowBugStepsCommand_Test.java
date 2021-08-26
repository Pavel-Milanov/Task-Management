package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddStepsToBugCommand;
import com.taskmanagement.commands.creation.ShowBugStepsCommand;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ShowBugStepsCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private Command command1;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.US);
    private LocalDateTime timestamp = LocalDateTime.now();

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowBugStepsCommand(taskManagementRepository);
        this.command1 = new AddStepsToBugCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ShowBugStepsCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1,ShowBugStepsCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_registerUser_when_passedValidInput1() {

        taskManagementRepository.createBoard("board111");

        Assertions.assertEquals("board111", taskManagementRepository.getBoards().get(0).getName());

    }

    @Test
    public void execute_should_registerUser_when_passedValidInput2() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa");

        command.executeCommand(List.of("1"));
        Bug bug =taskManagementRepository.getBugs().get(0);
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("1")));

    }

    @Test
    public void execute_should_when_passedValidInput() {
        taskManagementRepository.createMember("aaaaa");
        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa");
        command1.executeCommand(List.of("1. First step; 2 Second step"));
        command.executeCommand(List.of("1"));

        Bug bug = (Bug) taskManagementRepository.getTasks().get(0);
        String output = command.executeCommand(List.of("1"));
        Assertions.assertEquals(output,"1. First step;\n 2 Second step");
    }
}
