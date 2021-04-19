import java.util.*;
import java.text.*;
import java.io.*;

/**
 * Poised is a subclass that inherits methods from the InputCheck superclass.
 * <p>
 * This class runs the main program method and calls other methods from the
 * project, people, CreatePerson and SavedProjects classes. It displays a menu
 * to the user with options for the Poised Management System.
 * 
 * @author Quinton Amos
 */
public class Poised extends InputCheck {
	/**
	 * This is the main method which runs the program.
	 * <p>
	 * 
	 * @param args java command line arguments
	 * @throws ParseException occurs if a date string is in the incorrect format
	 */
	public static void main(String[] args) throws ParseException {
		/*
		 * A welcome message is displayed to the user. Thereafter, the system creates a
		 * 'projects.txt.' file to store project info, if it can't find the file or the
		 * file does not exist.
		 */
		System.out.println("Welcome to Poised!");

		try {
			File file = new File("projects.txt");
			// if file doesn't exist it will create a new File
			if (file.createNewFile()) {
				System.out.println("File Created: " + file.getName());

			} else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		/*
		 * A menu pops up displaying different options. A file check is run to check if
		 * there are existing projects and, if not, the user is asked to choose option
		 * '2' to create a new project object. A number of methods from the projects,
		 * CreatePerson, people, and SavedProjects classes are called on, depending on
		 * the user's selected option. For example, the newProject() method from project
		 * class is called on for option '2'. The user will return to the menu after
		 * each option, unless they enter "99" to exit the program.
		 */
		while (true) {
			System.out.println("\nPlease Enter a number from menu below: " + "\n1 : View all created Projects"
					+ "\n2 : Create a New Project" + "\n3 : Edit Existing Project Details" + "\n4 : Finalize a Project"
					+ "\n5 : View all Incomplete Projects" + "\n6 : View all Overdue Projects" + "\n7 : Find a Project"
					+ "\n8 : Create and Save Person" + "\n99: Exit");

			int userInput = intCheck("menu option"); // Checking if user entered an integer from the PoisedInputChecks
														// class.
			boolean projects = fileCheck();

			if ((projects == false) && ((userInput == 1) || (userInput == 3) || (userInput == 4) || (userInput == 5)
					|| (userInput == 6) || (userInput == 7))) {
				System.out.println("There are no projects. \nPlease Enter option 2 to create a new project.");

			} else if ((projects == true) && (userInput == 1)) { // If user enters "1" they can view all projects in
																	// "projects.txt"
				SavedProjects view = new SavedProjects();
				view.viewProject();
				//
			} else if (userInput == 2) { // If user enter "2" they can create a new project
				project create = new project();
				create.newProject();

			} else if ((projects == true) && (userInput == 3)) { // if user enters "3" they can edit a project of their
																	// choosing
				System.out.println(
						"Please enter Project number of the project that you which you would like to edit: \n ");
				String projectDetails = checkString("project number");
				System.out.println(
						"What would you like to edit: " + "\n1 : Edit Due Date" + "\n2 : Edit total fees paid to date");

				int option = intCheck("edit option");
				SavedProjects edit = new SavedProjects();
				int lineNum = edit.searchProject(projectDetails);
				edit.editProject(option, lineNum);

			} else if ((projects == true) && (userInput == 4)) { // If user enters "4" the can finalize a project
				System.out.println("Please enter the project number or name of the project you wish to finalise: \n");
				String projectDetails = checkString("project name OR number");
				SavedProjects object = new SavedProjects();
				int lineNum = object.searchProject(projectDetails);
				object.finalising(lineNum);

			} else if ((projects == true) && (userInput == 5)) { // If user enters "5" they can view all in complete
																	// task that are not over due
				SavedProjects incomplete = new SavedProjects();
				incomplete.checkDate("incomplete");

			} else if ((projects == true) && (userInput == 6)) {// if user enters "6" they can view all overdue tasks
				SavedProjects overDue = new SavedProjects();
				overDue.checkDate("overdue");

			} else if ((projects == true) && (userInput == 7)) {// if user enters "7" they can search for a project
				System.out.println("Enter project number to find project: ");
				String projectInfo = checkString("project name OR number");
				SavedProjects search = new SavedProjects();
				int lineCount = search.searchProject(projectInfo);

				String[] details = new String[10]; // Setting a string array to store info from the project's line in
				// the text file.
				int findLine = 0;

				try {
					File searchFile = new File("projects.txt");
					Scanner findProject = new Scanner(searchFile);

					while (findProject.hasNextLine()) { // Searching for the correct project in the file by line count.

						if (findLine == lineCount) {
							details = findProject.nextLine().split(", "); // Storing the correct line info into the
							// array.

						} else {
							findLine++;

						}
					}

				} catch (FileNotFoundException e) {
					System.out.println("ERROR");

				}
				String projectNum = details[0];
				String projectName = details[1];
				String buildingType = details[2];
				String address = details[3];
				String erf = details[4];
				String totalFee = details[5];
				String amountPaid = details[6];
				String dueDate = details[7];
				String complete = details[8];
				String finalised = details[9];

				System.out.println("\nProject Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address + "\nERF Number: " + erf
						+ "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid + "\nDeadline: " + dueDate
						+ "\nCompletion Date: " + complete + "\nProject Status: " + finalised);

			} else if ((projects == true) && (userInput == 8)) { // if user enters "8" they can create a person
				CreatePerson create = new CreatePerson();
				create.newPerson();

			} else if (userInput == 99) { // If user enters "99" they can exit the program.
				System.out.println("Exiting");
				break;
			}

		}
	}

	/**
	 * The fileCheck method is run to check if there are any projects in the
	 * projects.txt file.
	 * <p>
	 * It guides the user's menu choices, depending on whether there are existing
	 * projects in the text file.
	 * 
	 * @return returns the boolean value for checkProject
	 */
	public static boolean fileCheck() {
		boolean checkProject = false;

		try {
			File projects = new File("projects.txt");
			Scanner projectFile = new Scanner(projects);

			if (projectFile.hasNextLine()) { // If the file contains contents for a project, projectCheck is 'true'.
				checkProject = true;

			} else {
				checkProject = false; // If the file does not contain projects, projectCheck is 'false'.

			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");

		}
		return checkProject; // boolean returned
	}
}
	
			

		

		
		 
		
		
			
		
		
