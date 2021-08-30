package com.taskmanagement.models;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.constants.OutputMessages;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    private final List<ActivityHistory> activityHistories;
    private final int id;
    private String name;

    public MemberImpl(int id, String name) {
        this.id = id;
        setName(name);
        this.activityHistories = new ArrayList<>();
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.MEMBER_REGISTERED, name)), LocalDateTime.now()));
    }

    @Override
    public List<ActivityHistory> getActivityHistories() {
        return new ArrayList<>(activityHistories);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateInRange(name.length(), ModelConstants.MEMBER_NAME_MIN_LENGTH, ModelConstants.MEMBER_NAME_MAX_LENGTH, OutputMessages.MEMBER_NAME_ERR);
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getAsString() {
        return "Member{" +
                "id=" + id +
                ", username='" + name;
    }
}
