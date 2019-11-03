package interfaz.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.entidades.Observacion;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;

public class FrameListadoporZona implements ActionListener {


	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelZona;

	/** Atributos de TexField */
	private JTextField textZona;
	
	/** Atributos de Botones */
	private JButton buttonListar;
	private JButton buttonCancelar;

	/** Tabla */
	private JTable tablaObservacion;

	public FrameListadoporZona(JFrame framePadre) {

		this.labelZona = new JLabel("Zona: ");
		
		this.textZona = new JTextField(15);
		
		JButton buttonListar = new JButton("Listar");
		buttonListar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonListar = buttonListar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Listado de Observaciones por zona");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel listarObservacionPanel = new JPanel(new GridBagLayout());
		listarObservacionPanel.setSize(600, 600);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		listarObservacionPanel.add(this.labelZona, constraints);

		constraints.gridx = 1;
		listarObservacionPanel.add(this.textZona, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		listarObservacionPanel.add(buttonListar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		listarObservacionPanel.add(buttonCancelar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		this.tablaObservacion = this.cargarTablaObservacion();
		
			listarObservacionPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Observaciones"));
	
			frame.add(listarObservacionPanel);
				
			frame.setVisible(true);
	
			this.frame = frame;
		}
		

	

	private JTable cargarTablaObservacion() {

		List<Observacion> observaciones; 
		
		String zona = this.textZona.getText();
		
		try{
			observaciones = ClientePDT.ListarObservacionporZona(zona);
		}
		catch (Exception e){
			return null;
		}

		String[] nombreColumnas = { "ID", "Usuario", "Fenomeno", "Localidad", "Descripcion", 
				"Imagen", "Latitud", "Longitud", "Altitud", "Estado", "Date"};
		

		/*
		 * El tamaño de la tabla es, 6 columnas (cantidad de datos a mostrar) y
		 * la cantidad de filas depende de la cantida de mascotas
		 */
		Object[][] datos = new Object[observaciones.size()][12];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		for (Observacion o : observaciones) {
			datos[fila][0] = o.getId();
			datos[fila][1] = o.getUsuario().getNombre();
			datos[fila][2] = o.getFenomeno().getNombreFen();
			datos[fila][3] = o.getLocalidad().getNombreLoc();
			datos[fila][4] = o.getDescripcion();
			datos[fila][5] = o.getImagen();
			datos[fila][6] = o.getLatitud();
			datos[fila][7] = o.getLongitud();
			datos[fila][8] = o.getAltitud();
			datos[fila][9] = o.getEstado().getNombre();
			datos[fila][10] = o.getFecha();
			
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

		this.tablaObservacion = table;

		return table;

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
		} else {
			this.accionListar();
		}

	}
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();
	}

	private void accionListar() {

		// Si es Eliminar se validan datos!

		String fieldZona = this.textZona.getText();
		
		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldZona.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe ingresar la zona a filtrar", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
			
		
		
	}

		
	
}
