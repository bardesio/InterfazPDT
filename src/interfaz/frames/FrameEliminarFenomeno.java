package interfaz.frames;




import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.entidades.Fenomeno;

import com.exception.ServiciosException;



import interfaz.locator.ClientePDT;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameEliminarFenomeno implements ActionListener {

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelfenomeno;
	
	/** Atributos de TexField */
	private JTextField textcodFen;

	/** Atributos de Botones */
	private JButton buttonEliminar;
	private JButton buttonCancelar;

	public FrameEliminarFenomeno(JFrame framePadre) {

		this.labelfenomeno = new JLabel("Ingrese el Codigo de Fenomeno a eliminar: ");
	
		this.textcodFen = new JTextField(15);
		
		JButton buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonEliminar = buttonEliminar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Eliminar Fenomeno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel eliminarFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		eliminarFenomenoPanel.add(this.labelfenomeno, constraints);

		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textcodFen, constraints);
		
				
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		eliminarFenomenoPanel.add(buttonEliminar, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		eliminarFenomenoPanel.add(buttonCancelar, constraints);
		

		eliminarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Eliminacion de un Fenomeno"));

		frame.add(eliminarFenomenoPanel);

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
	
	public void accionIngesar(){
		long fieldcodigo = Integer.parseInt(this.textcodFen.getText());
				
		boolean almaceno;
		
		try {
			
			almaceno= ClientePDT.EliminarFenomeno(fieldcodigo);
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		
	}
		if (almaceno) {
			JOptionPane.showMessageDialog(frame, "El usuario ha sido eliminado con éxito.",
					"Usuario eliminado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al eliminar. Intente nuevamente más tarde",
					"Error al eliminar!", JOptionPane.ERROR_MESSAGE);
		}
	}private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();
	}

	}
	
	
