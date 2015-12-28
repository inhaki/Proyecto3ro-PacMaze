package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

/**Clase principal del nivel 1 del juego
 * Ventana del nivel 1
 * @author JulenT IñakiG
 */
public class VentanaJuegoNivel1 extends JFrame implements KeyListener{

	private JPanel panelJuego;
	private Laberinto1 LaberintoVirtual = new Laberinto1();//tipo Laberinto1 que es la clase que tiene la forma del laberinto
    private JLabel[][] LaberintoGrafico = new JLabel[LaberintoVirtual.DevolverCantidadFilasLaberinto()][LaberintoVirtual.DevolverCantidadColumnasLaberinto(0)];
    private PersComecoco Pacman = new PersComecoco();//comecoco del juego
    private PersonajeJuego avatar= new PersonajeJuego();//avatar del juego
    private ObjManzana manzana= new ObjManzana();//manzana que aparece de bonificación
    private ObjEstrellaPuntas estrellaPuntas= new ObjEstrellaPuntas();//Estrella de 5 puntas que da bonificación
    private ObjCamino camino= new ObjCamino();//pixel del camino que sirve para eliminar objetos sobreponiendo la imagen
    ArrayList<ObjEstrella> estrellas = new ArrayList<>();//ArrayList que contiene las bolas del juego
    private JLabel PersComecoco = new JLabel();//label para el avatar comecoco
    private JLabel ObjManzana= new JLabel();//label para el objeto manzana
    private JLabel ObjEstrellaPuntas= new JLabel();//label para el objeto Estrella
    private JLabel ObjCamino= new JLabel();//Label para el camino extra
    private boolean PartidaTerminada = false;
    private JLabel EstrellaBola= new JLabel();
    private JTextField textMinutos;
    private JTextField textSegundos;
    Timer myTimer;//timer para el contador
    private boolean parado;//para parar el tiempo si se excede la puntuacion
    private JTextField textPuntuacion;
   
      
    /**Constructor de la ventana, crea y devuelve la ventana inicializada
     * no hay ningún avatar dentro
     */
    public VentanaJuegoNivel1()
    {
    	
        setTitle("PACMAZE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620,540);
        setResizable(false);
        setVisible(true);
        addKeyListener(this);
        getContentPane().setLayout(null);
        
        panelJuego = new JPanel();
        panelJuego.setBackground(Color.white);
        panelJuego.setBounds(20, 20, 400, 400);
        
        getContentPane().add(panelJuego);
        
        //CONTADOR JUEGO
        //CASILLA DE LOS MINUTOS
        textMinutos = new JTextField();
        textMinutos.setHorizontalAlignment(SwingConstants.RIGHT);
        textMinutos.setEditable(false);
        textMinutos.setFont(new Font("Tahoma", Font.BOLD, 18));
        textMinutos.setBounds(252, 429, 52, 29);
        getContentPane().add(textMinutos);
        textMinutos.setColumns(10);
        //CASILLA DE LOS SEGUNDOS
        textSegundos = new JTextField();
        textSegundos.setEditable(false);
        textSegundos.setFont(new Font("Tahoma", Font.BOLD, 18));
        textSegundos.setColumns(10);
        textSegundos.setBounds(305, 429, 52, 29);
        getContentPane().add(textSegundos);
        //VALORES INICIALES
        textMinutos.setText("02");
        textSegundos.setText("07");
        //CASILLA DE LA PUNTUACION
        textPuntuacion =new JTextField();
        textPuntuacion.setHorizontalAlignment(SwingConstants.RIGHT);
        textPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 44));
        textPuntuacion.setEditable(false);
        textPuntuacion.setBounds(30, 423, 200, 47);
        getContentPane().add(textPuntuacion);
        textPuntuacion.setColumns(10);
        //VALOR INICIAL
        textPuntuacion.setText("0");     
        //LABEL PUNTUACION
        JLabel lblPuntuacion = new JLabel("PUNTUACION");
        lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPuntuacion.setBounds(65, 471, 129, 20);
        getContentPane().add(lblPuntuacion);
        
        //NO FUNCIONA BIEN ESTE HILO
        Timer myTimer =new Timer();
          
        Thread BucleContador= new Thread(myTimer);
        BucleContador.start();
        
        //Carga el avatar del jugador
        //haremos un switch aqui para cargar diferentes avatares dependiendo de la elección del jugador
        CargarUsuario();
        
        //Cargar las estrellas, bolas del juego
        CargarEstrellas();
        ChoqueBola();
        
        //Cargar el objeto bonus manzana
        //PODEMOS HACER UN RANDOM PARA QUE APAREZCAN ALEATORIAMENTE SEGUN EL TIEMPO, ENTRE LOS OBJETOS DISPONIBLES
        CargarManzana();
        CargarObjEstrella();
        QuitaObjEstrella(6000);

        //Genera el laberinto grafico a partir del Laberinto virtual
        //LABERINTO DE NIVEL 1, EL INICIAL
        GenerarLaberintoGrafico();
        

       
       this.requestFocus();
       try{Thread.sleep(1000);
       //Conversor();
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
                panelJuego.add(LaberintoGrafico[i][j]);
                LaberintoGrafico[i][j].setIcon(new ImageIcon("Imagenes/Laberinto/Laberinto GIF/" + LaberintoVirtual.DeolverCodigoImagenMatriz(i, j) + ".gif"));
                //if(LaberintoGrafico[i][j].equals("CA"))
                if(LaberintoVirtual.LaberintoActual[i][j].equals("CA"))
                {
                	estrellas.add(new ObjEstrella(i, j));
                } 
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
        
        /*for(int w=0;w<estrellas.size();w++){
        	//Carga la posicion de la estrella
         	JLabel EstrellaBola= new JLabel();
         
      		EstrellaBola.setIcon(estrellas.get(w).ObtenerEstrella());
         	System.out.println(estrellas.get(w).PosicionX+ " "+estrellas.get(w).PosicionY);
         
         	EstrellaBola.setBounds(estrellas.get(w).PosicionX*10, estrellas.get(w).PosicionY*10, 10, 10);
         	panelJuego.add(EstrellaBola);
         	panelJuego.validate();
         
  		}*/
    }
    /*private void GenerarLaberintoGrafico()
    {
    	//el for da 40 vueltas, tantas como las filas declaradas en la clase Laberinto1, DelvolverCantidadColumnas es un metodo en esta clase
        for(int i = 0; i < LaberintoVirtual.DevolverCantidadFilasLaberinto(); i++)
        {
        	//por cada fila tiene que generar una columna, el for hace de nuevo 40 vueltas, porque tenemos 40 columnas
            //CON EL PRIMER for establecemos las imagenes en el orden deseado
        	for(int j = 0; j < LaberintoVirtual.DevolverCantidadColumnasLaberinto(i); j++)
            {
                LaberintoGrafico[i][j] = new JLabel();
                panelJuego.add(LaberintoGrafico[i][j]);
            	LaberintoGrafico[i][j].setIcon(new ImageIcon("Imagenes/Laberinto/Laberinto GIF/" + LaberintoVirtual.DeolverCodigoImagenMatriz(i, j) + ".gif"));
            	//if(LaberintoGrafico[i][j].equals("CA")){}
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
        int w = 0;
        for (JLabel EstrellaBola : estrellas) {//JLabel para instanciar las estrellas, por cada elemento que contenga el arraylist insertamos un label
         
        	//Carga la posicion de la estrella
        	EstrellaBola.setIcon(estrellas.get(w).ObtenerEstrella());
        	System.out.println(estrellas.get(w).PosicionX+ " "+estrellas.get(w).PosicionY);
         
        	EstrellaBola.setBounds(estrellas.get(w).PosicionX, estrellas.get(w).PosicionY, 20, 20);
        	panelJuego.add(EstrellaBola);
        	EstrellaBola.validate();
        	w++;
        }
    }*/
    
    /**METODO PARA SACAR POR PANTALLA AL PERSONAJE COMECOCOS
     */
    public void CargarUsuario()
    {
       //AÃ±ade el Usuario al laberinto
       Pacman.EstablecerPosicionInicial(60, 80);//posicion base del personaje, metodo de PersonajeJuego
       Pacman.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
     
       panelJuego.setLayout(null);
        
       //Carga las posiciones del usuario
       PersComecoco.setIcon(Pacman.ObtenerImagen());
       panelJuego.add(PersComecoco);
       PersComecoco.validate();
    }
    
    
    /**METODO QUE NOS PERMITE AÑADIR ESTRELLAS AL JUEGO
     */
    public void CargarEstrellas(){
    	//un solo for con dos for para i=x y j=y y generar las bolas si hay posibilidad de que sea camino CA
	 	//CREAR LAS FILAS Y COLUMNAS CON LAS BOLAS
	 	for (int i = 0; i < 12; i++) {//linea izquierda arriba 2da fila
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
    	for (int j=0; j<2; j++){//columna arriba izquierda, 3ra linea
    		estrellas.add (new ObjEstrella(165, j*20+41));
    	}
    	for (int j=0; j<15; j++){//columna arriba izquierda, 2da linea
    		estrellas.add( new ObjEstrella(85, j*20+41));
    	}
    	for(int i=0; i<18; i++){//fila abajo del todo horizontal
    		estrellas.add(new ObjEstrella(i*20+25, 360));
    	}
    	for(int j=0; j<15; j++){//2da columna principal larga parte derecha
    		estrellas.add(new ObjEstrella(285, j*20+41));
    	}
    	for(int i=0; i<9; i++){//fila arriba derecha del todo hasta la mitad
    		estrellas.add (new ObjEstrella(i*20+205, 21));
    	}
    	for(int j=0; j<2; j++){//columna arriba derecha, 4 columna empezando desde la izq
    		estrellas.add (new ObjEstrella(205, j*20+41));
    	}
    	for(int j=0; j<5; j++){//columna derecha del todo arriba, 6ta columna en total
    		estrellas.add (new ObjEstrella(365, j*20+41));
    	}
    	
    	int i = 0;
    	for (JLabel EstrellaBola : estrellas) {//JLabel para instanciar las estrellas, por cada elemento que contenga el arraylist insertamos un label
    	   
    		//Carga la posicion de la estrella
    		EstrellaBola.setIcon(estrellas.get(i).ObtenerEstrella());
    	    System.out.println(estrellas.get(i).PosicionX+ " "+estrellas.get(i).PosicionY);
    	    EstrellaBola.setBounds(estrellas.get(i).PosicionX, estrellas.get(i).PosicionY, 20, 20);
    	    panelJuego.add(EstrellaBola);
    	    EstrellaBola.validate();
    	    i++;
		}
    	
    	/*int w=0;
    	for(JLabel EstrellaBola: estrellas){
        	//Carga la posicion de la estrella
         	
      		EstrellaBola.setIcon(estrellas.get(w).ObtenerEstrella());
         	System.out.println(estrellas.get(w).PosicionX+ " "+estrellas.get(w).PosicionY);
         
         	EstrellaBola.setBounds(estrellas.get(w).PosicionX*10, estrellas.get(w).PosicionY*10, 10, 10);
         	panelJuego.add(EstrellaBola);
         	EstrellaBola.validate();
         	w++;
         
  		}*/
    }
    /**Metodo con el que añadimos uno de los objetos
     */
    public void CargarManzana(){
    	manzana.EstablecerPosicionInicial(180, 200);//coordenadas donde aparecera
    	manzana.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA MANZANA
        ObjManzana.setIcon(manzana.ObtenerManzana());
        ObjManzana.setBounds(manzana.PosicionX, manzana.PosicionY, 20,20);
        panelJuego.add(ObjManzana);
        ObjManzana.validate();
    }
    /**Metodo para añadir al panel del juego la estrella que otorga velocidad extra
     */
    public void CargarObjEstrella(){
    	estrellaPuntas.EstablecerPosicionInicial(180,160);//coordenadas inicialmente, se puede hacer Random
    	estrellaPuntas.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    
    	panelJuego.setLayout(null);
    	
    	//PINTAR LA ESTRELLA
    	ObjEstrellaPuntas.setIcon(estrellaPuntas.ObtenerEstrellaPuntas());
    	ObjEstrellaPuntas.setBounds(estrellaPuntas.PosicionX, estrellaPuntas.PosicionY, 20,20);
    	panelJuego.add(ObjEstrellaPuntas);
    	ObjEstrellaPuntas.validate();
    }
    /**Comprobar si el avatar va comiendo las bolas
     * Por cada bola que coma se da una puntuacion
     */
    public void ChoqueBola()
    {
    	for(int w=0;w<estrellas.size();w++){
    		ObjEstrella est= estrellas.get(w);
    		
    		if(Pacman.PosicionX==estrellas.get(w).PosicionX&&Pacman.PosicionY==estrellas.get(w).PosicionY){
    			System.out.println("Pasa por aquí");
    			panelJuego.remove(EstrellaBola);
    			panelJuego.repaint();
    			estrellas.remove(est);
    			
    			Puntuacion(3);//da 10 puntos por cada bola
    		}
    	}
    }
    /**Metodo para comprobar si el avatar come la manzana
     * En ese caso se da un extra de puntos
     */
    public void ChoqueManzana()
    {
    	if(Pacman.PosicionX==manzana.PosicionX&&Pacman.PosicionY==manzana.PosicionY){
    		System.out.println("Pasa por aqui");
    		panelJuego.remove(ObjManzana);
    		panelJuego.repaint();
    		
    		Puntuacion(1);//da 100 puntos extra por comerse la manzana
    		
    		
    		/*camino.EstablecerPosicionInicial(180,200);
    		camino.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    		
    		ObjCamino.setIcon(camino.ObtenerCamino());
    		ObjCamino.setBounds(camino.PosicionX, camino.PosicionY, 20, 20);
    		panelJuego.add(ObjCamino);
    		ObjCamino.validate();*/
    	}
    }
    /**Metodo para quitar la estrella de puntas si pasa un cierto tiempo
     * @param maxTiempo el tiempo máximo que estará la estrella de puntas
     */
    public void QuitaObjEstrella(long maxTiempo){
    	if(System.currentTimeMillis()-estrellaPuntas.getHoraCreacion()> maxTiempo){
    		panelJuego.remove(ObjEstrellaPuntas);
    		panelJuego.repaint();
    	}
    		
    }
    /**Metodo para chequear si el avatar come a la estrella
     * En ese caso se le da una velocidad superior al avatar
     */
    public void ChoqueEstrellaPuntas()
    {
    	if(Pacman.PosicionX==estrellaPuntas.PosicionX&&Pacman.PosicionY==estrellaPuntas.PosicionY){
    		panelJuego.remove(ObjEstrellaPuntas);
    		panelJuego.repaint();
    		
    		Puntuacion(2);
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
        	
        	ChoqueBola();
        	ChoqueManzana();
        	ChoqueEstrellaPuntas();
        	
        	QuitaObjEstrella(10000);
        	
        	//pinta la manzana--NO HACE FALTA PONERLO AQUI DENTRO DEL HILO
        	/*ObjManzana.setIcon(manzana.ImagenManzana);
        	ObjManzana.setBounds(manzana.PosicionX, manzana.PosicionY, 20,20);
        	ObjManzana.repaint();
        	*/
        }
    }
    
    /**Metodo para calcular la puntuación del juego
     * @param objeto dependiendo el objeto que haya comido se otorgarán puntos
     */
    private void Puntuacion(int objeto){
    	int puntuacion;
    	puntuacion=Short.parseShort(textPuntuacion.getText());
    	
    	switch(objeto){
    	case 1: puntuacion=puntuacion+100;
    		//manzana=100pts
    		break;
    	case 2: avatar.CambiarVelocidad(4);
    			puntuacion=puntuacion+150;
    		//estrella=150pts + velocidad doble
    		break;
    	case 3: puntuacion=puntuacion+10;
    		//bola=10pts
    		break;
    	}
    	textPuntuacion.setText(""+puntuacion);
    	if(puntuacion>=2000){
    		parado=false;
    		acaba();
    	}
    }
    
    /**Para el contador, las funciones que queremos que haga
     HAY QUE HACER TEST!!!!!!!
     */
     private void Conversor(){
    	/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	short seg;
    	short min;
    	
    	seg= Short.parseShort(textSegundos.getText());
    	min= Short.parseShort(textMinutos.getText());
    	
    	
    	if(seg==00){
			seg=60;
			min--;
			
			if(min==00){
				seg=60;
							
			}
		}
		seg--;
		if(min==00&&seg==00){
			acaba();
		}
		
		textMinutos.setText(""+min);
		textSegundos.setText(""+seg);
    }
    
    class Timer implements Runnable
    {
    	
    	public void run()
    	{
    		while(!PartidaTerminada&&true){
	    		try{
	    			Thread.sleep(1000);
	    			Conversor();
	    		}
	    		catch (InterruptedException e) 
				{
					return;
				}
    		}
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
   
   public void acaba(){
	   PartidaTerminada=true;
   }
}

