package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddMemberToTeamCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class AddMemberToTeamCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddMemberToTeamCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {AddMemberToTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, AddMemberToTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_addMember_when_passedValidInput() {
        taskManagementRepository.createMember("user1");
        Team team = taskManagementRepository.createTeam("team1");
        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("user1", "team1"))),
                () -> Assertions.assertFalse(taskManagementRepository.getMembers().isEmpty()),
                () -> Assertions.assertFalse(taskManagementRepository.getTeams().isEmpty()),
                () -> Assertions.assertEquals("user1", taskManagementRepository.getTeam("team1").getMembers().get(0).getName())
        );
    }

}
