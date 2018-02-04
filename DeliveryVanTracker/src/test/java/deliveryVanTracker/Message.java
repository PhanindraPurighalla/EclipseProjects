/**
 * 
 */
package deliveryVanTracker;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author phanindrapurighalla
 *
 */

@XmlRootElement
public class Message {

	private String firstName;
	
	private String lastName;
	
	private String permanentAccNo;
	
	private String passportNo;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the permanentAccNo
	 */
	public String getPermanentAccNo() {
		return permanentAccNo;
	}

	/**
	 * @param permanentAccNo the permanentAccNo to set
	 */
	public void setPermanentAccNo(String permanentAccNo) {
		this.permanentAccNo = permanentAccNo;
	}

	/**
	 * @return the passportNo
	 */
	public String getPassportNo() {
		return passportNo;
	}

	/**
	 * @param passportNo the passportNo to set
	 */
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	
}
