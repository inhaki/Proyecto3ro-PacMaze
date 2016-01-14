package ud.prog3.proyecto;

import javax.swing.*;


public class ObjEstrella extends PersonajeJuego{
	
	/**Constrauctor de la clase que contiene un ImageIcon y hereda de PersonajeJuego
	 * @param x posicion en X
	 * @param y posicion en Y
	 */
	public ObjEstrella(int x, int y){
		  super.ImagenEstrella= new ImageIcon("Imagenes/Objetos/EstrellaPacman.gif");
		  super.PosicionX = x;
		  super.PosicionY = y;
	}
}
