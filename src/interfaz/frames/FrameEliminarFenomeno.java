package interfaz.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.entidades.Fenomeno;
import com.entidades.Telefono;
import interfaz.locator.ClientePDT;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameEliminarFenomeno implements ActionListener {
		
		//Creo un frame global 
		private JFrame frame;
		
		//Creo un ID global
		Long fieldID = null;
		
		//Atributos de labels
		private JLabel labelCodigo;
		private JLabel labelNombre;
		private JLabel labelDescripcion;
		private JLabel labeltelefono;
		private JLabel labelestado;
		
		//Combobox de los telefonos
		private JComboBox<String> comboTel;
		
		
		
		//Atributos de TexField
		private JTextField textNombre;
		private JTextField textDescripcion;
		private JTextField textCodigo;
		private JTextField textEstado;

		//Atributos de Botones
		private JButton buttonEliminar;
		private JButton buttonCancelar;
		private JButton buttonBuscar;
		
		//Lista de telefonos
		private List<Telefono> telefonos;

		public FrameEliminarFenomeno(JFrame framePadre) {

			//Le pongo nombre a las labels
			this.labelCodigo = new JLabel("Código:"); 
			this.labelNombre = new JLabel("Nombre:");
			this.labelestado = new JLabel("Estado:");
			this.labelDescripcion = new JLabel("Descripción:");
			this.labeltelefono = new JLabel ("Teléfonos de Emergencia:");
			
			//Le asigno el tamaño a los textboxs
			 this.textCodigo=new JTextField(15);
			 this.textNombre= new JTextField(15);
			 this.textDescripcion = new JTextField(15);
			 this.textEstado = new JTextField(15);
			
			 //Nombre e icono del boton eliminar
			JButton buttonEliminar = new JButton("Eliminar");
			buttonEliminar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/delete.gif")));
			buttonEliminar.addActionListener(this);

			//Nombre e icono del boton Cancelar
			JButton buttonCancelar = new JButton("Cancelar");
			buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
			buttonCancelar.addActionListener(this);


			//Nombre e icono del boton Buscar
			JButton buttonBuscar = new JButton ("Buscar");
			buttonBuscar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/buscar.png")));
			buttonBuscar.addActionListener(this);
			
			//Asigno los botones con las propiedades
			this.buttonEliminar = buttonEliminar ;
			this.buttonCancelar = buttonCancelar;
			this.buttonBuscar = buttonBuscar;

			this.initalizeFrame(framePadre);
		}
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Eliminar Fenómeno");
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
		eliminarFenomenoPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textCodigo, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		eliminarFenomenoPanel.add(this.labelestado, constraints);

		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textEstado, constraints);
		this.textEstado.setText("ACTIVO");
		this.textEstado.setEditable(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		eliminarFenomenoPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textNombre, constraints);
		this.textNombre.setEditable(false);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		eliminarFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textDescripcion, constraints);
		this.textDescripcion.setEditable(false);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		eliminarFenomenoPanel.add(this.labeltelefono, constraints);
		
		
		constraints.gridx = 1;
		 this.comboTel = this.completarComboTelefono(frame);
		 this.comboTel.setEditable(false);
		 
		
			if (this.comboTel!=null) {
				eliminarFenomenoPanel.add(this.comboTel,constraints);
				comboTel.setEnabled(false);
			
			
				constraints.gridx = 2;
				constraints.gridy = 10;
				constraints.gridwidth = 3;
				constraints.anchor = GridBagConstraints.SOUTH;
				eliminarFenomenoPanel.add(buttonEliminar, constraints);
				this.buttonEliminar.setEnabled(false);

				constraints.gridx = 1;
				constraints.gridy = 10;
				constraints.gridwidth = 4;
				constraints.anchor = GridBagConstraints.WEST;
				eliminarFenomenoPanel.add(buttonCancelar, constraints);
				

				this.buttonBuscar.setBounds(60,60,89,23);
				eliminarFenomenoPanel.add(buttonBuscar);
				

				eliminarFenomenoPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Fenómeno"));

			frame.getContentPane().add(eliminarFenomenoPanel);

			frame.pack();
			frame.setVisible(true);

			this.frame = frame;

		}else
		{
			JOptionPane.showMessageDialog(frame, "Error en el servidor, por favor contacte a soporte tecnico",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();}
			
		}
	
		//Llenar el combo de telefonos
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
		 
		 
	//Realizo determinada acción a partir del boton que fue clickeado 
	@Override
	public void actionPerformed(ActionEvent e) {
			 	
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
				
				try{
					
					//Valido maximo en el campo codigo
					if (this.textCodigo.getText().length() > 50)
					{
						JOptionPane.showMessageDialog(frame, "No puede ingresar mas de 50 caracteres en el campo código", "Máximo superado!",
								JOptionPane.WARNING_MESSAGE);
						
						return;
					}
					
					//Valido que exista y me traigo los datos
					String codigo = this.textCodigo.getText().toUpperCase();
					List <Fenomeno> fenomenos= ClientePDT.existecodigo(codigo);
					
					//No existe el fenomeno que me ingresaron
					if (fenomenos.isEmpty())
					{
						JOptionPane.showMessageDialog(frame, "El código del fenómeno ingresado no existe.",
								"Fenómeno Inexistente!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					//Existe el fenomeno, muestro los datos como solo lectura
					else 
					{
						for(Fenomeno CFen : fenomenos)
						{
																					
							String dato=CFen.getCodigo();
							textCodigo.setText(dato);
							textCodigo.setEnabled(false);
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
							buttonEliminar.setEnabled(true);
							buttonBuscar.setEnabled(false);
							
						}
					}
					 
				}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
								"Error de conexión!", JOptionPane.WARNING_MESSAGE);
						return;
					}
	}			
	 
	public void accionEliminar(){
		
		String fieldDescripcion = this.textDescripcion.getText().toUpperCase();
		String fieldNombre= this.textNombre.getText().toUpperCase();
		String fieldcodigo = this.textCodigo.getText().toUpperCase();
		String tel = (String) this.comboTel.getSelectedItem();
		String Estado = "INACTIVO";
		
		
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldcodigo.equals("")){
			JOptionPane.showMessageDialog(frame, "Debe ingresar el nombre de usuario", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}


		boolean almaceno;
		
		try {
			List<Fenomeno> us = ClientePDT.existecodigo(fieldcodigo);
			
			if(us==null || us.size()==0)
			{
				JOptionPane.showMessageDialog(null, "Fenómeno no encontrado");
				return;
			}
			else {
				almaceno= ClientePDT.ModificarFenomeno(fieldID, fieldcodigo, Estado, fieldNombre, fieldDescripcion, tel);
			}
				
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		
	}
		if (almaceno) {
			JOptionPane.showMessageDialog(frame, "El fenómeno ha sido eliminado con éxito.",
					"Fenómeno Eliminado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al eliminar. Intente nuevamente más tarde",
					"Error al eliminar!", JOptionPane.ERROR_MESSAGE);
		}
	}private void accionCancelar() {
		this.frame.dispose();
	}

	}
	
	
