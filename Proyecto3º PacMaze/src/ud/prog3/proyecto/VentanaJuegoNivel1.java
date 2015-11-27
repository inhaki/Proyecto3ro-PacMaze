package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**Clase principal del nivel 1 del juego
 * Ventana del nivel 1
 * @author JulenT IñakiG
 */
public class VentanaJuegoNivel1 extends JFrame implements KeyListener{

	//private JPanel panelnv1;
    private Laberinto1 LaberintoVirtual = new Laberinto1();//tipo Laberinto1 que es la clase que tiene la forma del laberinto
    private JLabel[][] LaberintoGrafico = new JLabel[LaberintoVirtual.DevolverCantidadFilasLaberinto()][LaberintoVirtual.DevolverCantidadColumnasLaberinto(0)];
    private PersComecoco Pacman = new PersComecoco();//comecoco del juego
    ArrayList<ObjEstrella> estrellas = new ArrayList<>();//ArrayList que contiene las bolas del juego
    private JLabel PersComecoco = new JLabel();//label para el avatar comecoco
    private boolean PartidaTerminada = false;
    
    /**Constructor de la venta, crea y devuelve la ventana inicializada
     * no hay ningún avatar dentro
     */
    public VentanaJuegoNivel1()
    {
    	/*panelnv1= new JPanel();
    	panelnv1.setLayout(null);
    	panelnv1.setBackground(Color.white);*/
        setTitle("PACMAZE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620,540);
        setVisible(true);
        addKeyListener(this);
        
        //Carga el avatar del jugador
        //haremos un switch aqui para cargar diferentes avatares dependiendo de la elección del jugador
        CargarUsuario();
        
        //Cargar las estrellas, bolas del juego
        CargarEstrellas();

        //Genera el laberinto grafico a partir del Laberinto virtual
        //LABERINTO DE NIVEL 1, EL INICIAL
        GenerarLaberintoGrafico();
        

        
        this.requestFocus();
        try{Thread.sleep(1000);
        }catch(Exception e){}
        
        EmpezarPartida();

    }
    
    /** METODO QUE GENERA EL LABERINTO, Utilizando las imagenes gif correspondientes
     *Este es el primer laberinto que tendrá el juego
     */
    private void GenerarLaberintoGrafico()
    {
    	//el for da 40 vueltas, tantas como las filas declaradas en la clase Laberinto1, DelvolverCantidadColumnas es un metodo en esta clase
        for(int i = 0; i < LaberintoVirtual.DevolverCantidadFilasLaberinto(); i++)
        {
        	//por cada fila tiene que generar una columna, el for hace de nuevo 40 vueltas, porque tenemos 40 columnas
            //CON EL PRIMER for establecemos las imagenes en el orden deseado
        	for(int j = 0; j < LaberintoVirtual.DevolverCantidadColumnasLaberinto(i); j++)
            {
                LaberintoGrafico[i][j] = new JLabel();
                add(LaberintoGrafico[i][j]);
            	LaberintoGrafico[i][j].setIcon(new ImageIcon("Imagenes/Laberinto/Laberinto GIF/" + LaberintoVirtual.DeolverCodigoImagenMatriz(i, j) + ".gif"));
            }
        }
        //de nuevo al igual que en los for de arriba hacemos 40x40 vueltas, y en esta ocasion establecemos el ancho y alto
        for(int i = 0; i < LaberintoVirtual.DevolverCantidadFilasLaberinto(); i++)
        {
            for(int j = 0; j < LaberintoVirtual.DevolverCantidadColumnasLaberinto(i); j++)
            {
            	LaberintoGrafico[i][j].setBounds(i*LaberintoVirtual.DevolverLargoImagenes(), j*LaberintoVirtual.DevolverAlturaImagenes(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
                LaberintoGrafico[i][j].validate();
            }
        }
    }
    
    /**METODO PARA SACAR POR PANTALLA AL PERSONAJE COMECOCOS
     */
    private void CargarUsuario()
    {
       //AÃ±ade el Usuario al laberinto
       Pacman.EstablecerPosicionInicial(60, 80);//posicion base del personaje, metodo de PersonajeJuego
       Pacman.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
        
       //Carga las posiciones del usuario
       PersComecoco.setIcon(Pacman.ObtenerImagen());//llama al atributo protected de la clase PersonajeJuego que devuelve una imagen
       PersComecoco.setBounds(Pacman.PosicionX,Pacman.PosicionY,20,20);
       add(PersComecoco);
       PersComecoco.validate();
    }
    
    
    /**METODO QUE NOS PERMITE AÑADIR ESTRELLAS AL JUEGO
     */
 private void CargarEstrellas(){
    	
    	//CREAR LAS FILAS Y COLUMNAS CON LAS BOLAS
    	for (int i = 0; i < 13; i++) {//linea izquierda arriba 2da fila
			estrellas.add(new ObjEstrella(i*20+45, 81));
		}
    	for(int j=0; j<6; j++){//linea vertical arriba izq, primera columna
    		estrellas.add(new ObjEstrella(25, j*20+21));
    	}
    	for (int i=0; i<7; i++){//fila arriba izq del todo hasta la mitad
    		estrellas.add (new ObjEstrella(i*20+45, 21));
    	}
    	for (int i=0; i<3; i++){//3era fila desde arriba a la izquierda hasta choque con pared
    		estrellas.add (new ObjEstrella(i*20+45, 121));
    	}
    	for (int j=0; j<2; j++){//columna arriba izquierda, 2da linea
    		estrellas.add (new ObjEstrella(165, j*20+41));
    	}
    	int i = 0;
    	for (JLabel EstrellaBola : estrellas) {//JLabel para instanciar las estrellas, por cada elemento que contenga el arraylist insertamos un label
    	   
    		//Carga la posicion de la estrella
    		EstrellaBola.setIcon(estrellas.get(i).ObtenerEstrella());
    	    System.out.println(estrellas.get(i).PosicionX+ " "+estrellas.get(i).PosicionY);
    	    EstrellaBola.setBounds(estrellas.get(i).PosicionX, estrellas.get(i).PosicionY, 20, 20);
    	    add(EstrellaBola);
    	    EstrellaBola.validate();
    	    i++;
		}
    }
    
    public void EmpezarPartida()
    {
        Pacman.MoverPacman();
        
        while(!PartidaTerminada)        //Bucle Principal
        {
            //Pinta el usuario
        	PersComecoco.setIcon(Pacman.ImagenCoco);
        	PersComecoco.setBounds(Pacman.PosicionX,Pacman.PosicionY,20,20);
        	PersComecoco.repaint();
            
        }
    }
    
    ///////////////////////////////////////////////////
    //EVENTOS KEYLISTENER PARA LA ESCUCHA DEL TECLADO//
    ///////////////////////////////////////////////////
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {//Controles del teclado con tecla 
    	switch(e.getKeyCode())
    	{
    		case KeyEvent.VK_LEFT: //Izquierda
            {
            	Pacman.Izquierda = false;
                break;
            }
            case KeyEvent.VK_UP: //Arriba
            {
                Pacman.Arriba = false;
                break;
            }
            case KeyEvent.VK_RIGHT: //Derecha
            {
                Pacman.Derecha = false;
                break;
            }
            case KeyEvent.VK_DOWN: //Abajo
            {
                Pacman.Abajo = false;
                break;
            }
        }
   }
   public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: //Izquierda
            {
                Pacman.Izquierda = true;
                break;
            }
            case KeyEvent.VK_UP: //Arriba
            {
                Pacman.Arriba = true;
                break;
            }
            case KeyEvent.VK_RIGHT: //Derecha
            {
                Pacman.Derecha = true;
                break;
            }
            case KeyEvent.VK_DOWN: //Abajo
            {
                Pacman.Abajo = true;
                break;
            }
        }
    }
}

