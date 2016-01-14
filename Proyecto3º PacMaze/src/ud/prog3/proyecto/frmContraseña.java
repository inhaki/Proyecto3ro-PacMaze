package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmContraseña extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passCont;
	private JLabel lblIden;
	
	/**
	 * Create the frame.
	 */
	public frmContraseña() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setResizable(false);
		Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
		setTitle("Verificación de acceso a la administración "+f1.format(d1));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Usuario:");
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombre.setBounds(43, 69, 59, 14);
		contentPane.add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContrasea.setBounds(43, 146, 103, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(116, 97, 103, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passCont = new JPasswordField();
		passCont.setBounds(116, 171, 103, 20);
		passCont.setDocument(new JTextFieldLimit(8));
		contentPane.add(passCont);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAceptar.setBounds(167, 223, 116, 28);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		lblIden = new JLabel("IDENTIFICACI\u00D3N DE ACCESO AL MENU");
		lblIden.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIden.setBounds(71, 11, 278, 23);
		contentPane.add(lblIden);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancelar.setBounds(308, 223, 116, 28);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		//para que si se cierra la ventana se cierre la BBDD
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
		switch(e.getActionCommand())
		{
			case "Aceptar":
				int caso=0;//para que no salten los dos errores
				int contraseña=0;
				GestorAdministrador objAdmin= new GestorAdministrador();
				String usuario=txtUsuario.getText();
				
				try{
			        contraseña=Integer.parseInt(new String(passCont.getPassword()));
			    }
				catch(Exception e1){
			        JOptionPane.showMessageDialog(frmContraseña.this, "ERROR, la contraseña debe ser numérica.", "ERROR", JOptionPane.ERROR_MESSAGE);
			        passCont.setText("");
			        caso=1;//salta cuando se meten caracteres en lugar de numeros
				}
				if(objAdmin.ComprobarContraseña(usuario, contraseña)==true)
				{
					frmMenuAdministrador objA= new frmMenuAdministrador(usuario);
					//BaseDeDatos.initBD("vpd.bd");
					objA.setVisible(true);
					this.dispose();
				}
				else if(caso==0)
				{//salta cuando la cuenta no exista
					JOptionPane.showMessageDialog(frmContraseña.this, "ERROR, CONTRASEÑA O USUARIO DESCONOCIDOS","ERROR", JOptionPane.ERROR_MESSAGE);
					txtUsuario.setText("");
					passCont.setText("");
				}
				caso=0;

				break;
			case "Cancelar":
				frmVentanaPpal objMenu= new frmVentanaPpal();
				objMenu.setVisible(true);
				this.dispose();
				break;
		}
		
	}
}
