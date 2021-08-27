package com.taskmanagement.utils;

import com.taskmanagement.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }
        return String.join(System.lineSeparator(), result).trim();
    }

    public static String stepAsString(List<String> steps) {

        String[] split = steps.toString().split("; ");

        StringBuilder result = new StringBuilder();

        for (String s : split) {
            result.append(s).append(System.lineSeparator());
        }
        return String.join(System.lineSeparator(), result.substring(1, result.length() - 3)).trim();
    }
}
