package fr.tbr.iamcore.tests.services.dao.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAO;

public class IdentityXmlDAO implements IdentityDAO {

	@Override
	public List<Identity> search(Identity criteria) {
		List<Identity> results = new ArrayList<Identity>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("F:\\Work\\Dev\\Git\\Epita\\Fundamentals\\2015s2f2\\IamCore\\xml\\identities.xml");

			NodeList nodes = doc.getElementsByTagName("identity");
			int nodesSize = nodes.getLength();
			for (int i = 0; i < nodesSize; i++) {
				Node node = nodes.item(i);
				if (node instanceof Element) {
					
					
					Element identity = (Element) node;
					NodeList properties = identity.getElementsByTagName("property");
					int length = properties.getLength();
					String displayName = "", guid = "", email = "";
					for (int j = 0; j < length; j++) {
						Node item = properties.item(j);
						if (item instanceof Element) {
							Element propertyElt = (Element) item;
							
							String textContent = propertyElt.getTextContent();
							switch(propertyElt.getAttribute("name")){
							case "displayName":
								displayName = textContent;
								break;
							case "guid":
								guid = textContent;
								break;
							case "email":
								email = textContent;
								break;
							}
						}
					}
					Identity currentIdentity = new Identity(displayName, email, guid);
					results.add(currentIdentity);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public void create(Identity identity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Identity identity) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Identity identity) throws IOException {
		// TODO Auto-generated method stub

	}

}
