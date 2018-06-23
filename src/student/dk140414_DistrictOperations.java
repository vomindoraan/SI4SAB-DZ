package student;

import operations.CityOperations;
import operations.DistrictOperations;
import student.entities.City;
import student.entities.District;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

import static student.JPAUtil.EM;

public class dk140414_DistrictOperations implements DistrictOperations {
	@Override
	public int insertDistrict(String name, int idCity, int xPos, int yPos) {
		EM.getTransaction().begin();
		
		District district = new District();
		district.setIdCity(idCity);
		district.setName(name);
		district.setxPos(xPos);
		district.setyPos(yPos);
		
		EM.getTransaction().commit();
		return district.getIdDistrict();
	}
	
	@Override
	public int deleteDistricts(String... names) {
		EM.getTransaction().begin();
		
		// Find districts
		TypedQuery<District> q = EM.createQuery("SELECT d FROM District d WHERE d.name IN :names", District.class);
		q.setParameter("names", names);
		List<District> districts= q.getResultList();
		
		districts.forEach(EM::remove);
		
		EM.getTransaction().commit();
		return districts.size();
	}
	
	@Override
	public boolean deleteDistrict(int idDistrict) {
		EM.getTransaction().begin();
		
		District district = EM.find(District.class, idDistrict);
		if (district == null) {
			EM.getTransaction().rollback();
			return false;
		}
		EM.remove(district);
		
		EM.getTransaction().commit();
		return true;
	}
	
	@Override
	public int deleteAllDistrictsFromCity(String cityName) {
		EM.getTransaction().begin();
		
		int idCity;
		try {
			// Find city
			TypedQuery<Integer> q = EM.createQuery("SELECT c.id FROM City c WHERE c.name = :cityName", Integer.class);
			q.setParameter("cityName", cityName);
			idCity = q.getSingleResult();
		} catch (PersistenceException e) {
			EM.getTransaction().rollback();
			return 0;
		}
		
		int deleted = 0;
		for (int id : getAllDistrictsFromCity(idCity)) {
			deleted += deleteDistrict(id) ? 1 : 0;
		}
		
		EM.getTransaction().commit();
		return deleted;
	}
	
	@Override
	public List<Integer> getAllDistrictsFromCity(int idCity) {
		if (EM.find(City.class, idCity) == null) {
			return null;
		}
		return EM.createQuery("SELECT d.id FROM District d JOIN City c ON d.idCity = c.id WHERE d.idCity = :idCity", Integer.class)
		         .setParameter("idCity", idCity)
		         .getResultList();
	}
	
	@Override
	public List<Integer> getAllDistricts() {
		return EM.createQuery("SELECT d.id FROM District d", Integer.class).getResultList();
	}
}
