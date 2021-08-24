package com.taskmanagеment.models.contracts;

import java.util.List;

public interface Member extends Printable,Identifiable {


    void addTeam(Team team);




    List<Team> getTeamList();

    List<ActivityHistory> getActivityHistories();

    String getName();
}
