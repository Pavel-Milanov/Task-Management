package com.taskmanagement.models.enums;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.exceptions.InvalidUserInputException;

public enum TaskType {
    BUG,
    STORY,
    FEEDBACK;

    @Override
    public String toString() {
        switch (this) {
            case BUG:
                return "Bug";
            case STORY:
                return "Story";
            case FEEDBACK:
                return "Feedback";
            default:
                throw new InvalidUserInputException(String.format(ModelConstants.NOT_SUPPORTED_ENUM, this));
        }
    }
}
