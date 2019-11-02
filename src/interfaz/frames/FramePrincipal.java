package interfaz.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FramePrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

/** Método que inicializa toda la ventatna principal */
	
	private static void createAndShowGUI() {

		
		JFrame frame = new JFrame("Green Place");
		
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeMenuBar(frame);

		// Display the window.
		frame.setVisible(true);
	}
	
/** Inicializamos la barra de menu de arriba */
	
	private static void initializeMenuBar(JFrame frame) {
		
		JMenuBar menuBar = new JMenuBar();
		
		initializeMenuUsuarios(menuBar, frame);
		//initializeMenuObservaciones(menuBar, frame);
		//initializeMenuFenomenos(menuBar, frame);
		//initializeMenuListadoporZona(menuBar, frame);
		//initializeMenuListadoporFiltros(menuBar, frame);
		
		
		frame.setJMenuBar(menuBar);

}

	
/** Inicialicación de botones del menu de clientes */
	
	private static void initializeMenuUsuarios(JMenuBar menuBar, final JFrame frame) {
		
		JMenu usuarios = new JMenu("Usuarios");
		
		JMenuItem nuevoUsuario = new JMenuItem("Nuevo Usuario");
		
		nuevoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevoUsuario(frame);
            }
        });
		
		JMenuItem modificarUsuario = new JMenuItem("Modificar Usuario");
		
		nuevoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameModificarUsuario(frame);
            }
        });

		JMenuItem eliminarUsuario = new JMenuItem("Eliminar Usuario");
		
		nuevoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameEliminarUsuario(frame);
            }
        });
		
		usuarios.add(nuevoUsuario);
		usuarios.add(modificarUsuario);		
		usuarios.add(eliminarUsuario);
		menuBar.add(usuarios);		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
