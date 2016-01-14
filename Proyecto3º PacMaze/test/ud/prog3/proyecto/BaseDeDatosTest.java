package ud.prog3.proyecto;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDeDatosTest {
	
	private BaseDeDatos bd;
	

	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception{
		bd= new BaseDeDatos();
		bd.initBD("usuariosTest.bd");
		bd.insertDatosPuntos("Iñaki", 2000, 1);
		bd.insertDatosPuntos("inhaki", 2250, 2);
		bd.insertDatosPuntos("Inaki", 2500, 3);
		bd.insertDatosPuntos("Julen", 1000, 1);
		bd.insertDatosPuntos("Rocko", 100, 2);
		bd.insertDatosPuntos("Julian", 1760, 3);
	}
	
	@SuppressWarnings("static-access")
	@After
	public void tearDown() throws Exception {
		bd.finConexion();
	}
	
	//chequear que añade elementos a la tabla
	//y que al devolver la lista no se encuentra vacia
	//una vez añadidas las filas las dejamos comentadas
	@SuppressWarnings("static-access")
	@Test
	public void testLeerYAñadirBBDD(){
		//bd.initBD("usuariosTest.bd");
		bd.crearTablaUsuario();
		//bd.insertDatosUsuario("inhaki", 1234, "inhaki@gmail.com", 21, "Irun");
		//bd.insertDatosUsuario("julian", 5678, "julian@gmail.com", 23, "¿Vitoria?");
		//una vez hecho el test, los usuarios se quedan dentro
		assertNotNull(bd.VisualizarUsuario());
		System.out.println(bd.VisualizarUsuario());
		bd.crearTablaPuntuacion();
		
		assertNotNull(bd.VisualizarPuntos(1));
		assertNotNull(bd.VisualizarPuntos(2));
		assertNotNull(bd.VisualizarPuntos(3));
		System.out.println(bd.VisualizarPuntos(1));//nivel 1
		System.out.println(bd.VisualizarPuntos(2));//nivel 2
		System.out.println(bd.VisualizarPuntos(3));//nivel 3
	}
	
	//chequeo de loggin
	@SuppressWarnings("static-access")
	@Test
	public void testLogginBBDD(){
		//loggin con uno de los usuarios ya creados e insertados a la tabla
		assertEquals(true, bd.VerificarUsuario("inhaki", 1234));
		//ahora hacemos la prueba con un usuario inventado pero que no esta en la BBDD
		assertEquals(false, bd.VerificarUsuario("julen", 00100 ));
	}
	
	//chequeo de Borrado
	@SuppressWarnings("static-access")
	@Test
	public void BorrarDeBBDD(){
		bd.BorrarPuntuaciones();
		//se vacia la tabla y solo quedan los corchetes
		//comprobacion con el NotNull porque hay una lista vacia, pero siguen los corchetes propios de la BBDD
		assertNotNull(bd.VisualizarPuntos(1));
		assertNotNull(bd.VisualizarPuntos(2));
		assertNotNull(bd.VisualizarPuntos(3));
		//comprobacion visual de que solo quedan corchetes
		System.out.println(bd.VisualizarPuntos(1));
		System.out.println(bd.VisualizarPuntos(2));
		System.out.println(bd.VisualizarPuntos(3));
	}
	
	
	
}
