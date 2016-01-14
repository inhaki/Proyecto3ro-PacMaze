package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class frmMenuAdministrador extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JList listJugadores;
	private JTextField textUsuario;
	private JPanel panelBorrado;
	
	/**
	 * Create the frame.
	 */
	public frmMenuAdministrador(String usuario) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setResizable(false);
		Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
		setTitle("Bienvenido de nuevo "+usuario+" hoy es "+f1.format(d1));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setLayout(null);
		panelAdmin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuentas Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAdmin.setBounds(10, 11, 278, 300);
		contentPane.add(panelAdmin);
		
		JButton btnEliminarAdm = new JButton("Seleccionar");
		btnEliminarAdm.setBounds(40, 266, 144, 23);
		panelAdmin.add(btnEliminarAdm);
		btnEliminarAdm.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 17, 250, 240);
		panelAdmin.add(scrollPane);
		
		listJugadores = new JList();
		scrollPane.setViewportView(listJugadores);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(211, 338, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
		
		JLabel lbleliminarTodasLas = new JLabel("\u00BFEliminar todas las ");
		lbleliminarTodasLas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbleliminarTodasLas.setBounds(298, 36, 142, 31);
		contentPane.add(lbleliminarTodasLas);
		
		JLabel lblPuntuacionesGuardadas = new JLabel("puntuaciones guardadas?");
		lblPuntuacionesGuardadas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPuntuacionesGuardadas.setBounds(298, 57, 174, 31);
		contentPane.add(lblPuntuacionesGuardadas);
		
		JButton btnResetearHighScore = new JButton("Resetear High Score");
		btnResetearHighScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnResetearHighScore.setBounds(308, 105, 151, 23);
		contentPane.add(btnResetearHighScore);
		
		panelBorrado = new JPanel();
		panelBorrado.setLayout(null);
		panelBorrado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u00BFEstas seguro? ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBorrado.setBounds(298, 140, 186, 154);
		contentPane.add(panelBorrado);
		panelBorrado.setVisible(false);
		
		JLabel lblIntroduceElNombre = new JLabel("Introduce el nombre y confirma");
		lblIntroduceElNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIntroduceElNombre.setBounds(8, 23, 182, 14);
		panelBorrado.add(lblIntroduceElNombre);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(32, 106, 112, 23);
		panelBorrado.add(btnConfirmar);
		btnConfirmar.addActionListener(this);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(17, 58, 127, 20);
		panelBorrado.add(textUsuario);
		textUsuario.setColumns(10);
		btnResetearHighScore.addActionListener(this);
		
		//BaseDeDatos.initBD("vpd.bd");
		try {
			this.VerListaJugadores();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(frmMenuAdministrador.this, "ERROR, NO SE HAN INTRODUCIDO DATOS!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
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
			case "Atras": 
					frmVentanaPpal ppal= new frmVentanaPpal();
					ppal.setVisible(true);
					this.dispose();
				break;
			case "Seleccionar":
				DefaultListModel<String> E=(DefaultListModel<String>)listJugadores.getModel();
				int ind=listJugadores.getSelectedIndex();
				//E.remove(ind);
				/*String usuario=E.getElementAt(ind);
				usuario.substring(9,12);
				System.out.println(usuario.substring(9,12));
				*/
				panelBorrado.setVisible(true);
				
				break;
			case "Confirmar":
				String usuario=textUsuario.getText();
				try{
					BaseDeDatos.BorrarUsuario(usuario);
					panelBorrado.setVisible(false);
					textUsuario.setText("");
					//Una vez confirmado el borrado se vuelve a llamar a la lista para que se actualice
					try {
						this.VerListaJugadores();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(frmMenuAdministrador.this, "ERROR, NO SE HAN INTRODUCIDO DATOS!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					JOptionPane.showMessageDialog(frmMenuAdministrador.this, "ERROR, EL NOMBRE INTRODUCIDO NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "Resetear High Score":
				BaseDeDatos.BorrarPuntuaciones();
				break;
		}
		
	}
	
	/**Para visualizar la lista de Usuarios creamos un ArrayList donde vamos metiendo los "String" que llegan de BBDD
	 * @throws SQLException
	 */
	public void VerListaJugadores() throws SQLException{

	   DefaultListModel<String> listModel = new DefaultListModel<String>();
	   
	   ArrayList<String> listaU=BaseDeDatos.VisualizarUsuario(); 
	   //una vez rellenada la lista los añadimos al listModel
	   for(int i=0; i<listaU.size(); i++) 
	   {
		   listaU.get(i);
	       listModel.addElement(listaU.get(i));
	   }
	   
	   listJugadores.setModel(listModel);
	
	}
}
