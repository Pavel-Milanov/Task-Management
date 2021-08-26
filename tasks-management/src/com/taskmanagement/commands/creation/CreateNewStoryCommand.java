package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Board;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.models.enums.StoryStatus;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreateNewStoryCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewStoryCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        try {
            ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        }catch (IllegalArgumentException exception){
            ValidationHelpers.validateArgumentsCount(parameters,(EXPECTED_NUMBER_OF_ARGUMENTS - 1));
        }
        Board board = taskManagementRepository.findBoard(parameters.get(0));
        String name = parameters.get(1);
        String description = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3),Priority.class );
        Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class );
        StoryStatus status = ParsingHelpers.tryParseEnum(parameters.get(5), StoryStatus.class);
        String assignee = parameters.get(6);
        if (EXPECTED_NUMBER_OF_ARGUMENTS != 7) {
            assignee = "";
        }
        taskManagementRepository.validateAssigneeIsMemberOfTeam(board, assignee);
        return createStory(board,name,description,priority,size,status,assignee);

    }

    private String createStory(Board board, String name, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        Story story = taskManagementRepository.createStory(name,description,priority,size,status,assignee);
        taskManagementRepository.getBoard(board).addTask(story);
        return String.format(CommandConstants.TASK_ADDED_SUCCESSFULLY, name);
    }
}
