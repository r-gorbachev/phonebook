package org.phonebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.constants.Constants;
import org.phonebook.constants.ConstantsSQL;
import org.phonebook.dao.interfaces.IEmployeeDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.utils.DBManager;

@Stateless(name="EmployeeDaoJDBC")
public class EmployeeDaoJDBC implements IEmployeeDao{

	@Override
	public List<Employee> getAllEmployees() throws DaoException {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_ALL_EMPLOYEES)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt(Constants.ID_KEY));
				employee.setFirstName(resultSet.getString(Constants.FIRST_NAME_KEY));
				employee.setMiddleName(resultSet.getString(Constants.MIDDLE_NAME_KEY));
				employee.setLastName(resultSet.getString(Constants.LAST_NAME_KEY));
				employee.setPhone(resultSet.getInt(Constants.PHONE_KEY));
				Room room = new Room();
				room.setId(resultSet.getInt(Constants.ROOM_ID_KEY));
				room.setNumber(resultSet.getInt(Constants.ROOM_NUMBER_KEY));
				employee.setRoom(room);
				employees.add(employee);
			}

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.SELECT_ALL_EMPLOYEES, e);
		}
		
		return employees;
	}



	@Override
	public List<Employee> findEmployees(String nameTemplate,
			String roomTemplate, String phoneTemplate) throws DaoException {
		List<Employee> employees = new ArrayList<Employee>();
		StringBuilder searchQuery = new StringBuilder();
		searchQuery.append(ConstantsSQL.SELECT_ALL_EMPLOYEES);
		if(nameTemplate != null || roomTemplate != null || phoneTemplate != null) {
			searchQuery.append(" WHERE ");
			if(nameTemplate != null) {
				searchQuery.append("UPPER(LAST_NAME)");
				searchQuery.append(" LIKE ");
				searchQuery.append(nameTemplate);
				if(roomTemplate != null || phoneTemplate != null) {
					searchQuery.append(" AND ");
				}
			} 
			if(roomTemplate != null) {
				searchQuery.append("VARCHAR(ROOMS.NUMBER)");
				searchQuery.append(" LIKE ");
				searchQuery.append(roomTemplate);
				if(phoneTemplate != null) {
					searchQuery.append(" AND ");
				}
			}		
			
			if(phoneTemplate != null) {
				searchQuery.append("VARCHAR(PHONE)");
				searchQuery.append(" LIKE ");
				searchQuery.append(phoneTemplate);
			}		
		}
		
		Employee employee = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(searchQuery.toString())) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt(Constants.ID_KEY));
				employee.setFirstName(resultSet.getString(Constants.FIRST_NAME_KEY));
				employee.setMiddleName(resultSet.getString(Constants.MIDDLE_NAME_KEY));
				employee.setLastName(resultSet.getString(Constants.LAST_NAME_KEY));
				employee.setPhone(resultSet.getInt(Constants.PHONE_KEY));
				Room room = new Room();
				room.setId(resultSet.getInt(Constants.ROOM_ID_KEY));
				room.setNumber(resultSet.getInt(Constants.ROOM_NUMBER_KEY));
				employee.setRoom(room);
				employees.add(employee);
			}

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + searchQuery, e);
		}
		
		return employees;
	}

	public List<Employee> getEmployeesByRoom(Room room)  throws DaoException{
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_EMPLOYEES_BY_ROOM)) {
			statement.setInt(1, room.getId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt(Constants.ID_KEY));
				employee.setFirstName(resultSet.getString(Constants.FIRST_NAME_KEY));
				employee.setMiddleName(resultSet.getString(Constants.MIDDLE_NAME_KEY));
				employee.setLastName(resultSet.getString(Constants.LAST_NAME_KEY));
				employee.setPhone(resultSet.getInt(Constants.PHONE_KEY));
				Room employeeRoom = new Room();
				employeeRoom.setId(resultSet.getInt(Constants.ROOM_ID_KEY));
				employeeRoom.setNumber(resultSet.getInt(Constants.ROOM_NUMBER_KEY));
				employee.setRoom(employeeRoom);
				employees.add(employee);
			}

		} catch (SQLException e) {

			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.SELECT_EMPLOYEES_BY_ROOM, e);
		}
		
		return employees;
	}

	@Override
	public void update(Employee employee) throws DaoException {
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.UPDATE_EMPLOYEE)) {

			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getMiddleName());
			statement.setInt(4, employee.getPhone());
			statement.setInt(5, employee.getRoom().getId());
			statement.setInt(6, employee.getId());
			statement.executeUpdate();
			

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.UPDATE_EMPLOYEE, e);
		}
		
	}



	@Override
	public Employee getEmployeesById(int id) throws DaoException {
		Employee employee = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_EMPLOYEE_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt(Constants.ID_KEY));
				employee.setFirstName(resultSet.getString(Constants.FIRST_NAME_KEY));
				employee.setMiddleName(resultSet.getString(Constants.MIDDLE_NAME_KEY));
				employee.setLastName(resultSet.getString(Constants.LAST_NAME_KEY));
				employee.setPhone(resultSet.getInt(Constants.PHONE_KEY));
				Room room = new Room();
				room.setId(resultSet.getInt(Constants.ROOM_ID_KEY));
				room.setNumber(resultSet.getInt(Constants.ROOM_NUMBER_KEY));
				employee.setRoom(room);
			}

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.SELECT_EMPLOYEE_BY_ID, e);
		}
		
		return employee;
	}



	@Override
	public void remove(Employee employee) throws DaoException {
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.REMOVE_EMPLOYEE)) {
			statement.setInt(1, employee.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.REMOVE_EMPLOYEE, e);
		}
		
	}



	@Override
	public boolean isExist(Employee employee) throws DaoException {
		boolean isExist = false;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.IS_EMPLOYEE_EXIST)) {
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getMiddleName());
			statement.setString(3, employee.getLastName());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				isExist = true;
			}

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.IS_EMPLOYEE_EXIST, e);
		}
		
		return isExist;
	}



	@Override
	public void add(Employee employee) throws DaoException {
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.ADD_EMPLOYEE)) {
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getMiddleName());
			statement.setString(3, employee.getLastName());
			statement.setInt(4, employee.getPhone());
			statement.setInt(5, employee.getRoom().getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.ADD_EMPLOYEE, e);
		}
		
	}



	@Override
	public Employee getEmployee(String firstName, String lastName,
			String middleName) throws DaoException {
		Employee employee = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.IS_EMPLOYEE_EXIST)) {
			statement.setString(1, firstName);
			statement.setString(2, middleName);
			statement.setString(3, lastName);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getInt(Constants.ID_KEY));
				employee.setFirstName(resultSet.getString(Constants.FIRST_NAME_KEY));
				employee.setMiddleName(resultSet.getString(Constants.MIDDLE_NAME_KEY));
				employee.setLastName(resultSet.getString(Constants.LAST_NAME_KEY));
				employee.setPhone(resultSet.getInt(Constants.PHONE_KEY));
				Room room = new Room();
				room.setId(resultSet.getInt(Constants.ROOM_ID_KEY));
				room.setNumber(resultSet.getInt(Constants.ROOM_NUMBER_KEY));
				employee.setRoom(room);
			}

		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.IS_EMPLOYEE_EXIST, e);
		}
		
		return employee;
	}

	
	
}
