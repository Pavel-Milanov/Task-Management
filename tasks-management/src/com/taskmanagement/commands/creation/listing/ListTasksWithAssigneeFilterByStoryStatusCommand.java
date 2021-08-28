package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeFilterByStoryStatusCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeFilterByStoryStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(0), StoryStatus.class);
        return tasksFilteredByStoryStatus(storyStatus);
    }

    private String tasksFilteredByStoryStatus(StoryStatus storyStatus) {

        List<Story> filteredStoryTasks = taskManagementRepository.getStories().stream()
                .filter(story -> story.getStoryStatus().equals(storyStatus) && !story.getAssignee().equals(CommandConstants.NO_ASSIGNEE)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredStoryTasks);
    }

    //Ralitsa
}
