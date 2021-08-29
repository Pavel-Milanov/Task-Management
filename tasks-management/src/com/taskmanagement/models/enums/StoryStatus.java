package com.taskmanagement.models.enums;

public enum StoryStatus {

    NOT_DONE,
    INPROGRESS,
    DONE;


    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not Done";
            case INPROGRESS:
                return "In progress";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("This Story status does not exists");
        }
    }
}
