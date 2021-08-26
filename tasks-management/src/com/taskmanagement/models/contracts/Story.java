package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;

public interface Story extends Task {

    Priority getPriority();

    Size getSize();

    StoryStatus getStoryStatus();

    void changeStoryStatus(StoryStatus storyStatus);

    void changeSize(Size size);
}
