package interfaz.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.entidades.Usuario;

public class FramePrincipal {

	
	static List<Usuario> listUsuarios = null;
	
	//recibo en el constructor del frame el usuario
	public FramePrincipal(List<Usuario> usuarios) {
	//Lo guardo en una variable	
	listUsuarios = usuarios;
	}
	
	public void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	
	
/** Método que inicializa toda la ventana principal */
	
	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("Green Place");
		
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeMenuBar(frame);
				
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setSize(600,400);
		lblNewLabel.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/green.jpg")));
		panel.add(lblNewLabel);
		frame.setVisible(true);
	}
	
/** Inicializamos la barra de menu de arriba */
	
	private static void initializeMenuBar(JFrame frame) {
		
	
		try {
			
			JMenuBar menuBar = new JMenuBar();
			
			//Determino el tipo de usuario y le habilito o no las ventanas
			if (listUsuarios.get(0).getTipousuario().getNombre().equals("ADMINISTRADOR"))
			{
				initializeMenuUsuarios(menuBar, frame);
				initializeMenuObservaciones(menuBar, frame);
				initializeMenuFenomenos(menuBar, frame);
				initializeMenuListadoporZona(menuBar, frame);

			}
			else if (listUsuarios.get(0).getTipousuario().getNombre().equals("EXPERTO"))
			{
				initializeMenuObservaciones(menuBar, frame);
				initializeMenuListadoporZona(menuBar, frame);

			}
			else if (listUsuarios.get(0).getTipousuario().getNombre().equals("VOLUNTARIO"))
			{
				initializeMenuListadoporZona(menuBar, frame);
				initializeMenuObservaciones(menuBar, frame);
			}
			
			frame.setJMenuBar(menuBar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
	

/** Inicialicación de botones del menu de Fenomeno */

private static void initializeMenuFenomenos(JMenuBar menuBar, final JFrame frame) {
JMenu fenomeno = new JMenu("Fenómenos");

	JMenuItem nuevoFenomeno = new JMenuItem("Nuevo Fenómeno");
	
	nuevoFenomeno.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            
        	new FrameNuevoFenomeno(frame);
        	
        }
    });
	JMenuItem modificarFenomeno = new JMenuItem("Modificar Fenómeno");

	modificarFenomeno.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        
	    	new FrameModificarFenomeno(frame);
	    }
	});
	JMenuItem eliminarFenomeno = new JMenuItem("Eliminar Fenómeno");

	eliminarFenomeno.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        
	    	new FrameEliminarFenomeno(frame);
	    }
	});
	
	fenomeno.add(nuevoFenomeno);
	fenomeno.add(modificarFenomeno);
	fenomeno.add(eliminarFenomeno);
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


/** Inicialicación de botones del menu de usuario */
	
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
                
            	new FrameEliminarUsuario(frame, listUsuarios);
            }
        });
		
		usuarios.add(nuevoUsuario);
		usuarios.add(modificarUsuario);		
		usuarios.add(eliminarUsuario);
		menuBar.add(usuarios);		
	}

/** Inicialicación de botones del menu de observacion */

private static void initializeMenuObservaciones(JMenuBar menuBar, final JFrame frame) {
		
		JMenu observacion = new JMenu("Observaciones");
		
		JMenuItem nuevaObservacion = new JMenuItem("Nueva Observación");
		
		nuevaObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevaObservacion(frame, listUsuarios);
            }
        });
			
		
		JMenuItem modificarObservacion = new JMenuItem("Modificar Observación");
		
		
		modificarObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameModificarObservacion(frame, listUsuarios);
            }
            
        });

		JMenuItem eliminarObservacion = new JMenuItem("Eliminar Observación");
		
		eliminarObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameEliminarObservacion(frame, listUsuarios);
            }
        });
		
		if (!(listUsuarios.get(0).getTipousuario().getNombre().equals("VOLUNTARIO")))
		{
			observacion.add(nuevaObservacion);
			observacion.add(modificarObservacion);		
			observacion.add(eliminarObservacion);	
		}
		else {
			observacion.add(nuevaObservacion);
			observacion.add(modificarObservacion);		
		}		
		menuBar.add(observacion);		
	}


public void setVisible(boolean b) {
		createAndShowGUI();
	}

	

}
