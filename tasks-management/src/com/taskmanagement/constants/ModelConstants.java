package com.taskmanagement.constants;

import static java.lang.String.format;

public class ModelConstants {

    public static final int TEAM_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MAX_LENGTH = 15;
    public static final int MEMBER_NAME_MIN_LENGTH = 5;
    public static final int MEMBER_NAME_MAX_LENGTH = 15;
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final int CONTENT_LEN_MIN = 5;
    public static final int CONTENT_LEN_MAX = 200;
    public static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    public static final String NO_SUCH_ENUM = "There is no '%s' in '%s'.";
    public static final String DESCRIPTION_IS_EMPTY = "Description cannot be empty";
    public static final String COMMENT_ADD_SUCCESSFULLY = "Comment '%s' add successfully";
    public static final String COMMENT_REMOVED_SUCCESSFULLY = "Comment '%s' removed successfully";
    public static final String STEP_ADDED = "Step added : '%s'";
    public final static String TASK_HEADER = "--TASK--";
    public final static String NO_TASK = "--NO TASK--";
    public final static String BUG_STEPS_HEADER = "--BUG STEPS--";
    public final static String NO_BUG_STEPS_HEADER = "--NO BUG STEPS--";
    public static final String CREATED_ITEM = "The working item '%s' has been created successfully";
    public static final String BOARD_NOT_EXIST = "Board with name '%s' not exist!";
    public static final String MEMBER_NOT_EXIST = "Member with name '%s' not exist!";
    public static final String WORKING_ITEM_NOT_EXIST = "Working item with name '%s' not exist!";
    public static final String COMMENT_NOT_EXIST = "Comment: '%s' not exist!";
    public static final String STORY_STATUS_CHANGED = "Story status was changed from '%s' to '%s'";
    public static final String STORY_SIZE_CHANGED = "Size was changed from '%s' to '%s'";
    public static final String FEEDBACK_RATING_CHANGED = "Feedback rating was changed from '%s' to '%s'";
    public static final String FEEDBACK_STATUS_CHANGED = "Feedback status was changed from '%s' to '%s'";
    public static final String BUG_SEVERITY_CHANGED = "Severity was changed from '%s' to '%s'";
    public static final String BUG_STATUS_CHANGED = "Bug status was changed from '%s' to '%s'";
    public static final String TASK_ASSIGNEE_CHANGED = "Assignee changed from '%s' to '%s'.";
    public static final String PRIORITY_CHANGED = "Priority was changed from '%s' to '%s'";
    public static final String TEAM_CREATED = "Team with title %s was created.";
    public static final String BOARD_ADDED_TO_TEAM = "Board with title %s was add to Team %s.";
    public static final String BOARD_REMOVED_FROM_TEAM = "Board with title %s was removed from Team %s.";
    public static final String MEMBER_ADDED_TO_TEAM = "Member with name %s was add to Team %s.";
    public static final String MEMBER_REMOVED_FROM_TEAM = "Member with name %s was removed from Team %s.";
    public static final String MEMBER_REGISTERED = "Member with name %s was registered.";
    public static final String BOARD_CREATED = "Board with name %s was created.";
    public static final String WORKING_ITEM_ADDED_TO_BOARD = "Working Item %s add to %s Board ";
    public static final String WORKING_ITEM_REMOVED_FROM_BOARD = "Working Item %s removed from %s Board ";
}
