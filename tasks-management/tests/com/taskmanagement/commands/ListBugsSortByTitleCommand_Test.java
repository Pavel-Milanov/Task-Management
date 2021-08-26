package com.taskmanagement.commands;

public class ListBugsSortByTitleCommand_Test {
//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new ListBugsSortByTitleCommand(taskManagementRepository);
//    }
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {ListBugsSortByTitleCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1, ListBugsSortByTitleCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
//    }
//
//
//    @Test
//    public void execute_should_registerUser_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa");
//
//        command.executeCommand(List.of(""));
//        Bug bug =taskManagementRepository.getBugs().get(0);
//        Assertions.assertDoesNotThrow(() -> command.executeCommand(List.of("")));
//
//    }
//
//    @Test
//    public void execute_should_when_passedValidInput() {
//        taskManagementRepository.createMember("aaaaa");
//        taskManagementRepository.createBug("bugtitleeeee","description", Priority.LOW, Severity.CRITICAL, BugStatus.ACTIVE,"aaaaa")
//
//        command.executeCommand(List.of(""));
//        Bug bug = taskManagementRepository.getBugs().get(0);
//        String output = command.executeCommand(List.of(""));
//        Assertions.assertEquals(output,"Bug      : id=2, title: 'bugtitleeeee', description: 'description', Status Active, Priority High, Severity Critical, Assignee aaaaa");
//    }
}
