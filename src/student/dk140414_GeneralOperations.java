package student;

import operations.GeneralOperations;

import javax.persistence.PersistenceException;

import static student.JPAUtil.EM;

public class dk140414_GeneralOperations implements GeneralOperations {
	@Override
	public void eraseAll() {
		EM.getTransaction().begin();
		
		try {
			EM.createQuery("DELETE FROM City").executeUpdate();
			EM.createQuery("DELETE FROM Courier").executeUpdate();
			EM.createQuery("DELETE FROM CourierRequest").executeUpdate();
			EM.createQuery("DELETE FROM District").executeUpdate();
			EM.createQuery("DELETE FROM Drive").executeUpdate();
			EM.createQuery("DELETE FROM Package").executeUpdate();
			EM.createQuery("DELETE FROM TransportOffer").executeUpdate();
			EM.createQuery("DELETE FROM User").executeUpdate();
			EM.createQuery("DELETE FROM Vehicle").executeUpdate();
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			throw e;
		}
		
		EM.getTransaction().commit();
	}
}
