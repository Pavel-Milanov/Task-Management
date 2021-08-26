import com.taskmanagement.core.TaskManagementEngineImpl;

public class StartUp {
    public static void main(String[] args) {

        TaskManagementEngineImpl engine = new TaskManagementEngineImpl();
        engine.start();


    }
}
