package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.MEMBER_ALREADY_EXISTS;

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
