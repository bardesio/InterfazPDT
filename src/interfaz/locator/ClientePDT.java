package interfaz.locator;

import java.util.List;


import com.Remote.*;
import com.entidades.TipoUsuario;




public class ClientePDT {

	/*** Operaciones sobre Usuario ***/

	public static boolean existeUsuario(String ciCliente) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.existeUsuario(ciCliente);
	}
	
	public static boolean CrearUsuario(Long id, String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, TipoUsuario tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.CrearUsuario(id, pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
	}

	public static boolean EliminarUsuario(String usuario) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.EliminarUsuario(usuario);
	}
	
	public static List<TipoUsuario> obtenerTodoslosTipos() throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.obtenerTodoslosTipos();
	}
	
	
}
