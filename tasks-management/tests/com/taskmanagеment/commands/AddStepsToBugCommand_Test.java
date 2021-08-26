package com.taskmanagеment.commands;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.commands.creation.AddMemberToTeamCommand;
import com.taskmanagеment.commands.creation.AddStepsToBugCommand;
import com.taskmanagеment.commands.creation.CreateNewBugCommand;
import com.taskmanagеment.core.TaskManagementRepositoryImpl;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.utils.Factory;
import com.taskmanagеment.utils.TestData;
import com.taskmanagеment.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
