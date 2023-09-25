package clases;

import clases.Carrera;
import clases.Estudiante;
import clases.EstudianteCarrera;
import dtos.CarreraDto;
import dtos.EstudianteDto;
import helpers.ReadSCV;
import repositorios.CarreraRepositorio;
import repositorios.EstudianteCarreraRepositorio;
import repositorios.EstudianteRepositorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args) throws SQLException, FileNotFoundException, IOException {
        //dar de alta estudiante
//        darDeAltaUnEstudiante(8000,"nombre","apellido",15,"genero","ciudadResidencia","dni",254871);


        //dar de alta carrera
//        darDeAltaUnaCarrera(20,"cualquiera",2.5);

        //recuperar todos los estudiantes
//        List<EstudianteDto> estudiantes =  getAllEstudiantes();
//        System.out.println(estudiantes);

        //recuperar estudiante por id
//        EstudianteDto est = getEstudianteById(102);
//        System.out.println(est);

        //recuperar carrera por id
//        CarreraDto carr = getCarreraById(2);
//        System.out.println(carr);

        //matricular un estudiante en una carrera
//        matricularEstudianteHoy(8000,20);

        //ver carreras de un estudiante
        //ArrayList<EstudianteCarrera> carrs = verCarrerasDeEstudiante(102);

        //leer y cargar datos (estudiantes, carreras y estudianteCarreras)
//        cargarDatos();
    	
    	//recuperar carreras con estudiante inscriptos, ordenadas x cantidad dde inscriptos
        List<CarreraDto> rta = getCarrerasOrderByInscriptos();
        System.out.println(rta);



    }

    public static void darDeAltaUnEstudiante(Integer id,String nombre,String apellido,Integer edad, String genero, String ciudadResidencia,String dni ,Integer numeroLibreta ){
        Estudiante estudiante = new Estudiante(id,nombre, apellido, edad,genero,ciudadResidencia,dni ,numeroLibreta);
        EstudianteRepositorio er = new EstudianteRepositorio();
        er.insertEstudiante(estudiante);
    }
    public static void darDeAltaUnaCarrera(Integer id,String nombreCarrera, double duracionAnios){
        Carrera carrera = new Carrera(id,nombreCarrera, duracionAnios);
        CarreraRepositorio cr = new CarreraRepositorio();
        cr.insertCarrera(carrera);
    }

    public static List<EstudianteDto> getAllEstudiantes(){
        EstudianteRepositorio er = new EstudianteRepositorio();
        List<EstudianteDto> estudiantes =  er.getEstudiantesDto();
        return estudiantes;
    }

    public static EstudianteDto getEstudianteById(Integer id){
        EstudianteRepositorio er = new EstudianteRepositorio();
        EstudianteDto estudiante =  er.getEstudianteDtoById(id);
        return estudiante;
    }

    public static CarreraDto getCarreraById(Integer id){
        CarreraRepositorio cr = new CarreraRepositorio();
        CarreraDto carrera =  cr.getCarreraDtoById(id);
        return  carrera;
    }

    public static void matricularEstudianteHoy(Integer idEstudiante, Integer idCarrera){
        EstudianteRepositorio er = new EstudianteRepositorio();
        Estudiante estudiante =  er.getEstudianteById(idEstudiante);
        CarreraRepositorio cr = new CarreraRepositorio();
        Carrera carrera =  cr.getCarreraById(idCarrera);
        LocalDate hoy = LocalDate.now();
        EstudianteCarrera ec = new EstudianteCarrera(hoy, estudiante, carrera);
        EstudianteCarreraRepositorio ecr = new EstudianteCarreraRepositorio();
        ecr.insertEstudianteCarrera(ec);
    }

    public static ArrayList<EstudianteCarrera> verCarrerasDeEstudiante(Integer id){
        EstudianteRepositorio er = new EstudianteRepositorio();
        Estudiante estudiante =  er.getEstudianteById(id);
        ArrayList<EstudianteCarrera> carreras = estudiante.getCarreras();
        return carreras;
    }

  
    public static void cargarDatos()throws SQLException, FileNotFoundException, IOException{
    	ReadSCV rscv = new ReadSCV();
    	//carga estudiantes
        String scvEstudiantes = "../JPAIntegrador2/src/datasets/estudiantes.csv";
        rscv.readCSVEstudiante(scvEstudiantes);
        
        //carga carreras
        String scvCarreras = "../JPAIntegrador2/src/datasets/carreras.csv";
        rscv.readCSVCarrera(scvCarreras);
        
      //carga EstudianteCarreras
        String scvEstudianteCarrera = "../JPAIntegrador2/src/datasets/estudianteCarrera.csv";
        rscv.readCSVEstudianteCarrera(scvEstudianteCarrera);
    }
    public static List<CarreraDto> getCarrerasOrderByInscriptos(){
        CarreraRepositorio cr = new CarreraRepositorio();
        List<CarreraDto> carrInscriptos = cr.getCarrerasOrderByInscriptos();
        return carrInscriptos;
        }
    
}
