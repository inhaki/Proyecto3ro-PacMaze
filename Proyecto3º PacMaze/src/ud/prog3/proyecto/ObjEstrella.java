package ud.prog3.proyecto;

import javax.swing.*;


public class ObjEstrella extends PersonajeJuego{
	
	/*private int PosicionX;
	private int PosicionY;

	public int getPosicionX() {
		return PosicionX;
	}

	public void setPosicionX(int posicionX) {
		PosicionX = posicionX;
	}

	public int getPosicionY() {
		return PosicionY;
	}

	public void setPosicionY(int posicionY) {
		PosicionY = posicionY;
	}*/

	public ObjEstrella(int x, int y){
		  super.ImagenEstrella= new ImageIcon("Imagenes/Objetos/EstrellaPacman.gif");
		  super.PosicionX = x;
		  super.PosicionY = y;
	}
}
