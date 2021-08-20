package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;

import java.time.LocalDate;

public class ActivityHistoryImpl implements ActivityHistory {

    private String message;
    private LocalDate localDate;

    public ActivityHistoryImpl(String message, LocalDate localDate) {
        setMessage(message);
        setLocalDate(localDate);
    }

    @Override
    public String getActiveHistory() {
        return message;
    }

    @Override
    public LocalDate getTimeStamp() {
        return localDate;
    }

    private void setMessage(String message) {
        if (message == null) {
            throw new IllegalArgumentException("The message cannot be null.");
        }
        this.message = message;
    }

    private void setLocalDate(LocalDate localDate) {
        if (localDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("The date can not be in the past");
        this.localDate = localDate;
    }
}
