package ud.prog3.proyecto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonajeJuegoTest {
	
	PersonajeJuego p;
	
	@Before
	public void setUp() throws Exception{
		p= new PersonajeJuego();
	}
	
	//Chequeo de la posicion inicial del avatar
	@Test
	public void testPosicionInicial(){
		p.setPosicionX(60);
		p.setPosicionY(80);
		p.EstablecerPosicionInicial(60, 80);
		assertEquals(60, p.PosicionX, 0);
		assertEquals(80, p.PosicionY, 0);
		//compruebo que saldran en la casilla 60,80
		p.setPosicionX(380);
		p.setPosicionY(380);
		p.EstablecerPosicionInicial(380, 380);
		assertEquals(380, p.PosicionX, 0);
		assertEquals(380, p.PosicionY, 0);
		//compruebo que saldrán el la casilla 380, 380
	}
	
	@Test
	public void testAumentarDisminuirPosicionX(){
		p.setPosicionX(40);//si meto 41 en posicion de X me dará error
		p.setVelocidad(2);
		p.AumentarPosicionX();
		assertEquals(42, p.getPosicionX(), 0);
		p.DisminuirPosicionX();
		//ahora llamo a disminuir, debería de salir 40 ya que antes hemos aumentado de 40 a 42
		assertEquals(40, p.getPosicionX(), 0);
	}
	@Test
	public void testAumentarDisminuirPosicionY(){
		p.setPosicionY(100);
		p.setVelocidad(4);
		p.AumentarPosicionY();
		assertEquals(104, p.getPosicionY(), 0);
		p.setVelocidad(2);
		p.DisminuirPosicionY();
		//deberia salir 102, porque disminuyo 2 unidades la velocidad
		assertEquals(102, p.getPosicionY(), 0);
	}
	
	@Test
	public void testCambioVelocidad(){
		p.setVelocidad(2);//meto 2 como valor inicial
		p.CambiarVelocidad(1);
		assertEquals(1, p.getVelocidad(), 0);
		p.CambiarVelocidad(10);
		assertEquals(10, p.getVelocidad(), 0);
	}
	
}
