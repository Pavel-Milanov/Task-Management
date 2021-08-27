package com.taskmanagement.models.contracts;

import java.util.List;

public interface Member extends Printable, Identifiable {

    List<ActivityHistory> getActivityHistories();

    String getName();
}
