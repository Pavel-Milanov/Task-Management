package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.constants.CoreConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.*;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.StoryStatus;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ListingHelpers;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTypeTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public ListTypeTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String filterType = parameters.get(1).toUpperCase();
        String paramFilter = parameters.get(2).toUpperCase();
        return parseParam(taskType, filterType, paramFilter);
    }

    private String parseParam(TaskType taskType, String filterType, String paramFilter) {
        List<Task> taskList = taskManagementRepository.getTasks();
        List<FeedBack> feedbacks = getFeedbacks(taskList);
        List<Story> stories = getStories(taskList);
        List<BugStory> bugStoryList = getBugStory(taskList);
        List<Bug> bugs = getBugs(taskList);
        String output = "";

        switch (filterType) {

            case "STATUS":
                switch (taskType) {
                    case FEEDBACK:
                        List<FeedBack> feedbacks1 = getFilteredFeedbacks(paramFilter, feedbacks);

                        return ListingHelpers.elementsToString(feedbacks1);
                    case STORY:
                        List<Story> stories1 = getFilteredStories(paramFilter, stories);

                        return ListingHelpers.elementsToString(stories1);
                    case BUG:
                        List<Bug> bugs1 = getFilteredBugs(paramFilter, bugs);

                        return ListingHelpers.elementsToString(bugs1);
                }
                break;
            case "ASSIGNEE":
                validateTaskTypeNotThatType(taskType.equals(TaskType.FEEDBACK));

                List<BugStory> bugStories = getBugStory(taskList).stream()
                        .filter(bug -> bug.getAssignee().equalsIgnoreCase(paramFilter)).collect(Collectors.toList());


                return ListingHelpers.elementsToString(bugStories);
            case "SORT":
                switch (paramFilter) {
                    case "TITLE":
                        taskList.sort(Comparator.comparing(o -> o.getName().toUpperCase()));
                        return ListingHelpers.elementsToString(taskList);
                    case "PRIORITY":
                        validateTaskTypeNotThatType(taskType.equals(TaskType.FEEDBACK));

                        if (taskType.equals(TaskType.BUG)) {
                            bugStories = getBugStory(taskList).stream().filter(bugStory -> bugStory.getType().equals(TaskType.BUG)).collect(Collectors.toList());
                            bugStories.sort(Comparator.comparing(bug -> bug.getPriority().toString()));

                            return ListingHelpers.elementsToString(getBugs(taskList));
                        }

                        if (taskType.equals(TaskType.STORY)) {
                            bugStories = getBugStory(taskList).stream().filter(bugStory -> bugStory.getType().equals(TaskType.STORY)).collect(Collectors.toList());
                            bugStories.sort(Comparator.comparing(story -> story.getPriority().toString()));
                        }

                        return ListingHelpers.elementsToString(taskList);
                    case "SEVERITY":
                        validateTaskType(taskType, TaskType.BUG);
                        bugs = getBugs(taskList).stream()
                                .filter(bug -> bug.getSeverity().toString().equals(paramFilter)).collect(Collectors.toList());
                        return ListingHelpers.elementsToString(bugs);
                    case "SIZE":
                        validateTaskType(taskType, TaskType.STORY);
                        stories = getStories(taskList).stream()
                                .filter(story -> story.getStoryStatus().toString().equals(paramFilter)).collect(Collectors.toList());
                        return ListingHelpers.elementsToString(stories);
                    case "RATING":
                        validateTaskType(taskType, TaskType.FEEDBACK);
                        List<FeedBack> feedbackList = getFeedbacks(taskList).stream().sorted().collect(Collectors.toList());

                        return ListingHelpers.elementsToString(feedbackList);
                }
            default:
                throw new InvalidUserInputException(String.format(CommandConstants.NOT_SUPPORTED_OPERATION, this));
        }
        return output;
    }

    private List<Bug> getBugs(List<Task> taskList) {
        List<Bug> bugs = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getType().equals(TaskType.BUG)) bugs.add((Bug) task);
        }
        return bugs;
    }

    private void validateTaskTypeNotThatType(boolean equals) {
        if (equals) {
            throw new InvalidUserInputException(String.format(CoreConstants.INVALID_COMMAND, this));
        }
    }

    private void validateTaskType(TaskType taskType, TaskType taskTypeChecked) {
        validateTaskTypeNotThatType(!taskType.equals(taskTypeChecked));
    }

    private List<Bug> getFilteredBugs(String paramFilter, List<Bug> bugs) {
        List<Bug> bugs1 = new ArrayList<>();
        for (Bug bug : bugs) {
            if (bug.getBugStatus().equals(ParsingHelpers.tryParseEnum(paramFilter, BugStatus.class))) {
                bugs1.add(bug);
            }
        }
        return bugs1;
    }

    private List<Story> getFilteredStories(String paramFilter, List<Story> stories) {
        List<Story> stories1 = new ArrayList<>();
        for (Story story : stories) {
            if (story.getStoryStatus().equals(ParsingHelpers.tryParseEnum(paramFilter, StoryStatus.class))) {
                stories1.add(story);
            }
        }
        return stories1;
    }

    private List<FeedBack> getFilteredFeedbacks(String paramFilter, List<FeedBack> feedbacks) {
        List<FeedBack> feedbacks1 = new ArrayList<>();
        for (FeedBack feedback : feedbacks) {
            if (feedback.getFeedBackStatus().equals(ParsingHelpers.tryParseEnum(paramFilter, FeedBackStatus.class))) {
                feedbacks1.add(feedback);
            }
        }
        return feedbacks1;
    }

    private List<BugStory> getBugStory(List<Task> taskList) {
        List<BugStory> bugs = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getType().equals(TaskType.BUG)) bugs.add((BugStory) task);
        }
        return bugs;
    }

    private List<Story> getStories(List<Task> taskList) {
        List<Story> stories = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getType().equals(TaskType.STORY)) stories.add((Story) task);
        }
        return stories;
    }

    private List<FeedBack> getFeedbacks(List<Task> taskList) {
        List<FeedBack> feedbacks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getType().equals(TaskType.FEEDBACK)) feedbacks.add((FeedBack) task);
        }
        return feedbacks;
    }

}
