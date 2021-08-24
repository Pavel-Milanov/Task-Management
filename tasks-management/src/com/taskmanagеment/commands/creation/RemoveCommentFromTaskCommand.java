package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;

import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;


public class RemoveCommentFromTaskCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public RemoveCommentFromTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        String commentContent = parameters.get(1);
        String memberName = parameters.get(2);
        return removeComment(taskIndex, commentContent, memberName);
    }

    private String removeComment(int taskIndex, String commentContent, String memberName) {
        taskManagementRepository.validateMemberIsFromTeam(taskIndex, memberName);

        WorkingItem workingItem = taskManagementRepository.findElementById(taskManagementRepository.getWorkingItems(), taskIndex);

        Comment comment = workingItem.getComments().stream()
                .filter(comment1 -> comment1.getContent().equals(commentContent)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(String.format(INVALID_COMMENT, commentContent)));

        taskManagementRepository.removeComment(comment, workingItem);

        return String.format(COMMENT_REMOVED_SUCCESSFULLY, memberName);
    }
}
