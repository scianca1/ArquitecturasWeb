package repositorios;

import clases.Estudiante;
import dtos.EstudianteDto;
import interfaces.EstudianteInterface;
import javax.persistence.*;

import java.util.Collection;
import java.util.List;

public class EstudianteRepositorio extends Repositorio implements EstudianteInterface{


    @Override
    public List<EstudianteDto> getEstudiantesDto() {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        List<EstudianteDto> rta = em.createQuery("SELECT new dtos.EstudianteDto(e.nombre,e.apellido,e.edad,e.genero,e.ciudadResidencia,e.documento,e.numeroLibreta)  FROM Estudiante e").getResultList();
        em.getTransaction().commit();
        this.closeEM();
        return rta;
    }

    @Override
    public EstudianteDto getEstudianteDtoById(Integer id) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.EstudianteDto(e.nombre,e.apellido,e.edad,e.genero,e.ciudadResidencia,e.documento,e.numeroLibreta)  FROM Estudiante e WHERE e.id = ?1";
        TypedQuery<EstudianteDto> typedQuery = em.createQuery(jpql, EstudianteDto.class);
        typedQuery.setParameter(1, id);
        EstudianteDto rta = typedQuery.getSingleResult();
        em.getTransaction().commit();
        this.closeEM();
        return rta;
    }

    @Override
    public Estudiante getEstudianteById(Integer id) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        String jpql = "SELECT new clases.Estudiante("+id+",e.nombre,e.apellido,e.edad,e.genero,e.ciudadResidencia,e.documento,e.numeroLibreta)  FROM Estudiante e WHERE e.id ="+id;
        TypedQuery<Estudiante> typedQuery = em.createQuery(jpql, Estudiante.class);
        Estudiante rta = typedQuery.getSingleResult();
        em.getTransaction().commit();
        this.closeEM();
        return rta;
    }

    @Override
    public boolean removeEstudiante(Integer id) {
        return true;
    }

    @Override
    public void insertEstudiante(Estudiante e) {
        EntityManager em = this.getEM();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        this.closeEM();
    }
}
