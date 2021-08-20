package com.taskmanagеment.utils;

import static com.taskmanagеment.Constants.ModelConstants.*;

public class ParsingHelpers {

    public static <E extends Enum<E>> E tryParseEnum (String valueToParse, Class<E> type){

        try {
            return Enum.valueOf(type,valueToParse.replace(" ","_").toUpperCase());
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM,valueToParse,type.getSimpleName()));
        }
    }
}
