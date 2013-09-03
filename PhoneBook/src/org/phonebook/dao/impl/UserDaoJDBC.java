package org.phonebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.phonebook.beans.Role;
import org.phonebook.beans.User;
import org.phonebook.constants.Constants;
import org.phonebook.constants.ConstantsSQL;
import org.phonebook.dao.interfaces.IUserDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.utils.DBManager;

@Stateless(name="UserDaoJDBC")
public class UserDaoJDBC implements IUserDao {
	
	@Override
	public List<User> getAllUsers() throws DaoException {
		List<User> users = new ArrayList<User>();
		User user = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = 	connection.
				prepareStatement(ConstantsSQL.SELECT_ALL_USERS)) {
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(Constants.ID_KEY));
				user.setEmail(resultSet.getString(Constants.EMAIL_KEY));
				user.setName(resultSet.getString(Constants.NAME_KEY));
				resultSet.getInt(Constants.ROLE_ID_KEY);
				user.setRole(new Role(resultSet.getInt(Constants.ROLE_ID_KEY),
						resultSet.getString(Constants.ROLE_NAME_KEY)));
				users.add(user);
			}
		} catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_ALL_USERS, e);
		}
		return users;
	}
	

	@Override
	public User getUser(String email, String password) throws DaoException {
		User user = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = 	connection.
				prepareStatement(ConstantsSQL.SELECT_USER_BY_EMAIL_PASSWORD)) {
			statement.setString(1, email.trim());
			statement.setString(2, password.trim());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(Constants.ID_KEY));
				user.setEmail(resultSet.getString(Constants.EMAIL_KEY));
				user.setName(resultSet.getString(Constants.NAME_KEY));
				resultSet.getInt(Constants.ROLE_ID_KEY);
				user.setRole(new Role(resultSet.getInt(Constants.ROLE_ID_KEY),
						resultSet.getString(Constants.ROLE_NAME_KEY)));
			}
		} catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_USER_BY_EMAIL_PASSWORD, e);
		}
		return user;
	}

	@Override
	public void addUser(User user) throws DaoException {
		try(Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.ADD_USER)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getRole().getId());
			statement.executeUpdate();			
		}  catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.ADD_USER, e);
		}

	}

	@Override
	public void removeUser(User user) throws DaoException {
		try(Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.DELETE_USER)) {
			statement.setInt(1, user.getId());
			statement.executeUpdate();			
		}  catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.DELETE_USER, e);
		}

		
	}

	@Override
	public boolean isUserExist(String email) throws DaoException {
		boolean result = false;
		try(Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.IS_USER_EXIST);) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				result = true;
			}
		} catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.IS_USER_EXIST, e);
		}

		return result;
	}


	@Override
	public User getUser(int id) throws DaoException {
		User user = null;
		try(Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_USER)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();	
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(Constants.ID_KEY));
				user.setEmail(resultSet.getString(Constants.EMAIL_KEY));
				user.setName(resultSet.getString(Constants.NAME_KEY));
				resultSet.getInt(Constants.ROLE_ID_KEY);
				user.setRole(new Role(resultSet.getInt(Constants.ROLE_ID_KEY),
						resultSet.getString(Constants.ROLE_NAME_KEY)));
			}
		}  catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_USER, e);
		}
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws DaoException {
		User user = null;
		try(Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_USER_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();	
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(Constants.ID_KEY));
				user.setEmail(resultSet.getString(Constants.EMAIL_KEY));
				user.setName(resultSet.getString(Constants.NAME_KEY));
				resultSet.getInt(Constants.ROLE_ID_KEY);
				user.setRole(new Role(resultSet.getInt(Constants.ROLE_ID_KEY),
						resultSet.getString(Constants.ROLE_NAME_KEY)));
			}
		}  catch(SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL : " + ConstantsSQL.SELECT_USER_BY_EMAIL, e);
		}
		return user;
	}

}
