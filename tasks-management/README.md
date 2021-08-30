# Tasks Management

# Getting started with the Tasks Management System

#### Learn the foundations for using the Tasks Management System, starting with authentication and some endpoint examples.

#### Let's walk through system concepts as we tackle some everyday use cases.

## Overview

Task management console app makes it easy to plan and manage your work day. You can perform actions such as
creating/deleting tasks.

## Commands

### Description of the commands input:

> `commandType~first parameter~second parameter~third parameter`<br />
> Parameters size depend on commands, they expected different parameters.

#### Register member:

> `createNewMember~member name/username`<br />
> Command name then we need the member name/username, name is unique in the application.

#### Create board:

> `createNewBoard~board name`<br />
> Command name then we need the board name, name is unique in team.

#### Create team:

> `createNewTeam~team name`<br />
> Command name then we need the team name, name is unique in the application

#### Create bug:

> `createNewBug~boardID~bug title~bug description~priority~size~status`<br />
> `createNewBug~boardID~bug title~bug description~priority~size~status~assignee`<br />
> Command name then we need the boardID where we need to attach the bug. The bug title and description must be
> between 10 and 50 symbols. The assignee must be part of the team and may be assigned after
> creation of the bug.

#### Create feedback:

> `createNewFeedback~boardID~feedback title~feedback description~rating~status`<br />
> Command name then we need the boardID where we need to attach the feedback. The feedback title and description must be
> between 10 and 50 symbols. Feedback do not have assignee.

#### Create story:

> `createNewStory~boardID~story title~story description~priority~size~status`<br />
> `createNewStory~boardID~story title~story description~priority~size~status~assignee`<br />
> Command name then we need the boardID where we need to attach the story. The story title and description must be
> between 10 and 50 symbols. The assignee must be part of the team and may be assigned after
> creation of the story.

#### Add comment to Bug/Feedback/Story:

> `addCommentToTask~1~content of comment~author name`<br />
> Command name then we need the ID of the task and content & author.

#### Add member to team:

> `addMemberToTeam~member name/username~team name`<br />
> Command name then we need the member name/username and team name.

#### Add board to team:

> `addBoardToTeam~boardID~team name`<br />
> Command name then we need the ID of board and team name.

#### Add steps to bug:

> `addStepsToBug~bugID~member name/username~steps`<br />
> Command name then we need the ID of bug, member name/username and steps.<br />

#### Steps example:

> "1. Open the application; 2. Click "Log In"; 3. The application freezes!"<br />
> When is read ";" application insert new line.

#### Change label of task:

> `changeBugPriority~bugID~levelOfTypeChanged`<br />
> For the example we choose bug Priority. The task id is 4.
> We want change the label to low so the input be like:<br />
> `changeBugPriority~4~low`<br />
> And for the rest is similar.

#### List tasks by title:

> `listBugsSortByTitle`<br />
> For the example we choose to list bugs by title. We do not need
> the id, because the command is listing all bugs.
> And for the rest is similar when we sort one type of task.

#### List tasks by title:

> `listTasksWithAssigneeFilterByAssignee~member name/username`<br />
> For the example we choose to list task with assignee filter by assignee.
> We need to say which member we want so search by and write it in the input.
> We do not need the id, because the command is listing all bugs.
> And for the rest is similar when we sort one type of task.
> When we have filtered task by two arguments the input be like:
> `listTasksWithAssigneeFilterByAssignee~bug status~member name/username~`<br />

#### Remove object:

> `removeObject~objectId`<br />
> For the example we choose remove board.
> We need to say which we want to remove and write it in the input.<br />
> `removeBoard~1`<br />
> And board with id 1 will be removed.
> And for the rest is similar when we sort one type of task.

#### Show object:

> `showObject~objectId`<br />
> For the example we choose show activity board.
> We need to say which we want to show activity and write it in the input.<br />
> `showActivityBoard~1`<br />
> And board with id 1 will be shown.
> And for the rest is similar when we sort one type of task.

### Example Input:

> `createNewBoard~Tasks`<br />
> `createNewMember~Peter`<br />
> `createNewTeam~Team11`<br />
> `addNewBoardInTeam~1~Team11`<br />
> `addMemberToTeam~Peter~Team11`<br />
> `createNewStory~1~The program freezes is open~Work on first problem~high~large~inprogress~Peter`<br />
> `showTaskAllInfo~4`<br />
> `createNewBug~1~The program freezes~This needs to be fixed quickly!~high~critical~active~Peter`<br />
> `showTaskAllInfo~5`<br />
> `addCommentToTask~5~proba proba comment to add~Peter`<br />
> `showTaskAllInfo~5`<br />
> `changeBugPriority~5~low`<br />
> `changeAssignee~5~Peter`<br />
> `addStepsToBug~5~Peter~1. Open the application; 2. Click "Log In"; 3. The application freezes!`<br />
> `showTaskAllInfo~5`<br />
> `showTasksFromBoardID~1`<br />
> `showBugSteps~5`<br />

### Example Output:

> Board with name 'Board One' was created!
>###### ##############
>Member with name 'First Member' was created!
>###### ##############
>Team with name 'Team One' was created!
>###### ##############
>Board with name 'Board One' add to team 'Team One'!
>###### ##############
>'First Member' add to team 'Team One'!
>###### ##############
>'title story' added to Board successfully!
>###### ##############
>Story    : id=4, name: 'title story', description: 'story long description', Status Not Done, Size Medium, Priority: High, Assignee: First Member<br />
> ---HISTORY---<br />
> [29-August-2021 11:59:25] The working item 'title story' has been created successfully
>###### ##############
>'bug titleeeee' added to Board successfully!
>###### ##############
>Bug      : id=5, name: 'bug titleeeee', description: 'bug descriptionnnnn', Bug Status Active, Severity Critical, Priority: High, Assignee: --NO ASSIGNEE--<br />
> ---HISTORY---<br />
> [29-August-2021 11:59:25] The working item 'bug titleeeee' has been created successfully
>###### ##############
>'First Member' added comment successfully!
>###### ##############
>Bug      : id=5, name: 'bug titleeeee', description: 'bug descriptionnnnn', Bug Status Active, Severity Critical, Priority: High, Assignee: --NO ASSIGNEE--<br />
> ---COMMENTS---<br />
> proba proba comment to add , author: First Member<br />
> ---HISTORY---<br />
> [29-August-2021 11:59:25] The working item 'bug titleeeee' has been created successfully<br />
> [29-August-2021 11:59:25] Comment 'proba proba comment to add' add successfully
>###### ##############
>Priority of 'bug titleeeee' changed successfully.
>###### ##############
>Assignee changed to 'First Member'.
>###### ##############
>Step was add to bug with name 'bug titleeeee'.
>###### ##############
>Bug      : id=5, name: 'bug titleeeee', description: 'bug descriptionnnnn', Bug Status Active, Severity Critical, Priority: Low, Assignee: First Member<br />
> ---COMMENTS---<br />
> proba proba comment to add , author: First Member<br />
> ---HISTORY---<br />
> [29-August-2021 11:59:25] The working item 'bug titleeeee' has been created successfully<br />
> [29-August-2021 11:59:25] Comment 'proba proba comment to add' add successfully<br />
> [29-August-2021 11:59:25] Priority was changed from 'High' to 'Low'<br />
> [29-August-2021 11:59:25] Assignee changed from '--NO ASSIGNEE--' to 'First Member'.<br />
> [29-August-2021 11:59:25] Step added : '[1. Open the application; 2. Click "Log In"; 3. The application freezes!]'
>###### ##############
>--TASK--<br />
> Story    : id=4, name: 'title story', description: 'story long description', Status Not Done, Size Medium, Priority: High, Assignee: First Member<br />
> Bug      : id=5, name: 'bug titleeeee', description: 'bug descriptionnnnn', Bug Status Active, Severity Critical, Priority: Low, Assignee: First Member
>###### ##############
>--BUG STEPS--
>1. Open the application<br />
>2. Click "Log In"<br />
>3. The application freezes!<br />
>###### ##############
