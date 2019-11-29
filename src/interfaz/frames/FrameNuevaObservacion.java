package interfaz.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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

public class FrameNuevaObservacion implements ActionListener {

	//Campo global id
	Long fieldID = null;
	
	//Campo global para la ruta de la imagen
	String camino = "";
	
	//campo global para el archivo de la imagen
	File archivoImagen = null;

	// Frame de la ventana 
	private JFrame frame;

	// Atributos de labels 
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

	//Atributos para los combos
	private JComboBox<String> comboFenomenos;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboEstado;

	//Creo el Date Picker 
	private JDatePickerImpl datePickerFecha;

	// Atributos de TexField 
	private JTextField textIdentificacion;
	private JTextField textDescripcion;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JTextField textAltitud;
	private JTextField textUsuario;

	// Atributos de Botones 
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JButton buttonSeleccionar;
	
	
	
	// Lista de Tipos del sistema 
	private List<Fenomeno> fenomenos;
	private List<Localidad> localidades;
	private List<Estado> estados;

	//Lista global de usuarios
	List<Usuario> listaUsuarios = null;
	
	public FrameNuevaObservacion(JFrame framePadre, List<Usuario> listUsuarios) {
	
		//Me guardo el usuario actual
		listaUsuarios = listUsuarios;
		
		//Asigno los nombres de las labels
		this.labelIdentificacion = new JLabel("Identificación:");
		this.labelUsuario = new JLabel("Nombre de usuario:");
		this.labelFenomeno = new JLabel("Fenómeno:");
		this.labelEstado = new JLabel("Estado:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labelImagen = new JLabel("Imagen:");
		this.labelLatitud = new JLabel("Latitud:");
		this.labelAltitud = new JLabel("Altitud:");
		this.labelLongitud = new JLabel("Longitud:");
		this.labelEstado = new JLabel("Estado:");
		this.labelFecha = new JLabel("Fecha:");
		this.labelfoto = new JLabel("");

		//Asigno el tamaño de los textboxs
		this.textAltitud = new JTextField(15);
		this.textDescripcion = new JTextField(15);
		this.textIdentificacion = new JTextField(15);
		this.textLatitud = new JTextField(15);
		this.textLongitud = new JTextField(15);
		this.textUsuario = new JTextField(15);
		
		//Defino el nombre y la ruta del boton ingresar
		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/add.png")));
		buttonIngresar.addActionListener(this);

		//Defino el nombre y la ruta del boton cancelar
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
		buttonCancelar.addActionListener(this);
		
		//Defino el nombre y la ruta del boton seleccionar imagen
		JButton buttonSeleccionar = new JButton("Selecionar Imagen");
		buttonSeleccionar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/attach.png")));
		buttonSeleccionar.addActionListener(this);
		
		//Guardo las propiedades de los botones
		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;
		this.buttonSeleccionar = buttonSeleccionar;
		
		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nueva Observación");
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

		constraints.gridx = 1;
		constraints.gridwidth = 3;
		nuevaObservacionPanel.add(buttonSeleccionar, constraints);
		
		constraints.gridx = 2;
		nuevaObservacionPanel.add(this.labelfoto, constraints);

		
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
		
		
		constraints.gridx = 1;
		this.datePickerFecha =this.crearDatePicker();
		nuevaObservacionPanel.add(datePickerFecha,constraints);
		
			constraints.gridx = 1;
			constraints.gridy = 12;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonIngresar, constraints);

			constraints.gridx = 2;
			constraints.gridy = 12;
			constraints.gridwidth = 4;
			constraints.anchor = GridBagConstraints.SOUTH;
			nuevaObservacionPanel.add(buttonCancelar, constraints);

			nuevaObservacionPanel.setBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos de la Observación"));

			frame.getContentPane().add(nuevaObservacionPanel);

			frame.pack();
			frame.setVisible(true);

			this.frame = frame;
			
	}

	//Completo el combo fenomenos
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

	//Completo el combo localidad
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

	//Completar combo estado
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

	//Defino la accion del boton pulsado
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else if(e.getSource() == this.buttonIngresar){
				try {
					this.accionIngesar();
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
		}
			else if(e.getSource() == this.buttonSeleccionar){
				
				this.accionSeleccionar();
				
		}

	}
	
	//llamo al jfile chooser
	private void accionSeleccionar() {
		
		this.seleccionarImagen();
		
	}

	//Creo y date picker
	private JDatePickerImpl crearDatePicker() {
		
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	//Defino la accion del boton ingresar
	private void accionIngesar() throws ServiciosException {

		//Guardo los campos en variables
		String fieldIdentificacion = this.textIdentificacion.getText().toUpperCase();
		String fieldUsuario = listaUsuarios.get(0).getUsuario().toString();
		String fieldFenomeno = (String) this.comboFenomenos.getSelectedItem();
		String fieldEstado = (String) this.comboEstado.getSelectedItem();
		String fieldLocalidad = (String) this.comboLocalidad.getSelectedItem();
		String fieldDescripcion = this.textDescripcion.getText();
		float fieldLatitud = 0;
		float fieldLongitud = 0;
		float fieldAltitud = 0;
		Date fieldFecha = (Date) this.datePickerFecha.getModel().getValue();
		byte[] imagen = null;
		
		
		
		// Validacion para datos vacios
		if (fieldIdentificacion.equals("") || fieldDescripcion.equals("")) {

			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		
		try {	
			//Trato de parsear las coordenadas
			 fieldLatitud = Float.parseFloat(this.textLatitud.getText());
			 fieldLongitud = Float.parseFloat(this.textLongitud.getText());
			 fieldAltitud = Float.parseFloat(this.textAltitud.getText());		
		
		}catch(Exception e){
			//Si entra aca es porque tiene formato invalido
			JOptionPane.showMessageDialog(frame, "Las coordenadas ingresadas no tienen el formato correcto", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		try {
			//Obtengo la localidad, y en base a eso determino la zona
			Localidad localidad = ClientePDT.obtenerLocalidad(fieldLocalidad);
			
			if(localidad.getDepartamento().getZona().getNombre_zona().equals("NORTE"))
			{
				if (fieldLatitud > 30.085556) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona norte como máximo se puede ingresar: -30.085556 de latitud°", "Latitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

						
				if (fieldLongitud > 56.951667) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona norte como máximo se puede ingresar: -56.951667° de longitud", "Longitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

			}
			
			else if(localidad.getDepartamento().getZona().getNombre_zona().equals("SUR"))
			{
				if (fieldLatitud > 35.024444) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona sur como máximo se puede ingresar: -35.024444° de latitud", "Latitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

						
				if (fieldLongitud > 54.883056) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona sur como máximo se puede ingresar: -54.883056° de longitud", "Longitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			
			else if(localidad.getDepartamento().getZona().getNombre_zona().equals("ESTE"))
			{
				if (fieldLatitud > 32.653889) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona este como máximo se puede ingresar: -32.653889° de latitud", "Latitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

						
				if (fieldLongitud > 53.182778) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona este como máximo se puede ingresar: -53.182778 de longitud°", "Longitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			
			else if(localidad.getDepartamento().getZona().getNombre_zona().equals("OESTE"))
			{
				if (fieldLatitud > 33.525) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona este como máximo se puede ingresar: -33.525 de latitud°", "Latitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

						
				if (fieldLongitud > 58.433611) {

					JOptionPane.showMessageDialog(frame, "Para una localidad de zona este como máximo se puede ingresar: -58.433611° de longitud", "Longitud invalida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error al traer la localidad", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//Defino la altitud maxima permitida		
		if (fieldAltitud > 514) {

			JOptionPane.showMessageDialog(frame, "La altitud ingresada no es valida, el punto más alto de Uruguay es el es el cerro Catedral con 514msnm", "Altitud invalida!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		//Verifico que la ruta a la imagen no sea vacia
		if (!(camino.toString().equals(""))) {
		try {	
			imagen = Files.readAllBytes(archivoImagen.toPath());
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Error al leer la imagen, cargue nuevamente el archivo", "Error", JOptionPane.WARNING_MESSAGE);
		}
		}
		
		
		// Si estamos aquí,..quiere decir que no hay errores.
		boolean almacenado;

		try {
			//Verifico que exista la observacion
			List<Observacion> observaciones = ClientePDT.existeObservacion(fieldIdentificacion);

			// Si la lista es de tamaño 0
			if (observaciones == null || observaciones.size() == 0) {
				try {
					// Intento crear la observacion
					almacenado = ClientePDT.CrearObservacion(fieldIdentificacion, fieldUsuario, fieldFenomeno,
							fieldLocalidad, fieldDescripcion, imagen, fieldLatitud, fieldLongitud, fieldAltitud,
							fieldEstado, fieldFecha);

					// Si se devolvio verdadero el almacenado
					if (almacenado) {
						JOptionPane.showMessageDialog(frame, "La observación ha sido registrado con éxito.",
								"Observación Registrada!", JOptionPane.INFORMATION_MESSAGE);

						this.frame.dispose();

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);

					return;
				}

				// La observacion existe pero esta inactiva, la activo nuevamente con los campos ingresados
			} else if (observaciones.get(0).getEstado().toString() == "ACTIVO") {
				try {
					fieldID = observaciones.get(0).getId();
					almacenado = ClientePDT.ModificarObservacion(fieldID, fieldIdentificacion, fieldUsuario, fieldFenomeno, fieldLocalidad,
							fieldDescripcion, imagen, fieldLatitud, fieldLongitud, fieldAltitud, fieldEstado,
							fieldFecha);

					if (almacenado) {
						JOptionPane.showMessageDialog(frame,
								"Se ha registrado la observación ha sido registrado con éxito.", "Usuario Registrado!",
								JOptionPane.INFORMATION_MESSAGE);

						// cerramos la ventanta
						this.frame.dispose();

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);

					return;
				}
			}

			// La observación ya existe en el sistema y esta activa
			else {
				JOptionPane.showMessageDialog(null, "La observación ya existe en el sistema");
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error con el servidor, por favor contacte con su administrador",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);

			return;
		}
	}

	//Defino la accion del boton cancelar
	private void accionCancelar() {
		this.frame.dispose();

	}
	
	//Defino la accion del boton seleccionar imagen
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
				ImageIcon imagen = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
						labelfoto.setIcon(imagen);
			}catch(Exception e){
				JOptionPane.showMessageDialog(frame, "Error al cargar el archivo","Error", JOptionPane.WARNING_MESSAGE);
				
			}
			
		}
		
	}
	

}
