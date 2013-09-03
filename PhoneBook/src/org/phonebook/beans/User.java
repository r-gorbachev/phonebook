package org.phonebook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.phonebook.constants.ConstantsJPA;

@Entity
@Table(name = "USERS")
@NamedQueries({
	@NamedQuery(name = ConstantsJPA.FIND_ALL_USERS, query = ConstantsJPA.FIND_ALL_USERS_QUERY),
	@NamedQuery(name = ConstantsJPA.FIND_USER_AUTH, query = ConstantsJPA.FIND_USER_AUTH_QUERY),
	@NamedQuery(name = ConstantsJPA.FIND_USER_BY_EMAIL, query = ConstantsJPA.FIND_USER_BY_EMAIL_QUERY)
})

@XmlRootElement
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@ManyToOne(optional = false)
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String email, String password, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", role=" + role + "]";
	}
	
	
}
