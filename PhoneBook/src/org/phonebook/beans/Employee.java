package org.phonebook.beans;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.phonebook.constants.ConstantsJPA;
import org.phonebook.utils.MessagesUtil;

@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({
	@NamedQuery(name = ConstantsJPA.FIND_EMPLOYEES, query = ConstantsJPA.FIND_EMPLOYEES_QUERY),
	@NamedQuery(name = ConstantsJPA.FIND_ALL_EMPLOYEES, query = ConstantsJPA.FIND_ALL_EMPLOYEES_QUERY),
	@NamedQuery(name = ConstantsJPA.FIND_EMPLOYEES_BY_ROOM, query = ConstantsJPA.FIND_EMPLOYEES_BY_ROOM_QUERY),
})
@XmlRootElement
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	private Room room;
	private int phone;
	@Transient
	@XmlTransient
	private String formatedPhone;
	@Transient
	@XmlTransient
	public static DecimalFormat phoneFormat;
	
	static {
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setGroupingSeparator('-');
		phoneFormat = new DecimalFormat("#0,00", formatSymbols);
	}

	public Employee() {
		super();
	}

	public Employee(int id, String firstName, String middleName,
			String lastName, Room room, int phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.room = room;
		this.phone = phone;
		this.formatedPhone = phoneFormat.format(phone);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
		formatedPhone = phoneFormat.format(phone);
	}
	public String getFormatedPhone() {
		formatedPhone = phoneFormat.format(phone);
		return formatedPhone;
	}
	
	public void validateFirstName(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if (!name.matches("[a-zA-Zà-ÿ¸À-ß]{2,16}")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "firstNameValid", null)));
		}
	}
	
	public void validateLastName(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if (!name.matches("[a-zA-Zà-ÿ¸À-ß]{2,16}")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "lastNameValid", null)));
		}
	}
	
	public void validateMiddleName(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if (!name.matches("[a-zA-Zà-ÿ¸À-ß]{2,16}")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "middleNameValid", null)));
		}
	}
	
	public void validatePhone(FacesContext fc, UIComponent c, Object value) {
		String phone = String.valueOf(value);
		if (!phone.matches("[0-9]{3,4}")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "phoneValid", null)));
		}
	}
	
	public void validateRoom(FacesContext fc, UIComponent c, Object value) {
		String room = String.valueOf(value);
		if (!room.matches("([1-9]){1}([0-9]){0,4}")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "roomValid", null)));
		}
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", room=" + room + ", phone=" + phone + ", formatedPhone="
				+ formatedPhone + "]";
	}
	
	
}

