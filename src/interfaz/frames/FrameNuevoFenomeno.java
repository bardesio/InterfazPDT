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

public class FrameNuevoFenomeno implements ActionListener {

	//Defino id global
	Long id =  null;
	
	//Defino frame global
	private JFrame frame;

	// Atributos de labels 
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	private JLabel labeltelefono;
	private JLabel labelestado;
	
	//Defino combo telefonos
	private JComboBox<String> comboTel;
	
	// Atributos de TexField 
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textEstado;
	
	//** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	//Defino lista de telefonos
	private List<Telefono> telefonos;
	
	public FrameNuevoFenomeno(JFrame framePadre) {
	
		//Asigno nombre a las labels
		this.labelCodigo = new JLabel("Código:"); 
		this.labelestado = new JLabel("Estado:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labeltelefono = new JLabel ("Teléfonos de Emergencia:");
		
		//Asigno tamaño a los textboxs
		 this.textCodigo=new JTextField(15);
		 this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		 this.textEstado = new JTextField(15);
		
		 //Defino el nombre y el icono del boton ingresar
		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/add.png")));
		buttonIngresar.addActionListener((ActionListener) this);

		 //Defino el nombre y el icono del boton cancelar
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener((ActionListener) this);

		//Asigno propiedades a los botones
		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {
		
		JFrame frame = new JFrame("Nuevo Fenómeno");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevaFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10,10,10,10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaFenomenoPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textCodigo, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevaFenomenoPanel.add(this.labelestado, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textEstado, constraints);
		this.textEstado.setText("ACTIVO");
		this.textEstado.setEditable(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaFenomenoPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textNombre, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		nuevaFenomenoPanel.add(this.textDescripcion, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevaFenomenoPanel.add(this.labeltelefono, constraints);
		
		
		
		
		
		constraints.gridx = 1;
		 this.comboTel = this.completarComboTelefono(frame);

		if (this.comboTel!=null) {
			nuevaFenomenoPanel.add(this.comboTel,constraints);
			
			constraints.gridx = 2;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaFenomenoPanel.add(buttonIngresar, constraints);
	
			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.WEST;
			nuevaFenomenoPanel.add(buttonCancelar, constraints);																																																																															
							
	
			nuevaFenomenoPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Fenómeno"));
	
			frame.getContentPane().add(nuevaFenomenoPanel);
																																																																												
			frame.pack();																																							
			frame.setVisible(true);																																												
	
			this.frame = frame;
		}else
		{
			JOptionPane.showMessageDialog(frame, "Error en el servidor, por favor contacte a soporte tecnico",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();}
		}
		
	//Completo combo de telefonos
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
	
	//Metodo para separar nombre de telefono
	public String separarNombreDeTelefono(String telEmergencia) {
		int i = 0;
		while(i<= telEmergencia.length() -1) {
			if (telEmergencia.charAt(i) =='-') {
				return telEmergencia.substring(0, i-1);
			}
		}
		return telEmergencia;
	}

	//Defino el boton pulsado
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngesar();
		}
		
	}
	
	//Defino la accion ingresar 
	private void accionIngesar() {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText().toUpperCase();
		String fieldDescripcion = this.textDescripcion.getText().toUpperCase();
		String fieldCodigo = this.textCodigo.getText().toUpperCase();
		String fieldEstado = "ACTIVO";
		
		String tels =(String) this.comboTel.getSelectedItem();
				
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("")|| fieldCodigo.equals("")){
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		//Valido maximo en los campos
		if (fieldCodigo.length() > 50 || fieldNombre.length() > 50 || fieldDescripcion.length() > 50)
		{
			JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en los campos", "Máximo superado!",
					JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		
			
		boolean almacenado;
		
		
		try{
			//Verifico que no exista el fenomeno
			List<Fenomeno> fn = ClientePDT.existecodigo(fieldCodigo);
			
			if(fn == null || fn.size() == 0)
			{
				try {
					
					//Intento crearlo
					almacenado= ClientePDT.ingresarnuevoFenomeno(fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
					
					
					//Quiere decir que Almacenado devolvio true
					if(almacenado) {
						JOptionPane.showMessageDialog(frame, "El fenómeno ha sido registrado con éxito.",
								"Fenómeno Registrado!", JOptionPane.INFORMATION_MESSAGE);
						// cerramos la ventanta
						this.frame.dispose();
		
					}
				
			
			}catch (Exception e){
				JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
						"Error de conexión!", JOptionPane.WARNING_MESSAGE);

				return;
			}
			
			//Existe pero esta inactivo
			}else if (!String.valueOf(fn.get(0).getEstado()).equals("ACTIVO"))
				{
				try {
					id = fn.get(0).getId();
					almacenado = ClientePDT.ModificarFenomeno(id,fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
					
					if(almacenado) {
						
						JOptionPane.showMessageDialog(frame, "El Fenómeno ha sido registrado con éxito.",
								"Fenómeno Registrado!", JOptionPane.INFORMATION_MESSAGE);
						
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
			//El fenomeno ya existe en el sistema y esta activo
			else {
				JOptionPane.showMessageDialog(null, "El fenómeno ya existe en el sistema");
				return;
				}
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error con el servidor, por favor contacte con su administrador",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
	}
		

	//Defino el boton cancelar
	private void accionCancelar() {
		this.frame.dispose();

	}

}