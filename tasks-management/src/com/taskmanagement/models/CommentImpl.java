package com.taskmanagement.models;

import com.taskmanagement.models.contracts.Comment;

public class CommentImpl implements Comment {

    private final String content;
    private final String author;

    public CommentImpl(String content, String author) {
        this.content = content;
        this.author = author;
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
