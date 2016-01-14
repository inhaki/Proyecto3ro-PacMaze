package ud.prog3.proyecto;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import ud.prog3.proyecto.BaseDeDatos;

import javax.swing.JPasswordField;


public class frmVentanaPpal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textEmail;
	private JTextField textEdad;
	private JTextField textCiudad;
	private JTextField textUsuario1;
	private JPasswordField textContraseña1;
	private JPasswordField textContra;
	
	
	/**Constructor con todos los elementos incluidos
	 */
	public frmVentanaPpal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Date d1= new Date();
		SimpleDateFormat f1= new SimpleDateFormat("dd/MM/yyyy");
		setTitle("Menu Principal de PACMAZE "+f1.format(d1)+" ©Copywrite G&T");
		setBounds(100, 100, 620, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jpregistrarse = new JPanel();
		jpregistrarse.setBounds(368, 11, 226, 345);
		jpregistrarse.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " ¿Aún no te has registrado? ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(jpregistrarse);
		jpregistrarse.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(116, 42, 86, 20);
		jpregistrarse.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsuario.setBounds(23, 44, 70, 14);
		jpregistrarse.add(lblUsuario);
		
		JLabel lblApellido = new JLabel("Contraseña");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		lblApellido.setBounds(23, 85, 86, 14);
		jpregistrarse.add(lblApellido);
		
		JLabel lblCorreoElectronico = new JLabel("Email");
		lblCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(23, 129, 46, 14);
		jpregistrarse.add(lblCorreoElectronico);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEdad.setBounds(23, 177, 46, 14);
		jpregistrarse.add(lblEdad);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCiudad.setBounds(23, 229, 46, 14);
		jpregistrarse.add(lblCiudad);
		
		textEmail = new JTextField();
		textEmail.setBounds(116, 126, 86, 20);
		jpregistrarse.add(textEmail);
		textEmail.setColumns(10);
		
		textEdad = new JTextField();
		textEdad.setBounds(116, 174, 86, 20);
		jpregistrarse.add(textEdad);
		textEdad.setColumns(10);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(116, 226, 86, 20);
		jpregistrarse.add(textCiudad);
		textCiudad.setColumns(10);
		
		JButton btnRegistrate = new JButton("Registrate");
		btnRegistrate.setBounds(60, 292, 108, 23);
		jpregistrarse.add(btnRegistrate);
		btnRegistrate.addActionListener(this);
		
		textContra = new JPasswordField();
		textContra.setBounds(116, 83, 86, 20);
		jpregistrarse.add(textContra);
		
		JButton btnEmpiezaAJugar = new JButton("Empieza a jugar");
		btnEmpiezaAJugar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEmpiezaAJugar.setBounds(106, 330, 146, 38);
		contentPane.add(btnEmpiezaAJugar);
		btnEmpiezaAJugar.addActionListener(this);
		
		JLabel lblUsuario_1 = new JLabel("Usuario");
		lblUsuario_1.setBounds(94, 222, 69, 14);
		contentPane.add(lblUsuario_1);
		
		textUsuario1 = new JTextField();
		textUsuario1.setBounds(190, 219, 86, 20);
		contentPane.add(textUsuario1);
		textUsuario1.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(94, 266, 69, 14);
		contentPane.add(lblContrasea);
		
		textContraseña1 = new JPasswordField();
		textContraseña1.setBounds(190, 263, 86, 20);
		contentPane.add(textContraseña1);
		textContraseña1.setColumns(10);
		
		JLabel lblPacmaze = new JLabel("");
		lblPacmaze.setFont(new Font("Arial", Font.PLAIN, 56));
		lblPacmaze.setIcon(new ImageIcon(frmVentanaPpal.class.getResource("/Graficos/PACMAZE.gif")));
		lblPacmaze.setBounds(37, 69, 321, 63);
		contentPane.add(lblPacmaze);
		
		JPanel jpadmin = new JPanel();
		jpadmin.setBounds(368, 367, 226, 83);
		jpadmin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Administración ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(jpadmin);
		jpadmin.setLayout(null);
		
		JButton btnNewButton = new JButton("Entra aquí");
		btnNewButton.setBounds(60, 31, 108, 23);
		jpadmin.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnCuentaAdmin = new JButton("");
		btnCuentaAdmin.setIcon(new ImageIcon(frmVentanaPpal.class.getResource("/PersonajesJuego/PacmanDerecha.gif")));
		btnCuentaAdmin.setBounds(179, 11, 37, 28);
		jpadmin.add(btnCuentaAdmin);
		btnCuentaAdmin.addActionListener(this);
		
		JLabel lblComebolas = new JLabel("");
		lblComebolas.setBounds(152, 143, 124, 54);
		contentPane.add(lblComebolas);
		lblComebolas.setIcon(new ImageIcon(frmVentanaPpal.class.getResource("/Graficos/ComeBolas.gif")));
		
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
	
				BaseDeDatos.finConexion();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getActionCommand()){
		
		
		case "Registrate":
			
			
			BaseDeDatos.crearTablaUsuario();
			
			int caso=0;
			int contraseña=0;
			int edad=0;
			String usuario= textUsuario.getText();
			try{
				contraseña= Integer.parseInt(new String(textContra.getPassword()));
			}catch(Exception e1){
				JOptionPane.showMessageDialog(frmVentanaPpal.this, "ERROR, la contraseña debe ser numérica.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textContra.setText("");
				caso=1;
			}
			
			String email= textEmail.getText();
			
			try{
				edad= Integer.parseInt(textEdad.getText());
			}catch(Exception e1){
				JOptionPane.showMessageDialog(frmVentanaPpal.this, "ERROR, la edad debe ser numérica.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textEdad.setText("");
				caso=1;
			}
			String ciudad= textCiudad.getText();
				
			if(caso==0){
				try{
					BaseDeDatos.insertDatosUsuario(usuario, contraseña, email, edad, ciudad);
					//cuando envia correctamente se ponen todos vaíos
					textUsuario.setText("");
					textContra.setText("");
					textEmail.setText("");
					textEdad.setText("");
					textCiudad.setText("");
					JOptionPane.showMessageDialog(frmVentanaPpal.this,"REGISTRADO CORRECTAMENTE", "OKEY", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1){
					textUsuario.setText("");
					JOptionPane.showMessageDialog(frmVentanaPpal.this,"ERROR, USUARIO DUPLICADO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}			
			break;
			
		case "":
			frmAdministrador objA= new frmAdministrador ();
			objA.setVisible(true);
			this.dispose();
			break;
			
		case "Entra aquí":
			frmContraseña objC= new frmContraseña ();
			objC.setVisible(true);
			this.dispose();
			break;

			
		case "Empieza a jugar":
			String usuario1=textUsuario1.getText();
			int contraseña1=-1;
			try{
				contraseña1=Integer.parseInt(textContraseña1.getText());
			}catch(Exception e1){
				JOptionPane.showMessageDialog(frmVentanaPpal.this, "ERROR, la contraseña debe ser numérica.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textContraseña1.setText("");
				caso=1;
			}
			if(BaseDeDatos.VerificarUsuario(usuario1, contraseña1)==true){
			
				frmVentanaJuego obj= new frmVentanaJuego ();
				obj.setVisible(true);
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frmVentanaPpal.this, "ERROR, usuario o contraseña desconocidos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textUsuario1.setText("");
				textContraseña1.setText("");
			}
			break;
			
		}
		
	}
}
