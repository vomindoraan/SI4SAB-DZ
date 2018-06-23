package student;

import operations.VehicleOperations;
import student.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

import static student.JPAUtil.getEntityManager;

public class dk140414_VehicleOperations implements VehicleOperations {
	@Override
	public boolean insertVehicle(String plateNumber, int fuelType, BigDecimal fuelConsumption) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setPlateNumber(plateNumber);
			vehicle.setFuelType(fuelType);
			vehicle.setFuelConsumption(fuelConsumption);
			em.persist(vehicle);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public int deleteVehicles(String... plateNumbers) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		// Find vehicles
		TypedQuery<Vehicle> q = em.createQuery(
				"SELECT v FROM Vehicle v WHERE v.plateNumber IN :plateNumbers", Vehicle.class);
		q.setParameter("plateNumbers", plateNumbers);
		List<Vehicle> vehicles = q.getResultList();
		
		vehicles.forEach(em::remove);
		
		em.getTransaction().commit();
		return vehicles.size();
	}
	
	@Override
	public List<String> getAllVehichles() {
		return getEntityManager().createQuery("SELECT v.plateNumber FROM Vehicle v", String.class).getResultList();
	}
	
	@Override
	public boolean changeFuelType(String plateNumber, int fuelType) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			TypedQuery<Vehicle> q = em.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q.setParameter("plateNumber", plateNumber);
			q.getSingleResult().setFuelType(fuelType);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean changeConsumption(String plateNumber, BigDecimal fuelConsumption) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		try {
			TypedQuery<Vehicle> q = em.createQuery(
					"SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber", Vehicle.class);
			q.setParameter("plateNumber", plateNumber);
			q.getSingleResult().setFuelConsumption(fuelConsumption);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		return true;
	}
}
