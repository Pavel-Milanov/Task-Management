package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;

import static com.taskmanagеment.constants.ModelConstants.BOARD_NAME_MAX_LENGTH;
import static com.taskmanagеment.constants.ModelConstants.BOARD_NAME_MIN_LENGTH;
import static com.taskmanagеment.constants.OutputMessages.BOARD_NAME_ERR;

public class BoardImpl extends BaseModelImpl implements Board {


    public BoardImpl(int id, String name) {
        super(id, name);
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
        if (getTasks().size() > 0) {
            getTasks().remove(task);
        } else {
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
        } else {
            throw new IllegalArgumentException("There is no activity history"); //TODO
        }
    }


    @Override
    public String getAsString() {
        return null;
    }

}
