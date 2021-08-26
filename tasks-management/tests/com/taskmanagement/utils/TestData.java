package com.taskmanagement.utils;

import static com.taskmanagement.constants.ModelConstants.DESCRIPTION_MIN_LENGTH;
import static com.taskmanagement.constants.ModelConstants.TEAM_NAME_MIN_LENGTH;

public class TestData {
    public static class Team {
        public static final String VALID_NAME_TEAM = TestUtilities.initializeStringWithSize(TEAM_NAME_MIN_LENGTH + 1);
    }

    public static class Member {
        public static final String VALID_NAME_MEMBER = TestUtilities.initializeStringWithSize(TEAM_NAME_MIN_LENGTH + 1);
    }

    public static class Board {
        public static final String VALID_NAME_BOARD = TestUtilities.initializeStringWithSize(TEAM_NAME_MIN_LENGTH + 1);
    }

    public static class Item {
        public static final String VALID_NAME_ITEM = TestUtilities.initializeStringWithSize(DESCRIPTION_MIN_LENGTH + 1);
    }
}
