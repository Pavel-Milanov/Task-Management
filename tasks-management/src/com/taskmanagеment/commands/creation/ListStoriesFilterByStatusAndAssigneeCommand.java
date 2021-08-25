package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.StoryStatus;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

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
