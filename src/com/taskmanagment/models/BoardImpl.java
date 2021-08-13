package com.taskmanagment.models;

import java.util.List;

public class BoardImpl extends Base{

    // name -> // unique, between 5 and 10 symbols
    List<String> activityHistory;

    public static class ModelConstants {
    }

    public enum Severity {

        CRITICAL,
        MAJOR,
        LOW;
    }
}
