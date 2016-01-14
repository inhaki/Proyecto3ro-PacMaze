package ud.prog3.proyecto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;




public class GuardarFicheroAdmin {
	
	private final String rutaAdmin="Administrador.dat";//GUARDAR DATOS DE ADMIN
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	AppendableObjectOutputStream aos;
	
	/**Metodo para guardar en ficheros los datos de los administradores
	 * Comenzar el SAVE
	 */
	public void GuardarDatos(){
		String fichero=null;
		fichero=rutaAdmin;
		
		File fic;//para poder comprobar si existe desde un inicio el fichero de admin
		fic= new File(fichero);
		if(fic.exists())
		{
			
			try {
				aos= new AppendableObjectOutputStream(new FileOutputStream(fic, true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				fic.createNewFile();
				oos = new ObjectOutputStream(new FileOutputStream(fic));
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	/** Implementar el Save, pasando los parámetros a guardar
	 */
	public 	void Guardar(Serializable a)
	{
		try {
			if(oos!=null)
				oos.writeObject(a);
			else{
				if(aos!=null)
				{
					aos.writeObject(a);
				}
			}
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/** Terminar el SAVE
	 */
	public void TerminarGuardado()
	{
		try {
			if(oos!=null){
				oos.close();
			}
			else if(aos!=null){
				aos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos=null;//una vez guardado pasan a ser null de nuevo
		aos=null;
	}
	/** Comenzar la lectura del fichero
	 */
	public void ComenzarRead(){
		String fichero=null;
		fichero=rutaAdmin;
		
		File fic;
		
		fic=new File(fichero);
		if (fic.exists())
		{
			try
			{
				ois = new ObjectInputStream(new FileInputStream(fic));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*else
		{
			throw new IOException();
		}*/
	}
	/** Terminar Lectura
	 */
	public void TerminarRead()
	{
		try
		{
			if(ois!=null)ois.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Lee datos y los mete en un ArrayListSerializable
	 * @return
	 */
	public ArrayList<Serializable> LeerDatos()
	{
		
		ArrayList<Serializable>listaD;
		Serializable o=null;
		
		
		listaD=new ArrayList<Serializable>();
		try
		{
			
			while ((o = (Serializable)ois.readObject()) != null) 
			{
			       listaD.add(o);
			        
			 }
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			/*if(o==null) 
			{
				System.out.println(e.getMessage());
			}*/
		}catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return listaD;
	}
}
