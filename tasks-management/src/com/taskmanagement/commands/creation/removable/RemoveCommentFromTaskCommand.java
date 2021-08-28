package com.taskmanagement.commands.creation.removable;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.ElementNotFoundException;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Comment;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;
import static com.taskmanagement.constants.CoreConstants.ELEMENT_NOT_FOUND;


public class RemoveCommentFromTaskCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public RemoveCommentFromTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        String commentContent = parameters.get(1);
        String memberName = parameters.get(2);
        return removeCommentFromWorkingItem(taskIndex, commentContent, memberName);
    }

    private String removeCommentFromWorkingItem(int taskIndex, String commentContent, String memberName) {
        helperRepository.validateMemberIsFromTeam(taskIndex, memberName);

        WorkingItem workingItem = helperRepository.findElementById(helperRepository.getWorkingItems(), taskIndex);

        Comment comment = workingItem.getComments().stream()
                .filter(comment1 -> comment1.getContent().equals(commentContent)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(String.format(INVALID_COMMENT, commentContent)));

        removeCommentFromWorkingItem(comment, workingItem);

        return String.format(COMMENT_REMOVED_SUCCESSFULLY, memberName);
    }

    private void removeCommentFromWorkingItem(Comment comment, WorkingItem workingItem) {
        boolean removed = false;
        for (WorkingItem workingItem1 : helperRepository.getWorkingItems()) {
            if (workingItem1.getName().equals(workingItem.getName())) {
                workingItem1.removeComment(comment);
                removed = true;
            }
        }

        if (!removed)
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, comment.getContent()));
    }
}
