/**
 * 
 */
package fr.tbr.iamcore.tests.services.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.tests.services.match.Matcher;
import fr.tbr.iamcore.tests.services.match.impl.ContainsIdentityMatcher;
import fr.tbr.iamcore.tests.services.match.impl.StartsWithIdentityMatchStrategy;

/**
 * @author tbrou
 *
 */
public class IdentityFileDAO {

	// TODO change this path, make it configurable
	private final static String filePath = "/tests/iam/identities.txt";

	// Writer to insert identities into the file
	private PrintWriter writer;

	// Used to get the data from the file
	private Scanner scanner;
	
	//TODO we would like this to be configuration driven
	private Matcher<Identity> matcher = new StartsWithIdentityMatchStrategy();

	public IdentityFileDAO() throws IOException {

		File file = ensureFileExists(filePath);
		// open in write mode, but with append directive activated (the "true"
		// parameter)
		this.writer = new PrintWriter(new FileOutputStream(file, true));
		this.scanner = new Scanner(file);

	}

	public void create(Identity identity) {
		writer.println("--- identity:begin ---");
		writer.println(identity.getDisplayName());
		writer.println(identity.getEmailAddress());
		writer.println(identity.getUid());
		writer.println("--- identity:end ---");
		writer.flush();
	}

	public List<Identity> search(Identity criteria) {
		// Create the result structure
		List<Identity> identities = new ArrayList<Identity>();

		// While we have some remaining lines to read
		while (scanner.hasNext()) {
			Identity identity = readAnIdentity();
			
			//check if the identity is matching the criteria
			if (matcher.match(criteria,identity)){
				// put that newly created identity in the list
				identities.add(identity);
			}
			
		}
		// reset the scanner so we will be able to read from the beginning of
		// the file in the next call
		scanner.reset();
		return identities;

	}


	public List<Identity> readAll() {
		// Create the result structure
		List<Identity> identities = new ArrayList<Identity>();

		// While we have some remaining lines to read
		while (scanner.hasNext()) {
			Identity identity = readAnIdentity();
			// put that newly created identity in the list
			identities.add(identity);
		}
		// reset the scanner so we will be able to read from the beginning of
		// the file in the next call
		scanner.reset();
		return identities;
	}

	private Identity readAnIdentity() {
		scanner.nextLine(); // we do nothing with this line because it is
							// "--- identity:begin ---"
		String displayName = scanner.nextLine();
		String emailAddress = scanner.nextLine();
		String uid = scanner.nextLine();
		scanner.nextLine(); // we do nothing with this line because it is
							// "--- identity:end ---"
		// create an identity with the read properties
		Identity identity = new Identity(displayName, emailAddress, uid);
		return identity;
	}

	public void update(Identity identity) {

	}

	public void delete(Identity identity) {

	}

	/**
	 * This method will check that the file exists or create it if it doesn't
	 * 
	 * @param pathname
	 * @throws IOException
	 */
	public static File ensureFileExists(String pathname) throws IOException {
		File file = new File(pathname);
		if (!file.exists()) {
			// creation code after
			System.out.println("the file does not exists");
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
			System.out.println("file was successfully created");
		} else {
			System.out.println("the file already exists");
		}
		return file;
	}

}
