package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.AddStepsToBugCommand;
import com.taskmanagement.commands.creation.CreateNewBugCommand;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.Factory;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class AddStepsToBugCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private Command commandBug;

    @BeforeEach
    public void before(){
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddStepsToBugCommand(taskManagementRepository);
        this.commandBug = new CreateNewBugCommand(taskManagementRepository);
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
        Member member = Factory.createMember();
        Team team = Factory.createTeam();
        Board board = Factory.createBoard();
        taskManagementRepository.addMemberToTeam(member,team);
        taskManagementRepository.addBoardToTeam(board,team);
        commandBug.executeCommand(List.of("3","bugtitleee","bugdescription", "high","Critical","Active","zzzzzz"));
                Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("4", "Step")));


    }


}
