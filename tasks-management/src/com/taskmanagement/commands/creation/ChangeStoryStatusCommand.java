package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;
import static com.taskmanagement.constants.CommandConstants.LABEL_CHANGED_SUCCESSFULLY;


public class ChangeStoryStatusCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeStoryStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(1), StoryStatus.class);
        return changeStoryStatus(storyId, storyStatus);
    }

    private String changeStoryStatus(int storyId, StoryStatus storyStatus) {

        Story story = helperRepository.findElementById(taskManagementRepository.getStories(), storyId);

        story.changeStoryStatus(storyStatus);

        return LABEL_CHANGED_SUCCESSFULLY;
    }


    //Ralitsa
}
