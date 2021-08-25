package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.StoryStatus;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;


public class ChangeStoryStatusCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeStoryStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);
        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(1),StoryStatus.class );
        return changeStoryStatus(storyId,storyStatus);
    }

    private String changeStoryStatus(int storyId, StoryStatus storyStatus) {

        Story story = taskManagementRepository.findElementById(taskManagementRepository.getStories(),storyId);

        story.changeStoryStatus(storyStatus);

        return LABEL_CHANGED_SUCCESSFULLY;
    }


    //Ralitsa
}
