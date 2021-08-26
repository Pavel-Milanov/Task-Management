package com.taskmanagement.models.contracts;

public interface Comment extends Printable {

    String getAuthor();

    String getContent();
}
