package com.taskmanagеment.models.contracts;

import java.util.List;

public interface Member extends BaseModel, Printable {


    void addTeam(Team team);

    List<Team> getTeamList();
}
