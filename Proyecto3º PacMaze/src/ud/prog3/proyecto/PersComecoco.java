package ud.prog3.proyecto;

import javax.swing.*;

public class PersComecoco extends PersonajeJuego{
	
	private static boolean ESFERA_COCO=false;//dibujado para el caso de choque
	
	//Constructor inicial, saldrá la imagen orientada a la derecha
	public PersComecoco(){
		super.ImagenCoco= new ImageIcon("Imagenes/PersonajesJuego/PacmanDerecha.gif");
	}
	
	public void MoverPacman()
    {
        MoverPersonaje();
    }
	
	 @Override
	    public void CambiarImagen()
	    {
	        switch(CodDireccion)
	        {
	            case 1:{ImagenCoco = null; ImagenCoco = new ImageIcon("Imagenes/PersonajesJuego/PacmanArriba.gif"); break;}
	            case 2:{ImagenCoco = null; ImagenCoco = new ImageIcon("Imagenes/PersonajesJuego/PacmanAbajo.gif"); break;}
	            case 3:{ImagenCoco = null; ImagenCoco = new ImageIcon("Imagenes/PersonajesJuego/PacmanDerecha.gif"); break;}
	            case 4:{ImagenCoco = null; ImagenCoco = new ImageIcon("Imagenes/PersonajesJuego/PacmanIzquierda.gif"); break;}
	        }
	    }
	
}
