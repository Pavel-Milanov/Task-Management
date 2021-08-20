package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.MEMBER_NAME_MAX_LENGTH;
import static com.taskmanagеment.constants.ModelConstants.MEMBER_NAME_MIN_LENGTH;
import static com.taskmanagеment.constants.OutputMessages.MEMBER_NAME_ERR;

public class MemberImpl extends BaseModelImpl implements Member {

    private final List<Team> teamList = new ArrayList<>();


    public MemberImpl(int id, String name) {
        super(id, name);
    }

    @Override
    protected void validateName(String name) {
        ValidationHelpers.validateInRange(name.length(), MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH, MEMBER_NAME_ERR);
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
}
