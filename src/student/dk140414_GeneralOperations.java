package student;

import operations.GeneralOperations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static student.JPAUtil.getEntityManager;

public class dk140414_GeneralOperations implements GeneralOperations {
	@Override
	public void eraseAll() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			em.createQuery("DELETE FROM City").executeUpdate();
			em.createQuery("DELETE FROM Courier").executeUpdate();
			em.createQuery("DELETE FROM CourierRequest").executeUpdate();
			em.createQuery("DELETE FROM District").executeUpdate();
			em.createQuery("DELETE FROM Drive").executeUpdate();
			em.createQuery("DELETE FROM Package").executeUpdate();
			em.createQuery("DELETE FROM TransportOffer").executeUpdate();
			em.createQuery("DELETE FROM User").executeUpdate();
			em.createQuery("DELETE FROM Vehicle").executeUpdate();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			throw e;
		}
		
		em.getTransaction().commit();
	}
}
