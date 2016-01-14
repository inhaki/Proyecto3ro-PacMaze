package ud.prog3.proyecto;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
/**2da clase principal del programa, la que soporta el nivel 2 del juego
 * @author Alumno
 */
public class VentanaJuegoNivel2 extends JFrame implements KeyListener{
	
	private JPanel panelJuego;
	private Laberinto2 LaberintoVirtual= new Laberinto2();
	private JLabel[][] LaberintoGrafico = new JLabel[LaberintoVirtual.DevolverCantidadFilasLaberinto()][LaberintoVirtual.DevolverCantidadColumnasLaberinto(0)];
	private PersComecoco Pacman = new PersComecoco();//comecoco del juego
	private PersBala Bala= new PersBala();//Bala del juego
	private PersR2D2 R2D2= new PersR2D2();//R2D2 del juego
	private ObjManzana manzana= new ObjManzana();//manzana que aparece de bonificación
	private ObjEstrellaPuntas estrellaPuntas= new ObjEstrellaPuntas();//Estrella de 5 puntas que da bonificación
	private ObjCalavera calavera= new ObjCalavera();
    private ObjBolaMala bolaMala= new ObjBolaMala();
	ArrayList<ObjEstrella> estrellas = new ArrayList<>();//ArrayList que contiene las bolas del juego
    private JLabel PersComecoco = new JLabel();//label para el avatar comecoco
    private JLabel PersBala= new JLabel();//Label para el avatar bala
    private JLabel PersR2D2= new JLabel();//Label para el avatar del androide
    private JLabel ObjManzana= new JLabel();//label para el objeto manzana
    private JLabel ObjEstrellaPuntas= new JLabel();//label para el objeto Estrella
    private JLabel ObjBolaMala= new JLabel();//Label para el camino extra
    private JLabel ObjCalavera= new JLabel();
    private boolean PartidaTerminada = false;
    private JLabel EstrellaBola= new JLabel();
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
    
    private JList listToplvl2;
    private JButton btnVolverAlMenu;
    private JLabel lblPuntuacion;
    private JLabel lblEstCapturadas;
    private JLabel lblHighScoreNivel;
    
    /**Constructor de la ventana y panel del juego nivel 2
     */
    public VentanaJuegoNivel2(int personaje)
    {
		Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
    	setTitle("PACMAZE-lvl2 "+f1.format(d1));
    	setResizable(false);
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
        lblPuntuacion.setBounds(164, 470, 129, 20);
        getContentPane().add(lblPuntuacion);
        lblPuntuacion.setVisible(false);
        
        //LABEL ESTRELLAS CAPTURADAS
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
        
        lblHighScoreNivel = new JLabel("HIGH SCORE NIVEL 2");
        lblHighScoreNivel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblHighScoreNivel.setBounds(440, 20, 164, 20);
        getContentPane().add(lblHighScoreNivel);
        lblHighScoreNivel.setVisible(false);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(430, 43, 174, 327);
        getContentPane().add(scrollPane);
     
        listToplvl2= new JList();
        scrollPane.setViewportView(listToplvl2);
        
        btnVolverAlMenu = new JButton("Volver al menu anterior");
        btnVolverAlMenu.setBounds(430, 404, 174, 23);
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
        
        //NO FUNCIONA BIEN ESTE HILO
        Timer myTimer =new Timer();
          
        Thread BucleContador= new Thread(myTimer);
        BucleContador.start();
        
        CargarUsuario(personaje);
        
        CargarEstrellas();
        //ChoqueBola();
        
        //Cargar los objetos
        CargarManzana();
        CargarObjEstrella();
        CargarBolaMala(); 
        CargarCalavera();
        
        GenerarLaberintoGrafico();
        
        try{
        	this.VerListaTop2();
        }catch(SQLException e1){
        	e1.printStackTrace();
			JOptionPane.showMessageDialog(VentanaJuegoNivel2.this, "ERROR, NO SE HAN INTRODUCIDO DATOS!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        /*this.requestFocus();
        try{Thread.sleep(1000);
        //Conversor();
        }catch(Exception e){}*/
        
        panelJuego.requestFocus();
        panelJuego.addKeyListener(this);
        EmpezarPartida(personaje);
    }
    /** METODO QUE GENERA EL LABERINTO, Utilizando las imagenes gif correspondientes
     *Este es el segundo Laberinto del juego
     */
    private void GenerarLaberintoGrafico()
    {
        for(int i = 0; i < LaberintoVirtual.DevolverCantidadFilasLaberinto(); i++)
        {
        	for(int j = 0; j < LaberintoVirtual.DevolverCantidadColumnasLaberinto(i); j++)
            {
                LaberintoGrafico[i][j] = new JLabel();
                panelJuego.add(LaberintoGrafico[i][j]);
                LaberintoGrafico[i][j].setIcon(new ImageIcon("Imagenes/Laberinto2/Laberinto GIF/" + LaberintoVirtual.DeolverCodigoImagenMatriz(i, j) + ".gif"));
            }
        }
       //establecer el alto y ancho
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
    
    /**METODO PARA SACAR POR PANTALLA AL PERSONAJE COMECOCOS
     * @param tipo, 1=comecoco/2=bala/3=R2D2
     */
    public void CargarUsuario(int tipo)
    {
    	switch(tipo){
    		
    		case 1:
    			//AÃ±ade el Usuario al laberinto
		       Pacman.EstablecerPosicionInicial(60, 80);//posicion base del personaje, metodo de PersonajeJuego
		       Pacman.EstablecerLaberinto2(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
		     
		       panelJuego.setLayout(null);//quitar?¿?
		        
		       //Carga las posiciones del usuario
		       PersComecoco.setIcon(Pacman.ObtenerImagen());
		       panelJuego.add(PersComecoco);
		       PersComecoco.validate();
		     break;
	       
    		case 2:
    			Bala.EstablecerPosicionInicial(60,80);
    			Bala.EstablecerLaberinto2(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);//quitar!!!!
    			PersBala.setIcon(Bala.ObtenerImagenBala());
    			panelJuego.add(PersBala);
    			PersBala.validate();
    			break;
    		case 3:
    			R2D2.EstablecerPosicionInicial(60,80);
    			R2D2.EstablecerLaberinto2(LaberintoVirtual.DevolverLaberinto(),LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    			
    			panelJuego.setLayout(null);//se puede quitar?¿?¿
    			PersR2D2.setIcon(R2D2.ObtenerImagenR2D2());
    			panelJuego.add(PersR2D2);
    			PersR2D2.validate();
    			break;
    	}
    }
    
    /**Metodo para añadir las bolas amarillas al juego
    */
    public void CargarEstrellas(){
    	//primer cuadrante, arriba izda
    	for(int i=0; i<5; i++){//linea inicial donde sale el personaje
    		estrellas.add(new ObjEstrella(i*20+40, 80));
    	}
    	for(int i=0; i<5; i++){//linea arriba cuadrante
    		estrellas.add(new ObjEstrella(i*20+40, 20));
    	}
    	for(int i=0; i<3; i++){//linea abajo cuadrante
    		estrellas.add(new ObjEstrella(i*20+60, 140));
    	}
    	for(int j=0; j<5; j++){//columna izquierda cuadrante
    		estrellas.add(new ObjEstrella(20, j*20+40));
    	}
    	for(int j=0; j<3; j++){//columna derecha cuadrante
    		estrellas.add(new ObjEstrella(140, j*20+60));
    	}
    	//segundo cuadrante arriba, el central
    	for(int i=0; i<8; i++){//linea de arriba
    		estrellas.add(new ObjEstrella(i*20+120, 40));
    	}
    	for(int i=0; i<8; i++){//linea de abajo
    		estrellas.add(new ObjEstrella(i*20+120, 120));
    	}
    	//tercer cuadrante arriba derecha
    	for(int i=0; i<5; i++){//linea central (derecha arriba)
    		estrellas.add(new ObjEstrella(i*20+260,80));
    	}
    	for(int i=0; i<5; i++){//linea arriba del cuadrante
    		estrellas.add(new ObjEstrella(i*20+260, 20));
    	}
    	for(int i=0; i<3; i++){//linea abajo de cuadrante
    		estrellas.add(new ObjEstrella(i*20+280,140));
    	}
    	for(int j=0; j<3; j++){//columna izquierda cuadrante
    		estrellas.add(new ObjEstrella(240, j*20+60));
    	}
    	for(int j=0; j<5; j++){//columna derecha
    		estrellas.add(new ObjEstrella(360, j*20+40));
    	}
    	//cuarto cuadrante centro izquierda
    	for(int j=0; j<8; j++){//columna izquierda
    		estrellas.add(new ObjEstrella(40, j*20+120));
    	}
    	for(int j=0; j<6; j++){//columna derecha
    		estrellas.add(new ObjEstrella(120, j*20+140));
    	}
    	//quinto cuadrante centro centro
    	for(int j=0; j<6; j++){
    		estrellas.add(new ObjEstrella(160, j*20+140));
    	}
    	for(int j=0; j<6; j++){
    		estrellas.add(new ObjEstrella(220, j*20+140));
    	}
    	//sexto cuadrante centro derecha
    	for(int j=0; j<8; j++){//columna derecha
    		estrellas.add(new ObjEstrella(340, j*20+120));
    	}
    	for(int j=0; j<6; j++){//columna izquierda
    		estrellas.add(new ObjEstrella(260, j*20+140));
    	}
    	//septimo cuadrante abajo izquierda
    	for(int i=0; i<3; i++){//linea arriba cuadrante
    		estrellas.add(new ObjEstrella(i*20+60, 240));
    	}
    	for(int i=0; i<5; i++){//linea central cuadrante
    		estrellas.add(new ObjEstrella(i*20+40, 300));
    	}
    	for(int i=0; i<5; i++){//linea abajo cuadrante
    		estrellas.add(new ObjEstrella(i*20+40, 360));
    	}
    	for(int j=0; j<5; j++){//columna izquierda
    		estrellas.add(new ObjEstrella(20, j*20+260));
    	}
    	for(int j=0; j<3; j++){//columna derecha
    		estrellas.add(new ObjEstrella(140, j*20+280));
    	}
    	//octavo cuadrante abajo central
    	for(int i=0; i<8; i++){//linea arriba cuadrante
    		estrellas.add(new ObjEstrella(i*20+120, 260));
    	}
    	for(int i=0; i<8; i++){//linea abajo cuadrante
    		estrellas.add(new ObjEstrella(i*20+120, 340));
    	}
    	//noveno cuadrante abajo derecha
    	for(int i=0; i<3; i++){//linea arriba cuadrante
    		estrellas.add(new ObjEstrella(i*20+280,240));
    	}
    	for(int i=0; i<5; i++){//linea central cuadrante
    		estrellas.add(new ObjEstrella(i*20+260,300));
    	}
    	for(int i=0; i<5; i++){//linea abajo cuadrante
    		estrellas.add(new ObjEstrella(i*20+260, 360));
    	}
    	for(int j=0; j<3; j++){//columna izquierda cuadrante
    		estrellas.add(new ObjEstrella(240, j*20+280));
    	}
    	for(int j=0; j<5; j++){//columna derecha cuadrante
    		estrellas.add(new ObjEstrella(360, j*20+260));
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
    
    /**Metodo para generar la manzana*/
    public void CargarManzana(){
    	manzana.EstablecerPosicionInicial(300, 280);//coordenadas donde aparecera
    	manzana.EstablecerLaberinto2(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA MANZANA
        ObjManzana.setIcon(manzana.ObtenerManzana());
        ObjManzana.setBounds(manzana.PosicionX, manzana.PosicionY, 20,20);
        panelJuego.add(ObjManzana);
        ObjManzana.validate();
    }
    /**Metodo para crear el objeto de estrella que otorga extra de velocidad y puntuacion*/
    public void CargarObjEstrella(){
    	//if(System.currentTimeMillis()-tiempoEstrellaCreada>=5000){//--hay que arreglarlo
	    	estrellaPuntas.EstablecerPosicionInicial(180,160);//coordenadas inicialmente, se puede hacer Random
	    	estrellaPuntas.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
	    
	    	panelJuego.setLayout(null);
	    	
	    	//PINTAR LA ESTRELLA
	    	ObjEstrellaPuntas.setIcon(estrellaPuntas.ObtenerEstrellaPuntas());
	    	ObjEstrellaPuntas.setBounds(estrellaPuntas.PosicionX, estrellaPuntas.PosicionY, 20,20);
	    	panelJuego.add(ObjEstrellaPuntas);
	    	ObjEstrellaPuntas.validate();
	    	
	    	/*tiempoEstrellaCreada=System.currentTimeMillis();
    	}*/
    }
    /**Metodo de instancia de la bola "mala"
     */
    public void CargarBolaMala(){
    	bolaMala.EstablecerPosicionInicial(80,340);
    	bolaMala.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
    	
    	panelJuego.setLayout(null);
    	//PINTAR LA BOLA
    	ObjBolaMala.setIcon(bolaMala.ObtenerBolaMala());
    	ObjBolaMala.setBounds(bolaMala.PosicionX, bolaMala.PosicionY,20,20);
    	panelJuego.add(ObjBolaMala);
    	ObjBolaMala.validate();
    	
    }
    /**Metodo de instanciar la calavera
     */
    public void CargarCalavera(){
    	calavera.EstablecerPosicionInicial(200, 220);//coordenadas donde aparecera
    	calavera.EstablecerLaberinto1(LaberintoVirtual.DevolverLaberinto(), LaberintoVirtual.DevolverLargoImagenes(), LaberintoVirtual.DevolverAlturaImagenes());
       
        panelJuego.setLayout(null);
        
        //PINTAR LA CALAVERA
        ObjCalavera.setIcon(calavera.ObtenerCalavera());
        ObjCalavera.setBounds(calavera.PosicionX, calavera.PosicionY, 20,20);
        panelJuego.add(ObjCalavera);
        ObjCalavera.validate();
    }
    
    /**Metodo que comprueba continuamente las posiciones de los avatares
     * si se encuentran en la posicion de una bola se retira la bola*/
    public void ChoqueBola()
    {
    	for(int w=0; w<estrellas.size(); w++){
    		ObjEstrella est= estrellas.get(w);
    	
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
    /**Comprueba si el avatar esta en la posicion de la manzana*/
    public void ChoqueManzana()
    {
    	if(Pacman.PosicionX==manzana.PosicionX&&Pacman.PosicionY==manzana.PosicionY){
    		panelJuego.remove(ObjManzana);
    		panelJuego.repaint();
    		
    		if(puntosM==0){
    			Puntuacion(1);
    			puntosM=1;
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
    
    /**Metodo que ejecuta el hilo principal situado en la clase PersJuegi
     * @param tipo de personaje para hacer el repaint unicamente de ese personaje
     */
    public void EmpezarPartida(int tipo)
    {
    	switch(tipo){
	    	case 1: Pacman.MoverPacman2();
	    		break;
	    	case 2: Bala.MoverBala2();
	    		break;
	    	case 3: R2D2.MoverR2D22();
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
        	
        	QuitaObjEstrella(3500);//desaparece la estrella en 8 segundos
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
    	if(puntuacion>=1810){
    		parado=false;
    		acaba();
    	}
    }
    
    /**Metodo para actualizar el textfield con las estrellas capturadas
     */
    private void EstrellasCapturadas(){
    	int capturadas;
    	capturadas=Short.parseShort(textEstrellas.getText());
    	capturadas=capturadas+1;
    	textEstrellas.setText(""+capturadas);
    	if(capturadas>=156){
    		textEstrellas.setText("TODAS!");
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
			textSegundos.setText(""+seg);
			JOptionPane.showMessageDialog(VentanaJuegoNivel2.this, "Te has quedado sin tiempo \n la partida ha terminado", "INFO", JOptionPane.INFORMATION_MESSAGE);
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
   
   /**Rellenar el JList con los datos de BBDD
    */
   public void VerListaTop2() throws SQLException{
 	   
 	   DefaultListModel<String> listModel= new DefaultListModel<String>();
 	   
 	   ArrayList<String> lista2=BaseDeDatos.VisualizarPuntos(2);//la del nivel 2
 	   for(int i=0; i<lista2.size(); i++){
 		   lista2.get(i);
 		   listModel.addElement(lista2.get(i));
 	   }
 	   listToplvl2.setModel(listModel);
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
	   if(puntuacion>=1450){
		   JOptionPane.showMessageDialog(VentanaJuegoNivel2.this, "ENHORABUENA tu puntuacion es mayor \nque 1450 la contraseña para el nivel 3 \n es: ----------------'lvl3'---------------- ");
	   }
	   frmGuardarPuntuacion p= new frmGuardarPuntuacion(2, puntuacion);
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
