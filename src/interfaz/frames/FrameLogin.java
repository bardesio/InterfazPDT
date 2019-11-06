package interfaz.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entidades.Usuario;

import interfaz.locator.ClientePDT;

public class FrameLogin implements ActionListener{
	
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
		
	private static void createAndShowGUI() {

		
		JFrame frame = new JFrame("Green Place");
		
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.setVisible(true);
	}
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelPass;
	private JLabel labelUsuario;
	
	/** Atributos de TexField */
	private JTextField textPass;
	private JTextField textUsuario;
	
	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	//Defino el objeto Menú del tipo Menu Principal 
	//que es la clase que contiene el menú de todo el programa
	final FramePrincipal menu= new FramePrincipal();

	
	public void FrameNuevoUsuario(JFrame framePadre) {

		this.labelUsuario = new JLabel("Usuario:");
		this.labelPass = new JLabel("Contraseña:");
		
		this.textPass = new JTextField(15);
		this.textUsuario = new JTextField(15);JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Ingreso al sistema");
		frame.setSize(136, 133);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.rowWeights = new double[]{};
		gbl_loginPanel.columnWeights = new double[]{};
		JPanel loginPanel = new JPanel(gbl_loginPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		
		constraints.gridx = 0;
		constraints.gridy = 0;
		loginPanel.add(this.labelUsuario, constraints);
		
		constraints.gridx = 1;
		loginPanel.add(this.textUsuario, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		loginPanel.add(this.labelPass, constraints);

		constraints.gridx = 1;
		loginPanel.add(this.textPass, constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.SOUTH;
		loginPanel.add(buttonIngresar, constraints);

		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.SOUTH;
		loginPanel.add(buttonCancelar, constraints);

		loginPanel
			.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

	frame.getContentPane().add(loginPanel);

	frame.pack();
	frame.setVisible(true);

	this.frame = frame;
}

	/**
	 * Como implementos Action Listener, quiere decir que soy escuchado de
	 * eventos. Este método es quien se ejecutan cuando tocan un boton .
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngesar();
		}

	}
	
	private void accionIngesar() {

		// Si es ingresar se validan datos!

		String fieldUsuario = this.textUsuario.getText();
		String fieldPass = this.textPass.getText();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldUsuario.equals("") || fieldPass.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Valiamos ahora, que los datos ingresados sean correctos
		List<Usuario> existe;
		
		try{
			existe = ClientePDT.Login(fieldUsuario, fieldPass);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		
		if (existe==null) {
			JOptionPane.showMessageDialog(frame, "Usuario y/o contraseña invalida",
					"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		else {
			this.menu.setVisible(true); // Despliego el menú principal
			this.frame.dispose();
		}
	}
	
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

}
