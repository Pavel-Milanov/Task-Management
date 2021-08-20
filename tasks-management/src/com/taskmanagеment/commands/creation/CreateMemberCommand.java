package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand implements Command {
    public static final String MEMBER_CREATED_MESSAGE = "Member with name %s was created.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public CreateMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand (List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);



        return null;
    }
}
