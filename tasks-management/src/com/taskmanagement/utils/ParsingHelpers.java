package com.taskmanagement.utils;

import static com.taskmanagement.constants.ModelConstants.NO_SUCH_ENUM;

public class ParsingHelpers {

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {

        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
