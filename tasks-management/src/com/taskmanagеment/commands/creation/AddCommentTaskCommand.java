package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class AddCommentTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public AddCommentTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        String content = parameters.get(1);
        String author = parameters.get(2);
        return addComment(taskIndex, content, author);
    }

    private String addComment(int taskIndex, String content, String author) {
        Member member = taskManagementRepository.findMemberByName(author);

        Task task = taskManagementRepository.findElementById(taskManagementRepository.getTasks(), taskIndex);

        taskManagementRepository.validateMemberIsFromTeam(taskIndex, author);

        Comment comment = taskManagementRepository.createComment(content, author);

        task.addComment(comment);

        return String.format(CommandConstants.COMMENT_ADDED_SUCCESSFULLY, member.getName());
    }
}
