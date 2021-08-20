package com.taskmanagеment.models.enums;

public enum StoryStatus {

    NOTDONE,
    INPROGRESS,
    DONE;


    @Override
    public String toString() {
        switch (this){
            case NOTDONE:
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
