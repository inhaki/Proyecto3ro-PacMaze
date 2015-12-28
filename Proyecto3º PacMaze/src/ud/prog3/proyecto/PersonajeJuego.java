package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


import javax.swing.Timer;

public class PersonajeJuego extends JLabel{
	
	protected ImageIcon ImagenCoco;//Imagen del Comecoco-Personaje1
	protected ImageIcon ImagenEstrella;//Imagen del arraylist de estrellas que habra
	protected ImageIcon ImagenManzana;//Imagen de la manzana--hacer TEST
	protected ImageIcon ImagenObjEstrella;//Imagen de la estrella de bonificacion--hacer TEST sobre si dobla velocidad o n
	protected ImageIcon ImagenCamino;//Imagen del la casilla camino de 20x20 para "borrar" objetos
	protected int PosicionX=0;//Posicion del comecoco en X se da valor en VentanaJuegoNivel1
	protected int PosicionY=0;//Posicion en Y del comecoco
	protected int Velocidad=2;//Velocidad inicial que va a tener el personaje
	protected int CodDireccion=3;//Direccion inicial del objeto
	//1 arriba, 2 abajo, 3 derecha, 4 izda
	protected boolean PersonajeDetenido= false;//cuando choca con la pared se pone a false y se detiene
	
	protected Rectangle LaberintoVirtual1[][];//objeto donde se instancia el dibujo gráfico del Laberinto
	
	//COMANDOS del teclado
	protected boolean Arriba= false;
	protected boolean Abajo= false;
	protected boolean Derecha= false;
	protected boolean Izquierda= false;
	
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
	 protected ImageIcon ObtenerEstrella(){return ImagenEstrella;}
	 protected ImageIcon ObtenerManzana(){return ImagenManzana;}
	 protected ImageIcon ObtenerEstrellaPuntas(){return ImagenObjEstrella;}
	 protected ImageIcon ObtenerCamino(){return ImagenCamino;}
	 protected boolean CambioDireccion = true;
	 
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
	    
	    protected void CambiarImagen(){}//LAMA AL METODO DE LA CLASE Usuario, y le dice que direccion adoptar a la imagen
	}