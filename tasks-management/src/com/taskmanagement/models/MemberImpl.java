package com.taskmanagement.models;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.constants.OutputMessages;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    private final List<ActivityHistory> activityHistories;
    private final List<Team> teamList;
    private final int id;
    private String name;

    public MemberImpl(int id, String name) {
        this.id = id;
        setName(name);
        this.activityHistories = new ArrayList<>();
        this.teamList = new ArrayList<>();
        activityHistories.add(new ActivityHistoryImpl(("Member with name " + name + " was registered."), LocalDateTime.now()));
    }

    @Override
    public List<Team> getTeamList() {
        return new ArrayList<>(teamList);
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

    @Override
    public void addTeam(Team team) {
        teamList.add(team);
        activityHistories.add(new ActivityHistoryImpl(("Member with name " + name + " was added to " + team.getName() + "."), LocalDateTime.now()));
    }
}
