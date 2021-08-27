package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveStoryCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementHelperRepositoryImpl helperRepository;
    private final TaskManagementRepository taskManagementRepository;

    public RemoveStoryCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        return removeStory(storyId);
    }

    private String removeStory(int storyId) {
        Story story = helperRepository.findElementById(taskManagementRepository.getStories(), storyId);
        Board board = taskManagementRepository.getBoards().stream()
                .filter(board1 -> board1.getTasks().contains(story)).findAny().orElseThrow();
        board.removeTask(story);
        taskManagementRepository.removeStory(story);
        return String.format(CommandConstants.TASK_REMOVED_SUCCESSFULLY, story.getName());
    }
}
