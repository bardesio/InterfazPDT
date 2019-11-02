package interfaz.frames;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaz.locator.ClientePDT;

public class FrameNuevoFenomeno implements ActionListener {

	
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	
	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;

	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	
	
	public FrameNuevoFenomeno(JFrame framePadre) {

	
		this.labelCodigo = new JLabel("Codigo:"); 
		this.labelNombre = new JLabel("Nombre:");
		this.labelDescripcion = new JLabel("Descripcion:");
		
		 this.textCodigo=new JTextField(15);
		this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		
		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener((ActionListener) this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener((ActionListener) this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {
		
		JFrame frame = new JFrame("Nuevo Fenomeno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevaFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(20,20,20,20);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaFenomenoPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textCodigo, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevaFenomenoPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textNombre, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textDescripcion, constraints);

		
	
			constraints.gridx = 0;
			constraints.gridy = 5;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.CENTER;
			nuevaFenomenoPanel.add(buttonIngresar, constraints);
	
			constraints.gridx = 0;
			constraints.gridy = 6;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.CENTER;
			nuevaFenomenoPanel.add(buttonCancelar, constraints);
			
	
			nuevaFenomenoPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Fenomeno"));
	
			frame.getContentPane().add(nuevaFenomenoPanel);
	
			frame.pack();
			frame.setVisible(true);
	
			this.frame = frame;
		}
	
		

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

		String fieldNombre = this.textNombre.getText();
		String fieldDescripcion = this.textDescripcion.getText();
		long  codigo = this.textCodigo.getBaseline(0, 0);
				
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		
		// Valiamos ahora, que no exista un cliente con dicha CI
		
		
		boolean almacenado;
		
		try{
			
			almacenado= ClientePDT.ingresarnuevoFenomeno(codigo, fieldNombre, fieldDescripcion);
			
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();
			return;
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Fenomeno ha sido registrada con éxito.",
					"Fenomeno Registrada!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente más tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}


}