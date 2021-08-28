package com.taskmanagement.models;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.ModelConstants.BOARD_NAME_MAX_LENGTH;
import static com.taskmanagement.constants.ModelConstants.BOARD_NAME_MIN_LENGTH;
import static com.taskmanagement.constants.OutputMessages.BOARD_NAME_ERR;

public class BoardImpl implements Board {

    private final List<WorkingItem> workingItems;
    private final List<ActivityHistory> activityHistories;
    private final int id;
    private String name;


    public BoardImpl(int id, String name) {
        this.id = id;
        setName(name);
        this.workingItems = new ArrayList<>();
        this.activityHistories = new ArrayList<>();
        activityHistories.add(new ActivityHistoryImpl(("Board with name " + name + " was created."), LocalDateTime.now()));
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateInRange(name.length(), BOARD_NAME_MIN_LENGTH, BOARD_NAME_MAX_LENGTH, BOARD_NAME_ERR);
        this.name = name;
    }

    @Override
    public List<WorkingItem> getWorkingItems() {
        return new ArrayList<>(workingItems);
    }

    @Override
    public List<ActivityHistory> getActivityHistory() {
        return new ArrayList<>(activityHistories);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getAsString() {
        return "BoardImpl  " +
                "id=" + id +
                ", name='" + name +
                "\nTask Info :\n" + ListingHelpers.elementsToString(getWorkingItems());
    }

    @Override
    public void addWorkingItem(WorkingItem workingItem) {
        workingItems.add(workingItem);
        activityHistories.add(new ActivityHistoryImpl(("Working Item " + workingItem.getName() + " add to " + name + " Board "), LocalDateTime.now()));
    }

    @Override
    public void removeWorkingItem(WorkingItem workingItem) {
        if (!workingItems.contains(workingItem)) {
            throw new InvalidUserInputException(String.format(ModelConstants.WORKING_ITEM_NOT_EXIST, workingItem.getName()));
        }
        activityHistories.add(new ActivityHistoryImpl(("Working Item " + workingItem.getName() + " removed from " + name + " Board "), LocalDateTime.now()));
        workingItems.remove(workingItem);
    }
}
