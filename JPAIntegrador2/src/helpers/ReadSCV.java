package helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import clases.Carrera;
import clases.Estudiante;
import clases.EstudianteCarrera;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositorios.CarreraRepositorio;
import repositorios.EstudianteCarreraRepositorio;
import repositorios.EstudianteRepositorio;

public class ReadSCV {

    public ReadSCV(){
        super();
    }
    @SuppressWarnings("deprecation")
	public void readCSVEstudiante(String csv) throws SQLException {
        CSVParser parser;
        try {
            parser= CSVFormat.DEFAULT.withHeader().parse(new FileReader(csv));
            this.createEstudiante(parser);
            //this.conn.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void readCSVCarrera(String csv) throws SQLException {
        CSVParser parser;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csv));
            this.createCarrera(parser);
            //this.conn.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    protected void createEstudiante(CSVParser parser) throws SQLException {
        for(CSVRecord row: parser) {
        	Integer id=Integer.parseInt(row.get("id"));
        	String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            Integer edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudadResidencia = row.get("ciudad");
            String documento = row.get("DNI");
            Integer numeroLibreta = Integer.parseInt(row.get("LU"));
            //"hardcodeo" el insert
            Estudiante e = new Estudiante(id,nombre, apellido, edad, genero, ciudadResidencia, documento, numeroLibreta);
            EstudianteRepositorio er = new EstudianteRepositorio();
            er.insertEstudiante(e);
        }
    }
    @SuppressWarnings("deprecation")
	public void readCSVEstudianteCarrera(String csv)throws SQLException, FileNotFoundException, IOException{
    	 CSVParser parser;
    	 parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csv));
         this.createEstudianteCarrera(parser);
    }
    protected void createCarrera(CSVParser parser) throws SQLException {
        for(CSVRecord row: parser) {
        	Integer id= Integer.parseInt(row.get("id_carrera"));
        	String nombre = row.get("carrera");
            Double duracion = Double.parseDouble(row.get("duracion"));
            //"hardcodeo" el insert
            Carrera c = new Carrera(id,nombre, duracion);
            CarreraRepositorio cr = new CarreraRepositorio();
            cr.insertCarrera(c);
        }
     }
    protected void createEstudianteCarrera(CSVParser parser)throws SQLException{
    	 for(CSVRecord row: parser) {
    		 Integer idEstudiante = Integer.parseInt(row.get("id_estudiante"));
             Integer idCarrera = Integer.parseInt(row.get("id_carrera"));
             LocalDate inscripcion =  LocalDate.of(Integer.parseInt(row.get("inscripcion")),1,1) ;
             LocalDate graduacion =  LocalDate.of(Integer.parseInt(row.get("graduacion")),1,1) ;
             EstudianteRepositorio er = new EstudianteRepositorio();
             Estudiante estudiante =  er.getEstudianteById(idEstudiante);
             CarreraRepositorio cr = new CarreraRepositorio();
             Carrera carrera =  cr.getCarreraById(idCarrera);
           //"hardcodeo" el insert
             EstudianteCarrera ec = new EstudianteCarrera(inscripcion, graduacion, estudiante, carrera);
             EstudianteCarreraRepositorio ecr = new EstudianteCarreraRepositorio();
             ecr.insertEstudianteCarrera(ec);
    	 }
    }
    
    
    
   

}
