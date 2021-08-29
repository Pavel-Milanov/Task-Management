package com.taskmanagement.models;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.removable.RemoveBoardCommand;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.TaskManagementRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TeamImpl_Test {
    private TaskManagementRepository taskManagementRepository;
    private TaskManagementHelperRepositoryImpl helperRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", Locale.US);
    private LocalDateTime timestamp = LocalDateTime.now();
    @BeforeEach
    public void before() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Test
    public void should_getId_when_valid() {
        Team team = new TeamImpl(1,"team1");
        Assertions.assertEquals(1,team.getId());
    }

    @Test
    public void should_getActiveHistory_when_valid() {
        Team team = new TeamImpl(1,"team1");
        Assertions.assertEquals("[" + LocalDateTime.now().format(formatter) + "]" + " Team with title team1 was created.", ListingHelpers.elementsToString(team.getActiveHistory()));
    }

    @Test
    public void should_removeBoard_when_valid() {
        Team team = taskManagementRepository.createTeam("team1");
        Board board = taskManagementRepository.createBoard("board1");
        helperRepository.addBoardToTeam(board,team);
        team.removeBoard(board);
        Assertions.assertEquals(0,team.getBoards().size());
    }

    @Test
    public void should_removeMember_when_valid() {
        Team team = taskManagementRepository.createTeam("team1");
        Member member = taskManagementRepository.createMember("member1");
        helperRepository.addMemberToTeam(member,team);
        team.removeMember(member);
        Assertions.assertEquals(0,team.getMembers().size());
    }

}
