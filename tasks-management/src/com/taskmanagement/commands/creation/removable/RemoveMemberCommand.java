package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveMemberCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;
    private final TaskManagementRepository taskManagementRepository;

    public RemoveMemberCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int memberId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return removeMember(memberId);
    }

    private String removeMember(int memberId) {
        Member member = helperRepository.findElementById(taskManagementRepository.getMembers(), memberId);
        for (Task task : helperRepository.getTasks()) {
            if (task.getAssignee().equals(member.getName())) task.changeAssignee("");
        }
        taskManagementRepository.removeMember(member);
        return String.format(CommandConstants.MEMBER_REMOVED, member.getName());
    }
}
