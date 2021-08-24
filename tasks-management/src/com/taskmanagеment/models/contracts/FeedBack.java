package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.FeedBackStatus;

public interface FeedBack extends WorkingItem {

    int getRating();

    FeedBackStatus getFeedBackStatus();


    void changeFeedbackRating(int rating);

    void changeFeedbackStatus(FeedBackStatus feedBackStatus);
}
