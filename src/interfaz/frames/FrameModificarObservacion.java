package interfaz.frames;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.entidades.Estado;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.Observacion;
import com.entidades.Usuario;
import com.exception.ServiciosException;

import interfaz.locator.ClientePDT;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameModificarObservacion implements ActionListener {

	
	// Validamos ahora, que exista una Observacion son dicha ID
	List<Observacion> observaciones;
	
	Long fieldID = null;
	
	String camino = "";
	
	File archivoImagen = null;

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
	private JLabel labelfoto;

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
	private JTextField textUsuario;

	/** Atributos de Botones */
	private JButton buttonModificar;
	private JButton buttonCancelar;
	private JButton buttonBuscar;
	private JButton buttonSeleccionar;

	
	/** Lista de Tipos del sistema */
	private List<Fenomeno> fenomenos;
	private List<Localidad> localidades;
	private List<Estado> estados;

	List<Usuario> listaUsuarios = null;
	
	public FrameModificarObservacion(JFrame framePadre, List<Usuario> listUsuarios) {
	

		listaUsuarios = listUsuarios;
		
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
		this.labelfoto = new JLabel("");

		this.textAltitud = new JTextField(15);
		this.textDescripcion = new JTextField(15);
		this.textIdentificacion = new JTextField(15);
		this.textLatitud = new JTextField(15);
		this.textLongitud = new JTextField(15);
		this.textUsuario = new JTextField(15);
		
		JButton buttonModificar = new JButton("Modificar");
		buttonModificar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/Update.png")));
		buttonModificar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);
		
		JButton buttonBuscar = new JButton("Buscar");
		buttonBuscar.addActionListener(this);
		
		JButton buttonSeleccionar = new JButton("Seleccionar Imagen");
		buttonSeleccionar.addActionListener(this);
		
		this.buttonModificar = buttonModificar;
		this.buttonCancelar = buttonCancelar;
		this.buttonBuscar = buttonBuscar;
		this.buttonSeleccionar = buttonSeleccionar;
		
		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Modificar Observacion");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GridBagLayout gbl_nuevaObservacionPanel = new GridBagLayout();
		gbl_nuevaObservacionPanel.rowWeights = new double[] {};
		gbl_nuevaObservacionPanel.columnWeights = new double[] {};
		JPanel nuevaObservacionPanel = new JPanel(gbl_nuevaObservacionPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 30);
		
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
		this.textUsuario.setText(this.listaUsuarios.get(0).getUsuario());
		this.textUsuario.setEditable(false);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaObservacionPanel.add(this.labelFenomeno, constraints);
	
		constraints.gridx = 1;
		this.comboFenomenos = this.completarComboFenomeno(frame);
		nuevaObservacionPanel.add(this.comboFenomenos, constraints);
		this.comboFenomenos.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaObservacionPanel.add(this.labelLocalidad, constraints);

		constraints.gridx = 1;
		this.comboLocalidad = this.completarComboLocalidad(frame);
		nuevaObservacionPanel.add(this.comboLocalidad, constraints);
		this.comboLocalidad.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevaObservacionPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textDescripcion, constraints);
		this.textDescripcion.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevaObservacionPanel.add(this.labelImagen, constraints);

		constraints.gridx = 1;
		constraints.gridwidth = 3;
		nuevaObservacionPanel.add(buttonSeleccionar, constraints);
		this.buttonSeleccionar.setEnabled(false);
		
		constraints.gridx = 2;
		nuevaObservacionPanel.add(this.labelfoto, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevaObservacionPanel.add(this.labelLatitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLatitud, constraints);
		this.textLatitud.setEnabled(false);
		
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevaObservacionPanel.add(this.labelLongitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLongitud, constraints);
		this.textLongitud.setEnabled(false);
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevaObservacionPanel.add(this.labelAltitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textAltitud, constraints);
		this.textAltitud.setEnabled(false);
		
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
		
		
		constraints.gridx = 1;
		this.datePickerFecha =this.crearDatePicker();
		nuevaObservacionPanel.add(datePickerFecha,constraints);
		this.datePickerFecha.setTextEditable(false);
		
			constraints.gridx = 1;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonModificar, constraints);
			this.buttonModificar.setEnabled(false);


			constraints.gridx = 2;
			constraints.gridy = 12;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonCancelar, constraints);
			
			constraints.gridx = -1;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonBuscar, constraints);

			
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
			combo.addItem(fen.getCodigo());

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
		else if (e.getSource()==this.buttonSeleccionar)
		{
			this.seleccionarImagen();
		}
	}

	private void accionBuscar() {
	
		String fieldIdentificacion = this.textIdentificacion.getText().toUpperCase();
				
		try{
			observaciones = ClientePDT.existeObservacion(fieldIdentificacion);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		if (observaciones==null || observaciones.size() == 0 || observaciones.get(0).getEstado().getNombre().equals("INACTIVO")) {
			JOptionPane.showMessageDialog(frame, "La identificacion ingresada no existe.",
					"Usuario Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		else {
			
			//Habilito los campos
			
			this.buttonModificar.setEnabled(true);
			this.textDescripcion.setEnabled(true);
			this.textAltitud.setEnabled(true);
			this.textLongitud.setEnabled(true);
			this.textLatitud.setEnabled(true);
			this.comboLocalidad.setEnabled(true);
			this.buttonSeleccionar.setEnabled(true);
			
			//Deshabilito campos
			this.buttonBuscar.setEnabled(false);
			this.textIdentificacion.setEnabled(false);
			
			
			this.textAltitud.setText(Float.toString(observaciones.get(0).getAltitud()));
			this.textDescripcion.setText(observaciones.get(0).getDescripcion());
			this.textIdentificacion.setText(observaciones.get(0).getCodigo_OBS());
			this.textLatitud.setText(Float.toString(observaciones.get(0).getLatitud()));
			this.textLongitud.setText(Float.toString(observaciones.get(0).getLongitud()));
			this.textUsuario.setText(observaciones.get(0).getUsuario().getUsuario());
			this.comboEstado.setSelectedItem(observaciones.get(0).getEstado());
			this.comboFenomenos.setSelectedItem(observaciones.get(0).getFenomeno());
			this.comboLocalidad.setSelectedItem(observaciones.get(0).getLocalidad());
			this.fieldID = observaciones.get(0).getId();
			
			//Cargo la fecha actual
			String dateString = observaciones.get(0).getFecha().toString();
			dateString = dateString.split(" ")[0];
			String[] yyyymmdd = dateString.split("-");
			
			this.datePickerFecha.getModel().setDay(Integer.parseInt(yyyymmdd[2]));
			this.datePickerFecha.getModel().setMonth(Integer.parseInt(yyyymmdd[1])-1);
			this.datePickerFecha.getModel().setYear(Integer.parseInt(yyyymmdd[0]));
			
			 
			BufferedImage img = null;
			
			if(!(observaciones.get(0).getImagen()==null)) {
				byte[] imagenobtenida = observaciones.get(0).getImagen();
				ByteArrayInputStream bails = new ByteArrayInputStream(imagenobtenida);
				try {
					img = ImageIO.read(bails);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				ImageIcon imagen = new ImageIcon(img.getScaledInstance(15,15 , Image.SCALE_SMOOTH));
				this.labelfoto.setIcon(imagen);
			}
			
		}
	}

	private JDatePickerImpl crearDatePicker() {

		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	private void accionModificar() throws ServiciosException {

		// Si es Modificar se validan datos!

		String fieldIdentificacion = this.textIdentificacion.getText().toUpperCase();
		String fieldUsuario = listaUsuarios.get(0).getUsuario().toString();
		String fieldFenomeno = (String) this.comboFenomenos.getSelectedItem();
		String fieldEstado = (String) this.comboEstado.getSelectedItem();
		String fieldLocalidad = (String) this.comboLocalidad.getSelectedItem();
		String fieldDescripcion = this.textDescripcion.getText();
		float fieldLatitud = Float.parseFloat(this.textLatitud.getText());
		float fieldLongitud = Float.parseFloat(this.textLongitud.getText());
		float fieldAltitud = Float.parseFloat(this.textAltitud.getText());
		Date fieldFecha = (Date) this.datePickerFecha.getModel().getValue();
		byte[] imagen = null;
		
		
		
		// Validacion para datos vacios
		if (fieldIdentificacion.equals("") || fieldDescripcion.equals("")) {

			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		if (!camino.toString().equals("")) {
			try {	
				imagen = Files.readAllBytes(archivoImagen.toPath());
			}catch(Exception e){
				JOptionPane.showMessageDialog(frame, "Error al leer la imagen, cargue nuevamente el archivo", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
			
		
		
		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// Usuario y volvemos al menu
		boolean almacenado;

		try {
			
				if (camino == "")
				{
					imagen = observaciones.get(0).getImagen();
				}
				almacenado = ClientePDT.ModificarObservacion(fieldID ,fieldIdentificacion,fieldUsuario, fieldFenomeno,  
							fieldLocalidad, fieldDescripcion, imagen, fieldLatitud, fieldLongitud, fieldAltitud,
							fieldEstado, fieldFecha);

					// Si se devolvio verdadero el almacenado
					if (almacenado) {
						JOptionPane.showMessageDialog(frame, "La observacion ha sido modificada con éxito.",
								"Observacion Modificada!", JOptionPane.INFORMATION_MESSAGE);

						this.frame.dispose();

					}
					
					else {
						JOptionPane.showMessageDialog(frame, "No se pudo modificar la observacion",
								"Error de conexión!", JOptionPane.WARNING_MESSAGE);

						return;
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);

					return;
				} 

	}

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
	
	private void seleccionarImagen() {
		
		//Creamos el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		
		//Indicamos lo que podemos seleccionar
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//Creamos el filtro
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gir");
		fc.setFileFilter(filtro);
		
		//Abrimos la ventana guardamos la opcion seleccionada por el usuario
		int seleccion = fc.showOpenDialog(frame.getContentPane());
		
		//Si el usuario picha en aceptar 
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			
			//Seleccionamos el fichero
			archivoImagen = fc.getSelectedFile();
			
			
			//Escribe la ruta del fichero seleccionado en el campo de texto
			camino = archivoImagen.getAbsolutePath();
			
			//Cargar imagen en JLabel
			try {
				BufferedImage img = ImageIO.read(archivoImagen);
				ImageIcon imagen = new ImageIcon(img.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
						labelfoto.setIcon(imagen);
			}catch(Exception e){
				JOptionPane.showMessageDialog(frame, "Error al cargar el archivo","Error", JOptionPane.WARNING_MESSAGE);
				
			}
			
		}
		
	}
	
}

