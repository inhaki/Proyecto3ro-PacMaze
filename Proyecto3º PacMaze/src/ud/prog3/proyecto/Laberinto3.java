package ud.prog3.proyecto;

public class Laberinto3 {

	private final int LARGO_IMAGENES = 20;
	private final int ALTURA_IMAGENES = 20;
	
    public String LaberintoActual[][] = {
    /*columna 1	*/		{"1","5","5","5","5","5","13","13","5","5","5","5","13","13","5","5","5","5","5","3"},
    /*columna 2	*/		{"6","CM","CM","CM","CM","CM","2","4","CM","CM","CM","CM","2","4","CM","CM","CM","CM","CM","6"},
    /*columna 3	*/		{"6","CM","1","13","3","CM","CM","CM","CM","1","3","CM","CM","CM","CM","1","13","3","CM","6"},
    /*columna 4	*/		{"6","CM","2","14","4","CM","1","3","CM","15","16","CM","1","3","CM","2","14","4","CM","6"},
    /*columna 5	*/		{"6","CM","CM","CM","CM","CM","15","16","CM","2","4","CM","15","16","CM","CM","CM","CM","CM","6"},
    /*columna 6	*/		{"15","8","CM","1","3","CM","2","4","CM","CM","CM","CM","2","4","CM","1","3","CM","7","16"},
    /*columna 7	*/		{"6","CM","CM","15","16","CM","CM","CM","CM","1","3","CM","CM","CM","CM","15","16","CM","CM","6"},
    /*columna 8	*/		{"6","CM","7","14","4","CM","7","8","CM","15","16","CM","7","8","CM","2","14","8","CM","6"},
    /*columna 9	*/		{"6","CM","CM","CM","CM","CM","CM","CM","CM","2","4","CM","CM","CM","CM","CM","CM","CM","CM","6"},
    /*columna 10*/		{"15","13","13","3","CM","1","13","3","CM","CM","CM","CM","1","13","3","CM","1","13","13","16"},
    /*columna 11*/		{"15","14","14","4","CM","2","14","4","CM","CM","CM","CM","2","14","4","CM","2","14","14","16"},
    /*columna 12*/		{"6","CM","CM","CM","CM","CM","CM","CM","CM","1","3","CM","CM","CM","CM","CM","CM","CM","CM","6"},
    /*columna 13*/		{"6","CM","7","13","3","CM","7","8","CM","15","16","CM","7","8","CM","1","13","8","CM","6"},
    /*columna 14*/		{"6","CM","CM","15","16","CM","CM","CM","CM","2","4","CM","CM","CM","CM","15","16","CM","CM","6"},
    /*columna 15*/		{"15","8","CM","2","4","CM","1","3","CM","CM","CM","CM","1","3","CM","2","4","CM","7","16"},
    /*columna 16*/		{"6","CM","CM","CM","CM","CM","15","16","CM","1","3","CM","15","16","CM","CM","CM","CM","CM","6"},
    /*columna 17*/		{"6","CM","1","13","3","CM","2","4","CM","15","16","CM","2","4","CM","1","13","3","CM","6"},
    /*columna 18*/		{"6","CM","2","14","4","CM","CM","CM","CM","2","4","CM","CM","CM","CM","2","14","4","CM","6"},
    /*columna 19*/		{"6","CM","CM","CM","CM","CM","1","3","CM","CM","CM","CM","1","3","CM","CM","CM","CM","CM","6"},
    /*columna 20*/		{"2","5","5","5","5","5","14","14","5","5","5","5","14","14","5","5","5","5","5","4"},
    };
    
    //1= arriba, izquierda
    //2= arriba,derecha
    //3= abajo, izquierda
    //4= abajo, derecha
    //5= izquierda, derecha
    //6= arriba, abajo
    //7= arriba, izquierda, derecha
    //8= abajo, izquierda, derecha
    //9= derecha, arriba, abajo
    //10= izquierda, arriba, abajo
    //11= arriba, abajo, izquierda, derecha
    //12= vacio
    //13= izquierda
    //14= derecha
    //15= arriba
    //16= abajo
    //CM= camino
    
    public String[][] DevolverLaberinto(){return LaberintoActual;}
    public int DevolverLargoImagenes(){return LARGO_IMAGENES;}
    public int DevolverAlturaImagenes(){return ALTURA_IMAGENES;}
    public int DevolverCantidadFilasLaberinto(){return LaberintoActual.length;}
    public int DevolverCantidadColumnasLaberinto(int Fila){return LaberintoActual[Fila].length;}    
    public String DeolverCodigoImagenMatriz(int Fila, int Columna){return LaberintoActual[Fila][Columna];}
}
