package interfaz.constantes;

import interfaz.frames.FrameLogin;


public class VerificarLogin {
	public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FrameLogin login = new FrameLogin();
				login.setVisible(true);				
			}
		});
	}


}
