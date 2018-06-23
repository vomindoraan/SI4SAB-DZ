package student;

import operations.DistrictOperations;
import student.entities.City;
import student.entities.District;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

import static student.JPAUtil.getEntityManager;

public class dk140414_DistrictOperations implements DistrictOperations {
	@Override
	public int insertDistrict(String name, int idCity, int xPos, int yPos) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		District district = new District();
		district.setIdCity(idCity);
		district.setName(name);
		district.setxPos(xPos);
		district.setyPos(yPos);
		em.persist(district);
		
		em.getTransaction().commit();
		return district.getIdDistrict();
	}
	
	@Override
	public int deleteDistricts(String... names) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		// Find districts
		TypedQuery<District> q = em.createQuery("SELECT d FROM District d WHERE d.name IN :names", District.class);
		q.setParameter("names", names);
		List<District> districts= q.getResultList();
		
		districts.forEach(em::remove);
		
		em.getTransaction().commit();
		return districts.size();
	}
	
	@Override
	public boolean deleteDistrict(int idDistrict) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		District district = em.find(District.class, idDistrict);
		if (district == null) {
			em.getTransaction().rollback();
			return false;
		}
		em.remove(district);
		
		em.getTransaction().commit();
		return true;
	}
	
	@Override
	public int deleteAllDistrictsFromCity(String cityName) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		int idCity;
		try {
			// Find city
			TypedQuery<Integer> q = em.createQuery("SELECT c.id FROM City c WHERE c.name = :cityName", Integer.class);
			q.setParameter("cityName", cityName);
			idCity = q.getSingleResult();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			return 0;
		}
		
		int deleted = 0;
		for (int id : getAllDistrictsFromCity(idCity)) {
			deleted += deleteDistrict(id) ? 1 : 0;
		}
		
		em.getTransaction().commit();
		return deleted;
	}
	
	@Override
	public List<Integer> getAllDistrictsFromCity(int idCity) {
		EntityManager em = getEntityManager();
		if (em.find(City.class, idCity) == null) {
			return null;
		}
		return em.createQuery(
				"SELECT d.idCity FROM District d JOIN City c ON d.idCity = c.idCity WHERE d.idCity = :idCity", Integer.class)
				.setParameter("idCity", idCity)
				.getResultList();
	}
	
	@Override
	public List<Integer> getAllDistricts() {
		return getEntityManager().createQuery("SELECT d.id FROM District d", Integer.class).getResultList();
	}
}
