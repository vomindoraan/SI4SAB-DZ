package student;

import operations.CityOperations;
import student.entities.City;

import java.util.List;
import javax.persistence.*;

import static student.JPAUtil.EM;

public class dk140414_CityOperations implements CityOperations {
	@Override
	public int insertCity(String name, String postalCode) {
		EM.getTransaction().begin();

		City city = new City();
		city.setName(name);
		city.setPostalCode(postalCode);
		EM.persist(city);
		
		EM.getTransaction().commit();
		return city.getIdCity();
	}
	
	@Override
	public int deleteCity(String... names) {
		EM.getTransaction().begin();
		
		// Find cities
		TypedQuery<City> q = EM.createQuery("SELECT c FROM City c WHERE c.name IN :names", City.class);
		q.setParameter("names", names);
		List<City> cities = q.getResultList();
		
		cities.forEach(EM::remove);
		
		EM.getTransaction().commit();
		return cities.size();
	}
	
	@Override
	public boolean deleteCity(int idCity) {
		EM.getTransaction().begin();
		
		City city = EM.find(City.class, idCity);
		if (city == null) {
			EM.getTransaction().rollback();
			return false;
		}
		EM.remove(city);
		
		EM.getTransaction().commit();
		return true;
	}
	
	@Override
	public List<Integer> getAllCities() {
		return EM.createQuery("SELECT c.idCity FROM City c", Integer.class).getResultList();
	}
}
