package interfaz.locator;

import java.util.List;

import javax.naming.NamingException;

import com.Remote.*;
import com.entidades.Observacion;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.EJBLocator;




public class ClientePDT {

	/*** Operaciones sobre Usuario ***/
	
	private static String JNDI_USUARIO = "global/PDT/UsuarioBean!.com.Remote.UsuarioBeanRemote";
	private static String JNDI_FENOMENO ="global/PDT/FenoemenoBean!com.Remote.FenomenoBeanRemote";


/***************************************************USUARIO*******************************************************/
	public static Usuario existeUsuario(String nomUsu) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.existeUsuario(nomUsu);
	}
	
	public static boolean CrearUsuario(Long id, String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, TipoUsuario tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.CrearUsuario(id, pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
	}
	
	
	public static boolean ModificarUsuario(Long id, String nombre, String apellido, String tipoDoc, String numDoc, 
			String direccion, String correo, String pass, String estado, TipoUsuario tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.ModificarUsuario(id, nombre, apellido, tipoDoc, numDoc, direccion, correo, pass, estado, tipousuario);
	}
	
	public static boolean EliminarUsuario(Long usuario) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.EliminarUsuario(usuario);
	}
	
	public static List<TipoUsuario> obtenerTodoslosTipos() throws Exception {

		TIpoUsuarioBeanRemote tipousuarioBeanRemote = EJBLocator.getInstance().lookup(TIpoUsuarioBeanRemote.class);
		return tipousuarioBeanRemote.obtenerTodoslosTipos();
	}
		
/***************************************************FENOMENO*******************************************************/
	public static boolean ingresarnuevoFenomeno(long codigo, String fieldnombre , String fieldDescripcion) throws  Exception {
		
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.crearFenomeno(codigo, fieldnombre, fieldDescripcion);
	}
	
	/***************************************************LISTAR OBSERVACION POR ZONA  *******************************************************/
	
	public static List<Observacion> ListarObservacionporZona(String NomZona) throws Exception {
		
		ObservacionBeanRemote observacionbeanremote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		return observacionbeanremote.ListarObservacionporZona(NomZona);
	}
}
