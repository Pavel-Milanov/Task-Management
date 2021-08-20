package com.taskmanagеment.Constants;

import static com.taskmanagеment.Constants.ModelConstants.*;
import static com.taskmanagеment.Constants.ModelConstants.DESCRIPTION_MAX_LENGTH;
import static java.lang.String.format;

public class OutputMessages {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";

    public static final String BOARD_NAME_ERR = format(
            "Board name length must be between %d and %d symbols!",
            BOARD_NAME_MIN_LENGTH,BOARD_NAME_MAX_LENGTH);
    public static final String MEMBER_NAME_ERR = format(
            "Member name length must be between %d and %d symbols!",
            MEMBER_NAME_MIN_LENGTH,MEMBER_NAME_MAX_LENGTH);
    public static final String TEAM_NAME_ERR = format(
            "Team name length must be between %d and %d symbols!",
            TEAM_NAME_MIN_LENGTH,TEAM_NAME_MAX_LENGTH);
    public static final String TITLE_ERR = format(
            "Title must contains between %d and %d symbols!",
            TITLE_MIN_LENGTH,TITLE_MAX_LENGTH);
    public static final String DESCRIPTION_ERR = format(
            "Description must contains between %d and %d symbols!",
            DESCRIPTION_MIN_LENGTH,DESCRIPTION_MAX_LENGTH);
}
