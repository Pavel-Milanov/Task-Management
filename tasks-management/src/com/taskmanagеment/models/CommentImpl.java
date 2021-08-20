package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.Comment;

public class CommentImpl implements Comment {

    private String author;
    private String content;

    public CommentImpl(String author, String content) {
        this.author = author;
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
}
