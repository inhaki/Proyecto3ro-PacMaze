package ud.prog3.proyecto;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**Mundo virtual del nivel 1 del juego
 * Se programan los m�todos para el movimiento f�sico del personaje y los choques cotra el laberinto u otros objetos
 * Es un mundo 2D que se mueven los diferentes avatares y/u objetos
 */
public class MundoJuegoLvl1 {
	
	private JPanel panel1; //panel visual del lvl 1
	ComecocosJuego miCoco; //avatar comecocos del juego
	
	public MundoJuegoLvl1 (JPanel panel1){
		this.panel1= panel1;
	}

	private static Random r = new Random();//para la aparici�n aleatoria de los objetos Manzana y estrella
	
	public void crearPersonaje (int posX, int posY){
		miCoco = new ComecocosJuego();
		miCoco.setPosicion(posX, posY);
		panel1.add(miCoco.getCocoAmarillo());//metodo que devuelve el gr�fico en la clase ComecocosJuego
		miCoco.getCocoAmarillo().repaint();
		
	}
	
	/** Devuelve el comecocos del mundo
	 * @return Comecocos
	 */
	public ComecocosJuego getCoco(){
		return miCoco;
	}
}
