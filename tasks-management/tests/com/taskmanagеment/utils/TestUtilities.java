package com.taskmanagеment.utils;

import java.util.Arrays;
import java.util.List;

public class TestUtilities {
    public static String initializeStringWithSize(int wantedSize) {
        return "z".repeat(wantedSize);
    }

    public static List<String> initializeListWithSize(int argumentsCount) {
        return Arrays.asList(new String[argumentsCount]);
    }
}
