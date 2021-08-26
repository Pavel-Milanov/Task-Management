package com.taskmanagement.models.tasks;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.ActivityHistoryImpl;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;

import java.time.LocalDateTime;


public class StoryImpl extends TaskBase implements Story {

    private Size size;
    private StoryStatus storyStatus;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        super(id, title, description, priority, assignee);
        setSize(size);
        setStoryStatus(status);
    }

    @Override
    public Size getSize() {
        return size;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    @Override
    public StoryStatus getStoryStatus() {
        return storyStatus;
    }

    private void setStoryStatus(StoryStatus storyStatus) {
        this.storyStatus = storyStatus;
    }

    @Override
    public void changeStoryStatus(StoryStatus storyStatus) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.STORY_STATUS_CHANGED, getStoryStatus(), storyStatus), LocalDateTime.now()));

        setStoryStatus(storyStatus);
    }

    @Override
    public void changeSize(Size size) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.STORY_SIZE_CHANGED, getSize(), size), LocalDateTime.now()));

        setSize(size);
    }

    @Override
    public String getAsString() {
        return "Story    : " + super.getAsString() +
                ", Status " + getStoryStatus() + ", Size " + getSize() + ", Priority: " + getPriority() +
                ", Assignee: " + getAssignee();
    }
}
