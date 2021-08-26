package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;

import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Comment;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.*;

public class AddCommentTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public AddCommentTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
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
        Member member = taskManagementRepository.findMemberByName(author);

        WorkingItem workingItem = taskManagementRepository.findElementById(taskManagementRepository.getWorkingItems(), taskIndex);

        taskManagementRepository.validateMemberIsFromTeam(taskIndex, author);

        Comment comment = taskManagementRepository.createComment(content, author);

        workingItem.addComment(comment);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, member.getName());
    }
}
