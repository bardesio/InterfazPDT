
package interfaz.frames;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.entidades.*;
import interfaz.locator.ClientePDT;



public class FrameLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Frame de la ventana */
	private JFrame jframe;
	
	private JPanel jpanel;
	public static JTextField textUsuario;
	public static JTextField textPass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin window = new FrameLogin();
					window.jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize() {
		jframe = new JFrame();
		jframe.setBounds(100, 100, 450, 300);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public FrameLogin()

	{		
		initialize();
		jframe.setTitle("Bienvenido a Green Place");
		jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jframe.setBounds(100, 100, 323, 181);
		jframe.setLocationRelativeTo(null);
		jpanel= new JPanel();  			//Defino el panel
		jpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		jframe.setContentPane(jpanel);
		jpanel.setLayout(null);
	
		//Defino la etiqueta Usuario
		JLabel lblNewLabel = new JLabel("Nombre de Usuario:");
		lblNewLabel.setBounds(36, 44, 120, 14);
		jpanel.add(lblNewLabel);
				
		//Defino la etiqueta Contraseña
		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(36, 76, 90, 14);
		jpanel.add(lblContrasea);  
	
		//Defino el cuadro de texto Usuario 
		this.textUsuario = new JTextField();
		this.textUsuario.setBounds(170, 41, 86, 20);
		jpanel.add(textUsuario);
		textUsuario.setColumns(10);
		textUsuario.requestFocus();        	
	
		//Defino el cuadro de texto de la contraseña
		textPass = new JPasswordField();
		textPass.setBounds(170, 73, 86, 20);
		textPass.transferFocus();
		jpanel.add(textPass);
		
	
                    
		//Defino el botón Aceptar
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/confirm.png")));
				btnAceptar.addActionListener(new  ActionListener() { 
					@Override//Establezco que debe hacer cuando hago clic
					public void actionPerformed(ActionEvent e) {
						
						String usuario = textUsuario.getText().toUpperCase();
						String password = textPass.getText().toUpperCase();
					
						//Usuario u1= new Usuario();
						try {
							List<Usuario> usuarios = ClientePDT.Login(usuario, password);
							//llamarlo desde el cliente
							if(usuarios.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña invalida");
								return;
							}
							else {
								
									jframe.dispose();
									FramePrincipal menuprincipal= new FramePrincipal(usuarios);
								    menuprincipal.setVisible(true);
								
								for (Usuario usu : usuarios)
								{
									String user=usu.getUsuario();
									String pass=usu.getPass();
									usu.setUsuario(user);
									usu.setPass(pass);
								     JOptionPane.showMessageDialog(null, "Bienvenido \n"
							                    + usuario + " Has ingresado satisfactoriamente al sistema",   "Mensaje de bienvenida",
							                    JOptionPane.INFORMATION_MESSAGE); 
									
								}								
								
							}
							
						}
							
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error en el servidor, por favor contacte a soporte tecnico",
									"Error de conexión!", JOptionPane.WARNING_MESSAGE);
							dispose();
							}				
					}

					
					
				});
				btnAceptar.setBounds(60, 109, 100, 23);
				jpanel.add(btnAceptar);
				
				//Defino el botón Cancelar
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/cancel.png")));
				btnCancelar.addActionListener(new ActionListener() { //Establezco que debe hacer cuando hago clic en Cancelar
					public void actionPerformed(ActionEvent e) {
						System.exit(0); // Salgo del sistema
					}
				});
				btnCancelar.setBounds(180, 109, 105, 23);
				jpanel.add(btnCancelar);
			
				}
	
}

	
	







	




	


