import java.io.*;

/**
 * project is a subclass that inherits methods from the InputChecks superclass.
 * <p>
 * This class contains variables and a method for adding a new project object to
 * the Poised Management System. The Poised class, which runs the main program,
 * calls on methods from this class to add create projects.
 * 
 * @author Quinton Amos
 */

public class project extends InputCheck {
	// Attributes
	private int projectNum;
	private String projectName;
	private String buildingType;
	private String address;
	private int erf;
	private Double totalFee;
	private Double amountPaid;
	private String dueDate;
	private String clientSurname;
	private String complete;
	private String finalised;

	/**
	 * The newProject method is used to add a new project object to Poised.
	 * <p>
	 * It asks the user to enter information related to project details which is
	 * then verified with methods from the superclass InputChecks, and stored in
	 * variables related to the project object. It displays the new project object
	 * created and writes the project info to the projects.txt file.
	 */
	public void newProject() {
		// complete and finalised set to "no"
		complete = "not yet completed";
		finalised = "not finalised";

		System.out.print("Enter Project Number: ");
		projectNum = intCheck("Project Number");

		System.out.print("\nEnter the Total fee for the project: ");
		totalFee = doubleCheck("Total fee");

		System.out.print("\nEnter amount paid to date: ");
		amountPaid = doubleCheck("Amount Paid");

		System.out.print("\nEnter the Deadline for the project(dd-mm-yyyy): ");
		dueDate = checkString("Due Date");

		System.out.print("\nEnter ERF number: ");
		erf = intCheck("ERF Number");

		System.out.print("\nEnter Clients Surname: ");
		clientSurname = checkString("Surname");

		System.out.print("\nEnter Project name: ");
		projectName = checkString("Project Name");

		System.out.print("\nEnter the building type(house, appartment, office etc.): ");
		buildingType = checkString("Building type");

		System.out.print("\nEnter Physical Address: ");
		address = checkString("Physical Address");
		// if there is not a project name it will create a project name with the clients
		// surname and the building type
		if (projectName == "") {
			projectName = buildingType + " " + clientSurname;
			System.out.println("New Project Details: \n" + "Project Number: " + projectNum + "\nProjectName: "
					+ projectName + "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address
					+ "\nERF Number: " + erf + "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid
					+ "\nDue Date: " + dueDate + "\nCompletion Date: " + complete + "\nProject Status: " + finalised);

			// else it will print the new project that they created
		} else {
			System.out.println("Finalised Project Details: \n" + "Project Number: " + projectNum + "\nProject Name: "
					+ projectName + "\nBuilding Type: " + buildingType + "\nPhysical Address: " + address
					+ "\nERF Number: " + erf + "\nTotal Fee: R" + totalFee + "\nAmount Paid: R" + amountPaid
					+ "\nDue Date: " + dueDate + "\nCompletion Date: " + complete + "\nProject Status: " + finalised);
		}

		String projectDetails = projectNum + ", " + projectName + ", " + buildingType + ", " + address + ", " + erf
				+ ", " + totalFee + ", " + amountPaid + ", " + dueDate + ", " + complete + ", " + finalised;
		// Writing the string 'projectDetails' to the projects.txt file.
		// Try-catch block used to deal with any errors encountered.
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("projects.txt", true));

			out.write(projectDetails + "\r\n");
			out.close();
			System.out.println("Your project was successfully added.");

		} catch (IOException e) {
			System.out.print("Error: Exception " + e);

		}

	}

}

