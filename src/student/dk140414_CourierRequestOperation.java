package student;

import operations.CourierRequestOperation;
import student.entities.CourierRequest;
import student.entities.Vehicle;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

import static student.JPAUtil.EM;

public class dk140414_CourierRequestOperation implements CourierRequestOperation {
	@Override
	public boolean insertCourierRequest(String username, String plateNumber) {
		EM.getTransaction().begin();
		
		try {
			// Set user FK
			CourierRequest request = new CourierRequest();
			request.getUserByIdUser().setUsername(username);
			
			// Set vehicle FK
			TypedQuery<Vehicle> q = EM.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q.setParameter("plateNumber", plateNumber);
			request.setVehicleByIdVehicle(q.getSingleResult());
			
			EM.persist(request);
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			return false;
		}
		
		EM.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean deleteCourierRequest(String username) {
		EM.getTransaction().begin();
		
		try {
			// Find request
			TypedQuery<CourierRequest> q = EM.createQuery(
					"SELECT r FROM CourierRequest r WHERE r.userByIdUser.username = :username", CourierRequest.class);
			q.setParameter("username", username);
			
			EM.remove(q.getSingleResult());
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			return false;
		}
		
		EM.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean changeVehicleInCourierRequest(String username, String plateNumber) {
		EM.getTransaction().begin();
		
		try {
			// Find request
			TypedQuery<CourierRequest> q1 = EM.createQuery(
					"SELECT r FROM CourierRequest r WHERE r.userByIdUser.username = :username", CourierRequest.class);
			q1.setParameter("username", username);
			CourierRequest request = q1.getSingleResult();
			
			// Find vehicle
			TypedQuery<Vehicle> q2 = EM.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q2.setParameter("plateNumber", plateNumber);
			
			request.setVehicleByIdVehicle(q2.getSingleResult());
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			return false;
		}
		
		EM.getTransaction().commit();
		return true;
	}
	
	@Override
	public List<String> getAllCourierRequests() {
		return EM.createQuery("SELECT r.userByIdUser.username FROM CourierRequest r", String.class).getResultList();
	}
	
	@Override
	public boolean grantRequest(String username) {
		EM.getTransaction().begin();
		
		CourierRequest request;
		try {
			// Find request
			TypedQuery<CourierRequest> q = EM.createQuery(
					"SELECT r FROM CourierRequest r WHERE r.userByIdUser.username = :username", CourierRequest.class);
			q.setParameter("username", username);
			request = q.getSingleResult();
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			return false;
		}
		
		// TODO: Call stored procedure
		
		EM.getTransaction().commit();
		return true;
	}
}
