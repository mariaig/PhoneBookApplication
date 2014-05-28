package PBA.model;
/**
 * ContactDetail contine de fapt detaliile numarului de telefon:
 * prefix, numarul, si reteaua in care se afla
 * @author Maria
 *
 */
public class ContactDetail {
	private long contactDetailId;
	private long contactId;
	private String prefix;
	private String phoneNumber;
	private String networkName;
	
	public String toString() {
		return "ContactDetail [contactDetailId=" + contactDetailId
				+ ", contactId=" + contactId + ", prefix=" + prefix
				+ ", phoneNumber=" + phoneNumber + ", networkName="
				+ networkName + "]";
	}
	public long getContactDetailId() {
		return contactDetailId;
	}
	public void setContactDetailId(long contactDetailId) {
		this.contactDetailId = contactDetailId;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	
	
	
	
}
