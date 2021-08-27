package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.CommentImpl;
import com.taskmanagement.models.contracts.Comment;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.COMMENT_ADDED_SUCCESSFULLY;
import static com.taskmanagement.constants.CommandConstants.INVALID_INPUT_MESSAGE;

public class AddCommentTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementHelperRepositoryImpl helperRepository;

    public AddCommentTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        String content = parameters.get(1);
        String author = parameters.get(2);
        return addComment(taskIndex, content, author);
    }

    private String addComment(int taskIndex, String content, String author) {
        Member member = helperRepository.findMemberByName(author);

        WorkingItem workingItem = helperRepository.findElementById(helperRepository.getWorkingItems(), taskIndex);

        helperRepository.validateMemberIsFromTeam(taskIndex, author);

        Comment comment = new CommentImpl(content, author);

        workingItem.addComment(comment);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, member.getName());
    }
}
