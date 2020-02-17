# CSX42: Assignment 2
## Name: Yash Mukeshbhai Shingadiya

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursePlanner/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursePlanner/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursePlanner/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursePlanner/src/build.xml run -Darg0="input.txt" -Darg1="output.txt" 

Notes:

- While passing the values of input and output files from the command line please follow the order and names, as mentioned in the above command. 

-----------------------------------------------------------------------
## Number of slack days used: 1

-----------------------------------------------------------------------
## Description:

The driver code takes given student info as first parameter and calls studentProcessing of the CoursePlannerContext class which in turn calls getReadLine of the FileProcessor class to read the student info file and returns the content back to the CoursePlannerContext class where preprocessing of the students file is done and divides the info into 5 different groups.After that, all these created groups except group 5 are sorted and are used for the main algorithm logic. Apart from this, the algorithm takes care of the dynamic changing of the state and then finally, returns the results to the driver code which in turn calls the methods of Results class and writes the output to the file.

Explanation of waitlist:

In the algorithm, two conditions are strictly followed which are:
(1)Except for group 5 courses, if a course and its pre-requisite/s cannot be assigned to a student in the same semester then add it to waitlist. 
(2)Also, if a course cannot be allowed due to pre-requisites then add it to the wait-list.
Once the course has been added to the waitlist, it is not removed until the current semester is over.So, everytime the input string is parsed, first the waitlist gets checked. If the waitlist has any courses then they will be processed first and those courses will be assigned first only if the above two conditions are not met and then these courses will be removed from their corresponding groups and will also be removed from the waitlist else, they will remain in the waitlist and the parsing of the string will continue. In this way, students get their priority courses first.

Explanation of State Pattern:

There are 5 concrete classes namely CoursePlannerState1,CoursePlannerState2,CoursePlannerState3,CoursePlannerState4 and CoursePlannerState5 which implements CoursePlannerStateI interface. Depending on the condition CoursePlannerContext class uses CoursePlannerStateI interface to change the states accordingly. In this assignment, we only need number of state changes so, nothing is done inside concrete classes because everytime the state gets changed the counter in context class takes care of everything. So, these concrete classes are simply made for demonstrating state pattern and debugging the number of states that got changed and also, to show extensibility of the program in the future.

Note:

- An assumption has been taken that if the any course is missing in the given input file then that course does not exist in real world and will take the alphabetical order considering only the present courses inside the input file.For example, if B is not present in input file then A will become prerequisite of C, C will become prerequisite of D and it will still allocate the courses.If A is not present in input file then B will become prerequisite of C and C will become prerequisite of D.If A,B,C,D all are present then it will follow the alphabetical order A will become prerequiste of B, B will become prerequisite of C and so on. The algorithm only considers the alphabets given inside the input file and does the course planning accordingly.

-----------------------------------------------------------------------
## Data Structures used:

### ArrayList: Mainly used for preprocessing of the file and extracting the contents after reading the file and, also for storing the assigned courses. Apart from that, it is also used for maintaining the waitlist.

-----------------------------------------------------------------------
## References:

### https://www.geeksforgeeks.org: State Pattern, ArrayList, Collection.sort(), split(), regex
### https://stackoverflow.com: read/write operations

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 8th October 2019
Name: Yash Mukeshbhai Shingadiya


