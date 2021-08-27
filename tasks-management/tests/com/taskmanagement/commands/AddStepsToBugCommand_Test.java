package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddStepsToBugCommand;
import com.taskmanagement.commands.creation.CreateNewBugCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
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

public class AddStepsToBugCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;
    private Command commandBug;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddStepsToBugCommand(taskManagementRepository);
        this.commandBug = new CreateNewBugCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {AddStepsToBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, AddStepsToBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_addBugStep_when_passedValidInput() {
        Member member = taskManagementRepository.createMember("zzzzzz");
        Team team = taskManagementRepository.createTeam("team1");
        Board board = taskManagementRepository.createBoard("board1");


        helperRepository.addMemberToTeam(member, team);
        helperRepository.addBoardToTeam(board, team);
        Bug bug = taskManagementRepository.createBug("bug titleeeee", "bugdescriptionnnnn", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "zzzzzz");
        Assertions.assertEquals(4, bug.getId());
        command.executeCommand(List.of("4", "zzzzzz", "Step"));
        Assertions.assertEquals(List.of("Step"), bug.getStepsToReproduce());

    }

    @Test
    public void execute_should_throwException_when_notAssignee() {
        Member member = taskManagementRepository.createMember("zzzzzz");
        Team team = taskManagementRepository.createTeam("team1");
        Board board = taskManagementRepository.createBoard("board1");


        helperRepository.addMemberToTeam(member, team);
        helperRepository.addBoardToTeam(board, team);
        Bug bug = taskManagementRepository.createBug("bug titleeeee", "bugdescriptionnnnn", Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "zzzzzz");
        Assertions.assertEquals(4, bug.getId());

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of("4", "zzzzzzz", "Step")));


    }

}
