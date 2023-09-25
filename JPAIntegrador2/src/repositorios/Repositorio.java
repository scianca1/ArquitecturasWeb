package repositorios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repositorio {
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public Repositorio() {
    }
    protected EntityManager getEM(){
        if(em == null || !em.isOpen()){
            EntityManagerFactory emf = this.getEMF();
            this.em = emf.createEntityManager();
        }
        return this.em;
    }
    private EntityManagerFactory getEMF(){
        if(emf == null || !emf.isOpen()){
            this.emf = Persistence.createEntityManagerFactory("emf");
        }
        return this.emf;
    }
    protected  void closeEM(){
        this.em.close();
        this.em = null;
        this.emf.close();
        this.emf = null;
    }
}
