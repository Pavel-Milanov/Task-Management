package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.ShowAllTeamBoardsCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ShowAllTeamBoardsCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.US);
//    private LocalDateTime timestamp = LocalDateTime.now();
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ShowAllTeamBoardsCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ShowAllTeamBoardsCommand_Test.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
//    }
//
//    @Test
//    public void execute_should_registerUser_when_passedValidInput() {
//
//        taskManagementRepository.createBoard("board111");
//
//        Assertions.assertEquals("board111", taskManagementRepository.getBoards().get(0).getName());
//
//    }
//
//    @Test
//    public void execute_should_throwException() {
//
//        taskManagementRepository.createBoard("board111");
//
//        Assertions.assertThrows(InvalidUserInputException.class,
//                () -> command.executeCommand(List.of("board111", "team")));
//    }
//
//    @Test
//    public void execute_should_registerUser_when_passedValidInput3() {
//
//
//        Team team = taskManagementRepository.createTeam("team1");
//        Board board = taskManagementRepository.createBoard("board1");
//        taskManagementRepository.addBoardToTeam(board, team);
//
//        Assertions.assertAll(
//                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("team1"))),
//                () -> Assertions.assertEquals("BoardImpl  id=2, name='board1\n" +
//                        "Task Info :", command.executeCommand(List.of("team1"))));
//
//    }
}
