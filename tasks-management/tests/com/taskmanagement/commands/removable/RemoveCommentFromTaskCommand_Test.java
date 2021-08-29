package com.taskmanagement.commands.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.addition.AddCommentTaskCommand;
import com.taskmanagement.commands.creation.removable.RemoveCommentFromTaskCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.CommentImpl;
import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RemoveCommentFromTaskCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;
    private Command command1;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
        this.command = new RemoveCommentFromTaskCommand(taskManagementRepository);
        this.command1 = new AddCommentTaskCommand(taskManagementRepository);
    }


    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {RemoveCommentFromTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveCommentFromTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_removeComment_when_passedValidInput() {

        Member member = taskManagementRepository.createMember("Peter");

        Team team = taskManagementRepository.createTeam("Team11");

        Board board = taskManagementRepository.createBoard("Tasks");
        Bug bug = taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!"
                , Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");

        Comment comment = new CommentImpl("Bug is done", "Peter");
        helperRepository.addMemberToTeam(member, team);
        helperRepository.addBoardToTeam(board, team);

        helperRepository.getWorkingItems().get(0).addComment(comment);

        taskManagementRepository.getBoards().get(0).addWorkingItem(bug);


        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of(String.valueOf(helperRepository.getWorkingItems().get(0).getId())
                        , "Bug is done", "Peter"))),
                () -> Assertions.assertFalse(taskManagementRepository.getMembers().isEmpty()),
                () -> Assertions.assertFalse(taskManagementRepository.getTeams().isEmpty()),
                () -> Assertions.assertEquals(0, taskManagementRepository.getBugs().get(0).getComments().size())
        );
    }
        @Test
        public void execute_should_throwException_when_passedInvalidTMemberName () {

            Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of(String.valueOf(1), "The task is working now", "Peter")));

        }

        @Test
        public void execute_should_throwException_when_listIsEmpty () {

            Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(List.of()));
        }
    }
