package com.taskmanagеment.models;

import com.taskmanagеment.constants.ModelConstants;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.ActivityHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ActivityHistoryImpl implements ActivityHistory {

    private final String message;
    private final LocalDateTime timestamp;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.US);


    public ActivityHistoryImpl(String message, LocalDateTime timestamp) {
        if (message.isEmpty()) throw new InvalidUserInputException(ModelConstants.DESCRIPTION_IS_EMPTY);
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String getAsString() {
        return String.format("[%s] %s", timestamp.format(formatter), message);
    }
}
