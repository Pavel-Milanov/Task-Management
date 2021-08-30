package com.taskmanagement.commands.creation.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class CreateNewMemberCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateMemberCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_createNewMember_when_passValidInput() {

        // Arrange
        List<String> parameters = List.of("Peter");

        //Act,Assert

        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(parameters)),
                () -> Assertions.assertFalse(taskManagementRepository.getMembers().isEmpty())

        );

    }

    @Test
    public void execute_should_throwException_when_memberExists() {

        // Arrange
        String name = "Peter";

        //Act
        taskManagementRepository.createMember(name);

        //Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of("Peter")));
    }
}
