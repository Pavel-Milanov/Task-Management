package com.taskmanagement.commands.creation.addition;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
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

public class AddCommentTaskCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private TaskManagementHelperRepositoryImpl helperRepository;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddCommentTaskCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
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

        helperRepository.addMemberToTeam(member, team);
        helperRepository.addBoardToTeam(board, team);

        taskManagementRepository.getBoards().get(0).addWorkingItem(bug);


        Assertions.assertAll(
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of(String.valueOf(bug.getId()), "Bug is done", "Peter"))),
                () -> Assertions.assertFalse(taskManagementRepository.getMembers().isEmpty()),
                () -> Assertions.assertFalse(taskManagementRepository.getTeams().isEmpty()),
                () -> Assertions.assertEquals(1, taskManagementRepository.getBugs().get(0).getComments().size())
        );

    }

}
