package com.taskmanagement.commands.creation.listing;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeFilterByStoryStatusAndAssigneeCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeFilterByStoryStatusAndAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(0), StoryStatus.class);
        String assignee = parameters.get(1);
        return filterByStoryStatusAndAssignee(storyStatus, assignee);
    }

    private String filterByStoryStatusAndAssignee(StoryStatus storyStatus, String assignee) {

        List<Story> filteredStories = taskManagementRepository.getStories().stream()
                .filter(story -> story.getStoryStatus().equals(storyStatus) && story.getAssignee().equals(assignee))
                .collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredStories);
    }
}
