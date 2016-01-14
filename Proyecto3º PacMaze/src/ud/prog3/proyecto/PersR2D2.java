package ud.prog3.proyecto;

import javax.swing.*;

/** Clase donde se crean los ImageIcon del 3er personaje del juego
 * @author Alumno
 */
public class PersR2D2 extends PersonajeJuego{

	public PersR2D2(){
		super.ImagenR2D2=new ImageIcon("Imagenes/PersonajesJuego/R2D2Derecha.gif");
	}
	
	//para mover a R2D2 en el laberinto 1
	public void MoverR2D2(){
		MoverPersonajeR2D2();
	}
	//para mover a R2D2 en el laberinto 2
	public void MoverR2D22(){
		MoverPersonajeR2D22();
	}
	
	public void MoverR2D23(){
		MoverPersonajeR2D23();
	}
	
	public void CambiarImagen()
    {
        switch(CodDireccion)
        {
            case 1:{ImagenR2D2 = null; ImagenR2D2 = new ImageIcon("Imagenes/PersonajesJuego/R2D2Arriba.gif"); break;}
            case 2:{ImagenR2D2 = null; ImagenR2D2 = new ImageIcon("Imagenes/PersonajesJuego/R2D2Abajo.gif"); break;}
            case 3:{ImagenR2D2 = null; ImagenR2D2 = new ImageIcon("Imagenes/PersonajesJuego/R2D2Derecha.gif"); break;}
            case 4:{ImagenR2D2 = null; ImagenR2D2 = new ImageIcon("Imagenes/PersonajesJuego/R2D2Izda.gif"); break;}
        }
    }
}
