package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Story;
import com.taskmanagement.models.enums.Size;
import com.taskmanagement.utils.ListingHelpers;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesSortBySizeCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ListStoriesSortBySizeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(0), Size.class);
        return listBugs(size);
    }

    private String listBugs(Size size) {
        List<Story> storiesFilter;
        storiesFilter = taskManagementRepository.getStories().stream()
                .filter(story -> story.getSize().equals(size)).collect(Collectors.toList());
        return ListingHelpers.elementsToString(storiesFilter);

    }
}
