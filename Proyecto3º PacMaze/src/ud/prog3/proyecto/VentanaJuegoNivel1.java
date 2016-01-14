package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private PersBala Bala= new PersBala();//Bala del juego
    private PersR2D2 R2D2= new PersR2D2();//R2D2 del juego
    private ObjManzana manzana= new ObjManzana();//manzana que aparece de bonificación
    private ObjEstrellaPuntas estrellaPuntas= new ObjEstrellaPuntas();//Estrella de 5 puntas que da bonificación
    private ObjCalavera calavera= new ObjCalavera();//calavera que para el juego
    private ObjBolaMala bolaMala= new ObjBolaMala();//realentiza y resta puntos
    ArrayList<ObjEstrella> estrellas = new ArrayList<>();//ArrayList que contiene las bolas del juego
    private JLabel PersComecoco = new JLabel();//label para el avatar comecoco
    private JLabel PersBala= new JLabel();//Label para el avatar bala
    private JLabel PersR2D2= new JLabel();//Label para el avatar del androide
    private JLabel ObjManzana= new JLabel();//label para el objeto manzana
    private JLabel ObjEstrellaPuntas= new JLabel();//label para el objeto Estrella
    private JLabel ObjBolaMala= new JLabel();//Label para el camino extra
    private JLabel ObjCalavera= new JLabel();
    private boolean PartidaTerminada = false;
    private JTextField textMinutos;
    private JTextField textSegundos;
    Timer myTimer;//timer para el contador
    private boolean parado;//para parar el tiempo si se excede la puntuacion
    private JTextField textPuntuacion;
    private int puntosM=0;//Para que solo de puntos una vez cuando coja la manzana
    private static long tiempoEstrellaCreada=0;//Momento creacion de la estrella
    private int puntosE=0;//control de puntos con el objeto Estrella Puntas
    private int puntosB=0;//control de puntos con el objeto de bola mala
    private JTextField textEstrellas;
    int puntuacion;//para guardar la puntuacion del usuario
     
    private JList listToplvl1;
    private JButton btnVolverAlMenu;
    private JLabel lblPuntuacion;//labels para informar al jugador
    private JLabel lblEstCapturadas;
    private JLabel lblHighScoreNivel;
    
    /**Constructor de la ventana, crea y devuelve la ventana inicializada
     * no hay ningún avatar dentro
     */
    public VentanaJuegoNivel1(int personaje)
    {
    	Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
    	setTitle("PACMAZE-lvl1 "+f1.format(d1));
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
        textMinutos.setBounds(315, 430, 52, 29);
        getContentPane().add(textMinutos);
        textMinutos.setColumns(10);
        //CASILLA DE LOS SEGUNDOS
        textSegundos = new JTextField();
        textSegundos.setEditable(false);
        textSegundos.setFont(new Font("Tahoma", Font.BOLD, 18));
        textSegundos.setColumns(10);
        textSegundos.setBounds(368, 430, 52, 29);
        getContentPane().add(textSegundos);
        //VALORES INICIALES
        textMinutos.setText("00");
        textSegundos.setText("28");
        //CASILLA DE LA PUNTUACION
        textPuntuacion =new JTextField();
        textPuntuacion.setHorizontalAlignment(SwingConstants.RIGHT);
        textPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 44));
        textPuntuacion.setEditable(false);
        textPuntuacion.setBounds(126, 424, 179, 47);
        getContentPane().add(textPuntuacion);
        textPuntuacion.setColumns(10);
        //VALOR INICIAL
        textPuntuacion.setText("0");     
        //LABEL PUNTUACION
        lblPuntuacion = new JLabel("PUNTUACION");
        lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPuntuacion.setBounds(148, 470, 129, 20);
        getContentPane().add(lblPuntuacion);
        lblPuntuacion.setVisible(false);
        
        textEstrellas = new JTextField();
        textEstrellas.setForeground(Color.RED);
        textEstrellas.setFont(new Font("Tahoma", Font.BOLD, 28));
        textEstrellas.setHorizontalAlignment(SwingConstants.RIGHT);
        textEstrellas.setEditable(false);
        textEstrellas.setBounds(10, 430, 106, 39);
        getContentPane().add(textEstrellas);
        textEstrellas.setColumns(10);
        //VALOR INICIAL
        textEstrellas.setVisible(true);
        textEstrellas.setText("0");
        
        lblEstCapturadas = new JLabel("E.Capturadas");
        lblEstCapturadas.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEstCapturadas.setBounds(10, 470, 113, 20);
        getContentPane().add(lblEstCapturadas);
        lblEstCapturadas.setVisible(true);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(430, 43, 174, 327);
        getContentPane().add(scrollPane);
        
        lblHighScoreNivel = new JLabel("HIGH SCORE NIVEL 1");
        lblHighScoreNivel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblHighScoreNivel.setBounds(440, 20, 164, 20);
        getContentPane().add(lblHighScoreNivel);
        lblHighScoreNivel.setVisible(false);
     
        listToplvl1= new JList();
        scrollPane.setViewportView(listToplvl1);
        
        btnVolverAlMenu = new JButton("Volver al menu anterior");
        btnVolverAlMenu.setBounds(430, 395, 174, 23);
        getContentPane().add(btnVolverAlMenu);
        btnVolverAlMenu.setVisible(true);
        btnVolverAlMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e2) {
				// TODO Auto-generated method stub
				PartidaTerminada=true;
				frmVentanaJuego v= new frmVentanaJuego();
				v.setVisible(true);
				dispose();
			}
        	
        });
        //para cerrar la BBD al cerrar
        addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			
				BaseDeDatos.finConexion();
			}
		});
        
        Timer myTimer =new Timer();
          
        Thread BucleContador= new Thread(myTimer);
        BucleContador.start();
        
        //Carga el avatar del jugador
        //haremos un switch aqui para cargar diferentes avatares dependiendo de la elección del jugador
        CargarUsuario(personaje);//luego int dependiendo del personaje
        
        //Cargar las estrellas, bolas del juego
        CargarEstrellas();
        //ChoqueBola();
        
        //Cargar el objeto bonus manzana
        CargarManzana();
        CargarObjEstrella();
        CargarBolaMala(); 
        CargarCalavera();
        //Genera el laberinto grafico a partir del Laberinto virtual
        //LABERINTO DE NIVEL 1, EL INICIAL
        GenerarLaberintoGrafico();
        
       try{
        	this.VerListaTop1();
       }catch(SQLException e1){
        	e1.printStackTrace();
			JOptionPane.showMessageDialog(VentanaJuegoNivel1.this, "ERROR, NO SE HAN INTRODUCIDO DATOS!", "ERROR", JOptionPane.ERROR_MESSAGE);
       }
       
       //para que el panel escuche el KeyListener
       panelJuego.requestFocus();
       panelJuego.addKeyListener(this);
       EmpezarPartida(personaje);//parametro que vendrá del ventanaPpal juego

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
               /* if(LaberintoVirtual.LaberintoActual[i][j].equals("CA"))
                {
                	estrellas.add(new ObjEstrella(i, j));
                } */
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
       //ya que a veces no se cargan los hacemos visibles aquí
       btnVolverAlMenu.setVisible(true);
       lblPuntuacion.setVisible(true);
       lblEstCapturadas.setVisible(true);
       lblHighScoreNivel.setVisible(true);
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
    
    
    /**METODO PARA SACAR POR PANTALLA AL PERSONAJE COMECOCOS
     * @param tipo, 1=comecoco/2=bala/3=R2D2
     */
    public void CargarUsuario(int tipo)
    {
    	switch(tipo){
    		
    		case 1:
    			//AÃ±ade el Usuario al laberinto
		       Pacman.EstablecerPosicionInicial(60, 80);//posicion base del personaje, metodo de PersonajeJuego
		       Pacman.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
		     
		       panelJuego.setLayout(null);//para que no aparezca en posicion aleatoria
		        
		       //Carga las posiciones del usuario
		       PersComecoco.setIcon(Pacman.ObtenerImagen());
		       panelJuego.add(PersComecoco);
		       PersComecoco.validate();
		     break;
	       
    		case 2:
    			Bala.EstablecerPosicionInicial(60,80);
    			Bala.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);
    			PersBala.setIcon(Bala.ObtenerImagenBala());
    			panelJuego.add(PersBala);
    			PersBala.validate();
    			break;
    		case 3:
    			R2D2.EstablecerPosicionInicial(60,80);
    			R2D2.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(),LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);
    			PersR2D2.setIcon(R2D2.ObtenerImagenR2D2());
    			panelJuego.add(PersR2D2);
    			PersR2D2.validate();
    			break;
    	}
    }
    
    
    /**METODO QUE NOS PERMITE AÑADIR ESTRELLAS AL JUEGO
     */
    public void CargarEstrellas(){
    	//un solo for con dos for para i=x y j=y y generar las bolas si hay posibilidad de que sea camino CA
	 	//CREAR LAS FILAS Y COLUMNAS CON LAS BOLAS
	 	for (int i = 0; i < 12; i++) {//linea izquierda arriba 2da fila
			estrellas.add(new ObjEstrella(i*20+40, 80));
		}
    	for(int j=0; j<6; j++){//linea vertical arriba izq, primera columna
    		estrellas.add(new ObjEstrella(20, j*20+20));
    	}
    	for (int i=0; i<7; i++){//fila arriba izq del todo hasta la mitad
    		estrellas.add (new ObjEstrella(i*20+40, 20));
    	}
    	for (int i=0; i<3; i++){//3era fila desde arriba a la izquierda hasta choque con pared
    		estrellas.add (new ObjEstrella(i*20+40, 120));
    	}
    	for (int j=0; j<2; j++){//columna arriba izquierda, 3ra linea
    		estrellas.add (new ObjEstrella(160, j*20+40));
    	}
    	for (int j=0; j<15; j++){//columna arriba izquierda, 2da linea
    		estrellas.add( new ObjEstrella(80, j*20+40));
    	}
    	for(int i=0; i<18; i++){//fila abajo del todo horizontal
    		estrellas.add(new ObjEstrella(i*20+20, 360));
    	}
    	for(int j=0; j<15; j++){//2da columna principal larga parte derecha
    		estrellas.add(new ObjEstrella(280, j*20+40));
    	}
    	for(int i=0; i<9; i++){//fila arriba derecha del todo hasta la mitad
    		estrellas.add (new ObjEstrella(i*20+200, 20));
    	}
    	for(int j=0; j<2; j++){//columna arriba derecha, 4 columna empezando desde la izq
    		estrellas.add (new ObjEstrella(200, j*20+40));
    	}
    	for(int j=0; j<5; j++){//columna derecha del todo arriba, 6ta columna en total
    		estrellas.add (new ObjEstrella(360, j*20+40));
    	}
    	for(int j=0; j<8; j++){//columna izq mitad-central
    		estrellas.add(new ObjEstrella(120, j*20+100));
    	}
    	for(int j=0; j<8; j++){//columna derecha, mitad-central
    		estrellas.add(new ObjEstrella(240, j*20+100));
    	}
    	for(int i=0; i<3; i++){//fila arriba derecha, 3 fila
    		estrellas.add(new ObjEstrella(i*20+300, 120));
    	}
    	for(int i=0; i<3; i++){//fila central izquierda, la que empieza en el borde izquierdo central
    		estrellas.add(new ObjEstrella(i*20+20, 180));
    	}
    	for(int i=0; i<4; i++){//fila central derecha, la que acaba en el borde central
    		estrellas.add(new ObjEstrella(i*20+300,180));
    	}
    	for(int i=0; i<3; i++){//fila debajo de la central izquierda
    		estrellas.add(new ObjEstrella(i*20+20, 240));
    	}
    	for(int i=0; i<4; i++){//fila debajo de la central derecha
    		estrellas.add(new ObjEstrella(i*20+300, 240));
    	}
    	for(int j=0; j<2; j++){//columna debajo de la fila central a la izquierda
    		estrellas.add(new ObjEstrella(20, j*20+260));
    	}
    	for(int j=0; j<2; j++){//columna izquierda encima de la fila de abajo del todo
    		estrellas.add(new ObjEstrella(20, j*20+320));
    	}
    	for(int j=0; j<2; j++){//columna debajo de la fila central a la derecha
    		estrellas.add(new ObjEstrella(360, j*20+260));
    	}
    	for(int j=0; j<2; j++){//columna derecha encima de la fila de abajo del todo
    		estrellas.add(new ObjEstrella(360, j*20+320));
    	}
    	for(int i=0; i<2; i++){//fila abajo izquierda, encima de la de fondo inferior
    		estrellas.add(new ObjEstrella(i*20+40, 320));
    	}
    	for(int i=0; i<3; i++){//fila abajo derecha, encima de la de fondo inferior
    		estrellas.add(new ObjEstrella(i*20+300, 320));
    	}
    	for(int i=0; i<9; i++){//fila central abajo, por encima de la de fondo inferior
    		estrellas.add(new ObjEstrella(i*20+100, 280));
    	}
    	for(int i=0; i<5; i++){//fila central encima del sitio donde aparece la estrella con puntas
    		estrellas.add(new ObjEstrella(i*20+140,140));
    	}
    	for(int i=0; i<3; i++){//fila central corta izquierda por encima de la inferior
    		estrellas.add(new ObjEstrella(i*20+120,320));
    	}
    	for(int i=0; i<3; i++){//fila central corta derecha por encima de la inferior
    		estrellas.add(new ObjEstrella(i*20+200, 320));
    	}
    	int i = 0;
    	for (JLabel EstrellaBola : estrellas) {//JLabel para instanciar las estrellas, por cada elemento que contenga el arraylist insertamos un label
    	   
    		//Carga la posicion de la estrella
    		EstrellaBola.setIcon(estrellas.get(i).ObtenerEstrella());
    	    //System.out.println(estrellas.get(i).PosicionX+ " "+estrellas.get(i).PosicionY);
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
    /**Metodo de cargar las bolas que restarán puntos y/o velocidad
     */
    public void CargarBolaMala(){
    	bolaMala.EstablecerPosicionInicial(120,300);
    	bolaMala.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    	
    	panelJuego.setLayout(null);
    	//PINTAR LA BOLA
    	ObjBolaMala.setIcon(bolaMala.ObtenerBolaMala());
    	ObjBolaMala.setBounds(bolaMala.PosicionX, bolaMala.PosicionY,20,20);
    	panelJuego.add(ObjBolaMala);
    	ObjBolaMala.validate();
   
    }
    
    /** Metodo que cargará el objeto calavera que para el juego*/
    public void CargarCalavera(){
    	calavera.EstablecerPosicionInicial(368, 180);//coordenadas donde aparecera
    	calavera.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA CALAVERA
        ObjCalavera.setIcon(calavera.ObtenerCalavera());
        ObjCalavera.setBounds(calavera.PosicionX, calavera.PosicionY, 20,20);
        panelJuego.add(ObjCalavera);
        ObjCalavera.validate();
    }
    /**Comprobar si el avatar va comiendo las bolas
     * Por cada bola que coma se da una puntuacion
     */
    public void ChoqueBola()
    {
    	for(int w=0; w<estrellas.size(); w++){
    		ObjEstrella est= estrellas.get(w);
    		 //System.out.println(Pacman.PosicionX+" " + Pacman.PosicionY); 
    		 //System.out.println(estrellas.get(w).PosicionX+" "+ estrellas.get(w).PosicionY);
    		if(Pacman.PosicionX==estrellas.get(w).PosicionX&&Pacman.PosicionY==estrellas.get(w).PosicionY){
    			panelJuego.remove(est);
    			panelJuego.repaint();
    			estrellas.remove(est);
    			
    			Puntuacion(3);//da 10 puntos por cada bola
    			EstrellasCapturadas();//suma una unidad por cada estrella capturada
    		}
    		else if(Bala.PosicionX==estrellas.get(w).PosicionX&&Bala.PosicionY==estrellas.get(w).PosicionY){
    			panelJuego.remove(est);
    			panelJuego.repaint();
    			estrellas.remove(est);
    			
    			Puntuacion(3);
    			EstrellasCapturadas();
    		}
    		else if(R2D2.PosicionX==estrellas.get(w).PosicionX&&R2D2.PosicionY==estrellas.get(w).PosicionY){
    			panelJuego.remove(est);
    			panelJuego.repaint();
    			estrellas.remove(est);
    			
    			Puntuacion(3);
    			EstrellasCapturadas();
    		}
    	}
    }
    /**Metodo para comprobar si el avatar come la manzana
     * En ese caso se da un extra de puntos
     */
    public void ChoqueManzana()
    {
    	if(Pacman.PosicionX==manzana.PosicionX&&Pacman.PosicionY==manzana.PosicionY){
    		panelJuego.remove(ObjManzana);
    		panelJuego.repaint();
    		
    		if(puntosM==0){
    			Puntuacion(1);//da 100 puntos extra por comerse la manzana
    			puntosM=1;//asi no vuelve a dar puntos
    		}
    	}
    	else if(Bala.PosicionX==manzana.PosicionX&&Bala.PosicionY==manzana.PosicionY){
    		panelJuego.remove(ObjManzana);
    		panelJuego.repaint();
    		
    		if(puntosM==0){
    			Puntuacion(1);
    			puntosM=1;
    		}
    	}
    	else if(R2D2.PosicionX==manzana.PosicionX&&R2D2.PosicionY==manzana.PosicionY){
    		panelJuego.remove(ObjManzana);
    		panelJuego.repaint();
    		
    		if(puntosM==0){
    			Puntuacion(1);
    			puntosM=1;
    		}
    	}
    	
    }
    /**Metodo para quitar la estrella de puntas si pasa un cierto tiempo
     * @param maxTiempo el tiempo máximo que estará la estrella de puntas
     */
    public void QuitaObjEstrella(long maxTiempo){
    	if(System.currentTimeMillis()-estrellaPuntas.getHoraCreacion()> maxTiempo){
    		panelJuego.remove(ObjEstrellaPuntas);
    		panelJuego.repaint();
    		puntosE=1;//no dara puntos por pasar por la posicion
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
    		
    		if(puntosE==0){
    			Puntuacion(2);
    			puntosE=1;//no da puntos por pasar por la posicion cuando la estrella ya no este
    		}
    	}
    	else if(Bala.PosicionX==estrellaPuntas.PosicionX&&Bala.PosicionY==estrellaPuntas.PosicionY){
    		panelJuego.remove(ObjEstrellaPuntas);
    		panelJuego.repaint();
    		
    		if(puntosE==0){
    			Puntuacion(2);
    			puntosE=1;
    		}
    	}
    	else if(R2D2.PosicionX==estrellaPuntas.PosicionX&&R2D2.PosicionY==estrellaPuntas.PosicionY){
    		panelJuego.remove(ObjEstrellaPuntas);
    		panelJuego.repaint();
    		
    		if(puntosE==0){
    			Puntuacion(2);
    			puntosE=1;
    		}
    	}
    }
    /**Metodo para comprobar si chocan los avatares con las bolas "malas"
     */
    public void ChoqueBolaMala(){
    	
    	if(Pacman.PosicionX==bolaMala.PosicionX&&Pacman.PosicionY==bolaMala.PosicionY){
    		panelJuego.remove(ObjBolaMala);
    		panelJuego.repaint();
    			
    		if(puntosB==0){
    			Puntuacion(4);
    			puntosB=1;
    		}
    	}
    	else if(Bala.PosicionX==bolaMala.PosicionX&&Bala.PosicionY==bolaMala.PosicionY){
    		panelJuego.remove(ObjBolaMala);
    		panelJuego.repaint();
    			
    		if(puntosB==0){
    			Puntuacion(4);
    			puntosB=1;
    		}
    	}
    	else if(R2D2.PosicionX==bolaMala.PosicionX&&R2D2.PosicionY==bolaMala.PosicionY){
    		panelJuego.remove(ObjBolaMala);
    		panelJuego.repaint();
    			
    		if(puntosB==0){
    			Puntuacion(4);
    			puntosB=1;
    		}
    	}
    }
    
    /**Metodo para comprobar si el avatar choca con la calavera
     * La calavera que esta oculta acarrea el "GAME OVER"
     */
    public void ChoqueCalavera()
    {
    	if(Pacman.PosicionX==calavera.PosicionX&&Pacman.PosicionY==calavera.PosicionY){
    		panelJuego.remove(ObjCalavera);
    		panelJuego.repaint();
    		
    		Puntuacion(5);//finaliza el juego
    	}
    	else if(Bala.PosicionX==calavera.PosicionX&&Bala.PosicionY==calavera.PosicionY){
    		panelJuego.remove(ObjCalavera);
    		panelJuego.repaint();
    		
    		Puntuacion(5);
    	}
    	else if(R2D2.PosicionX==calavera.PosicionX&&R2D2.PosicionY==calavera.PosicionY){
    		panelJuego.remove(ObjCalavera);
    		panelJuego.repaint();
    		
    		Puntuacion(5);
    	}
    }
    public void EmpezarPartida(int tipo)
    {
    	switch(tipo){
	    	case 1: Pacman.MoverPacman();
	    		break;
	    	case 2: Bala.MoverBala();
	    		break;
	    	case 3: R2D2.MoverR2D2();
	    		break;
    	}
        while(!PartidaTerminada)        //Bucle Principal
        {
        	switch(tipo){
        	case 1: //Repinta el usuario comecoco
	        	PersComecoco.setIcon(Pacman.ImagenCoco);
	        	PersComecoco.setBounds(Pacman.PosicionX,Pacman.PosicionY,20,20);
	        	PersComecoco.repaint();
	        	break;
        	case 2: //Repinta la bala
	        	PersBala.setIcon(Bala.ImagenBala);
	        	PersBala.setBounds(Bala.PosicionX, Bala.PosicionY, 20,20);
	        	PersBala.repaint();
	        	break;
        	case 3: //Repinta R2D2
	        	PersR2D2.setIcon(R2D2.ImagenR2D2);
	        	PersR2D2.setBounds(R2D2.PosicionX, R2D2.PosicionY,20,20);
	        	PersR2D2.repaint();
	        	break;
        	}
        	ChoqueBola();
        	ChoqueManzana();
        	ChoqueEstrellaPuntas();
        	ChoqueBolaMala();
        	ChoqueCalavera();    	
        
        	QuitaObjEstrella(7500);
        	
        }
    }
    
    /**Metodo para calcular la puntuación del juego
     * @param objeto dependiendo el objeto que haya comido se otorgarán puntos
     */
    private void Puntuacion(int objeto){
    	
    	puntuacion=Short.parseShort(textPuntuacion.getText());
    	
    	switch(objeto){
    	case 1: puntuacion=puntuacion+100;
    		//manzana=100pts
    		break;
    	case 2: Pacman.CambiarVelocidad(4);
    			R2D2.CambiarVelocidad(4);
    			Bala.CambiarVelocidad(4);
    			puntuacion=puntuacion+150;
    		//estrella=150pts + velocidad doble
    		break;
    	case 3: puntuacion=puntuacion+10;
    		//bola=10pts
    		break;
    	case 4: Pacman.CambiarVelocidad(1);
				R2D2.CambiarVelocidad(1);
				Bala.CambiarVelocidad(1);
    			puntuacion=puntuacion/2;
			break;
    	case 5: GameOver();//acaba por no pasar el nivel
    			JOptionPane.showMessageDialog(this, "Has capturado la calavera que no debías \n----------------GAMEis0VER---------------");
    		break;
    	}
    	textPuntuacion.setText(""+puntuacion);
    	if(puntuacion>=1850){
    		parado=false;
    		acaba();//acaba por pasarse el nivel
    	}
    }
    
    /**Metodo para actualizar el textfield con las estrellas capturadas
     */
    private void EstrellasCapturadas(){
    	int capturadas;
    	capturadas=Short.parseShort(textEstrellas.getText());
    	capturadas=capturadas+1;
    	textEstrellas.setText(""+capturadas);
    	if(capturadas>=160){
    		textEstrellas.setText("TODAS!");
    	}
    }
    
    /**Para el contador, las funciones que queremos que haga
     HAY QUE HACER TEST!!!!!!!
     */
     private void Conversor(){
    	
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
			textSegundos.setText(""+seg);
			JOptionPane.showMessageDialog(VentanaJuegoNivel1.this, "Te has quedado sin tiempo \n la partida ha terminado", "INFO", JOptionPane.INFORMATION_MESSAGE);
			acaba();//acaba por no pasarse el nivel
		}
		
		textMinutos.setText(""+min);
		textSegundos.setText(""+seg);
    }
    //HILO DEL CONTADOR, VA SEPARADO AL DEL JUEGO, ACTUALIZA POR SEGUNDOS
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
    
    public void VerListaTop1() throws SQLException{
 	   
 	   DefaultListModel<String> listModel= new DefaultListModel<String>();
 	   
 	   ArrayList<String> lista1=BaseDeDatos.VisualizarPuntos(1);
 	   for(int i=0; i<lista1.size(); i++){
 		   lista1.get(i);
 		   listModel.addElement(lista1.get(i));
 	   }
 	   listToplvl1.setModel(listModel);
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
            	Bala.Izquierda= false;
            	R2D2.Izquierda=false;
                break;
            }
            case KeyEvent.VK_UP: //Arriba
            {
                Pacman.Arriba = false;
                Bala.Arriba=false;
                R2D2.Arriba=false;
                break;
            }
            case KeyEvent.VK_RIGHT: //Derecha
            {
                Pacman.Derecha = false;
                Bala.Derecha= false;
                R2D2.Derecha=false;
                break;
            }
            case KeyEvent.VK_DOWN: //Abajo
            {
                Pacman.Abajo = false;
                Bala.Abajo= false;
                R2D2.Abajo=false;
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
                Bala.Izquierda= true;
                R2D2.Izquierda=true;
                break;
            }
            case KeyEvent.VK_UP: //Arriba
            {
                Pacman.Arriba = true;
                Bala.Arriba=true;
                R2D2.Arriba=true;
                break;
            }
            case KeyEvent.VK_RIGHT: //Derecha
            {
                Pacman.Derecha = true;
                Bala.Derecha=true;
                R2D2.Derecha=true;
                break;
            }
            case KeyEvent.VK_DOWN: //Abajo
            {
                Pacman.Abajo = true;
                Bala.Abajo=true;
                R2D2.Abajo=true;
                break;
            }
        }
    }
   
   public void acaba(){
	   PartidaTerminada=true;
	   if(puntuacion>=1500){
		   JOptionPane.showMessageDialog(VentanaJuegoNivel1.this, "ENHORABUENA tu puntuacion es mayor \nque 1500 la contraseña para el nivel 2 \n es: ----------------'lvl2'---------------- ");
	   }
	   frmGuardarPuntuacion p= new frmGuardarPuntuacion(1, puntuacion);
	   p.setVisible(true);
	   this.dispose();
   }
   public void GameOver(){
	   PartidaTerminada=true;
	   frmVentanaJuego j= new frmVentanaJuego();
	   j.setVisible(true);
	   this.dispose();
   }
}

