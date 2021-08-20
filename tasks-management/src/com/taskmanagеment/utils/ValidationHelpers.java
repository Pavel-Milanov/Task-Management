package com.taskmanagеment.utils;

import java.util.List;

import static com.taskmanagеment.Constants.OutputMessages.*;

public class ValidationHelpers {

    public static void validateInRange(int value, int minValue, int maxValue, String message){

        if (value < minValue || value > maxValue){
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateArgumentsCount(List<String> parameters, int expectedNumberOfParameters){

        if (parameters.size() < expectedNumberOfParameters){
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, parameters.size())
            );
        }
    }
}
