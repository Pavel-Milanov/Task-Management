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

        String[] split = steps.toString().split(";");

        StringBuilder result = new StringBuilder();
        result.append("--STEPS--").append(System.lineSeparator());

        for (String s : split) {
            result.append(s).append(System.lineSeparator());
        }
        result.replace(11,12," ");
        return String.join(System.lineSeparator(), result.substring(0,result.length()-3)).trim();
    }
}
