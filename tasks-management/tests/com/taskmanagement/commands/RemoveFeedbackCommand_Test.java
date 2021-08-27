package com.taskmanagement.commands;

public class RemoveFeedbackCommand_Test {

//    private TaskManagementRepository taskManagementRepository;
//    private Command command;
//
//    @BeforeEach
//    public void before() {
//        this.taskManagementRepository = new TaskManagementRepositoryImpl();
//        this.command = new RemoveFeedbackCommand(taskManagementRepository);
//    }
//
//
//    @ParameterizedTest(name = "with arguments count: {0}")
//    @ValueSource(ints = {RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, RemoveBoardCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
//    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
//        // Arrange
//        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(arguments));
//    }
//
//    @Test
//    public void execute_should_removeBoard_when_passedValidInput() {
//        FeedBack feedBack = taskManagementRepository.createFeedback("board1","desssssssssccccripton",15, FeedBackStatus.NEW);
//
//        command.executeCommand(List.of("1"));
//        Assertions.assertEquals(0, taskManagementRepository.getFeedBacks().size());
//
//    }
//
//    @Test
//    public void execute_should_throwException_when_listIsEmpty() {
//
//        Assertions.assertThrows(InvalidUserInputException.class,()-> command.executeCommand(List.of("1")));
//    }
}
