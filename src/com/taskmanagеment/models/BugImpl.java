package com.taskmanagеment.models;

import java.util.List;

public class BugImpl extends BaseTask {

    //private Severity severity;
    private BugStatus bugStatus;
    private List<String> stepsToReproduce;

    public static class TeamImpl {

        private String name; // unique, between 5 and 15 symbols
        //private List<Member>members;
        //private List<Board> boards;
    }
}
