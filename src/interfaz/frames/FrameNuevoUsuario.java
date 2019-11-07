package interfaz.frames;

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

import com.Remote.FenomenoBeanRemote;
import com.Remote.TIpoUsuarioBeanRemote;
import com.entidades.Fenomeno;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;
import interfaz.locator.EJBLocator;


public class FrameNuevoUsuario implements ActionListener{

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
		
		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Usuario");
		frame.setSize(136, 133);
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
		nuevoUsuarioPanel.add(this.textTipoDoc, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevoUsuarioPanel.add(this.labelTipoUsu, constraints);

		constraints.gridx = 1;
		 this.comboTipoUsu = this.completarComboUsuario(frame);
		 
		
		if (this.comboTipoUsu!=null){
			nuevoUsuarioPanel.add(this.comboTipoUsu, constraints);
		
			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevoUsuarioPanel.add(buttonIngresar, constraints);

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

	}else{
		JOptionPane.showMessageDialog(frame, "Estoy reventando al querer cargar el combo",
				"Error de conexión!", JOptionPane.WARNING_MESSAGE);
		frame.dispose();}
	}

	

	private JComboBox<String> completarComboUsuario(JFrame frame) {
		
		try{
			

			TIpoUsuarioBeanRemote tipousuariobeanremote = EJBLocator.getInstance().lookup(TIpoUsuarioBeanRemote.class);
			
			this.tipoUsuarios = tipousuariobeanremote.obtenerTodoslosTipos();
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

		String fieldNombre = this.textNombre.getText();
		String fieldApellido = this.textApellido.getText();
		String fieldUsuario = this.textUsuario.getText();
		String fieldDireccion = this.textDireccion.getText();
		String fieldEstado = this.textEstado.getText();
		String fieldMail = this.textMail.getText();
		String fieldNumeroDoc = this.textNumeroDoc.getText();
		String fieldPass = this.textPass.getText();
		String fieldTipoDoc = this.textTipoDoc.getText();
		long tipoUsu = (long) this.comboTipoUsu.getSelectedItem();
		Long fieldID = 1l;
		

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldUsuario.equals("")|| 
				fieldDireccion.equals("")|| fieldEstado.equals("")|| fieldMail.equals("")|| 
				fieldNumeroDoc.equals("")|| fieldPass.equals("")|| fieldTipoDoc.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Valiamos ahora, que no exista un Usuario con dicha CI
		
		Usuario existe;
		
		try{
			existe = ClientePDT.existeUsuario(fieldNumeroDoc);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		
		if (existe!=null) {
			JOptionPane.showMessageDialog(frame, "El Usuario con dicho documento ya se ecuentra registrado.",
					"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// Usuario y volvemos al menu
		boolean almacenado;
		
				
		try{
			almacenado = ClientePDT.CrearUsuario(fieldID, fieldPass, fieldUsuario, fieldNombre, fieldApellido, fieldEstado, fieldTipoDoc, fieldNumeroDoc, fieldDireccion, fieldMail, tipoUsu);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Usuario ha sido registrado con éxito.",
					"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);
			
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
