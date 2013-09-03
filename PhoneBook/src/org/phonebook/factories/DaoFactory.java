package org.phonebook.factories;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import org.phonebook.dao.interfaces.IEmployeeDao;
import org.phonebook.dao.interfaces.IRoleDao;
import org.phonebook.dao.interfaces.IRoomDao;
import org.phonebook.dao.interfaces.IUserDao;

@Singleton
public class DaoFactory {
	@EJB(beanName = "EmployeeDaoJPA") private IEmployeeDao employeeDao;
	@EJB(beanName = "UserDaoJPA") private IUserDao userDao;
	@EJB(beanName = "RoleDaoJPA") private IRoleDao roleDao;
	@EJB(beanName = "RoomDaoJPA") private IRoomDao roomDao;
	
	public IEmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	public IRoleDao getRoleDao() {
		return roleDao;
	}
	public IRoomDao getRoomDao() {
		return roomDao;
	}
	
	
}
