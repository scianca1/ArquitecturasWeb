package Integrador1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));	
			for(CSVRecord row: parser) {
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("valor"));
			


		}

	}

}
