package ud.prog3.proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DropMode;


public class ContadorJuego extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtHoras;
	private JTextField txtMinutos;
	private JTextField txtSegundos;
	
	private JButton btnArranque;
	
	Timer myTimer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContadorJuego frame = new ContadorJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContadorJuego() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(10, 44, 86, 20);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);
		
		txtMinutos = new JTextField();
		txtMinutos.setColumns(10);
		txtMinutos.setBounds(154, 44, 86, 20);
		contentPane.add(txtMinutos);
		
		txtSegundos = new JTextField();
		txtSegundos.setEditable(false);
		txtSegundos.setColumns(10);
		txtSegundos.setBounds(288, 44, 86, 20);
		contentPane.add(txtSegundos);
		
		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setBounds(10, 31, 46, 14);
		contentPane.add(lblHoras);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(154, 31, 46, 14);
		contentPane.add(lblMinutos);
		
		JLabel lblSegundos = new JLabel("Segundos");
		lblSegundos.setBounds(288, 31, 86, 14);
		contentPane.add(lblSegundos);
		
		btnArranque = new JButton("Arrancar");
		btnArranque.setBounds(91, 112, 89, 23);
		contentPane.add(btnArranque);
		btnArranque.addActionListener(this);
		
				
		txtHoras.setText("0");
		txtMinutos.setText("2");
		txtSegundos.setText("00");
		
		myTimer = new Timer();
		
		Thread a= new Thread (myTimer);
		a.start();
	}
	
	private void Conversor()
	{
		short seg;
		short min;
		short hor;
		
		seg = Short.parseShort(txtSegundos.getText());
		min = Short.parseShort(txtMinutos.getText());
		hor = Short.parseShort(txtHoras.getText());
		
		//seg--;
		/*if (seg==60)
		{
			seg=0;
			
			
			min++;
			if (min==60)
			{
				min=0;
				hor++;
				
			}*/
		if(seg==00){
			seg=60;
			min--;
			
			if(min==00){
				seg=60;
							
			}
		}
		seg--;
		txtHoras.setText(""+hor );
		txtMinutos.setText(""+min );
		txtSegundos.setText(""+seg);
			
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
			
			myTimer.ArrancaPara();
			
			if(btnArranque.getText().equals("Arrancar"))
				btnArranque.setText("Parar");
			else
				btnArranque.setText("Arrancar");
	
	}
	
	class Timer implements Runnable
	{
		private boolean parado = true;
		
		@Override
		public void run() 
		{
			while(true)
			{
				if(parado == false)
				{
					try 
					{
						Thread.sleep(1000);
						Conversor();
						System.out.println("algo");
					}
					catch (InterruptedException e) 
					{
						return;
					}
				}
				else
				{
					//si estamos parados, nada.		
					System.out.println("nada");
				}

			}
			
		}
		
		public void ArrancaPara()
		{
			parado=!parado;
		}
	
	}
}
