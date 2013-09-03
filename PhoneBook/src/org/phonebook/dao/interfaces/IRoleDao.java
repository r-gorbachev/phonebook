package org.phonebook.dao.interfaces;

import java.util.List;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.DaoException;


public interface IRoleDao {
	public List<Role>getAllRoles() throws DaoException;
	public Role getRoleById(int id) throws DaoException;
	public Role getRoleByName(String name) throws DaoException;
}
