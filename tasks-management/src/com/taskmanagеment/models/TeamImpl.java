package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.TEAM_NAME_MAX_LENGTH;
import static com.taskmanagеment.constants.ModelConstants.TEAM_NAME_MIN_LENGTH;
import static com.taskmanagеment.constants.OutputMessages.TEAM_NAME_ERR;

public class TeamImpl implements Team {

    private final List<ActivityHistory> activityHistories = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private String name;

    public TeamImpl(String name) {
        setName(name);
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
