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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entidades.Observacion;

import interfaz.locator.ClientePDT;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameListadoporZona implements ActionListener {
	
	/** Frame */
	private Frame frame;

	/** Tabla */
	private JTable tablaObservaciones;

	/** Date Picker */
	private JDatePickerImpl datePicker;

	/** Labels */
	private JLabel labelFechaInicio;
	private JLabel labelFechaFin;
	private JLabel labelZona;


	/** Atributos de TexField */
	private JTextField textZona;
	
	/** Buttons */
	private JButton botonFiltrar;
	private JButton botonLimpiar;

	public FrameListadoporZona(JFrame framePadre) {

		this.labelFechaInicio = new JLabel("Fecha Inicio: ");
		this.labelFechaFin = new JLabel("Fecha Fin: ");

		this.labelZona = new JLabel("Zona: ");
		this.textZona = new JTextField(15);
		
		JButton botonFiltrar = new JButton("Filtrar");
		botonFiltrar.addActionListener(this);

		JButton botonLimpiar = new JButton("Limpiar Filtro");
		botonLimpiar.addActionListener(this);

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
		listarObservacionesPanel.setSize(600, 600);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		listarObservacionesPanel.add(this.labelFechaInicio, constraints);

		constraints.gridx = 1;
		this.datePicker = this.crearDatePicker();
		listarObservacionesPanel.add(this.datePicker, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		listarObservacionesPanel.add(this.labelFechaFin, constraints);
		
		constraints.gridx = 1;
		this.datePicker = this.crearDatePicker();
		listarObservacionesPanel.add(this.datePicker, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		listarObservacionesPanel.add(this.labelZona, constraints);
		
		constraints.gridx = 1;
		listarObservacionesPanel.add(this.textZona, constraints);
		
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

	private JTable cargartablaObservaciones() {

		List<Observacion> observaciones;
		
		try{
			observaciones = new ArrayList<Observacion>();
			observaciones = ClientePDT.obtenerTodasObservaciones();
		}
		catch (Exception e){
			return null;
		}

		String[] nombreColumnas = { "Id", "Usuario", "Codigo Fenomeno", "Localidad", "Descripcion", "Imagen"
				, "Latitud", "Longitud","Altitud","Estado", "Fecha"};

		/*
		 * El tamaño de la tabla es, 11 columnas (cantidad de datos a mostrar) y
		 * la cantidad de filas depende de la cantida de consultas
		 */
		Object[][] datos = new Object[observaciones.size()][11];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

		for (Observacion o : observaciones) {

			datos[fila][0] = o.getId();
			datos[fila][1] = o.getUsuario().getNombre();
			datos[fila][2] = o.getFenomeno().getCodigo();
			datos[fila][3] = o.getLocalidad().getNombreLoc();
			datos[fila][4] = o.getDescripcion();
			datos[fila][5] = o.getImagen();
			datos[fila][6] = o.getLatitud();
			datos[fila][7] = o.getLongitud();
			datos[fila][8] = o.getAltitud();
			datos[fila][9] = o.getEstado().getNombre();
			datos[fila][10] = formateadorFecha.format(o.getFecha());
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

	private JDatePickerImpl crearDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.botonFiltrar) {
			this.accionFiltrar();
		} else {
			this.accionLimpiarFiltro();
		}

	}

	private void accionLimpiarFiltro() {

		this.tablaObservaciones.setRowSorter(null);
		this.datePicker.getModel().setValue(null);
	}

	private void accionFiltrar() {

		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaObservaciones.getModel());

		Date fecha = (Date) this.datePicker.getModel().getValue();

		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

		if (fecha != null) {

			String fechaString = formateadorFecha.format(fecha);

			filtro.setRowFilter(RowFilter.regexFilter(fechaString, 0));

			this.tablaObservaciones.setRowSorter(filtro);

		}
	}

}

