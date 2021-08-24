package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;


public class AddMemberToTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public AddMemberToTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        String teamName = parameters.get(1);


        return addMemberToTeam(memberName,teamName);
    }

    private String addMemberToTeam(String memberName, String teamName) {

        if (!taskManagementRepository.teamExist(teamName)){
            throw new InvalidUserInputException(String.format(TEAM_NOT_EXISTS,teamName));
        }

        if (!taskManagementRepository.memberExist(memberName)){
            throw new InvalidUserInputException(String.format(MEMBER_NOT_EXISTS,teamName));
        }
        Member member = taskManagementRepository.findByMemberName(memberName);
        Team team = taskManagementRepository.findByTeamName(teamName);

        taskManagementRepository.addMemberToTeam(member,team);

        return String.format(CommandConstants.MEMBER_ADDED_TO_TEAM_SUCCESSFULLY,memberName,team.getName());
    }
}

