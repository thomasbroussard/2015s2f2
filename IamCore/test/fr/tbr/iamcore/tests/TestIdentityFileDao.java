/**
 * 
 */
package fr.tbr.iamcore.tests;

import java.io.IOException;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.tests.services.dao.IdentityFileDAO;


/**
 * @author tbrou
 *
 */
public class TestIdentityFileDao {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		IdentityFileDAO dao = new IdentityFileDAO();
		dao.create(new Identity("thomas", "tbr@tbr.com","456"));
		
		System.out.println(dao.readAll());

	}

}
