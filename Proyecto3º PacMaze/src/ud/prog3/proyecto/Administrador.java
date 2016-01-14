package ud.prog3.proyecto;

import java.io.Serializable;

public class Administrador implements Serializable{

	/**Clase para guardar en ficheros los administradores que habrá en el juego
	 * Unicamente podrán acceder a las puntuaciones, y gestionar los usuarios
	 * para ser administrador es necesario conocer el codigo de acceso para hacerse una cuenta
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private int contraseña;

	
	public Administrador(String usuario, int contraseña)
	{
		super();
		this.usuario=usuario;
		this.contraseña=contraseña;

	}
	
	public String getUsuario() 
	{
		return usuario;
	}
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	public int getContraseña() 
	{
		return contraseña;
	}
	public void setContraseña(int contraseña) 
	{
		this.contraseña = contraseña;
	}
	/**Queremos que el atributo clave sea el nombre 
	 * ya que puede ser que la contraseña se repita 
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
