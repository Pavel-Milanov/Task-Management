package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.exceptions.InvalidUserInputException;
import com.taskmanagement.models.contracts.Member;
import com.taskmanagement.models.contracts.Team;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.MEMBER_NOT_EXISTS;
import static com.taskmanagement.constants.CommandConstants.TEAM_NOT_EXISTS;


public class AddMemberToTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public AddMemberToTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        String teamName = parameters.get(1);


        return addMemberToTeam(memberName, teamName);
    }

    private String addMemberToTeam(String memberName, String teamName) {

        if (!taskManagementRepository.teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(TEAM_NOT_EXISTS, teamName));
        }

        if (!taskManagementRepository.memberExist(memberName)) {
            throw new InvalidUserInputException(String.format(MEMBER_NOT_EXISTS, teamName));
        }
        Member member = taskManagementRepository.findByMemberName(memberName);
        Team team = taskManagementRepository.findByTeamName(teamName);

        taskManagementRepository.addMemberToTeam(member, team);

        return String.format(CommandConstants.MEMBER_ADDED_TO_TEAM_SUCCESSFULLY, memberName, team.getName());
    }
}
