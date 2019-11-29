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

import com.entidades.Fenomeno;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.constantes.Constantes;
import interfaz.locator.ClientePDT;


public class FrameEliminarUsuario implements ActionListener{

	
	List<Usuario> usuarioActual = null;
	
	/** Frame de la ventana */
	private JFrame frame;

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
	private JButton buttonEliminar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;

	/** Lista de Tipos del sistema */
	private List<TipoUsuario> tipoUsuarios;
	
	public FrameEliminarUsuario(JFrame framePadre, List<Usuario> listUsuarios) {

		//Cargo el usuario actual con lo que me pasan de frame principal
		usuarioActual = listUsuarios;
		
		
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
		
		
		JButton buttonEliminar = new JButton("Eliminar");
		buttonEliminar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/delete.gif")));
		buttonEliminar.addActionListener(this);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);

		JButton buttonBuscar = new JButton ("Buscar");
		buttonBuscar.addActionListener(this);
		
		this.buttonEliminar = buttonEliminar;
		this.buttonCancelar = buttonCancelar;
		this.buttonBuscar = buttonBuscar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Eliminar Usuario");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		GridBagLayout gbl_eliminarUsuarioPanel = new GridBagLayout();
		gbl_eliminarUsuarioPanel.rowWeights = new double[]{};
		gbl_eliminarUsuarioPanel.columnWeights = new double[]{};
		JPanel eliminarUsuarioPanel = new JPanel(gbl_eliminarUsuarioPanel);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		eliminarUsuarioPanel.add(this.labelUsuario, constraints);
		
		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textUsuario, constraints);

		
		constraints.gridx = 0;
		constraints.gridy = 1;
		eliminarUsuarioPanel.add(this.labelPass, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textPass, constraints);
		this.textPass.setEditable(false);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		eliminarUsuarioPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textNombre, constraints);
		this.textNombre.setEditable(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		eliminarUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textApellido, constraints);
		this.textApellido.setEditable(false);

		
		constraints.gridx = 0;
		constraints.gridy = 4;
		eliminarUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textDireccion, constraints);
		this.textDireccion.setEditable(false);

		constraints.gridx = 0;
		constraints.gridy = 5;
		eliminarUsuarioPanel.add(this.labelEstado, constraints);
		
		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textEstado, constraints);
		this.textEstado.setEditable(false);

		constraints.gridx = 0;
		constraints.gridy = 6;
		eliminarUsuarioPanel.add(this.labelMail, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textMail, constraints);
		this.textMail.setEditable(false);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		eliminarUsuarioPanel.add(this.labelNumeroDoc, constraints);

		constraints.gridx = 1;
		eliminarUsuarioPanel.add(this.textNumeroDoc, constraints);
		this.textNumeroDoc.setEditable(false);

		constraints.gridx = 0;
		constraints.gridy = 8;
		eliminarUsuarioPanel.add(this.labelTipoDoc, constraints);

		constraints.gridx = 1;
		this.comboTipo = this.completarComboTipo();
		eliminarUsuarioPanel.add(this.comboTipo, constraints);
		this.comboTipo.setEnabled(false);

		constraints.gridx = 0;
		constraints.gridy = 9;
		eliminarUsuarioPanel.add(this.labelTipoUsu, constraints);

		constraints.gridx = 1;
		this.comboTipoUsu = this.completarComboUsuario(frame);
		eliminarUsuarioPanel.add(this.comboTipoUsu, constraints);
		this.comboTipoUsu.setEnabled(false);

			constraints.gridx = 2;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			eliminarUsuarioPanel.add(buttonEliminar, constraints);
			this.buttonEliminar.setEnabled(false);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			eliminarUsuarioPanel.add(buttonCancelar, constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			eliminarUsuarioPanel.add(buttonBuscar, constraints);

			eliminarUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(eliminarUsuarioPanel);

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
	
	
	/**
	 * Como implementos Action Listener, quiere decir que soy escuchado de
	 * eventos. Este método es quien se ejecutan cuando tocan un boton .
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else if (e.getSource() == this.buttonBuscar) 
		{
			this.accionBuscar();
		}
		else if (e.getSource()==this.buttonEliminar)
		{
			this.accionEliminar();
		}
		

	}
	
	private void accionBuscar() {

		String fieldUsuario = this.textUsuario.getText();
		
		if (fieldUsuario.length() > 50)
		{
			JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en el campo usuario", "Maximo superado!",
					JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		
		// Validamos ahora, que exista un Usuario con dicha CI
				List<Usuario> usuarios;
				
				try{
					usuarios = ClientePDT.existeUsuario(fieldUsuario.toUpperCase());
				} catch (Exception e){
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				if (usuarios==null || usuarios.size() == 0 || usuarios.get(0).getEstado().equals("INACTIVO")) {
					JOptionPane.showMessageDialog(frame, "El nombre de usuario ingresado no existe.",
							"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				else if (usuarioActual.get(0).getUsuario().equals(usuarios.get(0).getUsuario())){
					JOptionPane.showMessageDialog(frame, "No se puede eliminar a si mismo",
							"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				
				
				else {
					
					//Dejo los campos habilitados los campos
					
					this.buttonEliminar.setEnabled(true);
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
	
	private void accionEliminar() {

		String fieldUsuario = this.textUsuario.getText();
		String fieldNombre = this.textNombre.getText().toUpperCase();
		String fieldApellido = this.textApellido.getText().toUpperCase();
		String fieldDireccion = this.textDireccion.getText().toUpperCase();
		String fieldEstado = "INACTIVO";
		String fieldMail = this.textMail.getText().toUpperCase();
		String fieldNumeroDoc = this.textNumeroDoc.getText().toUpperCase();
		String fieldPass = this.textPass.getText().toUpperCase();
		String Tipodoc = (String) this.comboTipo.getSelectedItem();
		String tipoUsu = (String) this.comboTipoUsu.getSelectedItem();		
		
		
				
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldUsuario.equals("")){
			JOptionPane.showMessageDialog(frame, "Debe ingresar el nombre de usuario", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}


		boolean almacenado;
						
		try{
			almacenado = ClientePDT.ModificarUsuario(fieldID, fieldPass, fieldUsuario, fieldNombre, fieldApellido, 
					fieldEstado, Tipodoc, fieldNumeroDoc, fieldDireccion, fieldMail, tipoUsu);
			
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Usuario ha sido eliminado con éxito.",
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
