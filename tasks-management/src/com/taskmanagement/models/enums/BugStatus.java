package com.taskmanagement.models.enums;

public enum BugStatus {

    ACTIVE,
    FIXED;


    @Override
    public String toString() {

        switch (this){
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";
            default:
                throw new UnsupportedOperationException("This BugStatus does not exists");

        }
    }
}
