package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.*;

import static com.taskmanagеment.constants.OutputMessages.*;

public class TeamImpl implements Team {


    private String name;
    private  List<Member> members;
    private  List<Board> boards;
    private  List<ActivityHistory> activityHistories;


    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistories = new ArrayList<>();

    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateInRange(name.length(), TEAM_NAME_MIN_LENGTH
                , TEAM_NAME_MAX_LENGTH, TEAM_NAME_ERR);
        this.name = name;
    }

    @Override
    public void addMember(Member member) {

        members.add(member);
    }

    @Override
    public void removeMember(Member member) {

        if (members.isEmpty()) {
            throw new IllegalArgumentException("There is no members");
        }

        members.remove(member);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void addBoard(Board board) {

        boards.add(board);
    }

    @Override
    public void removeBoard(Board board) {
        if (boards.isEmpty()) {
            throw new IllegalArgumentException("There is no boards");
        }

        boards.remove(board);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public List<ActivityHistory> getActiveHistory() {
        return new ArrayList<>(activityHistories);
    }

}
