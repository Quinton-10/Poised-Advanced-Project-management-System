import java.io.*;
import java.util.*;
import java.text.*;

/**
 * SavedProjects is a subclass that inherits methods from the InputChecks
 * superclass.
 * <p>
 * This class contains methods to find, view, update, finalise and do date
 * checks on projects in Poised. The Poised class, which runs the main program,
 * calls on methods from this class to perform various functions on projects
 * managed by Poised.
 * 
 * @author Quinton Amos
 */
public class SavedProjects extends InputCheck {
	/**
	 * The searchProject method runs through the projects.txt file to find a certain
	 * project object needed.
	 * <p>
	 * It takes in one parameter with string info related to the project and returns
	 * an integer to indicate the line number of the project being located.
	 * 
	 * @param projectDetails either a project number or project name to identify the
	 *                       project object
	 * @return returns an integer lineNum to indicate the line number of the project
	 *         in the text file
	 */
	public int searchProject(String projectDetails) {
		// String array declared to stored project details from each line in the
		// external text file.
		// A lineNum set to run through the text file to find the project.
		String[] details = new String[10];
		int lineNum = 1;

		// A new file object is created and a while loop used to run through lines in
		// the test file.
		// Line info is then split and stored in the array.
		// While loop is exited if the first or second index in the array is equal to
		// the projectDetails parameter.
		try {
			File projectFile = new File("projects.txt");
			Scanner scan = new Scanner(projectFile);

			while (scan.hasNextLine()) {
				details = scan.nextLine().split(", ");

				if ((details[0].equalsIgnoreCase(projectDetails)) || (details[1].equalsIgnoreCase(projectDetails))) {
					break;

				} else {
					lineNum++;
				}

			}
		} catch (FileNotFoundException e) { // Try-catch block used to handle errors.
			System.out.println("File not Found or in wrong directory!");

		}
		return lineNum;

	}

	/**
	 * The viewProjects method runs through the projects.txt file to view all
	 * projects listed.
	 * <p>
	 * The projects are each displayed in an easy-to-read format.
	 */

	public void viewProject() {
		String[] details = new String[10];

		try {
			File viewFile = new File("projects.txt");
			Scanner projectFile = new Scanner(viewFile);

			while (projectFile.hasNextLine()) {
				details = projectFile.nextLine().split(", ");
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

				// prints out details in a readable format.
				System.out.println("\nProject Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address + "\nERF Number: " + erf
						+ "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid + "\nDeadline: " + dueDate
						+ "\nCompletion Date: " + complete + "\nProject Status: " + finalised);

			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found or in wrong directory");
		}
	}

	/**
	 * The editProject method is used to edit either the due date or the amount paid
	 * to date.
	 * 
	 * @param option  an integer option the user enters in main.
	 * @param lineNum int lineNum returned by searchProject to locate a project
	 */
	public void editProject(int option, int lineNum) {
		// An ArrayList is set to store each line
		ArrayList<String> arrayDetails = new ArrayList<String>();
		String[] details = new String[10];
		int line = 1;

		// A file object is created and a while loop used to run through each line of
		// the text file.
		// Each line of project details is added to the ArrayList.
		// The updated array can then be re-written to the projects.txt file.
		try {
			File file = new File("projects.txt");
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				arrayDetails.add(scan.nextLine());
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found or in wrong directory");
		}

		try {
			File file = new File("projects.txt");
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				if (line == lineNum) {
					details = scan.nextLine().split(", "); // each line is added to details

				} else if (line != lineNum) {
					line++;

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found or in wrong directory");
		}
		// If user enter "1" they can edit the due date and it is changed in the text
		// file
		if (option == 1) {
			System.out.println("Enter new Due Date: ");
			String newDueDate = checkString("Due Date");
			details[7] = newDueDate;
			String newDetails = Arrays.toString(details);
			String change = newDetails.replace("[", "");
			String newLine = change.replace("]", "");
			arrayDetails.set(lineNum - 1, newLine);

			// if user enters "2" the can edit the amount paid to date
		} else if (option == 2) {
			System.out.println("Enter new Total Amount paid to date: ");
			double newTotalFee = doubleCheck("Total Fee");
			details[5] = Double.toString(newTotalFee);
			String newDetails = Arrays.toString(details);
			String change = newDetails.replace("[", "");
			String newLine = change.replace("]", "");
			arrayDetails.set(lineNum - 1, newLine);

		}
		// then the edited part is written to the text file.
		try {
			Formatter write = new Formatter("projects.txt");
			for (String str : arrayDetails) {
				write.format("%s", str + "\r\n");
			}
			System.out.println("succsessfully edited project");
			write.close();

		} catch (Exception e) {
			System.out.println("An Error occurred");

		}
	}

	/**
	 * The finalising method finalises a project object by generating an invoice,
	 * marking the project as finalised and adding a completion date to the project
	 * info.
	 * <p>
	 * It selects a project object from the projects.txt file, checks whether an
	 * invoice must be generated and then marks the project as finalised, and adds a
	 * completion date. The finalised project is then stored in a new text file
	 * called CompletedProjects.txt.
	 * 
	 * @param lineNum integer lineNum determined by the searchProject function
	 */
	public void finalising(int lineNum) {
		ArrayList<String> arrayDetails = new ArrayList<String>();
		String[] details = new String[10];
		int line = 1;

		try {
			File file = new File("projects.txt");
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				arrayDetails.add(scan.nextLine());

			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found or in wrong directory");
		}

		try {
			File file = new File("projects.txt");
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				if (line == lineNum) {
					details = scan.nextLine().split(", ");

				} else if (line != lineNum) {
					line++;

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found or in wrong directory");

		}
		// Each index of the split line is then assigned to a new variable.
		// These variables describe project details.
		// The totalFee and amountPaid indexed variables are parsed for comparison.
		String projectNum = details[0];
		String projectName = details[1];
		String buildingType = details[2];
		String address = details[3];
		String erf = details[4];
		String dueDate = details[7];
		String complete;
		String finalised = "Finalised";

		double totalFee = Double.parseDouble(details[5]);
		double amountPaid = Double.parseDouble(details[6]);
		// checks if the total fee and amount paid to date are the same
		// if they are the same no invoice will be generated and a completion date is
		// required
		if (totalFee == amountPaid) {
			System.out.println("No Invoice, Project has been paid");
			System.out.println("Enter the date the project was Completed(eg. dd-mm-yyyy :11-05-2021): ");
			complete = checkString("Date Complete");

			// else a invoice will be generated and displayed to the user with the amount
			// they still owe with the details of the project
		} else {
			System.out.println("Generating Invoice for: ");
			System.out.println("\nEnter Customers Name: ");
			String customerName = checkString("Customer Name");

			System.out.println("\nEnter Customers Surname: ");
			String customerSurname = checkString("Customer Surname");

			String personType2 = "Customer";

			System.out.println("\nEnter the customer's telephone number: ");
			String telephoneNum2 = checkString("customer's telephone number");

			System.out.println("\nEnter the customer's email address: ");
			String email2 = checkString("customer's email address");

			System.out.println("\nEnter the customer's physical address: ");
			String address2 = checkString("customer's physical address");

			System.out.println(
					"Please add a completion date for this project (e.g. day, month, year, 3 December 2020): ");
			complete = checkString("Date complete");

			people newCustomer = new people(personType2, customerName, customerSurname, telephoneNum2, email2,
					address2);

			System.out.println("Generated Invoice: ");
			System.out.println(newCustomer.toString());
			double amountOwed = totalFee - amountPaid;
			System.out.println("The Amount stil owed is : R" + amountOwed);
		}
		System.out.println("\nFinalised Project Details: \n" + "Project Number: " + projectNum + "\nProjectName: "
				+ projectName + "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address + "\nERF Number: "
				+ erf + "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid + "\nDeadline: " + dueDate
				+ "\nCompletion Date: " + complete + "\nProject Status: " + finalised);

		arrayDetails.remove(lineNum - 1);
		// The updated arrayDetails are then written to project.txt
		try {
			Formatter write = new Formatter("projects.txt");
			for (String str : arrayDetails) {
				write.format("%s", str + "\r\n");
			}

			System.out.println("succsessfully Finalised project");
			write.close();

		} catch (Exception e) {
			System.out.println("An Error occurred");
		}
		String completedProject = projectNum + ", " + projectName + ", " + buildingType + ", " + address + ", " + erf
				+ ", " + totalFee + ", " + amountPaid + ", " + dueDate + ", " + complete + ", " + finalised;
		// A new file is then created to store or the projects that are finalised
		try {
			File completedFile = new File("completedProjects.txt");

			if (completedFile.createNewFile()) {
				System.out.print("New File created " + completedFile.getName());

			} else {
				System.out.println("File already exists");
			}
		} catch (IOException ioe) {
			System.out.println("An Exception occurred: ");
			ioe.printStackTrace();
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("completedProjects.txt", true));

			out.write(completedProject + "\r\n");
			out.close();
			System.out.println("Completed project saved to text file");

		} catch (IOException e) {
			System.out.println("Exception occurred: " + e);
		}

	}

	/**
	 * The dateCheck method determines whether a project is incomplete or overdue
	 * and displays those listed projects.
	 * <p>
	 * It takes in either an 'overdue' or 'incomplete' projectType string, searches
	 * through the project objects in the projects.txt file, and displays a list of
	 * overdue or incomplete project objects.
	 * 
	 * @param projectType string type to specify projects listed (either 'overdue'
	 *                    or incomplete')
	 * @throws ParseException occurs if a date string is in the wrong format to be
	 *                        parsed
	 */
	public void checkDate(String projectType) throws ParseException {

		String[] details = new String[10];

		try {
			File viewFile = new File("projects.txt");
			Scanner view = new Scanner(viewFile);

			while (view.hasNextLine()) {
				details = view.nextLine().split(", ");
				String projectNum = details[0];
				String projectName = details[1];
				String buildingType = details[2];
				String address = details[3];
				String erf = details[4];
				String totalFee = details[5];
				String amountPaid = details[6];
				String complete = details[8];
				String finalised = details[9];
				String dueDate = details[7];

				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				Date newDate = new Date();
				// gets todays date
				// uses the due date in the index details[7]
				String todaysDate = date.format(newDate);
				Date date1 = date.parse(todaysDate);
				Date date2 = date.parse(dueDate);

				String projectDetails = ("Project Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address + "\nERF Number: " + erf
						+ "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid + "\nDeadline: " + dueDate
						+ "\nCompletion Date: " + complete + "\nProject Status: " + finalised + "\n");

				// compares the to date to each other
				// if todays date is before the due date the project is incomplete and all
				// incompleted tasks will be printed

				if ((date1.compareTo(date2) < 0) && (projectType.equalsIgnoreCase("incomplete"))) {
					System.out.println("\nIncomplete Projects: \n" + projectDetails);

					// if todays date is after the due date then the project is overdue and all
					// overdue task will be printed
				} else if ((date1.compareTo(date2) > 0) && (projectType.equalsIgnoreCase("overdue"))) {
					System.out.println("overdue tasks : " + projectDetails);

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found or in wrong directory");
		}
	}

}
