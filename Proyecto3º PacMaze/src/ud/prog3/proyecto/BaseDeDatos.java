package ud.prog3.proyecto;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BaseDeDatos 
{

	private static Connection connection=null;
	private static Statement statement=null;
	
	
	public static Connection initBD( String nombreBD ) {
		try 
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error de conexión!! No se ha podido conectar con " + nombreBD );
			return null;
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}
	
	public static void crearTablaUsuario() 
	{
		if (statement==null) return;
		try {
			statement.executeUpdate("create table usuarios (usuario String, contraseña Integer, email String, edad Integer,ciudad String, CONSTRAINT usuario_pk PRIMARY KEY (usuario))");
		} catch (SQLException e) {
			if (!e.getMessage().equals("table usuarios already exists"))  // Este error sí es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	public static void crearTablaPuntuacion() 
	{
		if (statement==null) return;
		try {
			statement.executeUpdate("create table Puntos (nick String, puntuacion Integer, nivel Integer)");
		} catch (SQLException e) {
			if (!e.getMessage().equals("table Puntos already exists"))  // Este error sí es correcto si la tabla ya existe
				e.printStackTrace();
		}
	}
	
	public static void insertDatosUsuario( String usuario, int contraseña, String email, int edad, String ciudad ) throws SQLException 
	{
		String datos = "insert into usuarios values("+"'"+ usuario +"','"+ contraseña +"','"+ email +"','"+ edad +"','"+ ciudad +"')";
		statement.executeUpdate(datos);
	 
	}
	
	public static void insertDatosPuntos( String nick, int puntuacion , int nivel) 
	{
		String datos = "insert into Puntos values("+"'"+ nick +"','"+ puntuacion+"','"+nivel+"')";
			try 
			{
				statement.executeUpdate(datos);
			} 
			catch (SQLException e) 
			{
				System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
				e.printStackTrace();
			}
	}
	
	public static ArrayList<String> VisualizarUsuario() 
	{
		ArrayList<String> lista= new ArrayList<String>();
		
		String datos = "Select * from usuarios";
		int i=1;
		try 
		{
			statement.executeUpdate(datos);
			ResultSet rs= statement.executeQuery(datos);
			while(rs.next()){
				
				//System.out.println("nombre= "+ rs.getString("usuario")+" contraseña=" +rs.getInt("contraseña")+ " email= "+rs.getString("email")+" edad="+rs.getInt("edad")+" ciudad="+rs.getString("ciudad") );
				lista.add("["+i+"] Nombre: "+rs.getString("usuario")+", contraseña: " +rs.getInt("contraseña")+ ", email: "+rs.getString("email")+", edad: "+rs.getInt("edad")+", ciudad: "+rs.getString("ciudad"));
				i++;
			}
			if(rs!=null) return lista;
		} 
		catch (SQLException e) 
		{
			System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
			e.printStackTrace();
			
		}
		return null;
	}
	
	public static void BorrarUsuario(String usuario)
	{
		String datos= "Delete from usuarios where usuario='"+usuario+"' ";
		try{
			statement.executeUpdate(datos);
		}
		catch(SQLException e) 
		{
			System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
			e.printStackTrace();
			
		}
	}
	
	/**Metodo para visualizar
	 * @param nivel pasa por parámetro dependiendo el nivel que se quiera
	 * @return devuelve un ArrayList
	 */
	public static ArrayList<String> VisualizarPuntos(int nivel) 
	{	
		int lvl=nivel;//para igualar el parametro, dependiendo de donde le llamemos queremos unas puntuaciones u otras
		ArrayList<String> lista= new ArrayList<String>();
		String datos = "Select nick, puntuacion from Puntos where nivel="+lvl+" order by 2 desc";
		int i=1;//para facilitar la busqueda de jugadores
		
		try {
			statement.executeUpdate(datos);
			ResultSet rs= statement.executeQuery(datos);
			while(rs.next()){
				//System.out.println("Nick= "+ rs.getString("nick")+" puntuacion=" +rs.getInt("puntuacion"));
				lista.add("["+i+"] Nick: "+rs.getString("nick")+" -- Puntos: " +rs.getInt("puntuacion"));
				i++;
			}
			if(rs!=null) return lista;
		} 
		catch (SQLException e) 
		{
			System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
			e.printStackTrace();
		}
		return null;
	}
	
	/**Metodo que resetea todas las puntuaciones guardadas en memoria, elimina la tabla
	 */
	public static void BorrarPuntuaciones(){
		String datos="Delete from Puntos";
		try{
			statement.executeUpdate(datos);
		}
		catch(SQLException e) 
		{
			System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
			e.printStackTrace();
			
		}
		
	}
	
	/**Metodo para verificar la cuenta de usuario si existe o no
	 * @param usuario pasamos los parámetros introducidos en textField desde la ventanaPpal
	 * @param contraseña int
	 * @return devuelve true si existe la cuenta o false si no existe
	 */
	public static boolean VerificarUsuario(String usuario, int contraseña){
		
		String us=usuario;
		int cont=contraseña;
		//String datos= "Select usuario, contraseña from usuarios where usuario='"+us+"' & contraseña="+cont+" ";
		String datos="Select usuario, contraseña from usuarios";
		try{
			statement.executeUpdate(datos);
			ResultSet rs=statement.executeQuery(datos);
			while(rs.next()){
				//System.out.println(rs.getString("usuario")+rs.getInt("contraseña"));
				if(rs.getString("usuario").equals(us)&&rs.getInt("contraseña")==cont){
					return true;
				}
			}
			
		}catch(SQLException e) {
			System.out.println( "ERROR EN SENTENCIA SQL: " + datos);
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void finConexion() 
	{
		try 
		{
			statement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}