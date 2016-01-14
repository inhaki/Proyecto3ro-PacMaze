package ud.prog3.proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**Clase del personaje de la Bala, con sus correspondientes ImageIcon
 * @author Alumno
 */
public class PersBala extends PersonajeJuego{
	
	public PersBala(){
		super.ImagenBala= new ImageIcon("Imagenes/PersonajesJuego/BalaDerecha.gif");
	}
	
	//para mover la bala en el laberinto 1
	public void MoverBala()
	{
		MoverPersonajeBala();
	}
	
	//para mover la bala en el laberinto 2
	public void MoverBala2()
	{
		MoverPersonajeBala2();
	}
	
	public void MoverBala3(){
		MoverPersonajeBala3();
	}
	
	public void CambiarImagen()
	{
		switch(CodDireccion)
		{
			case 1:{ImagenBala=null; ImagenBala= new ImageIcon("Imagenes/PersonajesJuego/BalaArriba.gif"); break;}
			case 2:{ImagenBala=null; ImagenBala= new ImageIcon("Imagenes/PersonajesJuego/BalaAbajo.gif"); break;}
			case 3:{ImagenBala=null; ImagenBala= new ImageIcon("Imagenes/PersonajesJuego/BalaDerecha.gif"); break;}
			case 4:{ImagenBala=null; ImagenBala= new ImageIcon("Imagenes/PersonajesJuego/BalaIzda.gif"); break;}
		}
	}
	
}