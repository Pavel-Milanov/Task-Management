package com.taskmanagement.models.contracts;

import java.util.List;

public interface Team extends Printable, Identifiable {

    String getName();

    void addMember(Member member);

    void removeMember(Member member);

    List<Member> getMembers();

    void addBoard(Board board);

    void removeBoard(Board board);

    List<Board> getBoards();

    List<ActivityHistory> getActiveHistory();
}
