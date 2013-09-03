package org.phonebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.phonebook.beans.Role;
import org.phonebook.constants.Constants;
import org.phonebook.constants.ConstantsSQL;
import org.phonebook.dao.interfaces.IRoleDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.utils.DBManager;

@Stateless(name="RoleDaoJDBC")
public class RoleDaoJDBC implements IRoleDao {
	
	@Override
	public Role getRoleById(int id) throws DaoException{
		Role role = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_ROLE_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt(Constants.ID_KEY));
				role.setName(resultSet.getString(Constants.NAME_KEY));				
			}
		} catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_ROLE_BY_ID, e);
		}
		return role;
	}

	@Override
	public Role getRoleByName(String name) throws DaoException{
		Role role = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_ROLE_BY_NAME)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt(Constants.ID_KEY));
				role.setName(resultSet.getString(Constants.NAME_KEY));				
			}	
		} catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_ROLE_BY_NAME, e);
		}
		return role;
	}

	@Override
	public List<Role> getAllRoles() throws DaoException{
		// TODO Auto-generated method stub
		return null;
	}
}
