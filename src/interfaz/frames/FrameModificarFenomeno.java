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
import com.entidades.Telefono;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;
import interfaz.locator.EJBLocator;

public class FrameModificarFenomeno implements ActionListener{

	
	
	//Probando
	private JFrame frame;

	Long fieldID = null;
	/** Atributos de labels */
	private JLabel labelCodigo;
	private JLabel labelNombre;
	private JLabel labelDescripcion;
	private JLabel labeltelefono;
	
	private JComboBox<String> comboTel;
	
	
	
	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textCodigofen;

	/** Atributos de Botones */
	private JButton buttonModificar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;
	
	private List<Telefono> telefonos;

	public FrameModificarFenomeno(JFrame framePadre) {

	
		this.labelCodigo = new JLabel("Codigo:"); 
		this.labelNombre = new JLabel("Nombre:");
		this.labelDescripcion = new JLabel("Descripcion:");
		this.labeltelefono = new JLabel ("Telefonos de Emergencia:");
		
		 this.textCodigo=new JTextField(15);
		 this.textNombre= new JTextField(15);
		 this.textDescripcion = new JTextField(15);
		 
		
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
		constraints.gridy = 3;
		ModificarFenomenoPanel.add(this.labeltelefono, constraints);
		
		constraints.gridx = 1;
		 this.comboTel = this.completarComboTelefono(frame);

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
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonCancelar, constraints);
			

			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			ModificarFenomenoPanel.add(buttonBuscar, constraints);
			

			ModificarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del Usuario"));

		frame.getContentPane().add(ModificarFenomenoPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}else
	{
		JOptionPane.showMessageDialog(frame, "Error en el servidor, por favor contacte a soporte tecnico",
				"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);
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
							this.textDescripcion.setText(desc);
							String nom=CFen.getNombreFen();
							textNombre.setText(nom);
							comboTel.setEnabled(false);
							String nombretel = CFen.getTelefonos().getNombre();
							this.comboTel.setToolTipText(nombretel);
							this.comboTel.setEnabled(true);							
							this.fieldID = CFen.getId();
							buttonModificar.setEnabled(true);
							
							
							
							
							
						}
					}
					 
				}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
								"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);
						return;
					}
	}			
				

	private void accionModificar() {

		// Si es ingresar se validan datos!
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldNombre= this.textNombre.getText();
		String fieldcodigo = this.textCodigo.getText();
		String tel = (String) this.comboTel.getSelectedItem();
		
		
		// Si alguno es vac�o, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}


		boolean almacenado;
						
		try{
			
			//almacenado = fenomenobeanremote.modificarFenomeno(fieldcodigofen, fieldcodigo, fieldNombre, fieldDescripcion);			
			
			almacenado = ClientePDT.ModificarFenomeno(fieldID, fieldcodigo, fieldNombre, fieldDescripcion,tel);
			
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