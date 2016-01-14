package ud.prog3.proyecto;

import javax.swing.*;

public class ObjEstrellaPuntas extends PersonajeJuego {
	
	private long horaCreacion=System.currentTimeMillis();
	
	public ObjEstrellaPuntas(){
		
		super.ImagenObjEstrella= new ImageIcon("Imagenes/Objetos/estrella.gif");
	}

	public long getHoraCreacion() {
		return horaCreacion;
	}

}
