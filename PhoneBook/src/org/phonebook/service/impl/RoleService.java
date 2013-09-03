package org.phonebook.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.phonebook.beans.Role;
import org.phonebook.constants.ConstantsError;
import org.phonebook.dao.interfaces.IRoleDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.factories.DaoFactory;
import org.phonebook.service.interfaces.IRoleService;

@Stateless(name = "RoleService")
public class RoleService implements IRoleService {
	@EJB private DaoFactory daoFactory;	
	
	
	@Override
	public List<Role> getAllRoles() throws ServiceException {
		IRoleDao roleDao = daoFactory.getRoleDao();
		List<Role> roles = null;
		try {
			roles = roleDao.getAllRoles();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return roles;
	}

	@Override
	public Role getRoleById(int id) throws ServiceException {
		IRoleDao roleDao = daoFactory.getRoleDao();
		Role role = null;
		try {
			role = roleDao.getRoleById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		System.out.println("RoleService");
		return role;
	}

	@Override
	public Role getRoleByName(String name) throws ServiceException {
		IRoleDao roleDao = daoFactory.getRoleDao();
		Role role = null;
		try {
			role = roleDao.getRoleByName(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return role;
	}

}
