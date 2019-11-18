package interfaz.frames;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.entidades.Estado;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.Observacion;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;
import com.exception.ServiciosException;

import interfaz.locator.ClientePDT;
import interfaz.validaciones.ValidacionUsuario;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class FrameNuevaObservacion implements ActionListener {

	Long fieldID = null;

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelIdentificacion;
	private JLabel labelUsuario;
	private JLabel labelFenomeno;
	private JLabel labelLocalidad;
	private JLabel labelDescripcion;
	private JLabel labelImagen;
	private JLabel labelLatitud;
	private JLabel labelLongitud;
	private JLabel labelAltitud;
	private JLabel labelEstado;
	private JLabel labelFecha;

	private JComboBox<String> comboFenomenos;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboEstado;

	/** Date Picker */
	private JDatePickerImpl datePickerFecha;

	/** Atributos de TexField */
	private JTextField textIdentificacion;
	private JTextField textDescripcion;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JTextField textAltitud;
	private JTextField textDireccion;
	private JTextField textMail;
	private JTextField textUsuario;

	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;

	/** Lista de Tipos del sistema */
	private List<Fenomeno> fenomenos;
	private List<Localidad> localidades;
	private List<Estado> estados;

	public FrameNuevaObservacion(JFrame framePadre) {

		this.labelIdentificacion = new JLabel("Identificacion:");
		this.labelUsuario = new JLabel("Nombre de usuario:");
		this.labelFenomeno = new JLabel("Fenomeno:");
		this.labelEstado = new JLabel("Estado:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelDescripcion = new JLabel("Descripcion:");
		this.labelImagen = new JLabel("Imagen:");
		this.labelLatitud = new JLabel("Latitud:");
		this.labelAltitud = new JLabel("Altitud:");
		this.labelLongitud = new JLabel("Longitud:");
		this.labelEstado = new JLabel("Estado:");
		this.labelFecha = new JLabel("Fecha:");

		this.textAltitud = new JTextField(15);
		this.textDescripcion = new JTextField(15);
		this.textDireccion = new JTextField(15);
		this.textIdentificacion = new JTextField(15);
		this.textMail = new JTextField(15);
		this.textLatitud = new JTextField(15);
		this.textLongitud = new JTextField(15);
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

		JFrame frame = new JFrame("Nueva Observacion");
		frame.setSize(136, 133);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gbl_nuevaObservacionPanel = new GridBagLayout();
		gbl_nuevaObservacionPanel.rowWeights = new double[] {};
		gbl_nuevaObservacionPanel.columnWeights = new double[] {};
		JPanel nuevaObservacionPanel = new JPanel(gbl_nuevaObservacionPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaObservacionPanel.add(this.labelIdentificacion, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textIdentificacion, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevaObservacionPanel.add(this.labelUsuario, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textUsuario, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaObservacionPanel.add(this.labelFenomeno, constraints);
	
		constraints.gridx = 1;
		this.comboFenomenos = this.completarComboFenomeno(frame);
		nuevaObservacionPanel.add(this.comboFenomenos, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaObservacionPanel.add(this.labelLocalidad, constraints);

		constraints.gridx = 1;
		this.comboLocalidad = this.completarComboLocalidad(frame);
		nuevaObservacionPanel.add(this.comboLocalidad, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevaObservacionPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textDescripcion, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevaObservacionPanel.add(this.labelImagen, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevaObservacionPanel.add(this.labelLatitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLatitud, constraints);

		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevaObservacionPanel.add(this.labelLongitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLongitud, constraints);

		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevaObservacionPanel.add(this.labelAltitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textAltitud, constraints);

		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevaObservacionPanel.add(this.labelEstado, constraints);

		constraints.gridx = 1;
		this.comboEstado = this.completarComboEstado(frame);
		nuevaObservacionPanel.add(this.comboEstado, constraints);
		this.comboEstado.setEnabled(false);


		constraints.gridx = 0;
		constraints.gridy = 10;
		nuevaObservacionPanel.add(this.labelFecha, constraints);

		
			constraints.gridx = 0;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonIngresar, constraints);

			constraints.gridx = 1;
			constraints.gridy = 10;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonCancelar, constraints);

			nuevaObservacionPanel.setBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos de la Observacion"));

			frame.getContentPane().add(nuevaObservacionPanel);

			frame.pack();
			frame.setVisible(true);

			this.frame = frame;

	}

	private JComboBox<String> completarComboFenomeno(JFrame frame) {

		try {
			this.fenomenos = ClientePDT.obtenerTodosFenomenos();
		} catch (Exception e) {
			return null;
		}

		JComboBox<String> combo = new JComboBox<>();

		for (Fenomeno fen : this.fenomenos) {
			combo.addItem(fen.getNombreFen());

		}

		return combo;
	}

	private JComboBox<String> completarComboLocalidad(JFrame frame) {

		try {
			this.localidades = ClientePDT.obtenerTodasLocalidades();
		} catch (Exception e) {
			return null;
		}

		JComboBox<String> combo = new JComboBox<>();

		for (Localidad loc : this.localidades) {
			combo.addItem(loc.getNombreLoc());
		}

		return combo;
	}

	private JComboBox<String> completarComboEstado(JFrame frame) {

		try {
			this.estados = ClientePDT.obtenerTodosEstados();
		} catch (Exception e) {
			return null;
		}

		JComboBox<String> combo = new JComboBox<>();

		for (Estado est : this.estados) {
			combo.addItem(est.getNombre());
		}

		return combo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que bot�n fue clickeado */

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

		String fieldIdentificacion = this.textIdentificacion.getText().toUpperCase();
		String fieldUsuario = this.textUsuario.getText().toUpperCase();
		String fieldFenomeno = (String) this.comboFenomenos.getSelectedItem();
		String fieldEstado = (String) this.comboEstado.getSelectedItem();
		String fieldLocalidad = (String) this.comboLocalidad.getSelectedItem();
		String fieldDescripcion = this.textDescripcion.getText();
		Blob fieldImagen = null;
		float fieldLatitud = Float.parseFloat(this.textLatitud.getText());
		float fieldLongitud = Float.parseFloat(this.textLongitud.getText());
		float fieldAltitud = Float.parseFloat(this.textAltitud.getText());
		Date fieldFecha = (Date) this.datePickerFecha.getModel().getValue();

		// Validacion para datos vacios
		if (fieldIdentificacion.equals("") || fieldDescripcion.equals("")) {

			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Si estamos aqu�,..quiere decir que no hay errores. Almacenamos el
		// Usuario y volvemos al menu
		boolean almacenado;

		try {

			List<Observacion> observaciones = ClientePDT.existeObservacion(fieldIdentificacion);

			// Si la lista es de tama�o 0
			if (observaciones == null || observaciones.size() == 0) {
				// Intento crear la observacion
				try {

					almacenado = ClientePDT.CrearObservacion(fieldIdentificacion, fieldUsuario, fieldFenomeno,
							fieldLocalidad, fieldDescripcion, fieldImagen, fieldLatitud, fieldLongitud, fieldAltitud,
							fieldEstado, fieldFecha);

					// Si se devolvio verdadero el almacenado
					if (almacenado) {
						JOptionPane.showMessageDialog(frame, "La observacion ha sido registrado con �xito.",
								"Usuario Registrado!", JOptionPane.INFORMATION_MESSAGE);

						this.frame.dispose();

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
							"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

					return;
				}

				// La observacion existe pero esta inactiva
			} else if (!String.valueOf(observaciones.get(0).getEstado()).equals("ACTIVO")) {
				try {
					fieldID = observaciones.get(0).getId();
					almacenado = ClientePDT.ModificarObservacion(fieldID, fieldUsuario, fieldFenomeno, fieldLocalidad,
							fieldDescripcion, fieldImagen, fieldLatitud, fieldLongitud, fieldAltitud, fieldEstado,
							fieldFecha);

					if (almacenado) {
						JOptionPane.showMessageDialog(frame,
								"Se ha registrado la observaci�n ha sido registrado con �xito.", "Usuario Registrado!",
								JOptionPane.INFORMATION_MESSAGE);

						// cerramos la ventanta
						this.frame.dispose();

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
							"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

					return;
				}
			}

			// La observaci�n ya existe en el sistema
			else {
				JOptionPane.showMessageDialog(null, "La observaci�n ya existe en el sistema");
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error con el servidor, por favor contacte con su administrador",
					"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);

			return;
		}
	}

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

}