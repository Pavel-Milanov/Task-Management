package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;

public interface Story extends  Task {

    Priority getPriority();
    Size getSize();
    StoryStatus getStoryStatus();
    Member getMember();

    void changeStoryStatus(StoryStatus storyStatus);
    void changeStoryPriority(Priority priority);

    void changeSize(Size size);
}
