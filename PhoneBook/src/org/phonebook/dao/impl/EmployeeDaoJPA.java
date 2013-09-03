package org.phonebook.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.constants.ConstantsJPA;
import org.phonebook.dao.interfaces.IEmployeeDao;
import org.phonebook.exceptions.DaoException;

@Stateless(name = "EmployeeDaoJPA")
public class EmployeeDaoJPA implements IEmployeeDao {
	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() throws DaoException {
		List<Employee> employees = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_ALL_EMPLOYEES);
			employees = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(query.toString(), e);
		} 
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findEmployees(String nameTemplate,
			String roomTemplate, String phoneTemplate) throws DaoException {
		StringBuilder searchQuery = new StringBuilder();
		searchQuery.append(ConstantsJPA.FIND_ALL_EMPLOYEES_QUERY);
		if(nameTemplate != null || roomTemplate != null || phoneTemplate != null) {
			searchQuery.append(" where ");
			if(nameTemplate != null) {
				searchQuery.append("UPPER(e.lastName) LIKE ");
				searchQuery.append(nameTemplate);
				if(roomTemplate != null || phoneTemplate != null) {
					searchQuery.append(" AND ");
				}
			} 
			if(roomTemplate != null) {
				searchQuery.append("FUNC('VARCHAR', e.room.number) LIKE ");
				searchQuery.append(roomTemplate);
				if(phoneTemplate != null) {
					searchQuery.append(" AND ");
				}
			}		
			
			if(phoneTemplate != null) {
				searchQuery.append("FUNC('VARCHAR', e.phone) LIKE ");
				searchQuery.append(phoneTemplate);
			}		
		}
		Query query = null;
		List<Employee> employees = null;
		try {
			query = em.createQuery(searchQuery.toString());
			employees = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(query.toString(), e);
		} 
		return employees;

	}

	@Override
	public void update(Employee employee) throws DaoException {
		try {
			em.merge(employee);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException (e);
		} 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeesByRoom(Room room) throws DaoException {
		List<Employee> employees = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_EMPLOYEES_BY_ROOM);
			query.setParameter(1, room.getNumber());
			employees = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(query.toString(), e);
		} 
		return employees;
	}

	@Override
	public Employee getEmployeesById(int id) throws DaoException {
		Employee employee = null;
		try {
			employee = em.find(Employee.class, id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		return employee;
	}

	@Override
	public void remove(Employee employee) throws DaoException {
		try {
			em.remove(em.merge(employee));
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		
	}

	@Override
	public boolean isExist(Employee employee) throws DaoException {
		boolean result = false;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_EMPLOYEES);
			query.setParameter(1, employee.getFirstName());
			query.setParameter(2, employee.getLastName());
			query.setParameter(3, employee.getMiddleName());
			result = query.getResultList().size() != 0;			
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		return result;
	}

	@Override 
	public void add(Employee employee) throws DaoException {
		try {
			em.persist(employee);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		
	}

	@Override
	public Employee getEmployee(String firstName, String lastName,
			String middleName) throws DaoException {
		Employee employee = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_EMPLOYEES);
			query.setParameter(1, firstName);
			query.setParameter(2, lastName);
			query.setParameter(3, middleName);
			employee = (Employee) query.getResultList().get(0);		
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		return employee;
	}

}
