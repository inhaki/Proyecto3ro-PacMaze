package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;




public class frmVentanaJuego extends JFrame implements ActionListener{

	private JPanel contentPane;
	private int personaje=1;
	private static JTextField textpass2;
	private static JTextField textpass3;
	private static JLabel lblIntroduceLaContrasea;
	
	int v2=0;//para el uso de los botones caso nivel 2
	int v3=0;//para el uso de los botones caso nivel 3
	
	public frmVentanaJuego() {
		setResizable(false);
		setTitle("Selecciona el avatar y nivel para empezar a jugar.");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlSelecLab = new JLabel("___Selecciona el laberinto que desee:___________________");
		jlSelecLab.setBounds(59, 194, 296, 14);
		contentPane.add(jlSelecLab);
		
		JLabel lblseleccionaElPersonaje = new JLabel("___Selecciona el personaje que desee:_________________");
		lblseleccionaElPersonaje.setBounds(46, 42, 296, 14);
		contentPane.add(lblseleccionaElPersonaje);
		
		JButton btnNivel1 = new JButton("Nivel 1");
		btnNivel1.setBounds(59, 257, 89, 45);
		contentPane.add(btnNivel1);
		btnNivel1.addActionListener(this);
		
		JButton btnNivel2 = new JButton("Nivel 2");
		btnNivel2.setBounds(164, 257, 89, 45);
		contentPane.add(btnNivel2);
		btnNivel2.addActionListener(this);
		
		JButton btnNivel3 = new JButton("Nivel 3");
		btnNivel3.setBounds(266, 257, 89, 45);
		contentPane.add(btnNivel3);
		btnNivel3.addActionListener(this);
		
		JRadioButton rdbtnComecoco = new JRadioButton("Comecoco");
		rdbtnComecoco.setBounds(122, 78, 109, 23);
		rdbtnComecoco.setIcon(new ImageIcon(frmVentanaJuego.class.getResource("/PersonajesJuego/PacmanDerecha.gif")));
		contentPane.add(rdbtnComecoco);
		rdbtnComecoco.addActionListener(this);
		
		JRadioButton rdbtnBala = new JRadioButton("Bala");
		rdbtnBala.setBounds(122, 108, 109, 23);
		rdbtnBala.setIcon(new ImageIcon(frmVentanaJuego.class.getResource("/PersonajesJuego/BalaDerecha.gif")));
		contentPane.add(rdbtnBala);
		rdbtnBala.addActionListener(this);
		
		JRadioButton rdbtnR2D2 = new JRadioButton("R2D2");
		rdbtnR2D2.setBounds(122, 138, 109, 23);
		rdbtnR2D2.setIcon(new ImageIcon(frmVentanaJuego.class.getResource("/PersonajesJuego/R2D2Derecha.gif")));
		contentPane.add(rdbtnR2D2);
		rdbtnR2D2.addActionListener(this);
		
		ButtonGroup group= new ButtonGroup();
		group.add(rdbtnComecoco);
		group.add(rdbtnBala);
		group.add(rdbtnR2D2);
				
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(164, 329, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
		
		textpass2 = new JTextField();
		textpass2.setBounds(164, 238, 89, 20);
		contentPane.add(textpass2);
		textpass2.setColumns(10);
		textpass2.setVisible(false);
		
		textpass3 = new JTextField();
		textpass3.setColumns(10);
		textpass3.setBounds(266, 238, 89, 20);
		contentPane.add(textpass3);
		textpass3.setVisible(false);
		
		lblIntroduceLaContrasea = new JLabel("Introduce la contrase\u00F1a correspondiente y vuelve a pulsar el nivel");
		lblIntroduceLaContrasea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblIntroduceLaContrasea.setBounds(46, 219, 337, 14);
		contentPane.add(lblIntroduceLaContrasea);
		lblIntroduceLaContrasea.setVisible(false);
		
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TAREA 4
				BaseDeDatos.finConexion();
			}
		});
		
	}
	
	public static boolean Comprobar(int lvl){
		
		switch(lvl){
			case 1: //nada
				break;
			case 2:
				textpass2.setVisible(true);
				lblIntroduceLaContrasea.setVisible(true);
				if(textpass2.getText().equals("lvl2")){
					return true;
				}
				break;
			case 3:
				textpass3.setVisible(true);
				lblIntroduceLaContrasea.setVisible(true);
				if(textpass3.getText().equals("lvl3")){
					return true;
				}
				break;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		switch(e.getActionCommand())
		{
			case "Nivel 1":
				new Thread(new Runnable(){
		        	public void run(){
		        		new VentanaJuegoNivel1(personaje);
		        	}
		        }).start();
				/*VentanaJuegoNivel1 lvl1= new VentanaJuegoNivel1();//pasar parámetro 1=Coco/2=Bala/3=R2D2
				lvl1.setVisible(true);*/
				this.dispose();
				break;
			case "Nivel 2":
				if(Comprobar(2)==true){
					new Thread(new Runnable(){
			        	public void run(){
			        		new VentanaJuegoNivel2(personaje);
			        	}
			        }).start();
				/*VentanaJuegoNivel2 lvl2= new VentanaJuegoNivel2();
				lvl2.setVisible(true);*/
				this.dispose();
				}
				else{
					if(v2>0){
						JOptionPane.showMessageDialog(this, "La contraseña no es correcta, intentalo de nuevo");
						textpass2.setText("");
					}
				}
				v2=1;//en la segunda vez que se pulse si que puede salir esta opcion
				break;
			case "Nivel 3":
				if(Comprobar(3)==true){
					new Thread(new Runnable(){
			        	public void run(){
			        		new VentanaJuegoNivel3(personaje);
			        	}
			        }).start();
				/*VentanaJuegoNivel3 lvl3= new VentanaJuegoNivel3();
				lvl3.setVisible(true);*/
					this.dispose();
				}else{
					if(v3>0){
						JOptionPane.showMessageDialog(this, "La contraseña no es correcta, intentalo de nuevo");
						textpass3.setText("");
					}
				}
				v3=1;
				
				break;
			case "Comecoco":
				personaje=1;
				break;
			case "Bala":
				personaje=2;
				break;
			case "R2D2":
				personaje=3;
				break;
			case "Atras":
				frmVentanaPpal obj= new frmVentanaPpal();
				obj.setVisible(true);				
				this.dispose();
				break;
		}
	}
}
