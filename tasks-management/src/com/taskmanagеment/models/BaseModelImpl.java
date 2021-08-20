package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.BaseModel;
import com.taskmanagеment.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseModelImpl implements BaseModel {

    private String name;
    private List<Task> tasks ;
    private List<ActivityHistory> activityHistories;


    public BaseModelImpl(String name) {
        setName(name);
        this.tasks =new ArrayList<>();
        this.activityHistories = new ArrayList<>();

    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    protected abstract void validateName(String name);

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<ActivityHistory> getActiveHistory() {
        return new ArrayList<>(activityHistories);
    }


    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    @Override
    public void addActivityHistory(ActivityHistory activityHistory) {
        this.activityHistories.add(activityHistory);

    }

    @Override
    public void removeActivityHistory(ActivityHistory activityHistory) {
        this.activityHistories.remove(activityHistory);

    }

    @Override
    public String getAsString() {

        return null;
    }
}
