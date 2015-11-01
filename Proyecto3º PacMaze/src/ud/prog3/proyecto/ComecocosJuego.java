package ud.prog3.proyecto;

/** Clase para intanciar el personaje comecocos con representaci�n
 * gr�fica, para incluirlo en el panel de Swing
 * Seg�n se mueva el comecocos su representaci�n JLabel se mover�
 */
public class ComecocosJuego extends Personaje{
	
	private JLabelComecoco miGrafico; 
	
	/**Se crea un nuevo comecocos Amarillo
	 */
	public ComecocosJuego(){
		miGrafico= new JLabelComecoco();
	}
	
	public JLabelComecoco getCocoAmarillo(){
		return miGrafico;
	}
	
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)PosX, (int)PosY );
		// miGrafico.repaint();  // Al cambiar la location, Swing redibuja autom�ticamente
	}

	@Override
	public void setPosY(double PosY) {
		super.setPosY(PosY);
		miGrafico.setLocation( (int)PosX, (int)PosY );
		// miGrafico.repaint();  // Al cambiar la location, Swing redibuja autom�ticamente
	}

	@Override
	public void setDireccion( double dir ) {
		super.setDireccion(dir);
		miGrafico.setGiro( Direccion );//atributo declarado en clase Personas, double Direccion
		miGrafico.repaint();  // Necesario porque Swing no redibuja al cambiar el giro (no pasa nada en el JLabel)
	}
}
