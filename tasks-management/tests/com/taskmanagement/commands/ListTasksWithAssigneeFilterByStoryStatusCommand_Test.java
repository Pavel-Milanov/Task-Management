package com.taskmanagement.commands;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.*;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ListTasksWithAssigneeFilterByStoryStatusCommand_Test {
    private TaskManagementRepository taskManagementRepository;
    private Command command;
    private Command command1;
    private TaskManagementHelperRepositoryImpl helperRepository;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListTasksWithAssigneeFilterByStoryStatusCommand(taskManagementRepository);
        this.command1 = new CreateNewStoryCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListTasksWithAssigneeFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListTasksWithAssigneeFilterByAssigneeCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
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
        taskManagementRepository.createStory("storyNameeeeee","aaaaaaaaaaaaa", Priority.HIGH, Size.LARGE, StoryStatus.DONE,"aaaaa");
        String output = command.executeCommand(List.of("done"));
        Assertions.assertEquals("", output);
    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class,()-> command.executeCommand(List.of()));
    }
}
