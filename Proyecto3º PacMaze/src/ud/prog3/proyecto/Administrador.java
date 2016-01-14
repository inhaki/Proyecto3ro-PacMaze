package ud.prog3.proyecto;

import java.io.Serializable;

public class Administrador implements Serializable{

	/**Clase para guardar en ficheros los administradores que habr� en el juego
	 * Unicamente podr�n acceder a las puntuaciones, y gestionar los usuarios
	 * para ser administrador es necesario conocer el codigo de acceso para hacerse una cuenta
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private int contrase�a;

	
	public Administrador(String usuario, int contrase�a)
	{
		super();
		this.usuario=usuario;
		this.contrase�a=contrase�a;

	}
	
	public String getUsuario() 
	{
		return usuario;
	}
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	public int getContrase�a() 
	{
		return contrase�a;
	}
	public void setContrase�a(int contrase�a) 
	{
		this.contrase�a = contrase�a;
	}
	/**Queremos que el atributo clave sea el nombre 
	 * ya que puede ser que la contrase�a se repita 
	 */
	public int hashCode() 
	{//el atributo clave del set, el cual si se repite no entrara en la lista

		Integer code = new Integer(this.usuario);
		
		return code.hashCode();
		
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Administrador)) return false;
		
		Administrador con= (Administrador)obj;
		
		return (this.usuario==con.getUsuario());
	}

}
