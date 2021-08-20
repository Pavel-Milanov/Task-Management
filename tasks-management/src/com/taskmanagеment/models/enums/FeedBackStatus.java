package com.taskmanagеment.models.enums;

public enum FeedBackStatus {


    NEW,
    UNSCHEDULED,
    SCHEDULED,
    DONE;


    @Override
    public String toString() {
        switch (this){
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("This Feedback Status does not exists");
        }
       
    }
}
