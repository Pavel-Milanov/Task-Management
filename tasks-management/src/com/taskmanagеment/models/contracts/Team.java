package com.taskmanagеment.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();
    void addMember(Member member);
    void removeMember(Member member);
    List<Member> getMembers();
    void addBoard(Board board);
    void removeBoard(Board board);
    List<Board> getBoards();
}
