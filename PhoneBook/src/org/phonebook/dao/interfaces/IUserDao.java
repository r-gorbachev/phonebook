package org.phonebook.dao.interfaces;

import java.util.List;

import org.phonebook.beans.User;
import org.phonebook.exceptions.DaoException;


public interface IUserDao {
	public List<User> getAllUsers() throws DaoException;
	public User getUser(String email, String password) throws DaoException;
	public boolean isUserExist(String email) throws DaoException;
	public void addUser(User user) throws DaoException;
	public void removeUser(User user) throws DaoException;
	public User getUser(int id) throws DaoException;
	public User getUserByEmail(String email) throws DaoException;
}
