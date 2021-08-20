package com.taskmanagеment.models.tasks;

import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.Size;
import com.taskmanagеment.models.enums.StoryStatus;
import com.taskmanagеment.models.enums.TaskType;


public class StoryImpl extends BaseBugStory implements Story {


    private Size size;
    private StoryStatus storyStatus;

    public StoryImpl(int id, String title, String description, String assignee) {
        super(id, title, description, assignee);
        this.size = size;
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
    public TaskType getType() {
        return TaskType.STORY;
    }
}
