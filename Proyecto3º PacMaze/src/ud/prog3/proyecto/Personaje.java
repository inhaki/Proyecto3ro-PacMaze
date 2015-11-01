package ud.prog3.proyecto;

/**Clase que sirve para poder mover y sacar por pantalla a todos los personajes disponibles en el juego
 * Hereda de la clase ComponenteLaberinto
 */
public class Personaje{
	
	protected double PosX; //Posici�n en X
	protected double PosY; //Posici�n en Y
	protected double Velocidad; //Velocidad del personaje, ser� fija a no ser que haya bonificaci�n
	protected double Direccion; //Direcci�n, podr� estar entre 0-360� solo 0-90-180-270

	public Personaje() {
	
		PosX=50;
		PosY=50;
		Velocidad= 0;
		Direccion= 0;
	}

	public double getPosX() {
		return PosX;
	}
	public void setPosX(double posX) {
		PosX = posX;
	}

	public double getPosY() {
		return PosY;
	}
	public void setPosY(double posY) {
		PosY = posY;
	}
	
	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
	public double getVelocidad() {
		return Velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.Velocidad = velocidad;
	}

	public double getDireccion() {
		return Direccion;
	}
	//Metodo copiado de la Pr�ctica2, para establecer la direcci�n del personaje
	public void setDireccion(double direc) {
		// if (dir < 0) dir = 360 + dir;
				if (direc > 360) direc = direc - 360;
				Direccion = direc;
	}
	
	/** Cambia la direcci�n actual del coche, solo cambia de 90 grandos en 90 grados
	 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) {
		setDireccion(Direccion + giro );
	}
}
