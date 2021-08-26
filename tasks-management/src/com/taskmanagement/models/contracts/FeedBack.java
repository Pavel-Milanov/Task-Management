package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.FeedBackStatus;

public interface FeedBack extends WorkingItem {

    int getRating();

    FeedBackStatus getFeedBackStatus();


    void changeFeedbackRating(int rating);

    void changeFeedbackStatus(FeedBackStatus feedBackStatus);
}
