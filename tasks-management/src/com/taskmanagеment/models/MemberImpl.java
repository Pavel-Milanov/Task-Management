package com.taskmanagеment.models;

import com.taskmanagеment.Constants.ModelConstants;
import com.taskmanagеment.Constants.OutputMessages;
import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.Constants.ModelConstants.*;
import static com.taskmanagеment.Constants.OutputMessages.MEMBER_NAME_ERR;

public class MemberImpl extends BaseModelImpl implements Member {




    public MemberImpl(String name) {
        super(name);
    }

    @Override
    protected void validateName(String name) {
        ValidationHelpers.validateInRange(name.length(), MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH, MEMBER_NAME_ERR);
    }

    @Override
    public String getAsString() {
        return null;
    }
}
