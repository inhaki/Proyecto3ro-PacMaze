package ud.prog3.proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;


import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class frmGuardarPuntuacion extends JFrame implements ActionListener{
	private JTextField textNick;
	private JTextField textPuntuacion;
	private JTextField textNivel;
	
	public frmGuardarPuntuacion(int lvl, int puntuacion) {
		
		setTitle("HIGH-SCORE");
		setResizable(false);
		setResizable(false);
		setTitle("¡¡Introduce tu nick para dejar tu huella en el High Score!!");
		setSize(454,303);
		setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNick.setBounds(121, 57, 56, 21);
		getContentPane().add(lblNick);
		
		JLabel lblPuntuacion = new JLabel("Puntuacion:");
		lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPuntuacion.setBounds(73, 104, 96, 21);
		getContentPane().add(lblPuntuacion);
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNivel.setBounds(118, 152, 66, 21);
		getContentPane().add(lblNivel);
		
		textNick = new JTextField();
		textNick.setHorizontalAlignment(SwingConstants.LEFT);
		textNick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNick.setBounds(178, 57, 106, 21);
		getContentPane().add(textNick);
		textNick.setColumns(10);
		
		textPuntuacion = new JTextField();
		textPuntuacion.setHorizontalAlignment(SwingConstants.LEFT);
		textPuntuacion.setEditable(false);
		textPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPuntuacion.setColumns(10);
		textPuntuacion.setBounds(178, 106, 106, 21);
		getContentPane().add(textPuntuacion);
		//puntuacion=Short.parseShort(textPuntuacion.getText());
		textPuntuacion.setText(""+puntuacion);
		
		textNivel = new JTextField();
		textNivel.setHorizontalAlignment(SwingConstants.LEFT);
		textNivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNivel.setEditable(false);
		textNivel.setColumns(10);
		textNivel.setBounds(178, 154, 106, 21);
		getContentPane().add(textNivel);
		//lvl=Short.parseShort(textNivel.getText());
		textNivel.setText(""+lvl);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(87, 221, 96, 30);
		getContentPane().add(btnGuardar);
		btnGuardar.addActionListener(this);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(270, 221, 96, 29);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TAREA 4
				BaseDeDatos.finConexion();
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "Guardar":
			//int nivel=Short.parseShort(textNivel.getText());
			//inicializa la tabla de puntuacion si es la primera vez
			BaseDeDatos.initBD("vpd.bd");
			BaseDeDatos.crearTablaPuntuacion();
			
			String nick= textNick.getText();
			Integer puntuacion=Integer.parseInt(textPuntuacion.getText());//siempre van a ser int porque lo gestionamos nosotros
			Integer nivel= Integer.parseInt(textNivel.getText());
			//Crear la fila en la BBDD
			BaseDeDatos.insertDatosPuntos(nick, puntuacion, nivel);
			/*switch(nivel){
			case 1:
				
				break;
			case 2:
				break;
			case 3:
				break;
			}*/
			frmVentanaJuego j= new frmVentanaJuego();
			j.setVisible(true);
			this.dispose();
			break;
		case "Cancelar":
			frmVentanaJuego juego= new frmVentanaJuego();
			juego.setVisible(true);
			this.dispose();
			break;
		}
	}
}
