package com.taskmanagement.models;

import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {


    private int id;
    private String name;
    private List<Member> members;
    private List<WorkingItem> workingItems;
    private List<ActivityHistory> activityHistories;
    private List<Team> teamList;

    public MemberImpl(int id,String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.workingItems = new ArrayList<>();
        this.activityHistories = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public void addTeam(Team team) {
        teamList.add(team);
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

    @Override
    public int getId() {
        return id;
    }
}
