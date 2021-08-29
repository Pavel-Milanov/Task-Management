package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.models.contracts.Bug;
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

public class UnassignTaskFromMemberCommand_Test {

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new UnAssignTaskFromMemberCommand(taskManagementRepository);
    }


    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {UnAssignTaskFromMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, UnAssignTaskFromMemberCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
    }

    @Test
    public void execute_should_unassignTaskFromMember_when_passedValidInput() {
        Bug bug =  taskManagementRepository.createBug("The program freezes", "This needs to be fixed quickly!"
                , Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, "Peter");

        command.executeCommand(List.of(String.valueOf(bug.getId())));

        Assertions.assertEquals("--NO ASSIGNEE--", taskManagementRepository.getBugs().get(0).getAssignee());

    }

    @Test
    public void execute_should_throwException_when_listIsEmpty() {

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.executeCommand(List.of("1")));
    }
}
