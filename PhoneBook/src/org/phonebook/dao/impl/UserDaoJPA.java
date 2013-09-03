package org.phonebook.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.phonebook.beans.User;
import org.phonebook.constants.ConstantsJPA;
import org.phonebook.dao.interfaces.IUserDao;
import org.phonebook.exceptions.DaoException;

@Stateless(name = "UserDaoJPA")
public class UserDaoJPA implements IUserDao {
	@PersistenceContext(unitName="default")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws DaoException {
		List<User> users = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_ALL_USERS);
			users = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(query.toString(), e);
		}
		return users;

	}

	@Override
	public User getUser(String email, String password) throws DaoException {
		User user = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_USER_AUTH);
			query.setParameter(1, email);
			query.setParameter(2, password);
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(query.toString(), pe);
		}
		return user;
	}

	@Override
	public boolean isUserExist(String email) throws DaoException {
		User user = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_USER_BY_EMAIL);
			query.setParameter(1, email);
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(query.toString(), pe);
		}
		return user != null ;
	}

	@Override
	public void addUser(User user) throws DaoException {
		try {
			em.persist(user);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
		
	}

	@Override
	public void removeUser(User user) throws DaoException {
		try {
			em.remove(em.merge(user));
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 		
	}

	@Override
	public User getUser(int id) throws DaoException {
		User user = null;
		try {
			user = em.find(User.class, id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 	
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws DaoException {
		User user = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_USER_BY_EMAIL);
			query.setParameter(1, email);
			user = (User) query.getResultList().get(0);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(query.toString(), pe);
		}
		return user;
	}

}
