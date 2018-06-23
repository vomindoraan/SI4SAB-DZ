package student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory EMF;
	
	static {
		try {
			EMF = Persistence.createEntityManagerFactory("DeliveryServicePU");
		} catch (Throwable t) {
			System.err.println("Cannot initialize persistence context");
			throw new ExceptionInInitializerError(t);
		}
	}
	
//	public static EntityManagerFactory getEntityManagerFactory() {
//		return EMF;
//	}
//
//	public static EntityManager getEntityManager() {
//		return EMF.createEntityManager();
//	}

	private JPAUtil() {}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return EMF;
	}
	
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
}
