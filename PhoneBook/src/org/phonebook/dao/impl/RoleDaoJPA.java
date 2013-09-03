package org.phonebook.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.phonebook.beans.Role;
import org.phonebook.constants.ConstantsJPA;
import org.phonebook.dao.interfaces.IRoleDao;
import org.phonebook.exceptions.DaoException;

@Stateless(name="RoleDaoJPA")
public class RoleDaoJPA implements IRoleDao {
	@PersistenceContext(unitName="default")
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() throws DaoException {
		List<Role> roles = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_ALL_ROLES);
			roles = (List<Role>)query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DaoException(query.toString(), e);
		} 
		return roles;
	}

	@Override
	public Role getRoleById(int id) throws DaoException {
		Role role = null;
		try {
			role = em.find(Role.class, id);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 		
		return role;
	}

	@Override
	public Role getRoleByName(String name) throws DaoException {
		Role role = null;
		Query query = null;
		try {
			query = em.createNamedQuery(ConstantsJPA.FIND_ROLE_BY_NAME).
					setParameter(1, name).setMaxResults(1);
			role = (Role)query.getSingleResult();
		} catch (NoResultException ne) {
			role = null;
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(query.toString(), pe);
		}
		return role;
	}

}
