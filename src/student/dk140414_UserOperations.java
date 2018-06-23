package student;

import operations.UserOperations;
import student.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static student.JPAUtil.getEntityManager;

public class dk140414_UserOperations implements UserOperations {
	
	@Override
	public boolean insertUser(String username, String firstName, String lastName, String password) {
		return false; // TODO
	}
	
	@Override
	public int declareAdmin(String paramString) {
		return 0; // TODO
	}
	
	@Override
	public Integer getSentPackages(String... usernames) {
		return getEntityManager().createQuery(
				"SELECT SUM(u.packagesSent) FROM User u WHERE u.username IN :usernames", Integer.class)
				.setParameter("usernames", usernames)
				.getSingleResult();
	}
	
	@Override
	public int deleteUsers(String... usernames) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		// Find cities
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username IN :usernames", User.class);
		q.setParameter("usernames", usernames);
		List<User> users = q.getResultList();
		
		users.forEach(em::remove);
		
		em.getTransaction().commit();
		return users.size();
	}
	
	@Override
	public List<String> getAllUsers() {
		return getEntityManager().createQuery("SELECT u.username FROM User u", String.class).getResultList();
	}
}
