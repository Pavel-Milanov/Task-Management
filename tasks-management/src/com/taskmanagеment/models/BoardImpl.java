package com.taskmanagеment.models;

import com.taskmanagеment.Constants.ModelConstants;
import com.taskmanagеment.Constants.OutputMessages;
import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.BaseModel;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.Constants.ModelConstants.*;
import static com.taskmanagеment.Constants.OutputMessages.*;

public class BoardImpl extends BaseModelImpl implements Board {


    public BoardImpl(String name) {
        super(name);
    }

    @Override
    protected void validateName(String name) {

        ValidationHelpers.validateInRange(name.length(), BOARD_NAME_MIN_LENGTH, BOARD_NAME_MAX_LENGTH, BOARD_NAME_ERR);
    }

    @Override
    public void addTask(Task task) {
        getTasks().add(task);

    }

    @Override
    public void removeTask(Task task) {
        if (getTasks().size() > 0){
            getTasks().remove(task);
        }else {
            throw new IllegalArgumentException("There is no tasks"); //TODO
        }
    }

    @Override
    public void addActivityHistory(ActivityHistory activityHistory) {
        getActiveHistory().add(activityHistory);
    }

    @Override
    public void removeActivityHistory(ActivityHistory activityHistory) {

        if (getActiveHistory().size() > 0) {
            getActiveHistory().remove(activityHistory);
        }else {
            throw new IllegalArgumentException("There is no activity history"); //TODO
        }
    }


    @Override
    public String getAsString() {
        return null;
    }


}
