package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.LABEL_CHANGED_SUCCESSFULLY;

public class ChangeStoryPriorityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeStoryPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1),Priority.class );
        return changeStoryPriority(storyId,priority);
    }

    private String changeStoryPriority(int storyId, Priority priority) {

        Story story = taskManagementRepository.findElementById(taskManagementRepository.getStories(),storyId);

        story.changeStoryPriority(priority);

        return LABEL_CHANGED_SUCCESSFULLY;
    }

    //Ralitsa
}
