package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;

import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;

public class CreateMemberCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public CreateMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        return createMember(memberName);
    }

    private String createMember(String memberName) {
        if (taskManagementRepository.memberExist(memberName)) {
            throw new InvalidUserInputException(String.format(MEMBER_ALREADY_EXISTS, memberName));
        }

        taskManagementRepository.createMember(memberName);

        return String.format(CommandConstants.MEMBER_CREATED, memberName);
    }
}
