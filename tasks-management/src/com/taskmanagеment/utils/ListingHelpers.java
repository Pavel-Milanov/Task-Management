package com.taskmanagеment.utils;

import com.taskmanagеment.models.contracts.Printable;

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

        List<String> result = new ArrayList<>();
        result.add("--STEPS--");

        result.addAll(steps);

        return String.join(System.lineSeparator(), result).trim();
    }
}
