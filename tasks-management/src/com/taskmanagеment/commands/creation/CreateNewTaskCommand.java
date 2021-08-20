package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.exceptions.InvalidUserInputException;
import com.taskmanagеment.models.contracts.Board;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.contracts.Team;
import com.taskmanagеment.models.enums.TaskType;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class CreateNewTaskCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Board board = taskManagementRepository.findBoardInTeam(parameters.get(0));
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(1), TaskType.class);
        String title = parameters.get(2);
        String description = parameters.get(3);
        String additionalParam = parameters.get(4);

        if (!type.equals(TaskType.FEEDBACK)) {
            ValidateAssigneeIsMemberOfTeam(board, additionalParam);
        }

        return addTask(board, type, title, description, additionalParam);
    }


    private void ValidateAssigneeIsMemberOfTeam(Board board, String additionalParam) {
        Team team = taskManagementRepository.getTeams().stream()
                .filter(team1 -> team1.getBoards().contains(board)).findAny()
                .orElseThrow(() -> new InvalidUserInputException(String.format(CommandConstants.TEAM_NOT_EXISTS, "invalid team")));

        if (!taskManagementRepository.checkMemberIsFromTeam(additionalParam, team.getName())) {
            throw new InvalidUserInputException(String.format(CommandConstants.USER_NOT_MEMBER, additionalParam, team.getName()));

        }
    }

    private String addTask(Board board, TaskType type, String title, String description, String additionalParam) {
        Task task = createTask(type, title, description, additionalParam);
        taskManagementRepository.getBoard(board).addTask(task);
        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, title);
    }

    private Task createTask(TaskType type, String title, String description, String additionalParam) {
        switch (type) {
            case BUG:
                return taskManagementRepository.createBug(title, description, additionalParam);
            case STORY:
                return taskManagementRepository.createStory(title, description, additionalParam);
            case FEEDBACK:
                return taskManagementRepository.createFeedback(title, description, ParsingHelpers.tryParseInt(additionalParam, CommandConstants.INVALID_FEEDBACK_RATING));
            default:
                throw new IllegalArgumentException(CommandConstants.CANNOT_CREATE_THIS_TYPE_OF_TASK);
        }
    }

}
