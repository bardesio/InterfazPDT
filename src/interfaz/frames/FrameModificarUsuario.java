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

import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;

public class FrameModificarUsuario implements ActionListener{

	/** Frame de la ventana */
	private JFrame frame;
	
	//Probando
	
	/** Atributos de labels */
	private JLabel labelPass;
	private JLabel labelUsuario;
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelEstado;
	private JLabel labelTipoDoc;
	private JLabel labelNumeroDoc;
	private JLabel labelDireccion;
	private JLabel labelMail;
	private JLabel labelTipoUsu;

	
	private JComboBox<String> comboTipoUsu;

	/** Atributos de TexField */
	private JTextField textPass;
	private JTextField textUsuario;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEstado;
	private JTextField textTipoDoc;
	private JTextField textNumeroDoc;
	private JTextField textDireccion;
	private JTextField textMail;
	
	/** Atributos de Botones */
	private JButton buttonModificar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;
	
	
	/** Lista de Tipos del sistema */
	private List<TipoUsuario> tipoUsuarios;
	
	public FrameModificarUsuario(JFrame framePadre) {

		this.labelApellido = new JLabel("Apellido:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelDireccion = new JLabel("Direccion:");
		this.labelEstado = new JLabel("Estado:");
		this.labelMail = new JLabel("Mail:");
		this.labelNumeroDoc = new JLabel("Numero de documento:");
		this.labelPass = new JLabel("Password:");
		this.labelTipoDoc = new JLabel("Tipo de documento:");
		this.labelUsuario = new JLabel("Usuario:");
		this.labelTipoUsu = new JLabel("Tipo de Usuario:");

		

		this.textApellido = new JTextField(15);
		this.textNombre = new JTextField(15);
		this.textDireccion = new JTextField(15);
		this.textEstado = new JTextField(15);
		this.textMail = new JTextField(15);
		this.textNumeroDoc = new JTextField(15);
		this.textPass = new JTextField(15);
		this.textTipoDoc = new JTextField(15);
		this.textUsuario = new JTextField(15);
		
		JButton buttonModificar = new JButton("Modificar");
		buttonModificar.addActionListener(this);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		JButton buttonBuscar = new JButton ("Buscar");
		buttonBuscar.addActionListener(this);
		
		this.buttonModificar = buttonModificar;
		this.buttonCancelar = buttonCancelar;
		this.buttonBuscar = buttonBuscar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Usuario");
		frame.setSize(136, 133);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gbl_modificarUsuarioPanel = new GridBagLayout();
		gbl_modificarUsuarioPanel.rowWeights = new double[]{};
		gbl_modificarUsuarioPanel.columnWeights = new double[]{};
		JPanel nuevoUsuarioPanel = new JPanel(gbl_modificarUsuarioPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoUsuarioPanel.add(this.labelUsuario, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textUsuario, constraints);
			
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.SOUTH;
		nuevoUsuarioPanel.add(buttonBuscar, constraints);
		this.buttonBuscar.setEnabled(false);

		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoUsuarioPanel.add(this.labelPass, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textPass, constraints);
		this.textPass.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoUsuarioPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textNombre, constraints);
		this.textNombre.setEnabled(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textApellido, constraints);
		this.textApellido.setEnabled(false);

		
		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevoUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textDireccion, constraints);
		this.textDireccion.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevoUsuarioPanel.add(this.labelEstado, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textEstado, constraints);
		this.textEstado.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevoUsuarioPanel.add(this.labelMail, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textMail, constraints);
		this.textMail.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevoUsuarioPanel.add(this.labelNumeroDoc, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textNumeroDoc, constraints);
		this.textNumeroDoc.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevoUsuarioPanel.add(this.labelTipoDoc, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textTipoDoc, constraints);
		this.textTipoDoc.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevoUsuarioPanel.add(this.labelTipoUsu, constraints);

		constraints.gridx = 1;
		this.comboTipoUsu = this.completarComboUsuario(frame);
		this.comboTipoUsu.setEnabled(false);

			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevoUsuarioPanel.add(buttonModificar, constraints);
			this.buttonModificar.setEnabled(false);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevoUsuarioPanel.add(buttonCancelar, constraints);

		nuevoUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(nuevoUsuarioPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JComboBox<String> completarComboUsuario(JFrame frame) {
		
		try{
			this.tipoUsuarios = ClientePDT.obtenerTodoslosTipos();
		}
		catch (Exception e){
			return null;
		}
		
		JComboBox<String> combo = new JComboBox<>();
		
		for(TipoUsuario u : this.tipoUsuarios){
			combo.addItem(u.getNombre());
		}
		
		return combo;
		
		
	}
	
	/**
	 * Como implementos Action Listener, quiere decir que soy escuchado de
	 * eventos. Este m�todo es quien se ejecutan cuando tocan un boton .
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que bot�n fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else if (e.getSource() == this.buttonBuscar) 
		{
			this.accionBuscar();
		}
		else if (e.getSource()==this.buttonModificar)
		{
			this.accionModificar();
		}
		

	}
	
	private void accionBuscar() {

		String fieldUsuario = this.textUsuario.getText();
		
		
		// Validamos ahora, que exista un Usuario con dicha CI
				List<Usuario> existe;
				
				try{
					existe = ClientePDT.existeUsuario(fieldUsuario);
				} catch (Exception e){
					JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
							"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				if (existe==null) {
					JOptionPane.showMessageDialog(frame, "El nombre de usuario ingresado no existe.",
							"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				
				else {
					
					//Habilito los campos
					
					this.textUsuario.setEnabled(false);
					this.buttonModificar.setEnabled(true);
					this.textApellido.setEnabled(true);
					this.textDireccion.setEnabled(true);
					this.textEstado.setEnabled(true);
					this.textMail.setEnabled(true);
					this.textNombre.setEnabled(true);
					this.textNumeroDoc.setEnabled(true);
					this.textPass.setEnabled(true);
					this.textTipoDoc.setEnabled(true);
					
					//Cargo los campos
					/*
					this.textApellido.setText(existe.getApellido());
					this.textDireccion.setText(existe.getDireccion()); 
					this.textEstado.setText(existe.getEstado());;
					this.textMail.setText(existe.getMail());;
					this.textNombre.setText(existe.getNombre());;
					this.textNumeroDoc.setText(existe.getNumerodoc());;
					this.textPass.setText(existe.getPass());;
					this.textTipoDoc.setText(existe.getTipodoc());
					*/
				}
		
	}
	
	private void accionModificar() {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText();
		String fieldApellido = this.textApellido.getText();
		String fieldDireccion = this.textDireccion.getText();
		String fieldEstado = this.textEstado.getText();
		String fieldMail = this.textMail.getText();
		String fieldNumeroDoc = this.textNumeroDoc.getText();
		String fieldPass = this.textPass.getText();
		String fieldTipoDoc = this.textTipoDoc.getText();
		TipoUsuario fieldTipoUsu = (TipoUsuario) this.comboTipoUsu.getSelectedItem();
		Long fieldID = 1l;
				

		// Si alguno es vac�o, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldApellido.equals("") || 
				fieldDireccion.equals("")|| fieldEstado.equals("")|| fieldMail.equals("")|| 
				fieldNumeroDoc.equals("")|| fieldPass.equals("")|| fieldTipoDoc.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}


		boolean almacenado;
						
		try{
			almacenado = ClientePDT.ModificarUsuario(fieldID, fieldNombre, fieldApellido, fieldTipoDoc, 
					fieldNumeroDoc, fieldDireccion, fieldMail, fieldPass, fieldEstado, fieldTipoUsu);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
					"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Usuario ha sido modificado con �xito.",
					"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente m�s tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

}