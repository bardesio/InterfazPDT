package interfaz.locator;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Remote.*;
import com.entidades.Estado;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.Observacion;
import com.entidades.Telefono;
import com.entidades.TipoUsuario;
import com.entidades.Usuario;

import interfaz.locator.EJBLocator;





public class ClientePDT {

	/*** Operaciones sobre Usuario ***/
	
	private static String JNDI_USUARIO = "global/PDT/UsuarioBean!.com.Remote.UsuarioBeanRemote";
	private static String JNDI_FENOMENO ="global/PDT/FenoemenoBean!com.Remote.FenomenoBeanRemote";
	private static String JNDI_OBSERVACION ="global/PDT/ObservacionBean!com.Remote.ObservacionBeanRemote";



/***************************************************USUARIO*******************************************************/
	public static List<Usuario> existeUsuario(String nomUsu) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.existeUsuario(nomUsu);
	}
	
	
	public static boolean CrearUsuario(Long id, String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, String tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.CrearUsuario(id, pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
	}
	
	public static boolean ModificarUsuario(Long id, String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, String tipousuario)  throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.ModificarUsuario(id, pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
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
		
	public  static List<Usuario> Login(String usuario, String pass) throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.Login(usuario, pass);
	}
	
/***************************************************FENOMENO*******************************************************/
	public static boolean ingresarnuevoFenomeno(long id,String fieldCodigo ,String fieldNombre, String fieldEstado , String fieldDescripcion,String tels) throws  Exception {
		
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.crearFenomeno(id,fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
	}
	
	public static List<Fenomeno> existecodigo(String codigo) throws Exception {

		FenomenoBeanRemote fenomenobeanremote= EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.existecodigo(codigo);
		

	}
	
	public static boolean ModificarFenomeno (Long id, String codigo,String fieldEstado, String nombreFen,String descripcion,String tel) throws Exception
	{
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.modificarFenomeno(id, codigo,fieldEstado, nombreFen, descripcion,tel);
	}
	
	public static boolean EliminarFenomeno(long codigo) throws Exception {

		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.EliminarFenomeno(codigo);
		
	}
	
	public static List<Telefono> obtenerTelefonoE() throws Exception {

		FenomenoBeanRemote fenomenotelefono = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		List<Telefono> TL = fenomenotelefono.obtenerTelEmergencia();
		
		return TL;
	}
	
		
	/***************************************************LISTAR OBSERVACION POR ZONA  *******************************************************/
	
		public static List<Observacion> obtenerTodasObservaciones() throws Exception {
		
			try {
		ObservacionBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		List<Observacion> obs = new ArrayList<Observacion>();
		obs = observacionesBeanRemote.obtenerTodasObservaciones();
		 return obs;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static List<Fenomeno> obtenerTodosFenomenos() throws Exception {
			
			try {
		ObservacionBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		List<Fenomeno> fenomenos = new ArrayList<Fenomeno>();
		fenomenos = observacionesBeanRemote.obtenerTodosFenomenos();
		 return fenomenos;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static List<Localidad> obtenerTodasLocalidades() throws Exception {
			
			try {
		ObservacionBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		List<Localidad> localidades = new ArrayList<Localidad>();
		localidades = observacionesBeanRemote.obtenerTodasLocalidades();
		 return localidades;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static List<Estado> obtenerTodosEstados() throws Exception {
			
			try {
		ObservacionBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
		List<Estado> estados = new ArrayList<Estado>();
		estados = observacionesBeanRemote.obtenerTodosEstados();
		 return estados;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static boolean CrearObservacion(Long id, Usuario usuario, Fenomeno fenomeno, Localidad localidad, 
	    		String descripcion, Blob imagen, float latitud, float longitud, float altitud, Estado estado, Date fecha) throws Exception{
			
			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.CrearObservacion(id, usuario, fenomeno, localidad, descripcion, imagen, latitud, longitud, altitud, estado, fecha);
		}
		
		public static boolean ModificarObservacion(Long id, Usuario usuario, Fenomeno fenomeno, Localidad localidad, 
	    		String descripcion, Blob imagen, float latitud, float longitud, float altitud, Estado estado, Date fecha)   throws Exception{
			
			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.ModificarObservacion(id, usuario, fenomeno, localidad, descripcion, imagen, latitud, longitud, altitud, estado, fecha);
		}
		
		public static boolean EliminarObservacion(long id) throws Exception {

			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.EliminarObservacion(id);
		}





}
