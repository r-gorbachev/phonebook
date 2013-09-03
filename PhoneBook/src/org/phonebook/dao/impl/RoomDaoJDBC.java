package org.phonebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;

import org.phonebook.beans.Room;
import org.phonebook.constants.Constants;
import org.phonebook.constants.ConstantsSQL;
import org.phonebook.dao.interfaces.IRoomDao;
import org.phonebook.exceptions.DaoException;
import org.phonebook.utils.DBManager;

@Stateless(name="RoomDaoJDBC")
public class RoomDaoJDBC implements IRoomDao {
	
	@Override
	public Room getRoomById(int id) throws DaoException {
		Room room = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_ROOM_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt(Constants.ID_KEY));
				room.setNumber(resultSet.getInt(Constants.NUMBER_KEY));				
			}
			
		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.SELECT_ROOM_BY_ID, e);
		} 
		return room;
	}


	@Override
	public void addRoom(Room room) throws DaoException{
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.ADD_ROOM)) {
			statement.setInt(1, room.getNumber());
			statement.executeUpdate();
		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.ADD_ROOM, e);
		} 
	}


	@Override
	public Room getRoomByNumber(int number) throws DaoException {
		Room room = null;
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.SELECT_ROOM_BY_NUMBER)) {
			statement.setInt(1, number);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt(Constants.ID_KEY));
				room.setNumber(resultSet.getInt(Constants.NUMBER_KEY));				
			}
			
		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.SELECT_ROOM_BY_NUMBER, e);
		} 
		return room;
	}


	@Override
	public void delete(Room room) throws DaoException {
		try (Connection connection = DBManager.getConnection2();
				PreparedStatement statement = connection.
				prepareStatement(ConstantsSQL.DELETE_ROOM)) {
			statement.setInt(1, room.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			DBManager.printSQLException(e);
			throw new DaoException("SQL: " + ConstantsSQL.DELETE_ROOM, e);
		} 
		
	}
	
	
}
