package repositorios;
import clases.Carrera;
import dtos.CarreraDto;
import dtos.EstudianteDto;
import interfaces.CarreraInterface;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
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
        String jpql = "SELECT new clases.Carrera("+id+",c.nombre, c.duracionAnios)  FROM Carrera c WHERE c.id ="+id;
        TypedQuery<Carrera> typedQuery = em.createQuery(jpql, Carrera.class);
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
    public List<CarreraDto> getCarrerasOrderByInscriptos(){
        EntityManager em = this.getEM();
        String jpql = "SELECT new dtos.CarreraDto(c.id, c.nombre, c.duracionAnios, COUNT(ec.estudiante.id)) FROM Carrera c JOIN EstudianteCarrera ec  ON (c.id= ec.carrera.id) GROUP BY ec.carrera.id, c.nombre, c.duracionAnios ORDER BY COUNT(ec.estudiante.id) DESC";
        em.getTransaction().begin();
        TypedQuery<CarreraDto> query = em.createQuery(jpql, CarreraDto.class);
        List<CarreraDto> resultado= query.getResultList();
        em.getTransaction().commit();
        return resultado;
    }
   
//    public List<CarreraDto> getCarrerasReport(){
//    	List<CarreraDto> allCarreras = this.getCarrerasDto();
//    	List<CarreraDto> rta = new ArrayList<CarreraDto>();
//        EntityManager em = this.getEM();
//        em.getTransaction().begin();
//        for (CarreraDto carrera : allCarreras){
//            String jpql = "SELECT new dtos.EstudianteDto(e.nombre, e.apellido, e.edad, e.genero, e.ciudadResidencia, e.documento, e.numeroLibreta) FROM Estudiante e JOIN EstudianteCarrera ec ON e.id = ec.estudiante.id AND ec.carrera.id = ?1";
//            //String jpql = "SELECT new dtos.CarreraDto(estudiantes) FROM Estudiante e JOIN EstudianteCarrera ec  ON (c.id= ec.carrera.id) GROUP BY ec.carrera.id ORDER BY COUNT(ec.estudiante.id) DESC";
//            TypedQuery<CarreraDto> typedQuery = em.createQuery(jpql, CarreraDto.class);
//            typedQuery.setParameter(1, carrera.getId());
//            List<CarreraDto> resultado= typedQuery.getResultList();
//            em.getTransaction().commit();
//            rta.addAll(resultado);
//        }
//        return rta;
//    	}
    
  public List<CarreraDto> getCarrerasReport(){
	
    EntityManager em = this.getEM();
    em.getTransaction().begin();
   
        String jpql = "SELECT new dtos.CarreraDto(c.nombre,anio,count(cantEgresados),count(CantInscriptos)) FROM carrera c JOIN EstudianteCarrera ec ON c.id =ec.carrera.id group by ";
        //String jpql = "SELECT new dtos.CarreraDto(estudiantes) FROM Estudiante e JOIN EstudianteCarrera ec  ON (c.id= ec.carrera.id) GROUP BY ec.carrera.id ORDER BY COUNT(ec.estudiante.id) DESC";
        TypedQuery<CarreraDto> typedQuery = em.createQuery(jpql, CarreraDto.class);
        List<CarreraDto> resultado= typedQuery.getResultList();
        em.getTransaction().commit();
        return resultado;
	}

}
