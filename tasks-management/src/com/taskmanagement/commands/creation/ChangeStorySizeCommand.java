package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeStorySizeCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeStorySizeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class );
        return changeStatus(storyId,size);
    }

    private String changeStatus(int storyId, Size size) {
        Story story = taskManagementRepository.findElementById(taskManagementRepository.getStories(),storyId);
        story.changeSize(size);
        return String.format(CommandConstants.SIZE_CHANGED_SUCCESSFULLY,story.getName());
    }


}
