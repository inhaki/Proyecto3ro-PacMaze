package ud.prog3.proyecto;
/**
 *
 * @author 
 */
public class Laberinto1 {

	private final int LARGO_IMAGENES = 10;
	private final int ALTURA_IMAGENES = 10;
	
    public String LaberintoActual[][] = {
        {"ESI", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "IZ", "EII"},
        {"AR","ME","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","ME","ME","ME","AB","CA","CA","AR","ME","ME","ME","DE","DE","DE","DE","DE","DE","ME","ME","DE","DE","DE","DE","DE","DE","ME","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","ESD","EID","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","EII","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESI","EII","CA","CA","CA","CA","CA","CA","ESI","EII","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","ME","ME","AB","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","EID","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","DE","DE","DE","DE","EID","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","IZ","IZ","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","EII","CA","CA","ESI","IZ","IZ","IZ","ME","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESD","DE","DE","DE","DE","DE","DE","EID","CA","CA","ESD","DE","DE","EID","CA","CA","AR","AB","CA","CA","ESD","DE","DE","DE","ME","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","EII","CA","CA","AR","AB","CA","CA","ESI","EII","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESD","DE","DE","EID","CA","CA","AR","ME","ME","AB","CA","CA","ESD","DE","ME","AB","CA","CA","AR","AB","CA","CA","ESD","EID","CA","CA","AR","AB","CA","CA","ESD","EID","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","ME","IZ","IZ","IZ","IZ","IZ","EII","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","ME","IZ","IZ","IZ","EII","CA","CA","AR","ME","IZ","IZ","IZ","EII","CA","CA","AR","AB"},
        {"AR","ME","DE","DE","DE","DE","DE","EID","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","ME","DE","DE","DE","EID","CA","CA","AR","ME","DE","DE","DE","EID","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESI","IZ","IZ","EII","CA","CA","AR","ME","ME","AB","CA","CA","ESI","IZ","ME","AB","CA","CA","AR","AB","CA","CA","ESI","EII","CA","CA","AR","AB","CA","CA","ESI","EII","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","EID","CA","CA","AR","AB","CA","CA","ESD","EID","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESI","IZ","IZ","IZ","IZ","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","EII","CA","CA","AR","AB","CA","CA","ESI","IZ","IZ","IZ","ME","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","DE","DE","DE","DE","DE","DE","EID","CA","CA","ESD","DE","DE","EID","CA","CA","ESD","EID","CA","CA","ESD","DE","DE","DE","ME","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AR"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESI","IZ","IZ","IZ","IZ","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","EII","CA","CA","ESI","IZ","IZ","IZ","IZ","EII","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","ME","DE","DE","DE","DE","DE","EID","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","DE","DE","DE","EID","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","AR","AB","CA","CA","ESI","IZ","IZ","EII","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","AR","AB","CA","CA","ESI","EII","CA","CA","AR","AB","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","ESD","EID","CA","CA","ESD","DE","DE","EID","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","ESD","EID","CA","CA","AR","AB","CA","CA","ESD","EID","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","AB","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","CA","AR","ME","ME","AB","CA","CA","AR","ME","ME","AB","CA","CA","CA","CA","CA","CA","AR","AB","CA","CA","CA","CA","CA","CA","AR","AB"},
        {"AR","ME","IZ","IZ","IZ","IZ","IZ","IZ","IZ","IZ","IZ","IZ","IZ","IZ","ME","ME","ME","AB","CA","CA","AR","ME","ME","ME","IZ","IZ","IZ","IZ","IZ","IZ","ME","ME","IZ","IZ","IZ","IZ","IZ","IZ","ME","AB"},
        {"ESD","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","DE","EID"}
    };
    
    public String[][] DevolverLaberinto(){return LaberintoActual;}//devuevle el objeto creado en la parte de arriba, la combinación de gif para crear un entorno virtual
    public int DevolverLargoImagenes(){return LARGO_IMAGENES;}//alto y largo de las imagenes, para generar el laberinto
    public int DevolverAlturaImagenes(){return ALTURA_IMAGENES;}//son de 10x10 pixels
    public int DevolverCantidadFilasLaberinto(){return LaberintoActual.length;}//cuentas las filas y las utilizamos en la clase de VentanaJuegoNivel1
    public int DevolverCantidadColumnasLaberinto(int Fila){return LaberintoActual[Fila].length;}// cuentas las columnas que hay, nos sirve para generar el laberinto en el nivel 1
    public String DeolverCodigoImagenMatriz(int Fila, int Columna){return LaberintoActual[Fila][Columna];}
}