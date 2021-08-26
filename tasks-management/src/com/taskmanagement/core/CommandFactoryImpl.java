package com.taskmanagement.core;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.commands.creation.*;
import com.taskmanagement.commands.enums.CommandType;
import com.taskmanagement.constants.CoreConstants;
import com.taskmanagement.core.contacts.CommandFactory;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case ADDCOMMENTTOTASK:
                return new AddCommentTaskCommand(taskManagementRepository);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeamCommand(taskManagementRepository);
            case ADDNEWBOARDINTEAM:
                return new AddNewBoardInTeamCommand(taskManagementRepository);
            case ADDSTEPSTOBUG:
                return new AddStepsToBugCommand(taskManagementRepository);
            case CHANGEASSIGNEE:
                return new ChangeAssigneeCommand(taskManagementRepository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriorityCommand(taskManagementRepository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverityCommand(taskManagementRepository);
            case CHANGEBUGSTATUS:
                return new ChangeBugStatusCommand(taskManagementRepository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            case CHANGEFEEDBACKSTATUS:
                return new ChangeFeedbackStatusCommand(taskManagementRepository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriorityCommand(taskManagementRepository);
            case CHANGESTORYSIZE:
                return new ChangeStorySizeCommand(taskManagementRepository);
            case CHANGESTORYSTATUS:
                return new ChangeStoryStatusCommand(taskManagementRepository);
            case CREATENEWMEMBER:
                return new CreateMemberCommand(taskManagementRepository);
            case CREATENEWBOARD:
                return new CreateNewBoardCommand(taskManagementRepository);
            case CREATENEWBUG:
                return new CreateNewBugCommand(taskManagementRepository);
            case CREATENEWFEEDBACK:
                return new CreateNewFeedbackCommand(taskManagementRepository);
            case CREATENEWSTORY:
                return new CreateNewStoryCommand(taskManagementRepository);
            case CREATENEWTEAM:
                return new CreateNewTeamCommand(taskManagementRepository);
            case LISTALLTASKSFILTERBYTITLE:
                return new ListAllTasksFilterByTitleCommand(taskManagementRepository);
            case LISTALLTASKSSORTBYTITLE:
                return new ListAllTasksSortByTitle(taskManagementRepository);
            case LISTBUGSBYASSIGNEE:
//                return new ListBugsByAssigneeCommand(taskManagementRepository);
            case LISTBUGSFILTERBYASSIGNEE:
                return new ListBugsFilterByAssigneeCommand(taskManagementRepository);
            case LISTBUGSFILTERBYSTATUSANDASSIGNEE:
                return new ListBugsFilterByStatusAndAssigneeCommand(taskManagementRepository);
            case LISTBUGSFILTERBYSTATUS:
                return new ListBugsFilterByStatusCommand(taskManagementRepository);
            case LISTBUGSSORTBYPRIORITY:
                return new ListBugsSortByPriorityCommand(taskManagementRepository);
            case LISTBUGSSORTBYSEVERITY:
                return new ListBugsSortBySeverityCommand(taskManagementRepository);
            case LISTBUGSSORTBYTITLE:
                return new ListBugsSortByTitleCommand(taskManagementRepository);
            case LISTFEEDBACKSSORTBYRATING:
                return new ListFeedbacksSortByRatingCommand(taskManagementRepository);
            case LISTFEEDBACKSSORTBYTITLE:
                return new ListFeedbacksSortByTitleCommand(taskManagementRepository);
            case LISTSTORIESFILTERBYSTATUSANDASSIGNEE:
                return new ListStoriesFilterByStatusAndAssigneeCommand(taskManagementRepository);
            case LISTSTORIESSORTBYPRIORITY:
                return new ListStoriesSortByPriorityCommand(taskManagementRepository);
            case LISTSTORIESSORTBYSIZE:
                return new ListStoriesSortBySizeCommand(taskManagementRepository);
            case LISTSTORIESSORTBYTITLE:
                return new ListStoriesSortByTitleCommand(taskManagementRepository);
            case LISTSTORYBYASSIGNEE:
                return new ListStoryByAssigneeCommand(taskManagementRepository);
            case LISTTASKSWITHASSIGNEEFILTERBYASSIGNEE:
                return new ListTasksWithAssigneeFilterByAssigneeCommand(taskManagementRepository);
            case LISTTASKSWITHASSIGNEEFILTERBYBUGSTATUSANDASSIGNEE:
              //  return new ListTasksWithAssigneeFilterByBugStatusAndAssigneeCommand(taskManagementRepository);
            case LISTTASKSSWITHASSIGNEEFILTERBYBUGSTATUS:
               // return new ListTasksWithAssigneeFilterByBugStatusCommand(taskManagementRepository);
            case LISTTASKSWITHASSIGNEEFILTERBYSTORYSTATUSANDASSIGNEE:
              //  return new ListTasksWithAssigneeFilterByStoryStatusAndAssigneeCommand(taskManagementRepository);
            case LISTTASKSSWITHASSIGNEEFILTERBYSTORYSTATUS:
                // return new ListTasksWithAssigneeFilterByStoryStatusCommand(taskManagementRepository);
            case LISTTASKSWITHASSIGNEESORTBYTITLE:
                return new ListTasksWithAssigneeSortByTitleCommand(taskManagementRepository);
            case REMOVEASSIGNEE:
              //  return new RemoveAssigneeCommand(taskManagementRepository);
            case REMOVEBOARD:
                return new RemoveBoardCommand(taskManagementRepository);
            case REMOVECOMMENTFROMTASK:
                return new RemoveCommentFromTaskCommand(taskManagementRepository);
            case REMOVEMEMBER:
               // return new RemoveMemberCommand(taskManagementRepository);
            case REMOVETASK:
                return new RemoveTaskCommand(taskManagementRepository);
            case REMOVETEAM:
              //  return new RemoveTeamCommand(taskManagementRepository);
            case SHOWACTIVITYBOARD:
                return new ShowActivityBoardCommand(taskManagementRepository);
            case SHOWACTIVITYMEMBER:
                return new ShowActivityMemberCommand(taskManagementRepository);
            case SHOWACTIVITYTASK:
                return new ShowActivityTaskCommand(taskManagementRepository);
            case SHOWACTIVITYTEAM:
                return new ShowActivityTeamCommand(taskManagementRepository);
            case SHOWALLMEMBERS:
                return new ShowAllMembersCommand(taskManagementRepository);
            case SHOWALLTEAMBOARDS:
                //return new ShowAllTeamBoardsCommand(taskManagementRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamsCommand(taskManagementRepository);
            case SHOWBUGSTEPS:
                return new ShowBugStepsCommand(taskManagementRepository);
            case SHOWTASKALLINFO:
                return new ShowTaskAllInfoCommand(taskManagementRepository);
            case SHOWTASKSFROMBOARDID:
                return new ShowTaskFromBoardIdCommand(taskManagementRepository);
            case UNASSIGNTASKTOMEMBER:
                return new UnAssignTaskFromMemberCommand(taskManagementRepository);
            default:
                throw new IllegalArgumentException(String.format(CoreConstants.INVALID_COMMAND, commandType));
        }

    }
}
