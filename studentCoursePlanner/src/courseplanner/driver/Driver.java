package courseplanner.driver;

import courseplanner.state.CoursePlannerContext;
import courseplanner.state.CoursePlannerState1;
import courseplanner.util.Results;

/**
 * @author Yash Shingadiya
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			System.exit(0);
		}
		
		
		/**
		 * Restricting the order and name of the input and output files
		 */
		String input1 = "input.txt";
		String input2 = "output.txt";
		
		if((args[0].equals(input1)) && (args[1].equals(input2))){

			CoursePlannerContext gc = new CoursePlannerContext();
			String finalResults = gc.studentProcessing(args[0],gc);
			
			Results r = new Results();

			/**
			* For writing output to file
			*/
			r.writeToFile(args[1],finalResults);
			
			/**
			* For writing output to screen
			*/
			r.writeToStdout(finalResults);
		
		}	

		else{

			System.err.println("Please enter input file as input.txt and second output file as output.txt");
			System.exit(0);
		}		
		
	}
}
