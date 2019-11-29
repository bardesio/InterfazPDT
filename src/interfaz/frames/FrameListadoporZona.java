package interfaz.frames;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.entidades.Observacion;
import interfaz.locator.ClientePDT;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameListadoporZona implements ActionListener {
	
	// Atributo Frame 
	private Frame frame;

	// Creo la tabla 
	private JTable tablaObservaciones;

	// Creo los Date Picker
	private JDatePickerImpl datePickerFin;
	private JDatePickerImpl datePickerInicio;

	//Creo las Labels 
	private JLabel labelFechaInicio;
	private JLabel labelFechaFin;
	private JLabel labelZona;
	
	//Creo el formateador
	SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy/MM/dd");


	// Atributos del ComboBox 
	private JComboBox<String> comboZonas;
	
	// Creo los botones
	private JButton botonFiltrar;
	private JButton botonLimpiar;

	public FrameListadoporZona(JFrame framePadre) {
		
		//Le pongo nombre de las labels
		this.labelFechaInicio = new JLabel("Fecha Inicio: ");
		this.labelFechaFin = new JLabel("Fecha Fin: ");
		this.labelZona = new JLabel("Zona: ");
		
		
		//Le pongo nombre e icono al boton filtrar
		JButton botonFiltrar = new JButton("Filtrar");
		botonFiltrar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/filter.png")));
		botonFiltrar.addActionListener(this);

		//Le pongo nombre e icono al boton limpiar
		JButton botonLimpiar = new JButton("Limpiar Filtro");
		botonLimpiar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/clean.png")));
		botonLimpiar.addActionListener(this);

		//Asigno propiedades a los botones
		this.botonFiltrar = botonFiltrar;
		this.botonLimpiar = botonLimpiar;
		
		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Listado de Observaciones por zona y fecha");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel listarObservacionesPanel = new JPanel(new GridBagLayout());
		listarObservacionesPanel.setSize(1000, 1000);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(30, 2, 2, 30);

		constraints.gridx = 0;
		constraints.gridy = 0;
		listarObservacionesPanel.add(this.labelFechaInicio, constraints);

		constraints.gridx = 1;
		this.datePickerInicio = this.crearDatePicker();
		listarObservacionesPanel.add(this.datePickerInicio, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		listarObservacionesPanel.add(this.labelFechaFin, constraints);
		
		constraints.gridx = 1;
		this.datePickerFin = this.crearDatePicker();
		listarObservacionesPanel.add(this.datePickerFin, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		listarObservacionesPanel.add(this.labelZona, constraints);
		
		constraints.gridx = 1;
		this.comboZonas = this.completarComboZonas();
		listarObservacionesPanel.add(this.comboZonas, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		listarObservacionesPanel.add(this.botonFiltrar, constraints);

		constraints.gridx = 3;
		constraints.gridy = 0;
		listarObservacionesPanel.add(this.botonLimpiar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		this.tablaObservaciones = this.cargartablaObservaciones();
		
		if (this.tablaObservaciones!=null){
			listarObservacionesPanel.add(new JScrollPane(this.tablaObservaciones), constraints);
	
			listarObservacionesPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Observaciones"));
	
			frame.add(listarObservacionesPanel);
	
			this.botonFiltrar.addActionListener(this);
			this.botonLimpiar.addActionListener(this);
	
			frame.pack();
			frame.setVisible(true);
	
			this.frame = frame;
		}
		else{
			JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}

	}
	
	//Cargo la tabla observaciones
	private JTable cargartablaObservaciones() {

		List<Observacion> observaciones;
		
		try{
			observaciones = new ArrayList<Observacion>();
			observaciones = ClientePDT.obtenerTodasObservaciones();
		}
		catch (Exception e){
			return null;
		}		

		
		//Indico las columnas
		String[] nombreColumnas = { "Id", "Zona", "Fenomeno", "Descripcion", "Latitud", "Longitud", "Altitud"
				, "Fecha"};

			//Ingreso el tamaño
			Object[][] datos = new Object[observaciones.size()][8];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		//Foreach para recorrer la observacion y obtener los campos
		for (Observacion o : observaciones) {

			datos[fila][0] = o.getCodigo_OBS();
			datos[fila][1] = o.getLocalidad().getDepartamento().getZona().getNombre_zona();
			datos[fila][2] = o.getFenomeno().getNombreFen();
			datos[fila][3] = o.getDescripcion();
			datos[fila][4] = o.getLatitud();
			datos[fila][5] = o.getLongitud();
			datos[fila][6] = o.getAltitud();
			datos[fila][7] = o.getFecha();
			
			fila++;

		}
		
		
		
		/*
		 * Este codigo indica que las celdas no son editables y que son todas
		 * del tipos String
		 */
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(600, 600);

		this.tablaObservaciones = table;

		
		return table;
		
		
	}

	//Creo el datapicker
	private JDatePickerImpl crearDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	//Verifico que boton fue pulsado y que accion ejecutar
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.botonFiltrar) {
			this.accionFiltrar();
		} else {
			this.accionLimpiarFiltro();
		}

	}

	//Defino el boton limpiar
	private void accionLimpiarFiltro() {

		this.tablaObservaciones.setRowSorter(null);
		this.datePickerFin.getModel().setValue(null);
		this.datePickerInicio.getModel().setValue(null);

	}

	//Defino el boton filtrar
	private void accionFiltrar() {
		
		//Creo una lista con el filtro
	    List<RowFilter<Object,Object>> filtro = new ArrayList<RowFilter<Object,Object>>(2);
		
		//Asigno los campos en variables
		Date fechaInicio = (Date) this.datePickerInicio.getModel().getValue();
		Date fechaFin = (Date) this.datePickerFin.getModel().getValue();
		String fieldZona = (String) this.comboZonas.getSelectedItem();

		
		//Verifico que la fecha de inicio no supere a la fecha fin
		if (fechaInicio.after(fechaFin)) {
			JOptionPane.showMessageDialog(frame, "La fecha de inicio no puede ser mayor a la fecha de fin", "Fechas invalidas!", JOptionPane.WARNING_MESSAGE);
		}
		
		//Verifico que no sea nulo
		else if (fechaInicio != null && fechaFin != null && !(fieldZona.equals(""))) {
			
	
			filtro.add(RowFilter.dateFilter(ComparisonType.AFTER, fechaInicio, 7));
			filtro.add(RowFilter.dateFilter(ComparisonType.BEFORE, fechaFin, 7));
			filtro.add(RowFilter.regexFilter(fieldZona, 1));

			
			  DefaultTableModel dtm = (DefaultTableModel) tablaObservaciones.getModel();
			    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
			    tablaObservaciones.setRowSorter(tr);
			    RowFilter<Object, Object> rf = RowFilter.andFilter(filtro);
			    tr.setRowFilter(rf);
				
		}
		
		else {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	//Completo el combo zonas
	private JComboBox<String> completarComboZonas() {
		String[] valores = {"NORTE", "SUR", "ESTE", "OESTE"};
		return new JComboBox<>(valores);
	}
	
}

