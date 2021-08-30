package com.taskmanagement.models;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.contracts.Comment;
import com.taskmanagement.utils.ValidationHelpers;

import static com.taskmanagement.constants.ModelConstants.*;

public class CommentImpl implements Comment {

    private String content;
    private String author;

    public CommentImpl(String content, String author) {
        setContent(content);
        this.author = author;
    }


    public void setContent(String content) {
        ValidationHelpers.validateInRange(content.length(), CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }


    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAsString() {
        return String.format("%s , author: %s", getContent(), getAuthor());
    }
}
