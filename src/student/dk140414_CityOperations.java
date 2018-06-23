package student;

import operations.CityOperations;
import student.entities.City;

import java.util.List;
import javax.persistence.*;

import static student.JPAUtil.getEntityManager;

public class dk140414_CityOperations implements CityOperations {
	@Override
	public int insertCity(String name, String postalCode) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		City city = new City();
		try {
			city.setName(name);
			city.setPostalCode(postalCode);
			em.persist(city);
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return -1;
		}
		
		em.getTransaction().commit();
		return city.getIdCity();
	}
	
	@Override
	public int deleteCity(String... names) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		// Find cities
		TypedQuery<City> q = em.createQuery("SELECT c FROM City c WHERE c.name IN :names", City.class);
		q.setParameter("names", names);
		List<City> cities = q.getResultList();
		
		cities.forEach(em::remove);
		
		em.getTransaction().commit();
		return cities.size();
	}
	
	@Override
	public boolean deleteCity(int idCity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		City city = em.find(City.class, idCity);
		if (city == null) {
			em.getTransaction().rollback();
			return false;
		}
		em.remove(city);
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public List<Integer> getAllCities() {
		return getEntityManager().createQuery("SELECT c.idCity FROM City c", Integer.class).getResultList();
	}
}
