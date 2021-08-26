package com.taskmanagement.utils;

import com.taskmanagement.models.BoardImpl;
import com.taskmanagement.models.MemberImpl;
import com.taskmanagement.models.TeamImpl;
import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.models.tasks.BugImpl;
import com.taskmanagement.models.tasks.StoryImpl;

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
