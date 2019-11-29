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
	
	
	public static boolean CrearUsuario( String pass, String usuario, String nombre, String apellido, String estado, String tipodoc,
			String numerodoc, String direccion, String mail, String tipousuario) throws Exception{
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.CrearUsuario(pass, usuario, nombre, apellido, estado, tipodoc, numerodoc, direccion, mail, tipousuario);
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
	public static boolean ingresarnuevoFenomeno(String fieldCodigo ,String fieldNombre, String fieldEstado , String fieldDescripcion,String tels) throws  Exception {
		
		FenomenoBeanRemote fenomenobeanremote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		return fenomenobeanremote.crearFenomeno(fieldCodigo,fieldEstado, fieldNombre, fieldDescripcion,tels);
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
	
		
	/***************************************************OBSERVACIONES*******************************************************/
	
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
		FenomenoBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenoBeanRemote.class);
		List<Fenomeno> fenomenos = new ArrayList<Fenomeno>();
		fenomenos = fenomenoBeanRemote.Obtenertodoslosfenomenos();
		 return fenomenos;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static List<Localidad> obtenerTodasLocalidades() throws Exception {
			
			try {
		LocalidadBeanRemote localidadBeanRemote = EJBLocator.getInstance().lookup(LocalidadBeanRemote.class);
		List<Localidad> localidades = new ArrayList<Localidad>();
		localidades = localidadBeanRemote.obtenerTodasLocalidades();
		 return localidades;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static List<Estado> obtenerTodosEstados() throws Exception {
			
			try {
		EstadoBeanRemote estadoBeanRemote = EJBLocator.getInstance().lookup(EstadoBeanRemote.class);
		List<Estado> estados = new ArrayList<Estado>();
		estados = estadoBeanRemote.obtenerTodosEstados();
		 return estados;
			}
			catch (Exception e){
				System.out.print(e.getLocalizedMessage());
				return null;
			}
		}
		
		public static boolean CrearObservacion(String CodOBS, String usuario, String fenomeno, String localidad, 
	    		String descripcion,  byte[] imagen, float latitud, float longitud, float altitud, String estado, Date fecha) throws Exception{
			
			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.CrearObservacion(CodOBS, usuario, fenomeno, localidad, descripcion, imagen, latitud, longitud, altitud, estado, fecha);
		}
		
		public static boolean ModificarObservacion(Long id, String CodOBS, String usuario, String fenomeno, String localidad, 
	    		String descripcion,  byte[] imagen, float latitud, float longitud, float altitud, String estado, Date fecha)   throws Exception{
			
			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.ModificarObservacion(id, CodOBS, usuario, fenomeno, localidad, descripcion, imagen, latitud, longitud, altitud, estado, fecha);
		}
		
		public static boolean EliminarObservacion(long id) throws Exception {

			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.EliminarObservacion(id);
		}

		public static List<Observacion> existeObservacion(String identificacion) throws Exception {

			ObservacionBeanRemote observacionBeanRemote = EJBLocator.getInstance().lookup(ObservacionBeanRemote.class);
			return observacionBeanRemote.existeObservacion(identificacion);
		}
		
		public static Localidad obtenerLocalidad(String loc) throws Exception {

			LocalidadBeanRemote localidadBeanRemote = EJBLocator.getInstance().lookup(LocalidadBeanRemote.class);
			return localidadBeanRemote.obtenerLocalidad(loc);
		}
		
		
}
