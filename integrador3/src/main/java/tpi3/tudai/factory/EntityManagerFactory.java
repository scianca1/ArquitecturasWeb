package tpi3.tudai.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class EntityManagerFactory implements ServletContextListener {
		private static jakarta.persistence.EntityManagerFactory emf;
		
		@Override
		public void contextInitialized(ServletContextEvent arg) {
			emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		}
		
		@Override
		public void contextDestroyed(ServletContextEvent arg) {
			emf.close();
		}
		
		public static EntityManager createEntityManager() {
			if (emf == null) {
					throw new IllegalStateException("Context is not initialized yet.");
			}
			return emf.createEntityManager();
		}
	}