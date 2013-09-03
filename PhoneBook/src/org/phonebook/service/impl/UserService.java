package org.phonebook.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.phonebook.beans.User;
import org.phonebook.constants.ConstantsError;
import org.phonebook.dao.interfaces.IUserDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.factories.DaoFactory;
import org.phonebook.service.interfaces.IUserService;

@Stateless(name = "UserService")
public class UserService implements IUserService {
	@EJB private DaoFactory daoFactory;

	@Override
	public List<User> getAllUsers() throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		List<User> users = null;
		try {
			users = userDao.getAllUsers();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return users;
	}

	@Override
	public User getUser(String email, String password) throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		User user = null;
		try {
			user = userDao.getUser(email, password);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return user;
	}

	@Override
	public boolean addUser(User user) throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		boolean result = false;
		try {
			if(!userDao.isUserExist(user.getEmail())) {
				result = true;
				userDao.addUser(user);
			}
		} catch (DaoException e) {
			result = false;
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return result;
	}

	@Override
	public void removeUser(User user) throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		try {
			userDao.removeUser(user);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		
	}

	@Override
	public User getUser(int id) throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		User user = null;
		try {
			user = userDao.getUser(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return user;
	}

	@Override
	public User getUser(String email) throws ServiceException {
		IUserDao userDao = daoFactory.getUserDao();
		User user = null;
		try {
			user = userDao.getUserByEmail(email);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(ConstantsError.DATA_SOURCE, e);
		}
		return user;
	}
}
