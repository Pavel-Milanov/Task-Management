package MyPersonalRepository.core.models;

import java.util.List;

public class BugImpl extends BaseTask {

    private Severity severity;
    private BugStatus bugStatus;
    private List<String> stepsToReproduce;

}
