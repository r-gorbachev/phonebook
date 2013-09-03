package org.phonebook.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.phonebook.beans.Room;
import org.phonebook.constants.ConstantsJPA;
import org.phonebook.dao.interfaces.IRoomDao;
import org.phonebook.exceptions.DaoException;

@Stateless(name = "RoomDaoJPA")
public class RoomDaoJPA implements IRoomDao {
	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public Room getRoomById(int id) throws DaoException {
		Room room = null;
		try {
			room = em.find(Room.class, id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 		
		return room;
	}

	@Override
	public void addRoom(Room room) throws DaoException {
		try {
			em.persist(room);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 		
		
	}

	@Override
	public Room getRoomByNumber(int number) throws DaoException {
		Room room = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_ROOM_BY_NUMBER);
			query.setParameter(1, number);
			room = (Room) query.getSingleResult();
		} catch (NoResultException ne) {
			room = null;
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(pe);
		}
		return room;
	}

	@Override
	public void delete(Room room) throws DaoException {
		try {
			em.remove(em.merge(room));
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 		
		
	}

}
