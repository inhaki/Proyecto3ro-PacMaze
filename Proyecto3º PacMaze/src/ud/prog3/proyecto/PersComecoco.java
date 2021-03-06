package ud.prog3.proyecto;

import javax.swing.*;

public class PersComecoco extends PersonajeJuego
{
	
	//Constructor inicial, saldr� la imagen orientada a la derecha
	public PersComecoco(){
		super.ImagenCoco= new ImageIcon("Imagenes/PersonajesJuego/PacmanDerecha.gif");
	}
	
	public void MoverPacman()
    {
        MoverPersonaje();
    }
	
	public void MoverPacman2()
	{
		MoverPersonaje2();
	}
	
	public void MoverPacman3(){
		MoverPersonaje3();
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
