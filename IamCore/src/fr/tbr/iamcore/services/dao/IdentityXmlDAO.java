package fr.tbr.iamcore.services.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.tests.services.match.Matcher;
import fr.tbr.iamcore.tests.services.match.impl.StartsWithIdentityMatchStrategy;

public class IdentityXmlDAO implements IdentityDAO {

	Matcher<Identity> matcher = new StartsWithIdentityMatchStrategy();

	Document doc;

	// Intermediate instance to build a document builder (the factory to
	// parse DOM documents)
	private static DocumentBuilderFactory dbf ;
	// DOM Document builder, to get an empty document or parse it from
	// an xml source
	private static DocumentBuilder db ;
	
	
	public IdentityXmlDAO() {
		try {
			
			if (db == null){
				dbf = DocumentBuilderFactory.newInstance();
				db = dbf.newDocumentBuilder();
			}
			// Document representation in Java
			this.doc = db.parse("C:\\Work\\Dev\\Git\\Epita\\Fundamentals\\2015s2f2\\IamCore\\xml\\identities.xml");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO complete exception handling
		}
	}

	@Override
	public List<Identity> search(Identity criteria) {
		List<Identity> results = new ArrayList<Identity>();
		// gets all the nodes called "identity" (see xml/identities.xml in
		// the project)
		NodeList nodes = this.doc.getElementsByTagName("identity");
		int nodesSize = nodes.getLength();

		// for every found identity
		for (int i = 0; i < nodesSize; i++) {
			Node node = nodes.item(i);
			// test if the found node is really an Element
			// in the DOM implementation a Node can represent an Element, an
			// Attribute or a TextContent
			// so we have to be sure that the found node is of type
			// "Element" using the instanceof operator
			if (node instanceof Element) {
				Identity identity = readOneIdentityFromXmlElement(node);
				// usage of Matcher to filter only the wished identities.
				if (this.matcher.match(criteria, identity)) {
					results.add(identity);
				}
			}
		}
		return results;
	}
	
	public List<Identity> readAll(){
		List<Identity> results = new ArrayList<Identity>();
		// gets all the nodes called "identity" (see xml/identities.xml in
		// the project)
		NodeList nodes = this.doc.getElementsByTagName("identity");
		int nodesSize = nodes.getLength();

		// for every found identity
		for (int i = 0; i < nodesSize; i++) {
			Node node = nodes.item(i);
			// test if the found node is really an Element
			// in the DOM implementation a Node can represent an Element, an
			// Attribute or a TextContent
			// so we have to be sure that the found node is of type
			// "Element" using the instanceof operator
			if (node instanceof Element) {
				Identity identity = readOneIdentityFromXmlElement(node);
				// usage of Matcher to filter only the wished identities.
				
				results.add(identity);
			
			}
		}

		return results;
		
	}
	private Identity readOneIdentityFromXmlElement(Node node) {
		// cast the node into an Element, as we are sure it is an
		// instance of Element
		Element identity = (Element) node;

		// get the properties for the encountered identity
		NodeList properties = identity.getElementsByTagName("property");
		int length = properties.getLength();

		// declare and initialize several variables on the same line
		String displayName = "", guid = "", email = "";
		Date birthDate = null;
		// for every found property
		for (int j = 0; j < length; j++) {
			Node item = properties.item(j);
			// we check that the found property is really an element
			// (see the explanation above)
			if (item instanceof Element) {
				Element propertyElt = (Element) item;
				// we need to store the right value in the right
				// property so we use a switch-case structure
				// to handle the 3 cases
				String textContent = propertyElt.getTextContent();
				switch (propertyElt.getAttribute("name")) {
				case "displayName":
					displayName = textContent;
					break;
				case "guid":
					guid = textContent;
					break;
				case "email":
					email = textContent;
					break;

				case "birthDate":
					try {
						birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(textContent);
					} catch (Exception e) {
						e.printStackTrace();
						//TODO finish exception handling
					}
					break;

				default:
					// the encountered property name is not expected
					// so we use the "default" case
					break;
				}
			}
		}
		Identity currentIdentity = new Identity(displayName, email, guid);
		currentIdentity.setBirthDate(birthDate);
		return currentIdentity;
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
