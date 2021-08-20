package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.BaseModel;
import com.taskmanagеment.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseModelImpl implements BaseModel {

    private final List<Task> tasks = new ArrayList<>();
    private final List<ActivityHistory> activityHistories = new ArrayList<>();
    private final int id;
    private String name;

    public BaseModelImpl(int id, String name) {
        setName(name);
        this.id = id;

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

    @Override
    public int getId() {
        return id;
    }
}
