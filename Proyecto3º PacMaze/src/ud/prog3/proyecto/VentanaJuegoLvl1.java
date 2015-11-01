package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

/** Nivel 1 del juego PacMaze
 *
 */
public class VentanaJuegoLvl1 extends JFrame{

	JPanel p1; //Panel del juego (layout lib-null)
	MundoJuegoLvl1 miMundo1; //Mundo 1 del juego
	ComecocosJuego miCoco;  //Personaje Comecocos
	//MiRunnable miHile= null; //Hilo del bucle principal
	
	public VentanaJuegoLvl1(){
		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.white);
		add(p1, BorderLayout.CENTER);
		setSize(1000, 750);
		setResizable(false);
	}
	
}
