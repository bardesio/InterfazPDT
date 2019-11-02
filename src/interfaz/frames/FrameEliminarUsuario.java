package interfaz.frames;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.exception.ServiciosException;

import interfaz.locator.ClientePDT;


public class FrameEliminarUsuario extends JFrame {

	private JPanel contentPane;
	public static JTextField textusu;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnNewButton;

	public static void man(String[] args)  throws ServiciosException, NamingException {
		
	
	}
	
	/**
	 * Create the frame.
	 */
	public FrameEliminarUsuario() {
		setTitle("Borrar Usuario");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Usuario a eliminar");
		lblNombre.setBounds(23, 11, 250, 38);
		contentPane.add(lblNombre);
				
		textusu = new JTextField();
		textusu.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			
		char caracter = e.getKeyChar();
		if (textusu.getText().length()==50) {
			e.consume();
		}
		}
		});
		
		textusu.setBounds(97, 70, 80, 23);
		contentPane.add(textusu);
		textusu.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				try {
					String usuario =textusu.getText();
					if(ClientePDT.EliminarUsuario(usuario)) {
						JOptionPane.showConfirmDialog(null, "Usuario eliminada con éxito!", "Usuario", JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "El usuario no se pudo eliminar, Intente mas tarde");					}
				} catch (NamingException | ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
		});
		btnAceptar.setBounds(10, 147, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FramePrincipal menu = new FramePrincipal();
				menu.setVisible(true);
				dispose();			}
		});
		btnCancelar.setBounds(207, 147, 89, 23);
		contentPane.add(btnCancelar);
		
		btnNewButton = new JButton("LISTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPopupMenu popupMenu = new JPopupMenu();
				popupMenu.setBounds(0, 0, 67, 49);
				addPopup(contentPane, popupMenu);
				dispose();
				
			}
		});
		btnNewButton.setBounds(117, 147, 80, 23);
		contentPane.add(btnNewButton);
	}
	
	public FrameEliminarUsuario(JFrame frame) {
		// TODO Auto-generated constructor stub
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
