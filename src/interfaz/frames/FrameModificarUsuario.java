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
import javax.swing.JTextField;

import com.entidades.TipoUsuario;
import com.entidades.Usuario;
import com.exception.ServiciosException;

import interfaz.locator.ClientePDT;

public class FrameModificarUsuario implements ActionListener{

	/** Frame de la ventana */
	private JFrame frame;
	
	//declaro un ID que me servira en caso de que este inactivo
	Long fieldID = null;
	
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
		this.textUsuario = new JTextField(15);
		
		JButton buttonModificar = new JButton("Modificar");
		buttonModificar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/Update.png")));
		buttonModificar.addActionListener(this);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);

		JButton buttonBuscar = new JButton ("Buscar");
		buttonBuscar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/buscar.png")));
		buttonBuscar.addActionListener(this);
		
		this.buttonModificar = buttonModificar;
		this.buttonCancelar = buttonCancelar;
		this.buttonBuscar = buttonBuscar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Modificar Usuario");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gbl_modificarUsuarioPanel = new GridBagLayout();
		gbl_modificarUsuarioPanel.rowWeights = new double[]{};
		gbl_modificarUsuarioPanel.columnWeights = new double[]{};
		JPanel modificarUsuarioPanel = new JPanel(gbl_modificarUsuarioPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		
		constraints.gridx = 0;
		constraints.gridy = 0;
		modificarUsuarioPanel.add(this.labelUsuario, constraints);
		
		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textUsuario, constraints);

		
		constraints.gridx = 0;
		constraints.gridy = 1;
		modificarUsuarioPanel.add(this.labelPass, constraints);

		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textPass, constraints);
		this.textPass.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		modificarUsuarioPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textNombre, constraints);
		this.textNombre.setEnabled(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		modificarUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textApellido, constraints);
		this.textApellido.setEnabled(false);

		
		constraints.gridx = 0;
		constraints.gridy = 4;
		modificarUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textDireccion, constraints);
		this.textDireccion.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 5;
		modificarUsuarioPanel.add(this.labelEstado, constraints);
		
		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textEstado, constraints);
		this.textEstado.setEditable(false);

		constraints.gridx = 0;
		constraints.gridy = 6;
		modificarUsuarioPanel.add(this.labelMail, constraints);

		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textMail, constraints);
		this.textMail.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		modificarUsuarioPanel.add(this.labelNumeroDoc, constraints);

		constraints.gridx = 1;
		modificarUsuarioPanel.add(this.textNumeroDoc, constraints);
		this.textNumeroDoc.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 8;
		modificarUsuarioPanel.add(this.labelTipoDoc, constraints);

		constraints.gridx = 1;
		this.comboTipo = this.completarComboTipo();
		modificarUsuarioPanel.add(this.comboTipo, constraints);
		this.comboTipo.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 9;
		modificarUsuarioPanel.add(this.labelTipoUsu, constraints);

		constraints.gridx = 1;
		this.comboTipoUsu = this.completarComboUsuario(frame);
		modificarUsuarioPanel.add(this.comboTipoUsu, constraints);
		this.comboTipoUsu.setEnabled(false);

			constraints.gridx = 2;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			modificarUsuarioPanel.add(buttonModificar, constraints);
			this.buttonModificar.setEnabled(false);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			modificarUsuarioPanel.add(buttonCancelar, constraints);
			
			/*constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			modificarUsuarioPanel.add(buttonBuscar, constraints);*/
			this.buttonBuscar.setBounds(60,60,89,23);
			modificarUsuarioPanel.add(buttonBuscar);

		modificarUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(modificarUsuarioPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

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
		
		for(TipoUsuario u : this.tipoUsuarios){
			combo.addItem(u.getNombre());
		}
		
		return combo;
		
		
}
	
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
			try {
				this.accionModificar();
			} catch (ServiciosException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	private void accionBuscar() {

		String fieldUsuario = this.textUsuario.getText().toUpperCase();
		
		
		// Validamos ahora, que exista un Usuario con lo ingresado
				List<Usuario> usuarios;
				
				try{
					
					//Valido maximo en el campo usuario
					if (fieldUsuario.length() > 50)
					{
						JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en el campo usuario", "Maximo superado!",
								JOptionPane.WARNING_MESSAGE);
						
						return;
					}
					
					usuarios = ClientePDT.existeUsuario(fieldUsuario);
				} catch (Exception e){
					JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
							"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				if (usuarios==null || usuarios.size() == 0 || usuarios.get(0).getEstado().equals("INACTIVO")) {
					JOptionPane.showMessageDialog(frame, "El nombre de usuario ingresado no existe.",
							"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				
				else {
					
					//Habilito los campos
					
					this.buttonModificar.setEnabled(true);
					this.textApellido.setEnabled(true);
					this.textDireccion.setEnabled(true);
					this.textMail.setEnabled(true);
					this.textNombre.setEnabled(true);
					this.textNumeroDoc.setEnabled(true);
					this.textPass.setEnabled(true);
					this.comboTipo.setEnabled(true);
					
					//Deshabilito campos
					this.buttonBuscar.setEnabled(false);
					this.textUsuario.setEnabled(false);
					
					//Cargo los campos
					
					this.textApellido.setText(usuarios.get(0).getApellido());
					this.textDireccion.setText(usuarios.get(0).getDireccion()); 
					this.textEstado.setText("ACTIVO");
					this.textMail.setText(usuarios.get(0).getMail());
					this.textNombre.setText(usuarios.get(0).getNombre());
					this.textNumeroDoc.setText(usuarios.get(0).getNumerodoc());
					this.textPass.setText(usuarios.get(0).getPass());
					this.comboTipo.setSelectedItem(usuarios.get(0).getTipodoc());
					this.comboTipoUsu.setSelectedItem(usuarios.get(0).getTipousuario());
					this.fieldID = usuarios.get(0).getId();
				}
		
	}
	
	private void accionModificar() throws ServiciosException {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText().toUpperCase();
		String fieldApellido = this.textApellido.getText().toUpperCase();
		String fieldDireccion = this.textDireccion.getText().toUpperCase();
		String fieldEstado = this.textEstado.getText().toUpperCase();
		String fieldMail = this.textMail.getText().toUpperCase();
		String fieldNumeroDoc = this.textNumeroDoc.getText().toUpperCase();
		String fieldPass = this.textPass.getText().toUpperCase();
		String fieldUsuario = this.textUsuario.getText().toUpperCase();
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


		boolean almacenado;
						
		try{
			almacenado = ClientePDT.ModificarUsuario(fieldID, fieldPass, fieldUsuario, fieldNombre, fieldApellido, 
					fieldEstado, Tipodoc, fieldNumeroDoc, fieldDireccion, fieldMail, tipoUsu);
			
			
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