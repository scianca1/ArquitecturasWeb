package repositorios;
import clases.Carrera;
import dtos.CarreraDto;
import dtos.EstudianteDto;
import interfaces.CarreraInterface;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;


public class CarreraRepositorio extends Repositorio implements CarreraInterface {

    @Override
    public List<CarreraDto> getCarrerasDto() {
        return null;
    }

    @Override
    public CarreraDto getCarreraDtoById(Integer id) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.CarreraDto(c.nombre, c.duracionAnios)  FROM Carrera c WHERE c.id = ?1";
        TypedQuery<CarreraDto> typedQuery = em.createQuery(jpql, CarreraDto.class);
        typedQuery.setParameter(1, id);
        CarreraDto rta = typedQuery.getSingleResult();
        em.getTransaction().commit();
        this.closeEM();
        return rta;
    }

    @Override
    public Carrera getCarreraById(Integer id) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        String jpql = "SELECT new clases.Carrera(?1,c.nombre, c.duracionAnios)  FROM Carrera c WHERE c.id = ?2";
        TypedQuery<Carrera> typedQuery = em.createQuery(jpql, Carrera.class);
        typedQuery.setParameter(1, id);
        typedQuery.setParameter(2, id);
        Carrera rta = typedQuery.getSingleResult();
        em.getTransaction().commit();
        this.closeEM();
        return rta;
    }

    @Override
    public boolean removeCarrera(Integer id) {
        return true;
    }

    @Override
    public void insertCarrera(Carrera c) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        this.closeEM();
    }
}
