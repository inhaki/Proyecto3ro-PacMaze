package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;

/**Tercer nivel del juego principal, último nivel del programa
 * sigue la misma dinámica que los anteriores y con los mismos objetos
 * "mas complejidad"
 * @author Alumno
 */
public class VentanaJuegoNivel3 extends JFrame implements KeyListener{
	private JPanel panelJuego;
	private Laberinto3 LaberintoVirtual = new Laberinto3();//tipo Laberinto3 
    private JLabel[][] LaberintoGrafico = new JLabel[LaberintoVirtual.DevolverCantidadFilasLaberinto()][LaberintoVirtual.DevolverCantidadColumnasLaberinto(0)];
    private PersComecoco Pacman = new PersComecoco();//comecoco del juego
    private PersBala Bala= new PersBala();//Bala del juego
    private PersR2D2 R2D2= new PersR2D2();//R2D2 del juego  
    private ObjManzana manzana= new ObjManzana();//manzana que aparece de bonificación
    private ObjEstrellaPuntas estrellaPuntas= new ObjEstrellaPuntas();//Estrella que da bonificación+velocidad
    private ObjCalavera calavera= new ObjCalavera();
    private ObjBolaMala bolaMala= new ObjBolaMala();//pixel del camino que sirve para eliminar objetos sobreponiendo la imagen
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
    private int puntosB=0;
    private JTextField textEstrellas;
    int puntuacion;
    
    private JList listToplvl3;
    private JButton btnVolverAlMenu;
    private JLabel lblPuntuacion;//labels para informar al jugador
    private JLabel lblEstCapturadas;
    private JLabel lblHighScoreNivel;
    
    /**CONSTRUCTOR DE LA VENTANA DEL JUEGO NIVEL 3
     */
    public VentanaJuegoNivel3(int personaje)
    {
    	Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
    	setTitle("PACMAZE-lvl3 "+f1.format(d1));
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
        
        //CONTADOR DEL JUEGO
        //CASILLA MINS
        textMinutos = new JTextField();
        textMinutos.setHorizontalAlignment(SwingConstants.RIGHT);
        textMinutos.setEditable(false);
        textMinutos.setFont(new Font("Tahoma", Font.BOLD, 18));
        textMinutos.setBounds(315, 430, 52, 29);
        getContentPane().add(textMinutos);
        textMinutos.setColumns(10);
        //CASILLA SEGS
        textSegundos = new JTextField();
        textSegundos.setEditable(false);
        textSegundos.setFont(new Font("Tahoma", Font.BOLD, 18));
        textSegundos.setColumns(10);
        textSegundos.setBounds(368, 430, 52, 29);
        getContentPane().add(textSegundos);
        //VALORES INICIALES
        textMinutos.setText("00");
        textSegundos.setText("21");
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
        lblEstCapturadas.setVisible(false);
        
        lblHighScoreNivel = new JLabel("HIGH SCORE NIVEL 3");
        lblHighScoreNivel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblHighScoreNivel.setBounds(440, 20, 164, 20);
        getContentPane().add(lblHighScoreNivel);
        lblHighScoreNivel.setVisible(false);
               
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(430, 43, 174, 327);
        getContentPane().add(scrollPane);
     
        listToplvl3 = new JList();
        scrollPane.setViewportView(listToplvl3);
        
        btnVolverAlMenu = new JButton("Volver al menu anterior");
        btnVolverAlMenu.setBounds(430, 395, 174, 23);
        getContentPane().add(btnVolverAlMenu);
        btnVolverAlMenu.setVisible(false);
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
        
        addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				BaseDeDatos.finConexion();
			}
		});        
        
        Timer myTimer =new Timer();
          
        Thread BucleContador= new Thread(myTimer);
        BucleContador.start();
        
        CargarUsuario(personaje);
        
        CargarEstrellas();
        CargarManzana();
        CargarObjEstrella();
        CargarBolaMala(); 
        CargarCalavera();
        //GENERAR EL LABERINTO 3
        GenerarLaberintoGrafico();
        
        try{
        	this.VerListaTop3();
        }catch(SQLException e1){
        	e1.printStackTrace();
			JOptionPane.showMessageDialog(VentanaJuegoNivel3.this, "ERROR, NO SE HAN INTRODUCIDO DATOS!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        panelJuego.requestFocus();
        panelJuego.addKeyListener(this);
        EmpezarPartida(personaje);
        
    }
    
    /** METODO QUE GENERA LOS GIF DEL LABERINTO3
 	 *
     */
    private void GenerarLaberintoGrafico()
    {


     //el for da 40 vueltas, tantas como las filas declaradas en la clase Laberinto1, DelvolverCantidadColumnas es un metodo en esta clase
       for(int i = 0; i < LaberintoVirtual.DevolverCantidadFilasLaberinto(); i++)
       {
        	for(int j = 0; j < LaberintoVirtual.DevolverCantidadColumnasLaberinto(i); j++)
            {
                LaberintoGrafico[i][j] = new JLabel();
                panelJuego.add(LaberintoGrafico[i][j]);
                LaberintoGrafico[i][j].setIcon(new ImageIcon("Imagenes/Laberinto3/Laberinto GIF/" + LaberintoVirtual.DeolverCodigoImagenMatriz(i, j) + ".gif"));
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
       
    }
    
    /**METODO PARA SACAR POR PANTALLA AL PERSONAJE CORRESPONDIENTE
     * @param tipo, 1=comecoco/2=bala/3=R2D2
     */
    public void CargarUsuario(int tipo)
    {
    	switch(tipo){
    		
    		case 1:
    			//AÃ±ade el Usuario al laberinto
		       Pacman.EstablecerPosicionInicial(60, 100);//posicion base del personaje, metodo de PersonajeJuego
		       Pacman.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
		     
		       panelJuego.setLayout(null);//quitar?¿?
		        
		       //Carga las posiciones del usuario
		       PersComecoco.setIcon(Pacman.ObtenerImagen());
		       panelJuego.add(PersComecoco);
		       PersComecoco.validate();
		     break;
	       
    		case 2:
    			Bala.EstablecerPosicionInicial(60,100);
    			Bala.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);//quitar!!!!
    			PersBala.setIcon(Bala.ObtenerImagenBala());
    			panelJuego.add(PersBala);
    			PersBala.validate();
    			break;
    		case 3:
    			R2D2.EstablecerPosicionInicial(60,100);
    			R2D2.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(),LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);//se puede quitar?¿?¿
    			PersR2D2.setIcon(R2D2.ObtenerImagenR2D2());
    			panelJuego.add(PersR2D2);
    			PersR2D2.validate();
    			break;
    	}
    }
    
    /**METODO PARA CARGAR LAS BOLAS AMARILLAS
     */
    public void CargarEstrellas(){
    	//1er Cuadrante-Arriba-IZDA
    	for(int i=0; i<8; i++){//fila inicial donde sale el personaje
    		estrellas.add(new ObjEstrella(i*20+20,100));
    	}
    	for(int i=0; i<4; i++){//fila arriba izda
    		estrellas.add(new ObjEstrella(i*20+20, 20));
    	}
    	for(int i=0; i<3; i++){//fila arriba central
    		estrellas.add(new ObjEstrella(i*20+80, 40));
    	}
    	for(int i=0; i<3; i++){//fila arriba derecha
    		estrellas.add(new ObjEstrella(i*20+120, 20));
    	}
    	for(int j=0; j<3; j++){//columna izda
    		estrellas.add(new ObjEstrella(20, j*20+40));
    	}
    	for(int j=0; j<2; j++){//columna central
    		estrellas.add(new ObjEstrella(80, j*20+60));
    	}
    	for(int j=0; j<3; j++){//columna derecha
    		estrellas.add(new ObjEstrella(160, j*20+40));
    	}
    	//2do Cuadrante-Arriba-DERECHA
    	for(int i=0; i<8; i++){//fila abajo
    		estrellas.add(new ObjEstrella(i*20+220, 100));
    	}
    	for(int i=0; i<3; i++){//fila arriba derecha
    		estrellas.add(new ObjEstrella(i*20+220, 20));
    	}
    	for(int i=0; i<3; i++){//fila arriba central
    		estrellas.add(new ObjEstrella(i*20+260, 40));
    	}
    	for(int i=0; i<4; i++){//fila arriba derecha
    		estrellas.add(new ObjEstrella(i*20+300, 20));
    	}
    	for(int j=0; j<3; j++){//columna izquierda
    		estrellas.add(new ObjEstrella(220, j*20+40));
    	}
    	for(int j=0; j<2; j++){//columna central
    		estrellas.add(new ObjEstrella(300, j*20+60));
    	}
    	for(int j=0; j<3; j++){//columna derecha
    		estrellas.add(new ObjEstrella(360, j*20+40));
    	}
    	//1eras columnas intermedias, 6 columnas de izda a derecha
    	for(int j=0; j<2; j++){//1era columna
    		estrellas.add(new ObjEstrella(40, j*20+120));
    	}
    	for(int j=0; j<2; j++){//2da columna
    		estrellas.add(new ObjEstrella(120, j*20+120));
    	}
    	for(int j=0; j<2; j++){//3era columna
    		estrellas.add(new ObjEstrella(160, j*20+120));
    	}
    	for(int j=0; j<2; j++){//4ta columna
    		estrellas.add(new ObjEstrella(220, j*20+120));
    	}
    	for(int j=0; j<2; j++){//5a columna
    		estrellas.add(new ObjEstrella(260, j*20+120));
    	}
    	for(int j=0; j<2; j++){//6a columna
    		estrellas.add(new ObjEstrella(340, j*20+120));
    	}
    	//3er Cuadrante-Mitad IZDAy 4to Cuadrante-Mitad DERECHA
    	for(int i=0; i<18; i++){//fila arriba
    		estrellas.add(new ObjEstrella(i*20+20, 160));
    	}
    	for(int i=0; i<18; i++){//fila abajo
    		estrellas.add(new ObjEstrella(i*20+20, 220));
    	}
    	for(int j=0; j<2; j++){//columna izquierda del todo 1
    		estrellas.add(new ObjEstrella(20, j*20+180));
    	}
		for(int j=0; j<2; j++){//columna izquierda 2
			estrellas.add(new ObjEstrella(100, j*20+180));
		}
		for(int j=0; j<2; j++){//columna central 3
			estrellas.add(new ObjEstrella(180, j*20+180));
		}
		for(int j=0; j<2; j++){//columna derecha 4
			estrellas.add(new ObjEstrella(280, j*20+180));
    	}
		for(int j=0; j<2; j++){//columna derecha del todo, 5
			estrellas.add(new ObjEstrella(360, j*20+180));
    	}
		//2das columnas intermedias, 6 columnas de izda a derecha
    	for(int j=0; j<2; j++){//1era columna
    		estrellas.add(new ObjEstrella(40, j*20+240  ));
    	}
    	for(int j=0; j<2; j++){//2da columna
    		estrellas.add(new ObjEstrella(120, j*20+240));
    	}
    	for(int j=0; j<2; j++){//3era columna
    		estrellas.add(new ObjEstrella(160, j*20+240));
    	}
    	for(int j=0; j<2; j++){//4ta columna
    		estrellas.add(new ObjEstrella(220, j*20+240));
    	}
    	for(int j=0; j<2; j++){//5a columna
    		estrellas.add(new ObjEstrella(260, j*20+240));
    	}
    	for(int j=0; j<1; j++){//6a columna
    		estrellas.add(new ObjEstrella(340, j*20+240));
    	}
    	//5to Cuadrante-Abajo IZDA
		for(int i=0; i<8; i++){//fila arriba
			estrellas.add(new ObjEstrella(i*20+20, 280));
		}
		for(int i=0; i<4; i++){//fila abajo izda
			estrellas.add(new ObjEstrella(i*20+20, 360));
		}
		for(int i=0; i<3; i++){//fila abajo central
			estrellas.add(new ObjEstrella(i*20+80, 340));
		}
		for(int i=0; i<3; i++){//fila abajo derecha
			estrellas.add(new ObjEstrella(i*20+120, 360));
		}
		for(int j=0; j<3; j++){//columna izda
			estrellas.add(new ObjEstrella(20, j*20+300));
		}
		for(int j=0; j<3; j++){//columna central
			estrellas.add(new ObjEstrella(80, j*20+300));
		}
		for(int j=0; j<3; j++){//columna derecha
			estrellas.add(new ObjEstrella(160, j*20+300));
		}
    	//6to Cuadrante-Abajo-DERECHA
		for(int i=0; i<8; i++){//fila arriba
			estrellas.add(new ObjEstrella(i*20+220, 280));
		}
		for(int i=0; i<3; i++){//fila abajo izda
			estrellas.add(new ObjEstrella(i*20+220, 360));
		}
		for(int i=0; i<3; i++){//fila abajo central
			estrellas.add(new ObjEstrella(i*20+260, 340));
		}
		for(int i=0; i<4; i++){//fila abajo derecha
			estrellas.add(new ObjEstrella(i*20+300, 360));
		}
		for(int j=0; j<3; j++){//columna izda
			estrellas.add(new ObjEstrella(220, j*20+300));
		}
		for(int j=0; j<3; j++){//columna central
			estrellas.add(new ObjEstrella(300, j*20+300));
		}
		for(int j=0; j<3; j++){//columna derecha
			estrellas.add(new ObjEstrella(360, j*20+300));
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
    	
    }
    //CARGAR LA MANZANA EN EL LABERINTO
    public void CargarManzana(){
    	manzana.EstablecerPosicionInicial(200, 196);//coordenadas donde aparecera
    	manzana.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA MANZANA
        ObjManzana.setIcon(manzana.ObtenerManzana());
        ObjManzana.setBounds(manzana.PosicionX, manzana.PosicionY, 20,20);
        panelJuego.add(ObjManzana);
        ObjManzana.validate();
    	
    }
    //METODO PARA CARGAR LA ESTRELLA DE PUNTAS
    public void CargarObjEstrella(){
    	estrellaPuntas.EstablecerPosicionInicial(180,300);//coordenadas inicialmente, se puede hacer Random
    	estrellaPuntas.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    
    	panelJuego.setLayout(null);
    	
    	//PINTAR LA ESTRELLA
    	ObjEstrellaPuntas.setIcon(estrellaPuntas.ObtenerEstrellaPuntas());
    	ObjEstrellaPuntas.setBounds(estrellaPuntas.PosicionX, estrellaPuntas.PosicionY, 20,20);
    	panelJuego.add(ObjEstrellaPuntas);
    	ObjEstrellaPuntas.validate();
    	
    }
    //CARGA LA BOLA QUE RESTA PUNTUACION
    public void CargarBolaMala(){
    	bolaMala.EstablecerPosicionInicial(180,80);
    	bolaMala.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    	
    	panelJuego.setLayout(null);
    	//PINTAR LA BOLA
    	ObjBolaMala.setIcon(bolaMala.ObtenerBolaMala());
    	ObjBolaMala.setBounds(bolaMala.PosicionX, bolaMala.PosicionY,20,20);
    	panelJuego.add(ObjBolaMala);
    	ObjBolaMala.validate();
    	
    }
    
    //CALAVERA QUE SIGNIFICA FIN DE JUEGO
    public void CargarCalavera(){
    	calavera.EstablecerPosicionInicial(340, 260);//coordenadas donde aparecera
    	calavera.EstablecerLaberinto3(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA CALAVERA
        ObjCalavera.setIcon(calavera.ObtenerCalavera());
        ObjCalavera.setBounds(calavera.PosicionX, calavera.PosicionY, 20,20);
        panelJuego.add(ObjCalavera);
        ObjCalavera.validate();
    	
    }
    
    public void ChoqueBola(){
    	for(int w=0; w<estrellas.size(); w++){
    		ObjEstrella est= estrellas.get(w);
    		if(Pacman.PosicionX==estrellas.get(w).PosicionX&&Pacman.PosicionY==estrellas.get(w).PosicionY){
    			//System.out.println("Pasa por aquí");
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
    //COMPROBAR SI LA MANZANA CHOCA CON EL AVATAR
    public void ChoqueManzana(){
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
    		puntosE=1;
    	}
    		
    }
    //METODO PARA COMPROBAR QUE CHOCA LAS ESTRELLAS
    public void ChoqueEstrellaPuntas(){
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
    //si choca con el usuario se termina la partida
    public void ChoqueCalavera(){
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
	    	case 1: Pacman.MoverPacman3();
	    		break;
	    	case 2: Bala.MoverBala3();
	    		break;
	    	case 3: R2D2.MoverR2D23();
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
        
        	QuitaObjEstrella(5000);
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
    	if(puntuacion>=2000){//PUNTUACION??
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
    	if(capturadas>=175){//CAPTURADAS??
    		textEstrellas.setText("TODAS!");
    	}
    }
    
    /**El contador del juego, cuando llega al final el juego se detiene
    */
    private void Conversor()
    {
    	
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
				acaba();//acaba por no pasarse el nivel
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
	
	/**Para ver la lista de puntuaciones
	 * @throws SQLException si esta vacía
	 */
	public void VerListaTop3() throws SQLException{
	 	   
	 	   DefaultListModel<String> listModel= new DefaultListModel<String>();
	 	   
	 	   ArrayList<String> lista3=BaseDeDatos.VisualizarPuntos(3);
	 	   for(int i=0; i<lista3.size(); i++){
	 		   lista3.get(i);
	 		   listModel.addElement(lista3.get(i));
	 	   }
	 	   listToplvl3.setModel(listModel);
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
		frmGuardarPuntuacion p= new frmGuardarPuntuacion(3, puntuacion);
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
