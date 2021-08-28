package com.taskmanagement.core;

import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.BoardImpl;
import com.taskmanagement.models.MemberImpl;
import com.taskmanagement.models.TeamImpl;
import com.taskmanagement.models.contracts.*;
import com.taskmanagement.models.enums.*;
import com.taskmanagement.models.tasks.BugImpl;
import com.taskmanagement.models.tasks.FeedBackImpl;
import com.taskmanagement.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private final List<Team> teams;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<Story> stories;
    private final List<Bug> bugs;
    private final List<FeedBack> feedBacks;
    private int nextId;

    public TaskManagementRepositoryImpl() {
        nextId = 0;
        this.teams = new ArrayList<>();
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.feedBacks = new ArrayList<>();

    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
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
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<FeedBack> getFeedBacks() {
        return new ArrayList<>(feedBacks);
    }

    @Override
    public Member createMember(String memberName) {
        Member member = new MemberImpl(++nextId, memberName);
        this.members.add(member);
        return member;
    }

    @Override
    public Bug createBug(String name, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        Bug bug = new BugImpl(++nextId, name, description, priority, severity, status, assignee);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String name, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        Story story = new StoryImpl(++nextId, name, description, priority, size, status, assignee);
        this.stories.add(story);
        return story;
    }


    @Override
    public FeedBack createFeedback(String name, String description, int rating, FeedBackStatus feedBackStatus) {
        FeedBack feedback = new FeedBackImpl(++nextId, name, description, rating, feedBackStatus);
        this.feedBacks.add(feedback);
        return feedback;
    }


    @Override
    public Board createBoard(String boardName) {
        Board board = new BoardImpl(++nextId, boardName);
        boards.add(board);
        return board;
    }

    @Override
    public Team createTeam(String teamName) {
        Team team = new TeamImpl(++nextId, teamName);
        teams.add(team);
        return team;
    }


    @Override
    public void removeBoard(Board board) {
        boards.removeIf(board::equals);
    }

    @Override
    public void removeTeam(String teamName) {
        teams.removeIf(team -> team.getName().equals(teamName));
    }

    @Override
    public void removeBug(Bug bug) {
        bugs.removeIf(bug::equals);
    }

    @Override
    public void removeStory(Story story) {
        stories.removeIf(story::equals);
    }

    @Override
    public void removeMember(Member member) {
        members.removeIf(member::equals);
    }

}

