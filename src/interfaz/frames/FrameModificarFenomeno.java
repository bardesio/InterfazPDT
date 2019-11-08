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

import org.omg.CORBA.LongLongSeqHelper;

import com.Remote.FenomenoBeanRemote;
import com.entidades.Fenomeno;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;
import interfaz.locator.EJBLocator;

public class FrameModificarFenomeno implements ActionListener{

	
	
	//Probando
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	private JLabel labelCodigoFEN;
	
	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textCodigofen;

	/** Atributos de Botones */
	private JButton buttonModificar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;
	
	

	public FrameModificarFenomeno(JFrame framePadre) {

	
		this.labelCodigo = new JLabel("Codigo:"); 
		this.labelNombre = new JLabel("Nombre:");
		this.labelDescripcion = new JLabel("Descripcion:");
		
		 this.textCodigo=new JTextField(15);
		 this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		 this.textCodigofen = new JTextField(15);
		
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

		JFrame frame = new JFrame("Modificar Fenomeno");
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
		ModificarFenomenoPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textNombre, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		ModificarFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		ModificarFenomenoPanel.add(this.textDescripcion, constraints);

		
		
		
			constraints.gridx = 0;
			constraints.gridy = 5;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonModificar, constraints);
			this.buttonModificar.setEnabled(false);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonCancelar, constraints);
			

			constraints.gridx = 0;
			constraints.gridy = 6;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonBuscar, constraints);
			

			ModificarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(ModificarFenomenoPanel);

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
					
					FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
					String codigo = this.textCodigo.getText().toUpperCase();
					List <Fenomeno> fenomenos = fenomenobeanremote.existecodigo(codigo);
					if (fenomenos.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Fenomeno no encontrado");
						return;
					}else {
						for(Fenomeno CFen : fenomenos)
						{
																					
							String dato=CFen.getCodigo();
							textCodigo.setText(dato);
							String desc=CFen.getDescripcion();
							String nom=CFen.getNombreFen();
							textNombre.setText(nom);
							textDescripcion.setText(desc);
							buttonModificar.setEnabled(true);
							
							
							
							
						}
					}
					 
				}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
								"Error de conexión!", JOptionPane.WARNING_MESSAGE);
						return;
					}
	}			
				

	private void accionModificar() {

		// Si es ingresar se validan datos!
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldNombre= this.textNombre.getText();
		String fieldcodigo = this.textCodigo.getText();
		long fieldID = 1l;
		
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}


		boolean almacenado;
						
		try{
			
			//almacenado = fenomenobeanremote.modificarFenomeno(fieldcodigofen, fieldcodigo, fieldNombre, fieldDescripcion);			
			
			almacenado = ClientePDT.ModificarFenomeno(fieldID, fieldcodigo, fieldNombre, fieldDescripcion);
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El Usuario ha sido modificado con éxito.",
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