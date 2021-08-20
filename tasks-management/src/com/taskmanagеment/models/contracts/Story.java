package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Size;
import com.taskmanagеment.models.enums.StoryStatus;

public interface Story extends Task {

    Priority getPriority();
    Size getSize();
    StoryStatus getStoryStatus();
    Member getMember();
}
