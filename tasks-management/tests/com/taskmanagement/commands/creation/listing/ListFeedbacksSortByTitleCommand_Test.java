package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;
import com.taskmanagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListFeedbacksSortByTitleCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ListFeedbacksSortByTitleCommand(taskManagementRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ListFeedbacksSortByTitleCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, })
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_sortFeedbackByTitle_when_passedValidInput() {

        //Arrange
        taskManagementRepository.createFeedback("The program freezes is working on it", "All bugs are fixed!",5, FeedBackStatus.NEW);
        taskManagementRepository.createFeedback("The program freezes is done", "All bugs are fixed!",3, FeedBackStatus.NEW);
        //Act
        List<FeedBack> listSortedFeedbackByTitle = taskManagementRepository.getFeedBacks()
                .stream().sorted(Comparator.comparing(FeedBack::getName)).collect(Collectors.toList());
        //Assert
        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of()));


    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.executeCommand(List.of()));
    }

}
