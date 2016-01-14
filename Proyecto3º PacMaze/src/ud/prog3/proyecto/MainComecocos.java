package ud.prog3.proyecto;

import java.awt.EventQueue;

public class MainComecocos {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmVentanaPpal frame = new frmVentanaPpal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		BaseDeDatos.initBD("vpd.bd");
		//new VentanaJuegoNivel1();
	}
}
