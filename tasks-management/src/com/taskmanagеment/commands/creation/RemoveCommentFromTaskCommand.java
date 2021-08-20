package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class RemoveCommentFromTaskCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public RemoveCommentFromTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        String commentContent = parameters.get(1);
        String memberName = parameters.get(2);
        return removeComment(taskIndex, commentContent, memberName);
    }

    private String removeComment(int taskIndex, String commentContent, String memberName) {
        taskManagementRepository.validateMemberIsFromTeam(taskIndex, memberName);

        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskIndex);

        Comment comment = task.getComments().stream()
                .filter(comment1 -> comment1.getContent().equals(commentContent)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(String.format(CommandConstants.INVALID_COMMENT, commentContent)));

        taskManagementRepository.removeComment(comment, task);

        return String.format(CommandConstants.COMMENT_REMOVED_SUCCESSFULLY, memberName);
    }
}
