package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddCommentTaskCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.CommentImpl;
import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.models.tasks.FeedBackImpl;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.COMMENT_ADDED_SUCCESSFULLY;

public class AddCommentTaskCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddCommentTaskCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {AddCommentTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, AddCommentTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_addComment_when_argumentIsValid() {

        //Arrange, Act
        List<String> parameters = List.of(String.valueOf(3), "Peter", "Bug is done");


        //Assert
        Assertions.assertEquals(AddCommentTaskCommand.EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size());

    }

    @Test
    public void execute_should_throwException_when_passedUnparsableTaskIndex() {
        // Arrange
        List<String> arguments = List.of("invalid");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_addComment_when_passedValidInput() {

        Member member = taskManagementRepository.createMember("Peter");

        Team team = taskManagementRepository.createTeam("Momomo");

        Board board = taskManagementRepository.createBoard("Teamless");
        Bug bug = taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!"
                , Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");

        taskManagementRepository.addMemberToTeam(member, team);
        taskManagementRepository.addBoardToTeam(board, team);

        taskManagementRepository.getBoards().get(0).addTask(bug);




        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of(String.valueOf(bug.getId()),"Bug is done", "Peter"))),
                () -> Assertions.assertFalse(taskManagementRepository.getMembers().isEmpty()),
                () -> Assertions.assertFalse(taskManagementRepository.getTeams().isEmpty()),
                () -> Assertions.assertEquals(1, taskManagementRepository.getBugs().get(0).getComments().size())
        );

    }

}
