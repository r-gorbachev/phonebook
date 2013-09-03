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
@Table(name = "ROOMS")
@NamedQueries({
	@NamedQuery(name = ConstantsJPA.FIND_ROOM_BY_NUMBER, query = ConstantsJPA.FIND_ROOM_BY_NUMBER_QUERY)
})

@XmlRootElement
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Room(int id, int number) {
		super();
		this.id = id;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + "]";
	}
	


}
