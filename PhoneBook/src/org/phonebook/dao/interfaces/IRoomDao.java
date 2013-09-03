package org.phonebook.dao.interfaces;

import org.phonebook.beans.Room;
import org.phonebook.exceptions.DaoException;

public interface IRoomDao {
	public Room getRoomById(int id) throws DaoException;
	public void addRoom(Room room) throws DaoException;
	public Room getRoomByNumber(int number) throws DaoException;
	public void delete(Room room)  throws DaoException;
}
