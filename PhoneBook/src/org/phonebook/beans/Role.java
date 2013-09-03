package org.phonebook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.phonebook.constants.ConstantsJPA;

@Entity
@Table(name = "ROLES")
@NamedQueries({
	@NamedQuery(name = ConstantsJPA.FIND_ALL_ROLES, query = ConstantsJPA.FIND_ALL_ROLES_QUERY),
	@NamedQuery(name = ConstantsJPA.FIND_ROLE_BY_NAME, query = ConstantsJPA.FIND_ROLE_BY_NAME_QUERY)
})

@XmlRootElement
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	

}
