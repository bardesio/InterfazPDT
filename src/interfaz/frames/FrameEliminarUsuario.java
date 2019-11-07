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

import com.entidades.Fenomeno;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;


public class FrameEliminarUsuario implements ActionListener{

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelUsuario;
	
	/** Atributos de TexField */
	private JTextField textUsuario;

	/** Atributos de Botones */
	private JButton buttonEliminar;
	private JButton buttonCancelar;

	public FrameEliminarUsuario(JFrame framePadre) {

		this.labelUsuario = new JLabel("Ingrese el nombre de usuario a eliminar: ");
	
		this.textUsuario = new JTextField(15);
		
		JButton buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonEliminar = buttonEliminar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Eliminar Usuario");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel eliminarUsuarioPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		eliminarUsuarioPanel.add(this.labelUsuario, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textUsuario, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		eliminarUsuarioPanel.add(buttonEliminar, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		eliminarUsuarioPanel.add(buttonCancelar, constraints);
		

		eliminarUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Eliminacion de un usuario"));

		frame.add(eliminarUsuarioPanel);

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

		// Si es Eliminar se validan datos!

		String fieldUsuario = this.textUsuario.getText().toUpperCase();
		
		

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldUsuario.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe ingresar el usuario a eliminar", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
			
				boolean almaceno;
				
				try{
					
					Usuario us = ClientePDT.existeUsuario(fieldUsuario);
					
					if (us == null)
					{
						JOptionPane.showMessageDialog(null, "Usuario no encontrado");
						return;
					}else {
						almaceno= ClientePDT.EliminarUsuario(us.getId());
					}
						
				}catch(Exception e){
					JOptionPane.showMessageDialog(frame, "Error con el servidor, por favor contacte con su administrador",
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
			}

		private void accionCancelar() {
			// si se cancela, se eliminar la ventana
			this.frame.dispose();
		}

	}
