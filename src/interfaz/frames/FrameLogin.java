package interfaz.frames;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;


import com.entidades.*;
import com.servicios.*;

import interfaz.locator.ClientePDT;
import interfaz.locator.EJBLocator;

import com.Remote.*;

public class FrameLogin extends JFrame {

	private JFrame jframe;
	private JPanel jpanel;
	public static JTextField txt_usu;
	public static JTextField pass;

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
	

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jframe = new JFrame();
		jframe.setBounds(100, 100, 450, 300);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	
	
	public FrameLogin()

	{
		
		initialize();
		jframe.setTitle("Bienvenido 404 Not Found");
		jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jframe.setBounds(100, 100, 323, 181);
		jframe.setLocationRelativeTo(null);
		jpanel= new JPanel();  			//Defino el panel
		jpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		jframe.setContentPane(jpanel);
		jpanel.setLayout(null);
	
		//Defino la etiqueta Usuario
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(54, 44, 46, 14);
		jpanel.add(lblNewLabel);
				
		//Defino la etiqueta Contraseña
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(36, 76, 89, 14);
		jpanel.add(lblContrasea);  
	
		//Defino el cuadro de texto Usuario 
		txt_usu = new JTextField();
		txt_usu.setBounds(110, 41, 86, 20);
		jpanel.add(txt_usu);
		txt_usu.setColumns(10);
		txt_usu.requestFocus();        	
	
		//Defino el cuadro de texto de la contraseña
		pass = new JPasswordField();
		pass.setBounds(110, 73, 86, 20);
		pass.transferFocus();
		jpanel.add(pass);
		
	
		//Defino el objeto Menú del tipo Menu Principal 
		//que es la clase que contiene el menú de todo el programa
		 //VentanaPrincipal menuprincipal= new VentanaPrincipal();
		 //VerificarUsuario usu= new VerificarUsuario();
		this.dispose();

                    
		//Defino el botón Aceptar
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new  ActionListener() { 
					@Override//Establezco que debe hacer cuando hago clic
					public void actionPerformed(ActionEvent e) {
						
						String usuario = txt_usu.getText();
						String password = pass.getText();
					
						//Usuario u1= new Usuario();
						try {
							UsuarioBeanRemote usuariobeanremote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);		
							List<Usuario>u = usuariobeanremote.Login(usuario,password);
							//llamarlo desde el clietnte
							if(u.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Usuario no encontrado, Datos Invalidos!");
								return;
							}
							else {	
							for (Usuario usu : u)
							{
								String user=usu.getUsuario();
								String pass=usu.getPass();
								usu.setUsuario(user);
								usu.setPass(pass);
							     JOptionPane.showMessageDialog(null, "Bienvenido\n"
						                    + "Has ingresado satisfactoriamente al sistema",   "Mensaje de bienvenida",
						                    JOptionPane.INFORMATION_MESSAGE);
								
							}	
							}
							this.dispose();
		                    FramePrincipal bienvenido = new FramePrincipal();
		                    bienvenido.setVisible(true);
						}
							
						
			               
			                    //menuprincipal.setVisible(true);
			                    
								//dispose();
						
							
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}					
					
					}

					private void dispose() {
						// TODO Auto-generated method stub
						
					}
					
				});
				btnAceptar.setBounds(120, 111, 98, 20);
				jpanel.add(btnAceptar);
	}
	
}

	
	







	




	


