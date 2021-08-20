package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Story;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Size;
import com.taskmanagеment.models.enums.StoryStatus;


public class StoryImpl extends BaseBugStory implements Story {


    private Size size;
    private StoryStatus storyStatus;
    private String assignee;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, StoryStatus storyStatus, String assignee) {
        super(id, title, description, priority, assignee);
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


}
