/**
 * people is a subclass that creates and displays a person object.
 * <p>
 * This class contains variables and a constructor to create person objects for
 * the Poised Management System. It also contains a method to display the
 * created person object. The Poised class, which runs the main program, calls
 * on methods from this class to create new projects.
 * 
 * @author Quinton Amos
 */
public class people {
	// Attributes
	private String relation;
	private String name;
	private String surname;
	private String tellNum;
	private String email;
	private String physicalAddress;

	/**
	 * The constructor method people creates a person object with five parameters of
	 * information.
	 * <p>
	 * A person object will contain info and contact details related to someone
	 * involved with a project undertaken by Poised.
	 * 
	 * @param relation        relation describes the title of the person object
	 *                        being created (e.g. customer, contractor, architect
	 *                        etc.)
	 * @param name            name first name value for the person
	 * @param surname         surname for the person
	 * @param tellNum         telephone number for the person
	 * @param email           email address for the person
	 * @param physicalAddress physical address for the person
	 */
	public people(String relation, String name, String surname, String tellNum, String email, String physicalAddress) {
		this.relation = relation;
		this.name = name;
		this.surname = surname;
		this.tellNum = tellNum;
		this.email = email;
		this.physicalAddress = physicalAddress;
	}

	/**
	 * The method toString displays all the attributes of the person object in an
	 * easy-to-read format
	 * 
	 * @return returns a string output with all person object information
	 */
	public String toString() {
		// converts everything to a String with toSting function
		System.out.println(relation + " details are as follows: \n");
		String output = "Title: " + relation;
		output += "\nName: " + name;
		output += "\nSurname: " + surname;
		output += "\nTelephone Number: " + tellNum;
		output += "\nEmail Address: " + email;
		output += "\nPhysical Address: " + physicalAddress;

		return output;
	}

}
