package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.WorkingItem;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.BOARD_NAME_MAX_LENGTH;
import static com.taskmanagеment.constants.ModelConstants.BOARD_NAME_MIN_LENGTH;
import static com.taskmanagеment.constants.OutputMessages.BOARD_NAME_ERR;

public class BoardImpl implements Board {


    private int id;
    private String name;
    private List<WorkingItem> workingItems;
    private List<ActivityHistory> activityHistories;


    public BoardImpl(int id, String name) {
        this.id = id;
        setName(name);
        this.workingItems = new ArrayList<>();
        this.activityHistories = new ArrayList<>();

    }



    private void setName(String name) {

        ValidationHelpers.validateInRange(name.length(), BOARD_NAME_MIN_LENGTH, BOARD_NAME_MAX_LENGTH, BOARD_NAME_ERR);

        this.name = name;
    }


    @Override
    public void addTask(WorkingItem workingItem) {
        getTasks().add(workingItem);

    }

    @Override
    public void removeTask(WorkingItem workingItem) {
        if (getTasks().size() > 0) {
            getTasks().remove(workingItem);
        } else {
            throw new IllegalArgumentException("There is no tasks"); //TODO
        }
    }

    @Override
    public void addActivityHistory(ActivityHistory activityHistory) {
        getActivityHistory().add(activityHistory);
    }

    @Override
    public void removeActivityHistory(ActivityHistory activityHistory) {

        if (getActivityHistory().size() > 0) {
            getActivityHistory().remove(activityHistory);
        } else {
            throw new IllegalArgumentException("There is no activity history"); //TODO
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<WorkingItem> getTasks() {
        return workingItems;
    }

    @Override
    public List<ActivityHistory> getActivityHistory() {
        return activityHistories;
    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getAsString() {
        return null;
    }
}
