package PBA.model;

/** 
 * Un contact din agenda contine urmatoarele campuri: id, nume, prenume, 
 * zi de nastere, adresa
 * @author Maria
 */
public class Contact {
	private long contactId;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String street;
	private int number;
	private String city;
	private String country;
	
	
	public String toString() {
		return "Contact [contactId=" + contactId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", street=" + street + ", number=" + number + ", city="
				+ city + ", country=" + country + "]";
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
