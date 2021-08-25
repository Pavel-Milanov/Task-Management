package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.models.enums.Size;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

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
