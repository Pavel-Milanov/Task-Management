package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;

public class ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        BugStatus bugStatus = ParsingHelpers.tryParseEnum(parameters.get(1).toUpperCase(), BugStatus.class);
        String nameAssignee = parameters.get(3);


        return filterByBugStatusAndAssignee(taskId, bugStatus, nameAssignee);
    }

    private String filterByBugStatusAndAssignee(int taskId, BugStatus bugStatus, String nameAssignee) {

        Task task = helperRepository.findElementById(helperRepository.getTasks(), taskId);


        List<Bug> filteredTasks = new ArrayList<>();

        for (Bug bug : taskManagementRepository.getBugs()) {
            if (bug.getBugStatus().equals(bugStatus) || bug.getAssignee().equalsIgnoreCase(nameAssignee)) {
                filteredTasks.add(bug);
            }
        }

        return ListingHelpers.elementsToString(filteredTasks);
    }
    //Ralitsa
}
