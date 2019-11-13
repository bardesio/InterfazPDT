package interfaz.frames;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.entidades.Estado;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.Usuario;

import interfaz.locator.ClientePDT;
import net.sourceforge.jdatepicker.JDatePicker;

public class FrameMantenimientoObservacion implements ActionListener{
/*
    private long id;
    
	private Usuario usuario; 
	
    private Fenomeno fenomeno; 

    private Localidad localidad; 

	private String descripcion;

	private Blob imagen;
	
	private float latitud;
	
	private float longitud;
	
	private float altitud;

	private Estado estado;

    private Date fecha;
 * */
	
	
	JFrame frame;
	
	JButton btnModificar;
	JButton btnEliminar;
	JButton btnLimpiar;
	
	JLabel lblFenomeno;
	JComboBox<Fenomeno> cbxFenomeno;
	
	JLabel lblLocalidad;
	JComboBox<Localidad> cbxLocalidad;
	
	JLabel lblDescripcion;
	JTextArea tfDescripcion;
	
	JLabel lblEstado;
	JComboBox<Estado> cbxEstado;
	
	JLabel lblFechaYHora;
	JDatePicker dpFecha;
	
	//ver como hago imagenes
	JLabel lblImagen;	
	
	JLabel lblLatitud;
	JTextField txtLatitud;
	
	JLabel lblLongitud;
	JTextField txtLongitud;
	
	JLabel lblAltitud;
	JTextField txtAltitud;


	public FrameMantenimientoObservacion (JFrame framePadre) {

		lblFenomeno = new JLabel("Fenomeno: ");
		cbxFenomeno = new JComboBox<Fenomeno>();
		
		lblLocalidad = new JLabel("Localidad: ");
		cbxLocalidad = new JComboBox<Localidad>();
		
		lblDescripcion = new JLabel("Descripción: ");
		tfDescripcion = new JTextArea();
		
		//ver como hago imagenes
		lblImagen = new JLabel("Imagen: ");		
		
		lblLatitud = new JLabel("Latitud: ");
		txtLatitud = new JTextField();
		
		lblLongitud = new JLabel("Longitud: ");
		txtLongitud = new JTextField();
	
		lblAltitud = new JLabel("Altitud: ");
		txtAltitud = new JTextField();
		
		
		
		initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Mantenimiento Observacion");
		frame.setSize(framePadre.getSize().width, framePadre.getSize().height);
		frame.setResizable(true);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GridBagLayout gbl_Panel = new GridBagLayout();
		gbl_Panel.rowWeights = new double[]{};
		gbl_Panel.columnWeights = new double[]{};
		JPanel panel = new JPanel(gbl_Panel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(this.lblFenomeno, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(this.cbxFenomeno, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(this.lblLocalidad, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(this.lblDescripcion, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(this.tfDescripcion, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(this.lblImagen, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		panel.add(this.lblImagen, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(this.lblLatitud, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		panel.add(this.txtLatitud, constraints);
		
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos"));
		frame.getContentPane().add(panel);
		
		
	 /*	GridBagLayout gbl_Panel2 = new GridBagLayout();
		gbl_Panel.rowWeights = new double[]{};
		gbl_Panel.columnWeights = new double[]{};
		JPanel panel2 = new JPanel(gbl_Panel);
		
		panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Imagen"));
		panel.add(panel2);

		GridBagLayout gbl_Panel3 = new GridBagLayout();
		gbl_Panel.rowWeights = new double[]{};
		gbl_Panel.columnWeights = new double[]{};
		JPanel panel3 = new JPanel(gbl_Panel);
		
		panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Geolicalización"));
		panel.add(panel3);
	*/
		
		//frame.pack();
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
