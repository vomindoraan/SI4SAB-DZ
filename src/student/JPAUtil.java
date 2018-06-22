package student;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	public static final EntityManagerFactory EMF;
	
	static {
		try {
			EMF = Persistence.createEntityManagerFactory("DeliveryServicePU");
		} catch (Throwable t) {
			System.err.println("Cannot create EntityManagerFactory");
			throw new ExceptionInInitializerError(t);
		}
	}
	
	private JPAUtil() {}
}
