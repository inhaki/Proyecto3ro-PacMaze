package ud.prog3.proyecto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**Clase para gestionar los metodos de guardado y comprobacion de cuentas
 * @author Alumno
 *
 */
public class GestorAdministrador {
	HashSet<Administrador> SetAdmin;
	GuardarFicheroAdmin objDatos;
	ArrayList<Serializable> SetS;
	
	/**Constructor de la clase
	 * HashSet de la clase de Administrador
	 * Objeto para llamar a los metodos de datos
	 * ArrayList de Serializable para hacer la comprobacion de cuentas
	 */
	public GestorAdministrador()
	{
		SetAdmin= new HashSet<Administrador>();
		objDatos= new GuardarFicheroAdmin();
		SetS= new ArrayList<Serializable>();
	}
	
	/**Metodo para crear cuenta de administrador si el codigo introducido es correcto
	 * @param usuario nombre del administrador, que viene del frmAdministrador donde se crea la cuenta
	 * @param contrase�a
	 */
	public void CrearAdministrador(String usuario, int contrase�a){
		Administrador admin= new Administrador(usuario, contrase�a);
		objDatos.GuardarDatos();
		objDatos.Guardar(admin);
		objDatos.TerminarGuardado();
		
		admin=null;
	}
	
	/**Metodo que comprueba si existen las cuentas de administradores
	 * Se comprueba si existe el usuario y contrase�a iguales a los par�metros enviados desde el frmContrase�a
	 * @param usuario nombre del administrador
	 * @param contrase�a
	 * @return devuelve true si existe la cuenta, false si no
	 */
	public boolean ComprobarContrase�a(String usuario, int contrase�a)
	{
		boolean resultado=false;
	  
		objDatos.ComenzarRead();
		SetS=objDatos.LeerDatos();
		for(Serializable s: SetS)
		{
			
			if(((Administrador)s).getUsuario().compareTo(usuario)==0 && ((Administrador)s).getContrase�a()==contrase�a){ 
				resultado=true;
			}
		}
		objDatos.TerminarRead();
		return resultado;
	}
	
}
