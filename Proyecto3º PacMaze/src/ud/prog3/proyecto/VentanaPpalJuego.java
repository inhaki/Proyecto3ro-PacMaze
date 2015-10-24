package ud.prog3.proyecto;

import javax.swing.*;

public class VentanaPpalJuego extends JFrame{

	/**Clase principal del juego de PacMaze (Proyecto)
	 * Pantalla inicial que escogeremos el avatar y tendremos la opcion de loguearnos
	 * @author Julen Torralba
	 * @author Iñaki Gavela
	 * Estudiantes doble grado de 3ºADE+Ing.Informática (UD2015)
	 */
	private static final long serialVersionUID = 1L;
	JPanel pPrincipal; //Panel inicial del juego
	
	public VentanaPpalJuego(){
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );//para cerrar la ventana al cambiar de ventana
		pPrincipal= new JPanel();
		pPrincipal.setLayout(null);
		setSize(400,400);
		getContentPane().setLayout(null);
		
		
	}
}
