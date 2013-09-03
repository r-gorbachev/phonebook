package org.phonebook.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.constants.ConstantsError;
import org.phonebook.dao.interfaces.IEmployeeDao;
import org.phonebook.dao.interfaces.IRoomDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.factories.DaoFactory;
import org.phonebook.service.interfaces.IEmployeeService;

@Stateless(name = "EmployeeService")
public class EmployeeService implements IEmployeeService {
	@EJB private DaoFactory daoFactory;	

	@Override
	public List<Employee> getAllEmployees() throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		List<Employee> employees = null;
		try {
			employees = employeeDao.getAllEmployees();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return employees;
	}

	@Override
	public List<Employee> findEmployees(String lastName, int nameSearchType,
			String roomNumber, int roomSearchType, String phone,
			int phoneSearchType) throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		List<Employee> employees = null;
		lastName = getSearchTemplate(lastName.toUpperCase(), nameSearchType);
		roomNumber = getSearchTemplate(roomNumber, roomSearchType);
		phone = getSearchTemplate(phone.replaceAll("[\\-]", ""), phoneSearchType);
		try {
			employees = employeeDao.findEmployees(lastName, roomNumber, phone);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return employees;
	}
	
	@Override
	public Employee get(int id) throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		Employee employee = null;
		try {
			employee = employeeDao.getEmployeesById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return employee;
		
		
	}
	
	@Override
	public Employee get(String firstName, String lastName, String middleName)
			throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		Employee employee = null;
		try {
			employee = employeeDao.getEmployee(firstName, lastName, middleName);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return employee;
	}

	@Override
	public void update(Employee employee) throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		IRoomDao roomDao = daoFactory.getRoomDao();
		List<Employee> employees = null;
		try {
			Employee oldEmployee = employeeDao.getEmployeesById(employee.getId());
			if(oldEmployee == null) {
				throw new ServiceException(ConstantsError.DATA_SOURCE);
			}
			Room oldRoom = oldEmployee.getRoom();
			if(oldRoom.getNumber() != employee.getRoom().getNumber()) {
				employees = employeeDao.getEmployeesByRoom(oldEmployee.getRoom());
				Room room = roomDao.getRoomByNumber(employee.getRoom().getNumber());
				if(room == null) {
					room = new Room();
					room.setNumber(employee.getRoom().getNumber());
					roomDao.addRoom(room);
					room = roomDao.getRoomByNumber(employee.getRoom().getNumber());
				} 
				employee.setRoom(room);
				
			}
			employeeDao.update(employee);
			if(employees != null && employees.size() == 1) {
				roomDao.delete(oldRoom);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		
	}

	@Override
	public void remove(Employee employee) throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		IRoomDao roomDao = daoFactory.getRoomDao();
		List<Employee> employees;
		try {
			employeeDao.remove(employee);
			employees = employeeDao.getEmployeesByRoom(employee.getRoom());
			if(employees.size() == 0) {;
				roomDao.delete(employee.getRoom());		
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		
	}
	

	@Override
	public boolean addEmployee(Employee employee) throws ServiceException {
		IEmployeeDao employeeDao = daoFactory.getEmployeeDao();
		IRoomDao roomDao = daoFactory.getRoomDao();
		boolean addedSuccess = false;
		try {
			if(!employeeDao.isExist(employee)) {
				Room room = roomDao.getRoomByNumber(employee.getRoom().getNumber());
				if(room == null) {
					roomDao.addRoom(employee.getRoom());
					room = roomDao.getRoomByNumber(employee.getRoom().getNumber());
				}
				employee.setRoom(room);
				employeeDao.add(employee);
				addedSuccess = true;
			} 
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return addedSuccess;
	}
	
	private String getSearchTemplate(String field, int searchType) {
		String result = null;
		char anychar = '%';
		if(field == null || "".equals(field)) {
			return null;
		}
		switch(searchType) {
		case 1:
			result = String.format("'%s%c'", field, anychar);
			break;
		case 2:
			result = String.format("'%c%s%c'", anychar, field, anychar);
			break;
		case 3:
			result = String.format("'%c%s'", anychar, field);
			break;
		}
		return result;
	}
	
}
