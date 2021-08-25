package com.taskmanagеment.models.tasks;

import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.*;

import java.time.LocalDateTime;


public class StoryImpl extends TaskBase implements Story {


    private Size size;
    private StoryStatus storyStatus;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, BugStatus status, String assignee) {
        super(id, title, description, priority, assignee);
        setSize(this.size);
        setStoryStatus(storyStatus);

    }

    private void setSize(Size size) {
        this.size = size;
    }

    private void setStoryStatus(StoryStatus storyStatus) {
        this.storyStatus = storyStatus;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public StoryStatus getStoryStatus() {
        return storyStatus;
    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public void changeStoryStatus(StoryStatus storyStatus) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Story status was changed from %s to %s", getStoryStatus(), storyStatus), LocalDateTime.now()));

        setStoryStatus(storyStatus);
    }

    @Override
    public void changeStoryPriority(Priority priority) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Story status was changed from %s to %s", getStoryStatus(), storyStatus), LocalDateTime.now()));

        setStoryStatus(storyStatus);
    }

    @Override
    public void changeSize(Size size) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Size was changed from %s to %s", getSize(), size), LocalDateTime.now()));

        setSize(size);
    }


    @Override
    public TaskType getType() {
        return TaskType.STORY;
    }
}
