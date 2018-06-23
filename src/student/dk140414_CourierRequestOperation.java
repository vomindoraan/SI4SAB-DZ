package student;

import operations.CourierRequestOperation;
import student.entities.CourierRequest;
import student.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

import static student.JPAUtil.getEntityManager;

public class dk140414_CourierRequestOperation implements CourierRequestOperation {
	@Override
	public boolean insertCourierRequest(String username, String plateNumber) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			// Set user FK
			CourierRequest request = new CourierRequest();
			request.getUserByIdUser().setUsername(username);
			
			// Set vehicle FK
			TypedQuery<Vehicle> q = em.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q.setParameter("plateNumber", plateNumber);
			request.setVehicleByIdVehicle(q.getSingleResult());
			
			em.persist(request);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean deleteCourierRequest(String username) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			// Find request
			TypedQuery<CourierRequest> q = em.createQuery(
					"SELECT r FROM CourierRequest r WHERE r.userByIdUser.username = :username", CourierRequest.class);
			q.setParameter("username", username);
			
			em.remove(q.getSingleResult());
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean changeVehicleInCourierRequest(String username, String plateNumber) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			// Find request
			TypedQuery<CourierRequest> q1 = em.createQuery(
					"SELECT r FROM CourierRequest r WHERE r.userByIdUser.username = :username", CourierRequest.class);
			q1.setParameter("username", username);
			CourierRequest request = q1.getSingleResult();
			
			// Find vehicle
			TypedQuery<Vehicle> q2 = em.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q2.setParameter("plateNumber", plateNumber);
			
			request.setVehicleByIdVehicle(q2.getSingleResult());
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public List<String> getAllCourierRequests() {
		return getEntityManager().createQuery(
				"SELECT r.userByIdUser.username FROM CourierRequest r", String.class)
				.getResultList();
	}
	
	@Override
	public boolean grantRequest(String username) {
		return (Boolean) getEntityManager().createStoredProcedureQuery("grantRequest")
		                                   .setParameter("username", username)
		                                   .getSingleResult();
	}
}
