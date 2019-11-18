package interfaz.frames;

import java.awt.Component;
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

import org.jgroups.protocols.TUNNEL;

import com.entidades.Fenomeno;
import com.entidades.Telefono;
import com.entidades.TipoUsuario;

import interfaz.constantes.Constantes;
import interfaz.locator.ClientePDT;

public class FrameNuevoFenomeno implements ActionListener {

	Long id =  null;
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	private JLabel labeltelefono;
	private JLabel labelestado;
	
	private JComboBox<String> comboTel;
	
	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textEstado;
	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	
	private List<Telefono> telefonos;
	
	public FrameNuevoFenomeno(JFrame framePadre) {

	
		
		this.labelCodigo = new JLabel("Codigo:"); 
		this.labelestado = new JLabel("Estado:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelDescripcion = new JLabel("Descripcion:");
		this.labeltelefono = new JLabel ("Telefonos de Emergencia:");
		
		 this.textCodigo=new JTextField(15);
		 this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		 this.textEstado = new JTextField(15);
		
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
			
	
			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.CENTER;
			nuevaFenomenoPanel.add(buttonIngresar, constraints);
	
			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.CENTER;
			nuevaFenomenoPanel.add(buttonCancelar, constraints);																																																																															
							
	
			nuevaFenomenoPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Fenomeno"));
	
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
	
	
	public String separarNombreDeTelefono(String telEmergencia) {
		int i = 0;
		while(i<= telEmergencia.length() -1) {
			if (telEmergencia.charAt(i) =='-') {
				return telEmergencia.substring(0, i-1);
			}
		}
		return telEmergencia;
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

		String fieldNombre = this.textNombre.getText().toUpperCase();
		String fieldDescripcion = this.textDescripcion.getText().toUpperCase();
		String fieldCodigo = this.textCodigo.getText().toUpperCase();
		String fieldEstado = "ACTIVO";
		
		String tels =(String) this.comboTel.getSelectedItem();
				
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("")|| fieldCodigo.equals("")|| fieldEstado.equals("")){
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
			
		boolean almacenado;
		
		
		try{
			List<Fenomeno> fn = ClientePDT.existecodigo(fieldCodigo);
			
			
			if(fn == null || fn.size() == 0)
			{
				try {
					
					almacenado= ClientePDT.ingresarnuevoFenomeno(fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
					
					
					//Quiere decir que Almacenado devolvio true
					if(almacenado) {
						JOptionPane.showMessageDialog(frame, "El Fenomeno ha sido registrado con éxito.",
								"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);
						// cerramos la ventanta
						this.frame.dispose();
		
					}
				
			
			}catch (Exception e){
				JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
						"Error de conexión!", JOptionPane.WARNING_MESSAGE);

				return;
			}
			}else if (!String.valueOf(fn.get(0).getEstado()).equals("ACTIVO"))
				{
				try {
					id = fn.get(0).getId();
					almacenado = ClientePDT.ModificarFenomeno(id,fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
					
					if(almacenado) {
						
						JOptionPane.showMessageDialog(frame, "El Fenomeno ha sido registrado con éxito.",
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
				JOptionPane.showMessageDialog(null, "El fenomeno ya existe en el sistema");
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