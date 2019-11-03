package interfaz.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FramePrincipal {

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
		initializeMenuFenomenos(menuBar, frame);
		//initializeMenuObservaciones(menuBar, frame);
		initializeMenuListadoporZona(menuBar, frame);
		//initializeMenuListadoporFiltros(menuBar, frame);
		
		
		frame.setJMenuBar(menuBar);

}

	/** Inicialicación de botones del menu de Fenomeno */

private static void initializeMenuFenomenos(JMenuBar menuBar, final JFrame frame) {
JMenu fenomeno = new JMenu("Fenomeno");
	
	JMenuItem nuevoFenomeno = new JMenuItem("Nuevo Fenomeno");
	
	nuevoFenomeno.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            
        	new FrameNuevoFenomeno(frame);
        	
        }
    });
	fenomeno.add(nuevoFenomeno);
	menuBar.add(fenomeno);
}

/** Inicialicación de botones del menu de Listado de observaciones por zona */

private static void initializeMenuListadoporZona(JMenuBar menuBar, final JFrame frame) {
JMenu listado = new JMenu("Listado por zona");

JMenuItem nuevoListado = new JMenuItem("Listado de observaciones por zona");

nuevoListado.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
        
    	new FrameListadoporZona(frame);
    	
    }
});
listado.add(nuevoListado);
menuBar.add(listado);
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
		
		modificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameModificarUsuario(frame);
            }
        });

		JMenuItem eliminarUsuario = new JMenuItem("Eliminar Usuario");
		
		eliminarUsuario.addActionListener(new ActionListener() {
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
	

}
