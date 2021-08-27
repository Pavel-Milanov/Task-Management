package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.LABEL_CHANGED_SUCCESSFULLY;

public class ChangeStoryPriorityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeStoryPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        return changeStoryPriority(storyId, priority);
    }

    private String changeStoryPriority(int storyId, Priority priority) {

        Story story = helperRepository.findElementById(taskManagementRepository.getStories(), storyId);

        story.changePriority(priority);

        return LABEL_CHANGED_SUCCESSFULLY;
    }

    //Ralitsa
}
