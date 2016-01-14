package ud.prog3.proyecto;

public class Laberinto2 {

	private final int LARGO_IMAGENES = 20;
	private final int ALTURA_IMAGENES = 20;
	
    public String LaberintoActual[][] = {
    /*columna 1	*/		{"A","M","E","E","E","E","E","M","M","M","M","M","M","E","E","E","E","E","M","C"},
    /*columna 2	*/		{"Ñ","D","CAM","CAM","CAM","CAM","CAM","B","N","N","N","N","D","CAM","CAM","CAM","CAM","CAM","B","O"},
    /*columna 3	*/		{"F","CAM","CAM","J","CAM","J","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","J","CAM","J","CAM","CAM","F"},
    /*columna 4	*/		{"F","CAM","G","D","CAM","B","H","CAM","A","M","M","C","CAM","G","D","CAM","B","H","CAM","F"},
    /*columna 5	*/		{"F","CAM","CAM","CAM","CAM","CAM","CAM","CAM","Ñ","L","L","O","CAM","CAM","CAM","CAM","CAM","CAM","CAM","F"},
    /*columna 6	*/		{"F","CAM","G","C","CAM","A","H","CAM","B","N","N","D","CAM","G","C","CAM","A","H","CAM","F"},
    /*columna 7	*/		{"F","CAM","CAM","I","CAM","I","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","I","CAM","I","CAM","CAM","F"},
    /*columna 8	*/		{"Ñ","C","CAM","CAM","CAM","CAM","CAM","K","CAM","G","H","CAM","K","CAM","CAM","CAM","CAM","CAM","A","O"},
    /*columna 9	*/		{"Ñ","O","CAM","A","M","C","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","A","M","C","CAM","Ñ","O"},
    /*columna 10*/		{"Ñ","O","CAM","Ñ","L","O","CAM","J","CAM","A","C","CAM","J","CAM","Ñ","L","O","CAM","Ñ","O"},
    /*columna 11*/		{"Ñ","O","CAM","Ñ","L","O","CAM","I","CAM","B","D","CAM","I","CAM","Ñ","L","O","CAM","Ñ","O"},
    /*columna 12*/		{"Ñ","O","CAM","B","N","D","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","B","N","D","CAM","Ñ","O"},
    /*columna 13*/		{"Ñ","D","CAM","CAM","CAM","CAM","CAM","K","CAM","G","H","CAM","K","CAM","CAM","CAM","CAM","CAM","B","O"},
    /*columna 14*/		{"F","CAM","CAM","J","CAM","J","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","J","CAM","J","CAM","CAM","F"},
    /*columna 15*/		{"F","CAM","G","D","CAM","B","H","CAM","A","M","M","C","CAM","G","D","CAM","B","H","CAM","F"},
    /*columna 16*/		{"F","CAM","CAM","CAM","CAM","CAM","CAM","CAM","Ñ","L","L","O","CAM","CAM","CAM","CAM","CAM","CAM","CAM","F"},
    /*columna 17*/		{"F","CAM","G","C","CAM","A","H","CAM","B","N","N","D","CAM","G","C","CAM","A","H","CAM","F"},
    /*columna 18*/		{"F","CAM","CAM","I","CAM","I","CAM","CAM","CAM","CAM","CAM","CAM","CAM","CAM","I","CAM","I","CAM","CAM","F"},
    /*columna 19*/		{"Ñ","C","CAM","CAM","CAM","CAM","CAM","A","M","M","M","M","C","CAM","CAM","CAM","CAM","CAM","A","O"},
    /*columna 20*/		{"B","N","E","E","E","E","E","N","N","N","N","N","N","E","E","E","E","E","N","D"},
    };
    
    //A= arriba, izquierda
    //B= arriba,derecha
    //C= abajo, izquierda
    //D= abajo, derecha
    //E= izquierda, derecha
    //F= arriba, abajo
    //G= arriba, izquierda, derecha
    //H= abajo, izquierda, derecha
    //I= derecha, arriba, abajo
    //J= izquierda, arriba, abajo
    //K= arriba, abajo, izquierda, derecha
    //L= vacio
    //M= izquierda
    //N= derecha
    //Ñ= arriba
    //O= abajo
    //CAM= camino
    
    public String[][] DevolverLaberinto(){return LaberintoActual;}
    public int DevolverLargoImagenes(){return LARGO_IMAGENES;}
    public int DevolverAlturaImagenes(){return ALTURA_IMAGENES;}
    public int DevolverCantidadFilasLaberinto(){return LaberintoActual.length;}
    public int DevolverCantidadColumnasLaberinto(int Fila){return LaberintoActual[Fila].length;}    
    public String DeolverCodigoImagenMatriz(int Fila, int Columna){return LaberintoActual[Fila][Columna];}
}
