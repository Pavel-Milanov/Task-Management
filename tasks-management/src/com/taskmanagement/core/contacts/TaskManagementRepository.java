package com.taskmanagement.core.contacts;

import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {

    List<Team> getTeams();

    List<Member> getMembers();

    List<Board> getBoards();

    List<Story> getStories();

    List<Bug> getBugs();

    List<FeedBack> getFeedBacks();

    Bug createBug(String name, String description, Priority priority, Severity severity, BugStatus status, String assignee);

    Story createStory(String name, String description, Priority priority, Size size, StoryStatus status, String assignee);

    FeedBack createFeedback(String name, String description, int rating, FeedBackStatus feedBackStatus);

    Board createBoard(String boardName);

    Team createTeam(String teamName);

    Member createMember(String memberName);

    void removeBoard(String boardName);

    void removeTeam(String teamName);

    void removeBug(Bug bug);

    void removeStory(Story story);

    void removeMember(Member member);
}
