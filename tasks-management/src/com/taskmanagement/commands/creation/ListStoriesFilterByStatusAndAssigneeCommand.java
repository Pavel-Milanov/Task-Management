package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesFilterByStatusAndAssigneeCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ListStoriesFilterByStatusAndAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        StoryStatus status = ParsingHelpers.tryParseEnum(parameters.get(0),StoryStatus.class );
        String assignee = parameters.get(1);
        return listBugs(status,assignee);
    }

    private String listBugs(StoryStatus status, String assignee) {
        List<Story> storiesFilter ;
        storiesFilter = taskManagementRepository.getStories().stream()
                .filter(story -> story.getStoryStatus().equals(status) && story.getAssignee().equals(assignee)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(storiesFilter);

    }

}
