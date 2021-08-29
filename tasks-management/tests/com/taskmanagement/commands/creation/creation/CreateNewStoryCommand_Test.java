package com.taskmanagement.commands.creation.creation;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Story;
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

public class CreateNewStoryCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewStoryCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateNewStoryCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateNewStoryCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_throwException_when_passInvalidBoardId() {

        // Arrange
        List<String> parameters = List.of(String.valueOf(1), "The program freezes is open", "Work on first problem", "high", "large", "progress", "Peter");

        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(parameters));
    }

    @Test
   public void execute_should_createNewStory_when_passValidInput(){
        Team team = taskManagementRepository.createTeam("Team1");
        Board board = taskManagementRepository.createBoard("Tasks");
        helperRepository.addBoardToTeam(board,team);
        Story story = taskManagementRepository.createStory("The program freezes is open","Work on first problem",Priority.HIGH,Size.LARGE,StoryStatus.INPROGRESS, CommandConstants.NO_ASSIGNEE);

        command.executeCommand(List.of("2","The program freezes is open","Work on first problem","high","large","done"));

        Assertions.assertEquals(taskManagementRepository.getStories().get(0).getName(),story.getName());
   }
   //Ralitsa
}


