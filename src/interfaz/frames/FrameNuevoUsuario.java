package interfaz.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;
import com.exception.ServiciosException;
import interfaz.locator.ClientePDT;

public class FrameNuevoUsuario implements ActionListener{

	
	Long fieldID = null;
	
	/** Frame de la ventana */
	private JFrame frame;

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
	private JComboBox<String> comboTipo;


	/** Atributos de TexField */
	private JTextField textPass;
	private JTextField textUsuario;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEstado;
	private JTextField textNumeroDoc;
	private JTextField textDireccion;
	private JTextField textMail;
	
	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	/** Lista de Tipos del sistema */
	private List<TipoUsuario> tipoUsuarios;

	public FrameNuevoUsuario(JFrame framePadre) {

		this.labelApellido = new JLabel("Apellido:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelDireccion = new JLabel("Direccion:");
		this.labelEstado = new JLabel("Estado:");
		this.labelMail = new JLabel("Mail:");
		this.labelNumeroDoc = new JLabel("Numero de documento:");
		this.labelPass = new JLabel("Password:");
		this.labelTipoDoc = new JLabel("Tipo de documento:");
		this.labelUsuario = new JLabel("Nombre de Usuario:");
		this.labelTipoUsu = new JLabel("Tipo de Usuario:");

		

		this.textApellido = new JTextField(15);
		this.textNombre = new JTextField(15);
		this.textDireccion = new JTextField(15);
		this.textEstado = new JTextField(15);
		this.textMail = new JTextField(15);
		this.textNumeroDoc = new JTextField(15);
		this.textPass = new JPasswordField (15);
		this.textUsuario = new JTextField(15);
		
		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/add.png")));
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Usuario");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gbl_nuevoUsuarioPanel = new GridBagLayout();
		gbl_nuevoUsuarioPanel.rowWeights = new double[]{};
		gbl_nuevoUsuarioPanel.columnWeights = new double[]{};
		JPanel nuevoUsuarioPanel = new JPanel(gbl_nuevoUsuarioPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoUsuarioPanel.add(this.labelUsuario, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textUsuario, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoUsuarioPanel.add(this.labelPass, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textPass, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoUsuarioPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textNombre, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textApellido, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevoUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textDireccion, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevoUsuarioPanel.add(this.labelEstado, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textEstado, constraints);
		this.textEstado.setText("ACTIVO");
		this.textEstado.setEditable(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevoUsuarioPanel.add(this.labelMail, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textMail, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevoUsuarioPanel.add(this.labelNumeroDoc, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textNumeroDoc, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevoUsuarioPanel.add(this.labelTipoDoc, constraints);

		constraints.gridx = 1;
		this.comboTipo = this.completarComboTipo();
		nuevoUsuarioPanel.add(this.comboTipo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevoUsuarioPanel.add(this.labelTipoUsu, constraints);

		constraints.gridx = 1;
		 this.comboTipoUsu = this.completarComboUsuario(frame);
		 
		
		if (this.comboTipoUsu!=null){
			nuevoUsuarioPanel.add(this.comboTipoUsu, constraints);
		
			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevoUsuarioPanel.add(buttonIngresar, constraints);
			//buttonIngresar.setBounds(1,10,3,23);
			//nuevoUsuarioPanel.add(buttonIngresar);

			constraints.gridx = 2;
			constraints.gridy = 10;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevoUsuarioPanel.add(buttonCancelar, constraints);

		nuevoUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(nuevoUsuarioPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}else{
		JOptionPane.showMessageDialog(frame, "Error en el servidor, por favor contacte a soporte tecnico",
				"Error de conexión!", JOptionPane.WARNING_MESSAGE);
		frame.dispose();
		}
	}

	private JComboBox<String> completarComboTipo() {
		String[] valores = {"CI", "PASAPORTE", "CARTA DE CIUDADANIA", "OTROS"};
		return new JComboBox<>(valores);
	}

	private JComboBox<String> completarComboUsuario(JFrame frame) {
		
		try{
			this.tipoUsuarios = ClientePDT.obtenerTodoslosTipos();
		}
		catch (Exception e){
			return null;
		}
		
		JComboBox<String> combo = new JComboBox<>();
		
		for(TipoUsuario tu : this.tipoUsuarios){
			combo.addItem(tu.getNombre());
		}
		
		return combo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
				try {
					this.accionIngesar();
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
		 
		}

	}
	
	private void accionIngesar() throws ServiciosException {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText().toUpperCase();
		String fieldApellido = this.textApellido.getText().toUpperCase();
		String fieldUsuario = this.textUsuario.getText().toUpperCase();
		String fieldDireccion = this.textDireccion.getText().toUpperCase();
		String fieldEstado = "ACTIVO";
		String fieldMail = this.textMail.getText().toUpperCase();
		String fieldNumeroDoc = this.textNumeroDoc.getText().toUpperCase();
		String fieldPass = this.textPass.getText().toUpperCase();
		String Tipodoc = (String) this.comboTipo.getSelectedItem();
		String tipoUsu = (String) this.comboTipoUsu.getSelectedItem();		

		
		//Validacion para datos vacios
		
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldUsuario.equals("")|| 
				fieldDireccion.equals("")|| fieldMail.equals("")|| 
				fieldNumeroDoc.equals("")|| fieldPass.equals(""))
		{
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		if (fieldNombre.length() > 50 || fieldApellido.length() > 50 || fieldUsuario.length() > 50|| 
				fieldDireccion.length() > 50|| fieldMail.length() > 50|| 
				fieldNumeroDoc.length() > 50|| fieldPass.length() > 50)
		{
			JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en los campos", "Maximo superado!",
					JOptionPane.WARNING_MESSAGE);
			
			return;
		}
			
			
			
		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// Usuario y volvemos al menu
		boolean almacenado;
		
				
		try{
			
			List<Usuario> us = ClientePDT.existeUsuario(fieldUsuario);
			
			//Si la lista es de tamaño 0
			if (us == null || us.size() == 0)
			{
				//Intento crear el usuario
				try{
					
					almacenado = ClientePDT.CrearUsuario( fieldPass, fieldUsuario, fieldNombre, fieldApellido, fieldEstado, Tipodoc, fieldNumeroDoc, fieldDireccion, fieldMail, tipoUsu);
				
					//Si se devolvio verdadero el almacenado
					if (almacenado) {
						JOptionPane.showMessageDialog(frame, "El Usuario ha sido registrado con éxito.",
								"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);
						
						this.frame.dispose();
		
					}
				
				}catch (Exception e){
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				//El usuario existe pero esta inactivo
			}else if (!String.valueOf(us.get(0).getEstado()).equals("ACTIVO"))
				{
				try {
					fieldID = us.get(0).getId();
					almacenado = ClientePDT.ModificarUsuario(fieldID, fieldPass, fieldUsuario, fieldNombre, fieldApellido, fieldEstado, Tipodoc, fieldNumeroDoc, fieldDireccion, fieldMail, tipoUsu);
				
					if (almacenado) {
						JOptionPane.showMessageDialog(frame, "El Usuario ha sido registrado con éxito.",
								"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);
						
						// cerramos la ventanta
						this.frame.dispose();
		
					}
				
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);
					
					return;
					}
				}

				//El usuario ya existe en el sistema
			else {
				JOptionPane.showMessageDialog(null, "El usuario ya existe en el sistema");
				return;
				}
			
			
	
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error con el servidor, por favor contacte con su administrador",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
	}
		
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

	
}
