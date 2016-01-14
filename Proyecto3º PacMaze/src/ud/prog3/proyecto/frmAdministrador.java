package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;





import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;


public class frmAdministrador extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField passContrase�a;
	private JPasswordField passClave;
	private JTextField textUsuario;
	GuardarFicheroAdmin objDatos;
	


	public frmAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setResizable(false);
		Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
		setTitle("Crear cuenta de administracion "+f1.format(d1));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		objDatos= new GuardarFicheroAdmin();
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(66, 68, 84, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(66, 102, 84, 14);
		contentPane.add(lblContrasea);
		
		passContrase�a = new JPasswordField();
		passContrase�a.setBounds(235, 99, 98, 20);
		passContrase�a.setDocument(new JTextFieldLimit(8));
		contentPane.add(passContrase�a);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 159, 379, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblClaveDeAcceso = new JLabel("Clave de acceso");
		lblClaveDeAcceso.setBounds(26, 14, 127, 14);
		panel.add(lblClaveDeAcceso);
		
		passClave = new JPasswordField();
		passClave.setBounds(180, 11, 55, 20);
		passClave.setDocument(new JTextFieldLimit(3));
		panel.add(passClave);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(66, 227, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(272, 227, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
		
		JLabel lblRegistroDeContrasea = new JLabel("Registro de contrase\u00F1a");
		lblRegistroDeContrasea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRegistroDeContrasea.setBounds(116, 21, 217, 23);
		contentPane.add(lblRegistroDeContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(235, 65, 98, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TAREA 4
				BaseDeDatos.finConexion();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		switch(e.getActionCommand())
		{
			case "Aceptar":
				
				int caso=0;
				int contrase�a=0;
				int codigo=0;
				GestorAdministrador objA= new GestorAdministrador();
				String usuario= textUsuario.getText();
	
				try{
			       contrase�a=Integer.parseInt(new String(passContrase�a.getPassword()));
			    }
				catch(Exception e1){
			        JOptionPane.showMessageDialog(frmAdministrador.this, "ERROR, la contrase�a debe ser num�rica.", "ERROR", JOptionPane.ERROR_MESSAGE);
			        caso=1;
				}
				try{
					codigo=Integer.parseInt(new String (passClave.getPassword()));
				}
				catch(Exception e2){
					 JOptionPane.showMessageDialog(frmAdministrador.this, "ERROR, el c�digo debe ser num�rico.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				if((codigo==123)&&(caso==0))
				{
					objA.CrearAdministrador(usuario, contrase�a);
				}
				else if(codigo!=123){
					JOptionPane.showMessageDialog(frmAdministrador.this, "C�digo incorrecto.");
				}
				
				frmVentanaPpal objVentana= new frmVentanaPpal();
				objVentana.setVisible(true);
				this.dispose();
				break;
				
			case "Atras":
				frmVentanaPpal objVentana1= new frmVentanaPpal();
				objVentana1.setVisible(true);
				this.dispose();
				break;
		}
	}
	
}
