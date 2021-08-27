package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddNewBoardInTeamCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Board;

import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;

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

public class AddNewBoardInTeamCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddNewBoardInTeamCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {AddNewBoardInTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, AddNewBoardInTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_addNewBoard_when_argumentIsValid() {

        //Arrange, Act
        List<String> parameters = List.of("Tasks", "Team11");

        //Assert
        Assertions.assertEquals(AddNewBoardInTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size());

    }

    @Test
    public void execute_should_addNewBoard_when_passedValidInput() {

        Board board = taskManagementRepository.createBoard("Trello");

        Team team = taskManagementRepository.createTeam("Team11");

        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("Trello", "Team11"))),
                () -> Assertions.assertFalse(taskManagementRepository.boardExist("Trella")),
                () -> Assertions.assertFalse(taskManagementRepository.getTeams().isEmpty()),
                () -> Assertions.assertEquals("Trello", taskManagementRepository.getBoards().get(0).getName())
        );

    }

    @Test
    public void execute_should_throwException_when_BoardNotAttached() {
      Board board = taskManagementRepository.createBoard("Tasks");
      Team team = taskManagementRepository.createTeam("Team11");
        List<String> parameters = List.of("Tasks", "Team11");
        command.executeCommand(parameters);

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(parameters));
    }



}