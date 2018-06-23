package student;

import operations.CourierOperations;
import student.entities.Courier;
import student.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

import static student.JPAUtil.getEntityManager;

public class dk140414_CourierOperations implements CourierOperations {
	@Override
	public boolean insertCourier(String username, String plateNumber) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			// Set user FK
			Courier courier = new Courier();
			courier.getUserByIdUser().setUsername(username);
			
			// Set vehicle FK
			TypedQuery<Vehicle> q = em.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q.setParameter("plateNumber", plateNumber);
			courier.setVehicleByIdVehicle(q.getSingleResult());
			
			// Set basic attributes
			courier.setStatus(Courier.STATUS_NOT_DRIVING);
			courier.setDeliveries(0);
			courier.setProfit(BigDecimal.ZERO);
			
			em.persist(courier);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean deleteCourier(String username) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			// Find courier
			TypedQuery<Courier> q = em.createQuery(
					"SELECT c FROM Courier c WHERE c.userByIdUser.username = :username", Courier.class);
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
	public List<String> getCouriersWithStatus(int status) {
		return getEntityManager().createQuery(
				"SELECT c.userByIdUser.username FROM Courier c WHERE c.status = :status", String.class)
				.setParameter("status", status)
				.getResultList();
	}
	
	@Override
	public List<String> getAllCouriers() {
		return getEntityManager().createQuery(
				"SELECT c.userByIdUser.username FROM Courier c ORDER BY c.profit DESC", String.class)
				.getResultList();
//		TypedQuery<Courier> q = getEntityManager().createQuery("SELECT c FROM Courier c", Courier.class);
//		return q.getResultList().stream()
//				.sorted(Comparator.comparing(Courier::getProfit).reversed())
//				.map(c -> c.getUserByIdUser().getUsername())
//				.collect(Collectors.toList());
	}
	
	@Override
	public BigDecimal getAverageCourierProfit(int deliveryThreshold) {
		return getEntityManager().createQuery(
				"SELECT AVG(c.profit) FROM Courier c WHERE c.deliveries >= :threshold", BigDecimal.class)
				.setParameter("threshold", deliveryThreshold)
				.getSingleResult();
	}
}
