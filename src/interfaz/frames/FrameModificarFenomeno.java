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
import com.entidades.Telefono;
import interfaz.locator.ClientePDT;

public class FrameModificarFenomeno implements ActionListener{

	
	
	//Atributo frame
	private JFrame frame;

	Long fieldID = null;
	
	// Atributos de labels 
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	private JLabel labeltelefono;
	private JLabel labelestado;
	
	//Creo el combobox de telefonos
	private JComboBox<String> comboTel;
	
	
	
	// Atributos de TexField 
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textEstado;

	// Atributos de Botones 
	private JButton buttonModificar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;
	
	//Creo la lista de telefonos
	private List<Telefono> telefonos;

	public FrameModificarFenomeno(JFrame framePadre) {

		//Defino el nombre de las labels
		this.labelCodigo = new JLabel("Código:"); 
		this.labelNombre = new JLabel("Nombre:");
		this.labelestado = new JLabel("Estado:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labeltelefono = new JLabel ("Teléfonos de Emergencia:");
		
		//Defino el tamaño de los textbox
		 this.textCodigo=new JTextField(15);
		 this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		 this.textEstado = new JTextField(15);
		 
		//Defino nombre e icono del boton modificar
		JButton buttonModificar = new JButton("Modificar");
		buttonModificar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/Update.png")));
		buttonModificar.addActionListener(this);

		//Defino nombre e icono del boton cancelar
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);

		//Defino nombre e icono del boton buscar
		JButton buttonBuscar = new JButton ("Buscar");
		buttonBuscar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/buscar.png")));
		buttonBuscar.addActionListener(this);
		
		//Asigno el boton buscar las propiedades
		this.buttonModificar = buttonModificar;
		this.buttonCancelar = buttonCancelar;
		this.buttonBuscar = buttonBuscar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Modificar Fenómeno");
		frame.setSize(136, 133);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel ModificarFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		ModificarFenomenoPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textCodigo, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		ModificarFenomenoPanel.add(this.labelestado, constraints);

		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textEstado, constraints);
		this.textEstado.setText("ACTIVO");
		this.textEstado.setEditable(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		ModificarFenomenoPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textNombre, constraints);
		this.textNombre.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		ModificarFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textDescripcion, constraints);
		this.textDescripcion.setEnabled(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		ModificarFenomenoPanel.add(this.labeltelefono, constraints);
		
		constraints.gridx = 1;
		 this.comboTel = this.completarComboTelefono(frame);
		 this.comboTel.setEnabled(false);
		 

		if (this.comboTel!=null) {
			ModificarFenomenoPanel.add(this.comboTel,constraints);
			comboTel.setEnabled(false);
		
		
			constraints.gridx = 2;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonModificar, constraints);
			this.buttonModificar.setEnabled(false);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.WEST;
			ModificarFenomenoPanel.add(buttonCancelar, constraints);
			

			
			this.buttonBuscar.setBounds(60,60,89,23);
			ModificarFenomenoPanel.add(buttonBuscar);			

			ModificarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Fenómeno"));

		frame.getContentPane().add(ModificarFenomenoPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}else
	{
		JOptionPane.showMessageDialog(frame, "Error en el servidor, por favor contacte a soporte tecnico",
				"Error de conexión!", JOptionPane.WARNING_MESSAGE);
		frame.dispose();}
		
	}
	
	//Cargo combo de telefonos
	private JComboBox<String> completarComboTelefono(JFrame frame) {
		
		try{
			this.telefonos = ClientePDT.obtenerTelefonoE();
			
		}
		catch (Exception e){
			return null;
		}
		
		JComboBox<String> combo = new JComboBox<>();
		
		for(Telefono tu : this.telefonos){
			
			combo.addItem(tu.getNombre());
			
		}
		
		return combo;
	}
	
	//Defino y ejecuto el boton que se pulso
	@Override
	public void actionPerformed(ActionEvent e) {

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

		
				try{
					
					//Valido maximo en el campo codigo
					if (this.textCodigo.getText().length() > 50)
					{
						JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en el campo código", "Máximo superado!",
								JOptionPane.WARNING_MESSAGE);
						
						return;
					}
					
					//Me guardo el codigo y busco los fenomenos con dicho codigo
					String codigo = this.textCodigo.getText().toUpperCase();
					List <Fenomeno> fenomenos= ClientePDT.existecodigo(codigo);
				
					// Si no se encuentran resultado muestro mensaje
					if (fenomenos.isEmpty())
					{
						JOptionPane.showMessageDialog(frame, "El código del fenómeno ingresado no existe.",
								"Fenómeno Inexistente!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					//Camino feliz, se encontraron resultados. Los muestro.
					else {
						for(Fenomeno CFen : fenomenos)
						{
																					
							String dato=CFen.getCodigo();
							textCodigo.setText(dato);
							String estado=CFen.getEstado();
							this.textEstado.setText(estado);
							String desc=CFen.getDescripcion();
							this.textDescripcion.setText(desc);
							String nom=CFen.getNombreFen();
							textNombre.setText(nom);
							comboTel.setEnabled(false);
							this.comboTel.setSelectedItem(CFen.getTelefonos().getNombre());
							this.comboTel.setEnabled(true);							
							this.fieldID = CFen.getId();
							
							
							
							//Habilito los campos
							this.textDescripcion.setEnabled(true);
							this.textNombre.setEnabled(true);
							buttonModificar.setEnabled(true);
							this.textCodigo.setEnabled(true);
							
							//Deshabilito los campos 
							this.textCodigo.setEnabled(false);
							this.buttonBuscar.setEnabled(false);
							
							
						}
					}
					 
				}
				
				//Fallo en ir al servidor.
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
								"Error de conexión!", JOptionPane.WARNING_MESSAGE);
						return;
					}
	}			
				

	private void accionModificar() {

		// Guardo lo ingresado en variables
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldNombre= this.textNombre.getText();
		String fieldcodigo = this.textCodigo.getText();
		String tel = (String) this.comboTel.getSelectedItem();
		String fieldEstado = this.textEstado.getText().toUpperCase();
		
		
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldcodigo.equals("") || fieldNombre.equals("") || fieldDescripcion.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		
		//Valido maximo en los campos
		if (fieldcodigo.length() > 50 || fieldNombre.length() > 50 || fieldDescripcion.length() > 50)
		{
			JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en los campos", "Máximo superado!",
					JOptionPane.WARNING_MESSAGE);
			
			return;
		}

		boolean almacenado;
						
		try{
			
			//Intento modificar
			almacenado = ClientePDT.ModificarFenomeno(fieldID, fieldcodigo,fieldEstado, fieldNombre, fieldDescripcion,tel);
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		//Camino feliz, lo guardo bien
		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Fenómeno ha sido modificado con éxito.",
					"Fenomeno Modificado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		
		//Error al guardarlo
		else{ 
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente más tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//Defino la accion del boton cancelar
	private void accionCancelar() {
		this.frame.dispose();

	}

}