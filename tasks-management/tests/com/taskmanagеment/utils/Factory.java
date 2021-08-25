package com.taskmanagеment.utils;

import com.taskmanagеment.models.BoardImpl;
import com.taskmanagеment.models.MemberImpl;
import com.taskmanagеment.models.TeamImpl;
import com.taskmanagеment.models.contracts.*;
import com.taskmanagеment.models.enums.*;
import com.taskmanagеment.models.tasks.BugImpl;
import com.taskmanagеment.models.tasks.StoryImpl;

public class Factory {
    public static Team createTeam(){
        return new TeamImpl(TestData.Team.VALID_NAME_TEAM);
    }
    public static Board createBoard(){
        return new BoardImpl(1,TestData.Board.VALID_NAME_BOARD);
    }
    public static Member createMember(){
        return new MemberImpl(2,TestData.Team.VALID_NAME_TEAM);
    }
    public static Story createStory(){
        return new StoryImpl(3,"storytitle","storydescription", Priority.HIGH, Size.LARGE, StoryStatus.DONE,TestData.Team.VALID_NAME_TEAM);
    }
    public static Bug createBug(){
        return new BugImpl(4,"bugtitleeeeee","bugdescription",Priority.HIGH, Severity.CRITICAL,BugStatus.ACTIVE,TestData.Team.VALID_NAME_TEAM);
    }
}
