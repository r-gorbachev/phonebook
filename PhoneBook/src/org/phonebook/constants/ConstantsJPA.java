package org.phonebook.constants;

public class ConstantsJPA {
	public static final String FIND_ROLE_BY_NAME = "Role.findByName";
	public static final String FIND_ROLE_BY_NAME_QUERY = "select r from Role r where r.name=?1";
	
	public static final String FIND_ALL_ROLES = "Role.findAll";
	public static final String FIND_ALL_ROLES_QUERY = "select r from Role r";
	
	public static final String FIND_ALL_USERS = "User.findAll";
	public static final String FIND_ALL_USERS_QUERY = "select u from User u";
	
	public static final String FIND_USER_AUTH = "User.findAuth";
	public static final String FIND_USER_AUTH_QUERY = "select u from User u where u.email=?1 and u.password=?2";
	
	public static final String FIND_USER_BY_EMAIL = "User.findByEmail";
	public static final String FIND_USER_BY_EMAIL_QUERY = "select u from User u where u.email=?1";
	
	public static final String FIND_ROOM_BY_NUMBER = "Room.findByNumber";
	public static final String FIND_ROOM_BY_NUMBER_QUERY = "select r from Room r where r.number=?1";
	
	public static final String FIND_ALL_EMPLOYEES = "Employee.findAll";
	public static final String FIND_ALL_EMPLOYEES_QUERY = "select e from Employee e";
	
	public static final String FIND_EMPLOYEES_BY_ROOM = "Employee.findByRoom";
	public static final String FIND_EMPLOYEES_BY_ROOM_QUERY = "select e from Employee e where e.room.number=?1";
	
	public static final String FIND_EMPLOYEES = "Employee.find";
	public static final String FIND_EMPLOYEES_QUERY = "select e from Employee e where e.firstName=?1 and e.lastName=?2 and e.middleName=?3";
	
}
