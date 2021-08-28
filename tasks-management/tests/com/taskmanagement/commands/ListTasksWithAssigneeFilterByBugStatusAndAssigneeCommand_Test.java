package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.creation.CreateNewBugCommand;
import com.taskmanagement.commands.creation.listing.ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private Command command1;
    private TaskManagementHelperRepositoryImpl helperRepository;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand(taskManagementRepository);
        this.command1 = new CreateNewBugCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }


    @Test
    public void execute_should_when_passedValidInput() {
        Member member = taskManagementRepository.createMember("aaaaa");
        Team team = taskManagementRepository.createTeam("team1");
        Board board = taskManagementRepository.createBoard("board1");
        helperRepository.addMemberToTeam(member, team);
        helperRepository.addBoardToTeam(board, team);
        //taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa");

        command1.executeCommand(List.of("board1", "bugtitleeeee", "descriptionon", "low", "Critical", "active", "aaaaa"));
        command.executeCommand(List.of("active", "aaaaa"));
        Bug bug = taskManagementRepository.getBugs().get(0);
        String output = command.executeCommand(List.of("active", "aaaaa"));
        Assertions.assertEquals("Bug      : id=4, name: 'bugtitleeeee', description: 'descriptionon', Bug Status Active, Severity Critical, Priority: Low, Assignee: aaaaa", output);
    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(List.of()));
    }
}
