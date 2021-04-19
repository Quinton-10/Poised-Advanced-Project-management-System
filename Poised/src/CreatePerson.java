import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CreatePerson is a subclass that inherits methods from the InputChecks
 * superclass.
 * <p>
 * This class contains variables and a method for creating a person object to
 * Poised . The Poised class, which runs the main program, calls on methods from
 * this class to create a person.
 * 
 * @author Quinton Amos
 */
public class CreatePerson extends InputCheck {
	private String relation;
	private String name;
	private String surname;
	private String tellNum;
	private String email;
	private String physicalAddress;

	/**
	 * The newPerson method is used to create a new person object to Poised.
	 * <p>
	 * It asks the user to enter information related to the persons details which is
	 * then verified with methods from the superclass InputChecks, and stored in
	 * variables related to the person object. It displays the new person object
	 * created and writes the persons details to the People.txt file.
	 */
	public void newPerson() {
		// asks user to enter the persons details
		System.out.println("\nEnter relation of person (eg. Architect, Contractor, Customer: ");
		relation = checkString("relation");

		System.out.println("\nEnter " + relation + " name: ");
		name = checkString("name");

		System.out.println("\nEnter the Surname of the person: ");
		surname = checkString("surname");

		System.out.println("\nEnter the customer's email address: ");
		email = checkString("customer's email address");

		System.out.println("\nEnter persons Telephone number: ");
		tellNum = checkString("Telephone Number");

		System.out.println("\nEnter the customer's physical address: ");
		physicalAddress = checkString("customer's physical address");
		// print out the created persons details
		System.out.println("Person created: \n" + "\nRelation: " + relation + "\nName: " + name + "\nSurname: "
				+ surname + "\nemail: " + email + "\nTelephone Number: " + tellNum + "\nPhysical address: "
				+ physicalAddress);

		String personDetails = relation + ", " + name + ", " + surname + ", " + email + ", " + tellNum + ", "
				+ physicalAddress;

		// writes personDetails to people.txt
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("People.txt", true));

			out.write(personDetails + "\r\n");
			out.close();
			System.out.println(name + " was successfully saved.");

		} catch (IOException e) {
			System.out.print("Error: Exception " + e);

		}

	}

}
