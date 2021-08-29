package com.taskmanagement.commands.creation.shown;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ShowTaskAllInfoCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private Command command;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.US);
    private LocalDateTime timestamp = LocalDateTime.now();

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowTaskAllInfoCommand(taskManagementRepository);
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ShowTaskAllInfoCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_showTaskAllInfo_when_passedValidInput() {
        //Arrange

        Story story =  taskManagementRepository.createStory("The program freezes is open","Work on first problem", Priority.HIGH, Size.LARGE, StoryStatus.INPROGRESS,"Peter");


        String output = command.executeCommand(List.of("1"));

        //Assert

        Assertions.assertEquals("Story    : id=1, name: 'The program freezes is open', description: 'Work on first problem', Status In progress, Size Large, Priority: High, Assignee: Peter" + System.lineSeparator() +
                "---HISTORY---" + System.lineSeparator() +
                "["+ LocalDateTime.now().format(formatter) +"]"+" The working item 'The program freezes is open' has been created successfully",output);
    }
}
