package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveMemberCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new RemoveMemberCommand(taskManagementRepository);
    }


    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {RemoveMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_removeMember_when_passedValidInput() {
        Member member = taskManagementRepository.createMember("Peter");

        command.executeCommand(List.of(String.valueOf(member.getId())));
        Assertions.assertEquals(0, taskManagementRepository.getMembers().size());

    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of("1")));
    }
}
