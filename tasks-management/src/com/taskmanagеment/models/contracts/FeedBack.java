package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.FeedBackStatus;

public interface FeedBack extends Task {

    int getRating();
    FeedBackStatus getFeedBackStatus();
}
