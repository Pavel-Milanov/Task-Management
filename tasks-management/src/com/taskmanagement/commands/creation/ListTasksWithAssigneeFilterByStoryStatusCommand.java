package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeFilterByStoryStatusCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ListTasksWithAssigneeFilterByStoryStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int tasId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(1).toUpperCase(), StoryStatus.class);
        return tasksFilteredByStoryStatus(tasId, storyStatus);
    }

    private String tasksFilteredByStoryStatus(int tasId, StoryStatus storyStatus) {

        Task task = helperRepository.findElementById(helperRepository.getTasks(), tasId);

        List<Story> filteredStoryTasks = taskManagementRepository.getStories().stream()
                .filter(story -> story.getStoryStatus().equals(storyStatus)).collect(Collectors.toList());

        return ListingHelpers.elementsToString(filteredStoryTasks);
    }

    //Ralitsa
}
