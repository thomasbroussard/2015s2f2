package fr.tbr.iamcore.datamodel;

import java.util.Date;


/**
 * This is the representation for the Identity, the main datamodel to manage in IamCore
 * @author tbrou
 *
 */
public class Identity {
	
	private String displayName;
	private String emailAddress;
	private String uid;
	private Date birthDate;
	
	
	/**
	 * @param displayName
	 * @param emailAddress
	 * @param uid
	 */
	public Identity(String displayName, String emailAddress, String uid) {
		this.displayName = displayName;
		this.emailAddress = emailAddress;
		this.uid = uid;
	}



	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}



	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}



	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}



	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}





	public Date getBirthDate() {
		return this.birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [displayName=" + this.displayName + ", emailAddress=" + this.emailAddress + ", uid=" + this.uid + ", birthDate=" + this.birthDate + "]";
	}
	
	
	
	

}
