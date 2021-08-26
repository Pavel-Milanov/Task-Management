package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.CreateNewFeedbackCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class CreateNewFeedbackCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewFeedbackCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateNewFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateNewFeedbackCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_addTaskToBoard_when_passedValidInput() {
        Member member = taskManagementRepository.createMember("user1");
        Team team = taskManagementRepository.createTeam("team1");
        Board board = taskManagementRepository.createBoard("title1");
        taskManagementRepository.addBoardToTeam(board, team);
        taskManagementRepository.addMemberToTeam(member, team);

        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("title1", "story", "storytitleeeeeeeeeee", "aaaaaaaaaaaaaa", "user1"))),
                () -> Assertions.assertFalse(taskManagementRepository.getBoards().isEmpty()),
                () -> Assertions.assertFalse(taskManagementRepository.getBoard(board).getTasks().isEmpty())
        );
    }
}
