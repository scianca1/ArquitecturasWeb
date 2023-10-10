

	EJEMPLOS DE ENDPOINTS:


	a) dar de alta un estudiante

	POST:	(ruta relativa)/students  

	Body:	 {
        		"id": 1,
        		"nombre": "Juan",
        		"apellido": "Perez",
        		"edad": 31,
        		"genero": "Male",
        		"ciudadResidencia": "Tandil",
        		"documento": "41092212",
       	 		"numeroLibreta": 12345,
       			"carreras": []
    		}



	b) matricular un estudiante en una carrera

	POST:	.../carrers/{id_carrer}/matricular

	Body:	{
        		"id": {id_student}
			}



	c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.

	GET:	.../students/orderByName



	d) recuperar un estudiante, en base a su número de libreta universitaria.

	GET:	.../students/notebook/{libreta}



	e) recuperar todos los estudiantes, en base a su género.

	GET:	.../students/genre/{genero}	



	f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.

	GET:	.../carrers/OrderByRegistered	



	g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia

	GET:	.../students/carrerAndCity/{id_carrer}/{city}
	
	
	
	h) 	generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. 
		Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
		
		GET:	.../carrers/report

