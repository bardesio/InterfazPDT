package interfaz.locator;

import java.util.List;

import com.Remote.*;
import com.entidades.Fenomeno;
import com.entidades.Observacion;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.EJBLocator;





public class ClientePDT {

	/*** Operaciones sobre Usuario ***/
	
	private static String JNDI_USUARIO = "global/PDT/UsuarioBean!.com.Remote.UsuarioBeanRemote";
	private static String JNDI_FENOMENO ="global/PDT/FenoemenoBean!com.Remote.FenomenoBeanRemote";
	private static String JNDI_OBSERVACION ="global/PDT/ObservacionBean!com.Remote.ObservacionBeanRemote";


/***************************************************USUARIO*******************************************************/
	public static Usuario existeUsuario(String nomUsu) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.existeUsuario(nomUsu);
	}
	
	
	public static boolean CrearUsuario(Long id, String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, String tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.CrearUsuario(id, pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
	}
	
	
	public static boolean ModificarUsuario(Long id, String nombre, String apellido, String tipoDoc, String numDoc, 
			String direccion, String correo, String pass, String estado, TipoUsuario tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.ModificarUsuario(id, nombre, apellido, tipoDoc, numDoc, direccion, correo, pass, estado, tipousuario);
	}
	
	public static boolean EliminarUsuario(long usuario) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.EliminarUsuario(usuario);
	}
	
	public static List<TipoUsuario> obtenerTodoslosTipos() throws Exception {

		TIpoUsuarioBeanRemote tipousuarioBeanRemote = EJBLocator.getInstance().lookup(TIpoUsuarioBeanRemote.class);
		List<TipoUsuario> TP = tipousuarioBeanRemote.obtenerTodoslosTipos(); 
		return TP;
	}
	
	public  List<Usuario> Login(String usuario, String pass) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.Login(usuario, pass);
	}
	
		
/***************************************************FENOMENO*******************************************************/
	public static boolean ingresarnuevoFenomeno(long id,String fieldCodigo ,String fieldNombre , String fieldDescripcion) throws  Exception {
		
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.crearFenomeno(id,fieldCodigo, fieldNombre, fieldDescripcion);
	}
	
	public List<Fenomeno> existecodigo(String codigo) throws Exception {

		FenomenoBeanRemote fenomenobeanremote= EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.existecodigo(codigo);
		

	}
	
	public static boolean ModificarFenomeno (String codigo, String nombreFen,String descripcion) throws Exception
	{
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.modificarFenomeno(codigo, nombreFen, descripcion);
	}
	
	public static boolean EliminarFenomeno(long codigo) throws Exception {

		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.EliminarFenomeno(codigo);
		
	}
	
	
	
	
	/***************************************************LISTAR OBSERVACION POR ZONA  *******************************************************/
	
	public static List<Observacion> ListarObservacionporZona(String NomZona) throws Exception {
		
		ObservacionBeanRemote observacionbeanremote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		return observacionbeanremote.ListarObservacionporZona(NomZona);
	}
}
