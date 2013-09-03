package org.phonebook.constants;

public class ConstantsSQL {
	public static final String SELECT_USER_BY_EMAIL_PASSWORD = "select USERS.ID, USERS.NAME, USERS.EMAIL, USERS.PASSWORD, " +
			" ROLES.ID as ROLE_ID, ROLES.NAME as ROLE_NAME from USERS," +
			" ROLES where USERS.ROLE_ID = ROLES.ID AND USERS.EMAIL = ? AND USERS.PASSWORD = ?";
	public static final String SELECT_USER = "select USERS.ID, USERS.NAME, USERS.EMAIL, USERS.PASSWORD, " +
			" ROLES.ID as ROLE_ID, ROLES.NAME as ROLE_NAME from USERS," +
			" ROLES where USERS.ROLE_ID = ROLES.ID AND USERS.ID = ?";
	public static final String SELECT_USER_BY_EMAIL = "select USERS.ID, USERS.NAME, USERS.EMAIL, USERS.PASSWORD, " +
			" ROLES.ID as ROLE_ID, ROLES.NAME as ROLE_NAME from USERS," +
			" ROLES where USERS.ROLE_ID = ROLES.ID AND USERS.EMAIL = ?";
	public static final String SELECT_ROLE_BY_NAME = "select ID, NAME from ROLES where NAME = ?";
	public static final String SELECT_ROLE_BY_ID = "select ID, NAME from ROLES where ID = ?";
	public static final String IS_USER_EXIST = "select * from USERS where EMAIL = ?";
	public static final String SELECT_ALL_ROOMS = "select * from ROOMS";
	public static final String ADD_USER = "INSERT INTO USERS(NAME, EMAIL, PASSWORD, ROLE_ID) VALUES(?, ?, ?, ?)";
	public static final String SELECT_ROOM_BY_ID = "select ID, NUMBER from ROOMS where ID = ?";
	public static final String SELECT_ROOM_BY_NUMBER = "select ID, NUMBER from ROOMS where NUMBER = ?";
	public static final String DELETE_ROOM = "DELETE FROM ROOMS WHERE ID=?";
	public static final String DELETE_USER = "DELETE FROM USERS WHERE ID=?";
	public static final String ADD_ROOM = "INSERT INTO ROOMS(NUMBER) VALUES(?)";
	public static final String SELECT_ALL_EMPLOYEES = "SELECT EMPLOYEES.ID, EMPLOYEES.FIRST_NAME, EMPLOYEES.MIDDLE_NAME, " +
			"EMPLOYEES.LAST_NAME, EMPLOYEES.PHONE, ROOMS.ID AS ROOM_ID, " +
			"ROOMS.NUMBER AS ROOM_NUMBER from " +
			"EMPLOYEES JOIN ROOMS ON EMPLOYEES.ROOM_ID = ROOMS.ID";
	public static final String SELECT_EMPLOYEES_BY_ROOM = "SELECT EMPLOYEES.ID, EMPLOYEES.FIRST_NAME, EMPLOYEES.MIDDLE_NAME, " +
			"EMPLOYEES.LAST_NAME, EMPLOYEES.PHONE, ROOMS.ID AS ROOM_ID, " +
			"ROOMS.NUMBER AS ROOM_NUMBER from " +
			"EMPLOYEES JOIN ROOMS ON EMPLOYEES.ROOM_ID = ROOMS.ID WHERE ROOMS.ID = ?";
	public static final String SELECT_EMPLOYEE_BY_ID  = "SELECT EMPLOYEES.ID, EMPLOYEES.FIRST_NAME, EMPLOYEES.MIDDLE_NAME, " +
			"EMPLOYEES.LAST_NAME, EMPLOYEES.PHONE, ROOMS.ID AS ROOM_ID, " +
			"ROOMS.NUMBER AS ROOM_NUMBER from " +
			"EMPLOYEES JOIN ROOMS ON EMPLOYEES.ROOM_ID = ROOMS.ID WHERE EMPLOYEES.ID = ?";
	public static final String UPDATE_EMPLOYEE = "UPDATE EMPLOYEES " +
			"SET FIRST_NAME=?, LAST_NAME=?, MIDDLE_NAME=?, PHONE=?, ROOM_ID=? " +
			"WHERE ID=?";
	public static final String REMOVE_EMPLOYEE = "DELETE FROM EMPLOYEES WHERE ID=?";
	public static final String IS_EMPLOYEE_EXIST = "SELECT ID FROM EMPLOYEES WHERE FIRST_NAME=? AND MIDDLE_NAME=? AND LAST_NAME=?";
	public static final String ADD_EMPLOYEE = "INSERT INTO EMPLOYEES(FIRST_NAME, MIDDLE_NAME, LAST_NAME, PHONE, ROOM_ID) " +
			"VALUES(?, ?, ?, ?, ?)";
	public static final String SELECT_ALL_USERS = "select USERS.ID, USERS.NAME, USERS.EMAIL, USERS.PASSWORD, " +
			" ROLES.ID as ROLE_ID, ROLES.NAME as ROLE_NAME from USERS," +
			" ROLES where USERS.ROLE_ID = ROLES.ID ";
	public static final String SELECT_EMPLOYEE  = "SELECT EMPLOYEES.ID, EMPLOYEES.FIRST_NAME, EMPLOYEES.MIDDLE_NAME, " +
			"EMPLOYEES.LAST_NAME, EMPLOYEES.PHONE, ROOMS.ID AS ROOM_ID, " +
			"ROOMS.NUMBER AS ROOM_NUMBER from " +
			"EMPLOYEES JOIN ROOMS ON EMPLOYEES.ROOM_ID = ROOMS.ID WHERE EMPLOYEES.FIRST_NAME = ? AND EMPLOYEES.MIDDLE_NAME = ? " + 
			"AND EMPLOYEES.LAST_NAME = ? ";
}