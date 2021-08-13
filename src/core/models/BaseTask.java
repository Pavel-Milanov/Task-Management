package core.models;

import javax.xml.stream.events.Comment;
import java.lang.reflect.Member;
import java.util.List;

public class BaseTask {

    private int id;
    private String title;
    private String description;
   // private Priority priority;
    private Member member; // here should be assignee
    private List<Comment> comments;
    private List<String> changesHistory;


    public enum Priority {


        HIGH,
        MEDIUM,
        LOW;
    }
}
