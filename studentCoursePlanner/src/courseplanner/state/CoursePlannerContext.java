package courseplanner.state;

import java.util.ArrayList;
import java.util.Collections;
import courseplanner.util.FileProcessor;

public class CoursePlannerContext{

	/**
	 * Using Composition for implementing state pattern
	 */
	CoursePlannerStateI currentState,state1,state2,state3,state4,state5;	;
	
	public int noOfStates = 0;
	public int group1Count = 0;
	public int group2Count = 0;
	public int group3Count = 0;
	public int group4Count = 0;
	public int group5Count = 0;
	public boolean IsGraduated = false;
	public ArrayList<String> coursesInput;
	public ArrayList<String> assignCourses;
	public ArrayList<String> group1;
	public ArrayList<String> group2;
	public ArrayList<String> group3;
	public ArrayList<String> group4;
	public ArrayList<String> group5;

	/**
	 * Initialising inside constructor
	 */
	public CoursePlannerContext(){

		
		state1 = new CoursePlannerState1();
		state2 = new CoursePlannerState2();
		state3 = new CoursePlannerState3();
		state4 = new CoursePlannerState4();
		state5 = new CoursePlannerState5();
	}
	/**
	 * State Pattern Implementation as shown in the state diagram pdf
	 */
	public void statePattern(CoursePlannerStateI state,int s1, int s2, int s3, int s4, int s5){
	
		if((s1>s2) && (s1>s3) && (s1>s4) && (s1>s5)){
			
			if(state != state1){
				currentState = state1;
				this.setState(currentState);
				this.noOfStates++;
			}
		}

		if((s2>s1) && (s2>s3) && (s2>s4) && (s2>s5)){

			if(state != state2){
				currentState = state2;
				this.setState(currentState);
				this.noOfStates++;
			}
		}

		if((s3>s1) && (s3>s2) && (s3>s4) && (s3>s5)){

			if(state != state3){
				currentState = state3;
				this.setState(currentState);
				this.noOfStates++;
			}	
		}	

		if((s4>s1) && (s4>s2) && (s4>s3) && (s4>s5)){

			if(state != state4){
				currentState = state4;
				this.setState(currentState);
				this.noOfStates++;
			}
		}

		if((s5>s1) && (s5>s2) && (s5>s3) && (s5>s4)){

			if(state != state5){
				currentState = state5;
				this.setState(currentState);
				this.noOfStates++;
			}
		}
		
	}
	
	/**
	 * According to the logic the state will be set dynamically
	 */
	public void setState(CoursePlannerStateI state){
      		
  		currentState = state;		
	}

	/**
	 * Created only for debugging purpose
	 */
	public void changeState(){

		currentState.changeState(this);
	}

	/**
	 * Recognizing which course belongs to which group
	 */
	public String recognizeGroup(String course){

		String s = null;
		if(this.group1.contains(course)){
			s = "group1";
		}
		else if(this.group2.contains(course)){
			s = "group2";
		}
		else if(this.group3.contains(course)){
			s = "group3";
		}
		else if(this.group4.contains(course)){
			s = "group4";
		}
		else if(this.group5.contains(course)){
			s = "group5";
		}
		else{
			s = null;
		}
		return s;
	}

	/**
	 * As soon as the semester gets over the assigned course is removed from their corresponding group
	 */
	public void removeAssignedCourseFromGroup(ArrayList assignedCourses){

		for(int i = 0;i < assignedCourses.size();i++){
			if(this.group1.contains(assignedCourses.get(i))){
				this.group1.remove(assignedCourses.get(i));
			}
			if(this.group2.contains(assignedCourses.get(i))){
				this.group2.remove(assignedCourses.get(i));
			}
			if(this.group3.contains(assignedCourses.get(i))){
				this.group3.remove(assignedCourses.get(i));
			}
			if(this.group4.contains(assignedCourses.get(i))){
				this.group4.remove(assignedCourses.get(i));
			}
			if(this.group5.contains(assignedCourses.get(i))){
				this.group5.remove(assignedCourses.get(i));
			}
		}
	}

	/**
	 * Whenever the course is assigned the counter of that particular group will be incremented accordingly
	 */
	public void setGroupCounter(String groupName){

		if(groupName == "group1"){
			this.group1Count++;
		}
		if(groupName == "group2"){
			this.group2Count++;
		}
		if(groupName == "group3"){
			this.group3Count++;
		}
		if(groupName == "group4"){
			this.group4Count++;
		}
		if(groupName == "group5"){
			this.group5Count++;
		}
	}

	/**
	 * returns the corresponding group of that particulr group
	 */
	public ArrayList getGroup(String groupName){

		ArrayList<String> group = null;
		if(groupName == "group1"){
			group = this.group1;
		}
		if(groupName == "group2"){
			group = this.group2;
		}
		if(groupName == "group3"){
			group = this.group3;
		}
		if(groupName == "group4"){
			group = this.group4;
		}
		if(groupName == "group5"){
			group = this.group5;
		}
		return group;
	}

	/**
	 * Checking if the course belongs to same group from which previous course has already been assigned
	 */
	public boolean sameGroup(String course){
	
		for(int j = 0;j < this.assignCourses.size();j++){
			if(this.recognizeGroup(course) == this.recognizeGroup(this.assignCourses.get(j))){

				return true;
			}
		}
		return false;
	}	

	/**
	 * Checking for pending prerequisites.If there are any then will add them to waitlist
	 */
	public boolean pendingPrerequisites(String course){

		if(this.getGroup(this.recognizeGroup(course)).indexOf(course) == 0){

			return false;
		}
		return true;
	}	

	/**
	 * Calling method of FileProcessor to read the student info file
	 */
	public String studentProcessing(String studentsFile,CoursePlannerContext gc){

		FileProcessor fp = new FileProcessor(studentsFile);
	
		int studentId = 0;
		String[] students;
		String[] studentPreferences;
		this.group1 = new ArrayList<String>();
		this.group2 = new ArrayList<String>();
		this.group3 = new ArrayList<String>();
		this.group4 = new ArrayList<String>();
		this.group5 = new ArrayList<String>();
		this.coursesInput = new ArrayList<String>();

		try{

			/**
        	 * Preprocessing of input file
             */     
			String studentsInfo = null;
			
			while ((studentsInfo = fp.readLineFromFile()) != null){

				students = studentsInfo.split(": ");				
				
				/**
				 * Student ID range should be in range of [1000-9999]
				 */
				if(Integer.parseInt(students[0]) < 1000 ||Integer.parseInt(students[0]) > 9999){

					System.err.println("Not a valid student ID. Please enter valid input of student ID within the range of [1000-9999] ");
					System.exit(0);
				}
				
				else{

					studentId = Integer.parseInt(students[0]);	
				}

				studentPreferences = students[1].split(" ");

				/**
				 * The whole input string
				 */
				for(int i = 0;i<studentPreferences.length;i++){
					/**
					 * Using regex to check if any numeric values are present as a course name
					 */
					if(studentPreferences[i].matches("-?\\d+(\\.\\d+)?")){
						
						System.err.println("No numeric courses are allowed in input file");
						System.exit(0);
					}
				
					else{
						if(studentPreferences[i].length() > 1){
							/**
							 * Checking the length of course name
							 */
							System.err.println("Course name should be single alphabetic character");
							System.exit(0);

						}
						else{
							this.coursesInput.add(studentPreferences[i]);	
						}						
					}
				}
				
				/**
				 * Dividing the input string into their corresponding groups
				 */
				for(int i = 0;i<studentPreferences.length;i++){

					if(studentPreferences[i].equals("A")|| studentPreferences[i].equals("B")|| studentPreferences[i].equals("C")|| studentPreferences[i].equals("D")){

						this.group1.add(studentPreferences[i]);
					}
					
					else if(studentPreferences[i].equals("E")|| studentPreferences[i].equals("F")|| studentPreferences[i].equals("G")|| studentPreferences[i].equals("H")){

						this.group2.add(studentPreferences[i]);
					}

					else if(studentPreferences[i].equals("I")|| studentPreferences[i].equals("J")|| studentPreferences[i].equals("K")|| studentPreferences[i].equals("L")){

						this.group3.add(studentPreferences[i]);
					}

					else if(studentPreferences[i].equals("M")|| studentPreferences[i].equals("N")|| studentPreferences[i].equals("O")|| studentPreferences[i].equals("P")){

						this.group4.add(studentPreferences[i]);
					}

					else{

						this.group5.add(studentPreferences[i]);
					}
				}	
				
			}
			
	
			
		}
		
		catch(Exception e){

			System.err.println("Please check the formatting of input file");
			System.exit(0);
		}
		
		finally{}

		/**
		 * Sorting all the groups except because in group 5 order does not matter
		 */
		Collections.sort(this.group1, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(this.group2, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(this.group3, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(this.group4, String.CASE_INSENSITIVE_ORDER);
				
		int noOfSemesters = 0;
		ArrayList<String> waitlist = new ArrayList<String>();
		this.assignCourses = new ArrayList<String>();
		String result = "";
		int noOfCourses = 0;	
		/**
		 * Inititializing to state 1
		 */
		gc.currentState = state1;

		if((this.group1.size() < 2) || (this.group2.size() < 2) || (this.group3.size() < 2) || (this.group4.size() < 2) || (this.group5.size() < 2)){
			

			result = "The student did not graduate"+"\n";
			noOfSemesters = 0;
			result = result + String.valueOf(studentId) + ":" + " ";
			result = result + String.join(" ", this.assignCourses)+" "+"--"+" ";
			result = result + String.valueOf(noOfSemesters)+" ";
			result = result + String.valueOf(gc.noOfStates);
			
			return  result;
		}
		else{

			while(this.IsGraduated == false){
			
				noOfSemesters++;
				/**
				 * Parsing the input string
				 */
				for(int j = 0;j < coursesInput.size(); j++){
							
					if(noOfCourses < 3){
						
						/**
						 * For same semester 
						 */
						if(!this.assignCourses.contains(this.coursesInput.get(j))){
							/**
							 * If the course has pending prerequisites or the course from the same group has already been assigned then adding to the waitlist
							 */
							if((sameGroup(coursesInput.get(j)) &&(this.recognizeGroup(coursesInput.get(j)) != "group5")) || ((this.pendingPrerequisites(coursesInput.get(j))&&(this.recognizeGroup(coursesInput.get(j)) != "group5")))){
								
								if(waitlist.contains(this.coursesInput.get(j))){

									waitlist.remove(this.coursesInput.get(j));
								}
								waitlist.add(this.coursesInput.get(j));
							}
							/**
							 * Exceptional condition for group 5 as the order does not matter
							 */
							else if(this.recognizeGroup(coursesInput.get(j)) == "group5"){

								/**
								 * Terminating condition
								 */
								if ((this.group1Count >= 2) && (this.group2Count >= 2) && (this.group3Count >= 2) && (this.group4Count >= 2) && (this.group5Count >= 2)){
									this.IsGraduated = true;
								}
								else{
									/**
									 * As soon as the course is assigned, the counter of that group gets incremented and the course is removed from
									 * the waitlist as well as from their corresponding group
									 */
									this.assignCourses.add(this.coursesInput.get(j));						
									this.setGroupCounter(this.recognizeGroup(this.coursesInput.get(j)));
									noOfCourses++;
									gc.statePattern(gc.currentState,this.group1Count,this.group2Count,this.group3Count,this.group4Count,this.group5Count);
								}	
							}
							else{

								/**
								 * Terminating condition
								 */
								if ((this.group1Count >= 2) && (this.group2Count >= 2) && (this.group3Count >= 2) && (this.group4Count >= 2) && (this.group5Count >= 2)){
									this.IsGraduated = true;
								}
								else{
									/**
									 * As soon as the course is assigned, the counter of that group gets incremented and the course is removed from
									 * the waitlist as well as from their corresponding group
									 */
									this.assignCourses.add(this.coursesInput.get(j));						
									this.setGroupCounter(this.recognizeGroup(this.coursesInput.get(j)));
									waitlist.remove(this.coursesInput.get(j));
									noOfCourses++;
									gc.statePattern(gc.currentState,this.group1Count,this.group2Count,this.group3Count,this.group4Count,this.group5Count);
									//cpc.changeState();
									//System.out.println(currentState +" "+ this.group1Count+" "+this.group2Count+" "+this.group3Count+" "+this.group4Count+" "+this.group5Count);
								}		
							}		
						}			
					}
				}
			
				/**
				 * Removing already assigned courses from their respective groups so that for next semester
				 * courses from that groups can be allowed
				 */
				this.removeAssignedCourseFromGroup(this.assignCourses);
				noOfCourses = 0;

			}	
		
		}
		/**
		 * If the student graduates then empty the remaining courses from the waitlist
		 */
		waitlist.clear();

			
		/**
		 * If the student does not meet the requirements then he will not graduate and the no of semesters will be set to 0
		 */
		if((this.assignCourses.size() < 10) || (this.IsGraduated == false)){

			result = "The student did not graduate"+"\n";
			noOfSemesters = 0;
			result = result + String.valueOf(studentId) + ":" + " ";
			result = result + String.join(" ", this.assignCourses)+" "+"--"+" ";
			result = result + String.valueOf(noOfSemesters)+" ";
			result = result + String.valueOf(gc.noOfStates);
			
			return result;
		}

		else{
		
			result = "The student graduated"+"\n";
			noOfSemesters = noOfSemesters;
		}

		/**
		 * Final result to be written
		 */
		result = result + String.valueOf(studentId) + ":" + " ";
		result = result + String.join(" ", this.assignCourses)+" "+"--"+" ";
		result = result + String.valueOf(noOfSemesters)+" ";
		result = result + String.valueOf(gc.noOfStates);
		
		return result;
			
	}
	
} 