package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


import javax.swing.Timer;

public class PersonajeJuego extends JLabel{
	
	protected ImageIcon ImagenCoco;//Imagen del Comecoco-Personaje1
	protected ImageIcon ImagenBala;//Imagen de la Bala-Personaje2
	protected ImageIcon ImagenR2D2;//Imagen de R2D2-Personaje3
	protected ImageIcon ImagenEstrella;//Imagen del arraylist de estrellas que habra
	protected ImageIcon ImagenManzana;//Imagen de la manzana--hacer TEST
	protected ImageIcon ImagenObjEstrella;//Imagen de la estrella de bonificacion--hacer TEST sobre si dobla velocidad o n
	protected ImageIcon ImagenBolaMala;//Imagen del la casilla camino de 20x20 para "borrar" objetos
	protected ImageIcon ImagenCalavera;
	protected int PosicionX=0;//Posicion del comecoco en X se da valor en VentanaJuegoNivel1
	protected int PosicionY=0;//Posicion en Y del comecoco
	protected int Velocidad=2;//Velocidad inicial que va a tener el personaje
	protected int CodDireccion=3;//Direccion inicial del objeto
	//1 arriba, 2 abajo, 3 derecha, 4 izda
	protected boolean PersonajeDetenido= false;//cuando choca con la pared se pone a false y se detiene
	
	protected Rectangle LaberintoVirtual1[][];//objeto donde se instancia el dibujo gráfico del Laberinto
	protected Rectangle LaberintoVirtual2[][];//se instancia el segundo laberinto
	protected Rectangle LaberintoVirtual3[][];//se instancia el tercer laberinto gráfico
	
	//COMANDOS del teclado
	protected boolean Arriba= false;
	protected boolean Abajo= false;
	protected boolean Derecha= false;
	protected boolean Izquierda= false;
	
	//GETTERS AND SETTERS DE LOS ATRIBUTOS CLAVE
	public void setPosicionX(int posX){
		this.PosicionX=posX;
	}
	public void setPosicionY(int posY){
		this.PosicionY=posY;
	}
	public void setVelocidad(int miVelocidad){
		this.Velocidad=miVelocidad;
	}
	public int getPosicionX(){
		return PosicionX;
	}
	public int getPosicionY(){
		return PosicionY;
	}
	public int getVelocidad(){
		return Velocidad;
	}
	/////
	/**METODO PARA INSTANCIAR LOS PIXELS QUE COMPONEN EL LABERINTO EN EL PANEL
	 * @param Laberinto es un String que viene de la clase Laberinto1, el patrón del dibujo
	 * @param LargoImagenes 10pixl
	 * @param AlturaImagenes 10pixl
	 */
	protected void EstablecerLaberinto1(String Laberinto[][], int LargoImagenes, int AlturaImagenes){
		
		LaberintoVirtual1= new Rectangle[Laberinto.length][];
		
		for(int i = 0; i < Laberinto.length; i++)
        {
            LaberintoVirtual1[i] = new Rectangle[Laberinto[i].length];
            
            for(int j = 0; j < Laberinto[i].length; j++)
            {	
                if(!(Laberinto[i][j].equals("CA")))
                {
                    LaberintoVirtual1[i][j] = new Rectangle(i*LargoImagenes,j*AlturaImagenes,LargoImagenes,AlturaImagenes);
                }
                else
                {
                    LaberintoVirtual1[i][j] = null;
                }
            }
        }
		
	}
	/**METODO PARA INSTANCIAR LOS PIXELS QUE COMPONEN EL LABERINTO 2 EN EL PANEL
	 * @param Laberinto es un String que viene de la clase Laberinto2, el patrón del dibujo
	 * @param LargoImagenes 20pixl
	 * @param AlturaImagenes 20pixl
	 */
	protected void EstablecerLaberinto2(String Laberinto[][], int LargoImagenes, int AlturaImagenes){
		
		LaberintoVirtual2= new Rectangle[Laberinto.length][];
		
		for(int i = 0; i < Laberinto.length; i++)
        {
            LaberintoVirtual2[i] = new Rectangle[Laberinto[i].length];
            
            for(int j = 0; j < Laberinto[i].length; j++)
            {	
                if(!(Laberinto[i][j].equals("CAM")))
                {
                    LaberintoVirtual2[i][j] = new Rectangle(i*LargoImagenes,j*AlturaImagenes,LargoImagenes,AlturaImagenes);
                }
                else
                {
                    LaberintoVirtual2[i][j] = null;
                }
            }
        }
		
	}
	/**METODO PARA INSTANCIAR LOS PIXELS QUE COMPONEN EL LABERINTO 3 EN EL PANEL
	 * @param Laberinto clase Laberinto3, el patrón del dibujo
	 * @param LargoImagenes 20pixl
	 * @param AlturaImagenes 20pixl
	 */
	protected void EstablecerLaberinto3(String Laberinto[][], int LargoImagenes, int AlturaImagenes){
		
		LaberintoVirtual3= new Rectangle[Laberinto.length][];
		
		for(int i = 0; i < Laberinto.length; i++)
        {
            LaberintoVirtual3[i] = new Rectangle[Laberinto[i].length];
            
            for(int j = 0; j < Laberinto[i].length; j++)
            {	
                if(!(Laberinto[i][j].equals("CM")))
                {
                    LaberintoVirtual3[i][j] = new Rectangle(i*LargoImagenes,j*AlturaImagenes,LargoImagenes,AlturaImagenes);
                }
                else
                {
                    LaberintoVirtual3[i][j] = null;
                }
            }
        }
		
	}	
	/**Metodo para establecer la posicion inicial del personaje, aqui se pasan las variables
	 * los valores se le asignan en VentanaJuegoNivel1
	 * @param X Posicion inicial en X del personaje
	 * @param Y Posicion inicial en Y del personaje
	 */
	 protected void EstablecerPosicionInicial(int X, int Y)
	    {
	        PosicionX = X;
	        PosicionY = Y;
	    }
	 
	 //Atributos de aumentar y disminuir la posicion del avatar
	 //dan la movilidad al personaje, permiten que se mueva por pantalla
	 protected void AumentarPosicionX(){PosicionX = PosicionX + Velocidad;}
	 protected void DisminuirPosicionX(){PosicionX = PosicionX - Velocidad;}
	 protected void AumentarPosicionY(){PosicionY = PosicionY + Velocidad;}
	 protected void DisminuirPosicionY(){PosicionY = PosicionY - Velocidad;}
	 protected void CambiarVelocidad(int NuevoValor){Velocidad = NuevoValor;}
	 protected ImageIcon ObtenerImagen(){return ImagenCoco;}
	 protected ImageIcon ObtenerImagenBala(){return ImagenBala;}
	 protected ImageIcon ObtenerImagenR2D2(){return ImagenR2D2;}
	 protected ImageIcon ObtenerEstrella(){return ImagenEstrella;}
	 protected ImageIcon ObtenerManzana(){return ImagenManzana;}
	 protected ImageIcon ObtenerEstrellaPuntas(){return ImagenObjEstrella;}
	 protected ImageIcon ObtenerBolaMala(){return ImagenBolaMala;}
	 protected ImageIcon ObtenerCalavera(){return ImagenCalavera;}
	 protected boolean CambioDireccion = true;
	 
	 /**Para comprobar si choca el avatar comecoco con las paredes del Laberinto1
	  * Se crea una rectangulo con la posicion del pacman
	  * @param PosX del avatar
	  * @param PosY del avatar
	  * @return false o true si estan chocando
	  */
	 protected boolean VerificarParedes(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionPacman = new Rectangle(PosX,PosY,ImagenCoco.getIconWidth(), ImagenCoco.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual1.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual1[i].length; j++)
				{
					if(LaberintoVirtual1[i][j] != null)
					{
						if(PosicionPacman.intersects(LaberintoVirtual1[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	 }
	 /**Metodo de comprobación de paredes para el comecoco en el nivel 2
	  * @param PosX
	  * @param PosY
	  * @return
	  */
	 protected boolean VerificarParedes2(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionPacman = new Rectangle(PosX,PosY,ImagenCoco.getIconWidth(), ImagenCoco.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual2.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual2[i].length; j++)
				{
					if(LaberintoVirtual2[i][j] != null)
					{
						if(PosicionPacman.intersects(LaberintoVirtual2[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    }
	 /**Metodo de comprobación de paredes para el comecoco en el nivel 3
	  * @param PosX
	  * @param PosY
	  * @return
	  */
	 protected boolean VerificarParedes3(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionPacman = new Rectangle(PosX,PosY,ImagenCoco.getIconWidth(), ImagenCoco.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual3.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual3[i].length; j++)
				{
					if(LaberintoVirtual3[i][j] != null)
					{
						if(PosicionPacman.intersects(LaberintoVirtual3[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    }
	 
	 /**Metodo de Verificacion de la Bala LABERINTO 1
	  */
	 protected boolean VerificarParedesBala(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionBala = new Rectangle(PosX,PosY,ImagenBala.getIconWidth(), ImagenBala.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual1.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual1[i].length; j++)
				{
					if(LaberintoVirtual1[i][j] != null)
					{
						if(PosicionBala.intersects(LaberintoVirtual1[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    }
	 
	 /**Metodo de Verificacion de la Bala LABERINTO 2
	  */
	 protected boolean VerificarParedesBala2(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionBala = new Rectangle(PosX,PosY,ImagenBala.getIconWidth(), ImagenBala.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual2.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual2[i].length; j++)
				{
					if(LaberintoVirtual2[i][j] != null)
					{
						if(PosicionBala.intersects(LaberintoVirtual2[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    }
	 /**Metodo de Verificacion de la Bala LABERINTO 2
	  */
	 protected boolean VerificarParedesBala3(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el pacman
		Rectangle PosicionBala = new Rectangle(PosX,PosY,ImagenBala.getIconWidth(), ImagenBala.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual3.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual3[i].length; j++)
				{
					if(LaberintoVirtual3[i][j] != null)
					{
						if(PosicionBala.intersects(LaberintoVirtual3[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    }
	 
	 /**Metodo de Verificacion del androide R2D2 LABERINTO 1
	  */
	 protected boolean VerificarParedesR2D2(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el androide
		Rectangle PosicionR2D2 = new Rectangle(PosX,PosY,ImagenR2D2.getIconWidth(), ImagenR2D2.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual1.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual1[i].length; j++)
				{
					if(LaberintoVirtual1[i][j] != null)
					{
						if(PosicionR2D2.intersects(LaberintoVirtual1[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    } 
	 
	 /**Metodo de Verificacion del androide R2D2 LABERINTO 2
	  */
	 protected boolean VerificarParedesR2D22(int PosX, int PosY)
	 {
		//Obtiene todas las paredes y compara si chocan con el androide
		Rectangle PosicionR2D2 = new Rectangle(PosX,PosY,ImagenR2D2.getIconWidth(), ImagenR2D2.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual2.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual2[i].length; j++)
				{
					if(LaberintoVirtual2[i][j] != null)
					{
						if(PosicionR2D2.intersects(LaberintoVirtual2[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    } 
	 /**Metodo de Verificacion del androide R2D2 LABERINTO 2
	  */
	 protected boolean VerificarParedesR2D23(int PosX, int PosY)
	 {
		Rectangle PosicionR2D2 = new Rectangle(PosX,PosY,ImagenR2D2.getIconWidth(), ImagenR2D2.getIconHeight());
		 
			for(int i = 0; i < LaberintoVirtual3.length; i++)
			{
				for(int j = 0; j < LaberintoVirtual3[i].length; j++)
				{
					if(LaberintoVirtual3[i][j] != null)
					{
						if(PosicionR2D2.intersects(LaberintoVirtual3[i][j]))
						{
							return true;
						}
					}
				}
	        }
	        return false;
	    } 
	 /**Metodo para mover el personaje del comecoco   
	  */
	protected void MoverPersonaje()
	{//MUEVE EL OBJETO SEGUN LO QUE APUNTE EL TECLADO
	 //ARRIBA ES EL CODIGO 1, ABAJO CODIGO 2, DER CODIGO 3, IZDA CODIGO 4
		Timer BucleJuego = new Timer(15, new ActionListener()
		//timer para refrescar la posicion del comecoco cada ese tiempo
	    { 
			public void actionPerformed(ActionEvent e) 
	        {
				MoverPosicionPersonaje();
	                
	            	if(Arriba)
	                if(!VerificarParedes(PosicionX,PosicionY - Velocidad))
	                {
	                	CodDireccion = 1;
	                    CambiarImagen();//LLAMA AL METODO PROTECTED DE ESTA CLASE, ABAJO DEL TODO, ESTÁ VACÍO
	                     //HAY UN METODO CAMBIAR IMAGEN EN LA CLASE Usuario
	                     //DEPENDIENDO DE LA DIRECCION, EL CODIGO, LA IMAGEN MIRA HACIA UN LADO U OTRO
	                }
	                if(Abajo)
	                if(!VerificarParedes(PosicionX,PosicionY + Velocidad))
	                {
	                     CodDireccion = 2;
	                     CambiarImagen();
	                }
	                if(Derecha)
	                if(!VerificarParedes(PosicionX + Velocidad,PosicionY))
	                {
	                     CodDireccion = 3;
	                     CambiarImagen();
	                }
	                if(Izquierda)
	                if(!VerificarParedes(PosicionX - Velocidad, PosicionY))
	                {
	                     CodDireccion = 4;
	                     CambiarImagen();
	                }
	         } 
	     });
	     BucleJuego.start();   
	 }
	
	/**Mismo metodo pero para el personaje del comecoco
	 * 	
	 */
	protected void MoverPersonajeBala()
	{
		
		Timer BucleJuego = new Timer(15, new ActionListener()
		
	    { 
			public void actionPerformed(ActionEvent e) 
	        {
				MoverPosicionPersonajeBala();
	                
	            	if(Arriba)
	                if(!VerificarParedesBala(PosicionX,PosicionY - Velocidad))
	                {
	                	CodDireccion = 1;
	                    CambiarImagen(); 
	                }
	                if(Abajo)
	                if(!VerificarParedesBala(PosicionX,PosicionY + Velocidad))
	                {
	                     CodDireccion = 2;
	                     CambiarImagen();
	                }
	                if(Derecha)
	                if(!VerificarParedesBala(PosicionX + Velocidad,PosicionY))
	                {
	                     CodDireccion = 3;
	                     CambiarImagen();
	                }
	                if(Izquierda)
	                if(!VerificarParedesBala(PosicionX - Velocidad, PosicionY))
	                {
	                     CodDireccion = 4;
	                     CambiarImagen();
	                }
	          } 
	     });
	     BucleJuego.start();
	        
	}
	
	/**Metodo para mover el personaje de R2D2   
	  */
	protected void MoverPersonajeR2D2()
	{
		Timer BucleJuego = new Timer(15, new ActionListener()
		
	    { 
			public void actionPerformed(ActionEvent e) 
	        {
				MoverPosicionPersonajeR2D2();
	                
	            	if(Arriba)
	                if(!VerificarParedesR2D2(PosicionX,PosicionY - Velocidad))
	                {
	                	CodDireccion = 1;
	                    CambiarImagen();
	                }
	                if(Abajo)
	                if(!VerificarParedesR2D2(PosicionX,PosicionY + Velocidad))
	                {
	                     CodDireccion = 2;
	                     CambiarImagen();
	                }
	                if(Derecha)
	                if(!VerificarParedesR2D2(PosicionX + Velocidad,PosicionY))
	                {
	                     CodDireccion = 3;
	                     CambiarImagen();
	                }
	                if(Izquierda)
	                if(!VerificarParedesR2D2(PosicionX - Velocidad, PosicionY))
	                {
	                     CodDireccion = 4;
	                     CambiarImagen();
	                }
	         } 
	     });
	     BucleJuego.start();   
	 }
	    
	    private void MoverPosicionPersonaje()
	    {
	    	//orienta al personaje dependiendo de la tecla pulsada
	    	//el personaje tiene velocidad fija por tanto con cambiar la direccion
	    	//es suficiente para dirigir al perosnaje
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedes(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedes(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedes(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedes(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    
	    /**Metodo de movimiento para la bala
	     */
	    private void MoverPosicionPersonajeBala()
	    {
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesBala(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesBala(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesBala(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesBala(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    private void MoverPosicionPersonajeR2D2()
	    {
	    	//orienta al personaje dependiendo de la tecla pulsada
	    
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesR2D2(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesR2D2(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesR2D2(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesR2D2(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    /*-------------------METODOS PARA EL MOVIMIENTO EN EL SEGUNDO LABERINTO----------------*/
	    /**Mover El personaje comecocos por el 2do laberinto
	     */
	    protected void MoverPersonaje2()
		{//MUEVE EL OBJETO SEGUN LO QUE APUNTE EL TECLADO
		 //ARRIBA ES EL CODIGO 1, ABAJO CODIGO 2, DER CODIGO 3, IZDA CODIGO 4
			Timer BucleJuego = new Timer(15, new ActionListener()
			//timer para refrescar la posicion del comecoco cada ese tiempo
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonaje2();
		                
		            	if(Arriba)
		                if(!VerificarParedes2(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();//LLAMA AL METODO PROTECTED DE ESTA CLASE, ABAJO DEL TODO, ESTÁ VACÍO
		                     //HAY UN METODO CAMBIAR IMAGEN EN LA CLASE Usuario
		                     //DEPENDIENDO DE LA DIRECCION, EL CODIGO, LA IMAGEN MIRA HACIA UN LADO U OTRO
		                }
		                if(Abajo)
		                if(!VerificarParedes2(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedes2(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedes2(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    protected void MoverPersonajeBala2()
		{//MUEVE EL OBJETO SEGUN LO QUE APUNTE EL TECLADO
		 //ARRIBA ES EL CODIGO 1, ABAJO CODIGO 2, DER CODIGO 3, IZDA CODIGO 4
			Timer BucleJuego = new Timer(15, new ActionListener()
			//timer para refrescar la posicion del comecoco cada ese tiempo
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonajeBala2();
		                
		            	if(Arriba)
		                if(!VerificarParedesBala2(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();//LLAMA AL METODO PROTECTED DE ESTA CLASE, ABAJO DEL TODO, ESTÁ VACÍO
		                     //HAY UN METODO CAMBIAR IMAGEN EN LA CLASE Usuario
		                     //DEPENDIENDO DE LA DIRECCION, EL CODIGO, LA IMAGEN MIRA HACIA UN LADO U OTRO
		                }
		                if(Abajo)
		                if(!VerificarParedesBala2(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedesBala2(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedesBala2(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    protected void MoverPersonajeR2D22()
		{//MUEVE EL OBJETO SEGUN LO QUE APUNTE EL TECLADO
		 //ARRIBA ES EL CODIGO 1, ABAJO CODIGO 2, DER CODIGO 3, IZDA CODIGO 4
			Timer BucleJuego = new Timer(15, new ActionListener()
			//timer para refrescar la posicion del comecoco cada ese tiempo
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonajeR2D22();
		                
		            	if(Arriba)
		                if(!VerificarParedesR2D22(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();//LLAMA AL METODO PROTECTED DE ESTA CLASE, ABAJO DEL TODO, ESTÁ VACÍO
		                     //HAY UN METODO CAMBIAR IMAGEN EN LA CLASE Usuario
		                     //DEPENDIENDO DE LA DIRECCION, EL CODIGO, LA IMAGEN MIRA HACIA UN LADO U OTRO
		                }
		                if(Abajo)
		                if(!VerificarParedesR2D22(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedesR2D22(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedesR2D22(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    private void MoverPosicionPersonaje2()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedes2(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedes2(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedes2(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedes2(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    
	    private void MoverPosicionPersonajeBala2()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesBala2(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesBala2(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesBala2(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesBala2(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    
	    private void MoverPosicionPersonajeR2D22()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesR2D22(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesR2D22(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesR2D22(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesR2D22(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    /**----------FIN DE LOS METODOS PARA EL MOVIMIENTO EN EL LABERINTO 2--------------*/
	    /*-----------INICIO DE LOS METODOS PARA EL MOVIMIENTO DE LOS AVATARES EN EL LABERINTO 3*/
	    /**Mover El personaje comecocos por el 2do laberinto
	     */
	    protected void MoverPersonaje3()
		{
			Timer BucleJuego = new Timer(15, new ActionListener()
			
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonaje3();
		                
		            	if(Arriba)
		                if(!VerificarParedes3(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();//LLAMA AL METODO PROTECTED DE ESTA CLASE, ABAJO DEL TODO, ESTÁ VACÍO
		                     //HAY UN METODO CAMBIAR IMAGEN EN LA CLASE Usuario
		                     //DEPENDIENDO DE LA DIRECCION, EL CODIGO, LA IMAGEN MIRA HACIA UN LADO U OTRO
		                }
		                if(Abajo)
		                if(!VerificarParedes3(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedes3(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedes3(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    protected void MoverPersonajeBala3()
		{
		 	Timer BucleJuego = new Timer(15, new ActionListener()
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonajeBala3();
		                
		            	if(Arriba)
		                if(!VerificarParedesBala3(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();
		                }
		                if(Abajo)
		                if(!VerificarParedesBala3(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedesBala3(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedesBala3(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    protected void MoverPersonajeR2D23()
		{
			Timer BucleJuego = new Timer(15, new ActionListener()
		    { 
				public void actionPerformed(ActionEvent e) 
		        {
					MoverPosicionPersonajeR2D23();
		                
		            	if(Arriba)
		                if(!VerificarParedesR2D23(PosicionX,PosicionY - Velocidad))
		                {
		                	CodDireccion = 1;
		                    CambiarImagen();
		                }
		                if(Abajo)
		                if(!VerificarParedesR2D23(PosicionX,PosicionY + Velocidad))
		                {
		                     CodDireccion = 2;
		                     CambiarImagen();
		                }
		                if(Derecha)
		                if(!VerificarParedesR2D23(PosicionX + Velocidad,PosicionY))
		                {
		                     CodDireccion = 3;
		                     CambiarImagen();
		                }
		                if(Izquierda)
		                if(!VerificarParedesR2D23(PosicionX - Velocidad, PosicionY))
		                {
		                     CodDireccion = 4;
		                     CambiarImagen();
		                }
		         } 
		     });
		     BucleJuego.start();   
		 }
	    
	    private void MoverPosicionPersonaje3()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedes3(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedes3(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedes3(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedes3(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    
	    private void MoverPosicionPersonajeBala3()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesBala3(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesBala3(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesBala3(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesBala3(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    
	    private void MoverPosicionPersonajeR2D23()
	    {
	    	
	        switch(CodDireccion)
	        {
	            case 1: //Arriba
	            {
	                if(!VerificarParedesR2D23(PosicionX,PosicionY - Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 2: //Abajo
	            {
	                if(!VerificarParedesR2D23(PosicionX,PosicionY + Velocidad))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionY();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	            case 3: //Derecha
	            {
	                if(!VerificarParedesR2D23(PosicionX + Velocidad,PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    AumentarPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }

	                break;
	            }
	            case 4: //Izquierda
	            {
	                if(!VerificarParedesR2D23(PosicionX - Velocidad, PosicionY))
	                {
	                    PersonajeDetenido = false;
	                    DisminuirPosicionX();
	                }
	                else
	                {
	                    PersonajeDetenido = true;
	                }
	                break;
	            }
	        }
	    }
	    /*******------FIN DE LOS MOVIMIENTOS EN EL LABERINTO 3.--------*/
	    protected void CambiarImagen(){}//LAMA AL METODO DE LA CLASE Usuario, y le dice que direccion adoptar a la imagen
	}