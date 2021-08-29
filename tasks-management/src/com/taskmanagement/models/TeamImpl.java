package com.taskmanagement.models;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.ModelConstants.TEAM_NAME_MAX_LENGTH;
import static com.taskmanagement.constants.ModelConstants.TEAM_NAME_MIN_LENGTH;
import static com.taskmanagement.constants.OutputMessages.TEAM_NAME_ERR;

public class TeamImpl implements Team {

    private final List<Member> members;
    private final List<Board> boards;
    private final List<ActivityHistory> activityHistories;
    private final int id;
    private String name;


    public TeamImpl(int id, String name) {
        this.id = id;
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistories = new ArrayList<>();
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.TEAM_CREATED,name)), LocalDateTime.now()));
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateInRange(name.length(), TEAM_NAME_MIN_LENGTH, TEAM_NAME_MAX_LENGTH, TEAM_NAME_ERR);
        this.name = name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<ActivityHistory> getActiveHistory() {
        return new ArrayList<>(activityHistories);
    }

    @Override
    public String getAsString() {
        return "TeamImpl{" +
                "id=" + id +
                ", team name='" + name + '\'' +
                ", users=" + ListingHelpers.elementsToString(getMembers()) +
                ", boards=" + ListingHelpers.elementsToString(getBoards()) +
                '}';
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.BOARD_ADDED_TO_TEAM,board.getName(),name)), LocalDateTime.now()));
    }

    @Override
    public void removeBoard(Board board) {
        if (!boards.contains(board)) {
            throw new InvalidUserInputException(String.format(ModelConstants.BOARD_NOT_EXIST, board.getName()));
        }
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.BOARD_REMOVED_FROM_TEAM,board.getName(),name)), LocalDateTime.now()));
        boards.remove(board);
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.MEMBER_ADDED_TO_TEAM,member.getName(),name)), LocalDateTime.now()));
    }

    @Override
    public void removeMember(Member member) {
        if (!members.contains(member)) {
            throw new InvalidUserInputException(String.format(ModelConstants.MEMBER_NOT_EXIST, member.getName()));
        }
        activityHistories.add(new ActivityHistoryImpl((String.format(ModelConstants.MEMBER_REMOVED_FROM_TEAM,member.getName(),name)), LocalDateTime.now()));
        members.remove(member);
    }

}
