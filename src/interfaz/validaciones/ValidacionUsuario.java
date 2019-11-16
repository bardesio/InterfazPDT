package interfaz.validaciones;

import com.exception.ServiciosException;

public class ValidacionUsuario {
	
	
	public static boolean verificarVacio(String fieldNombre, String fieldApellido, String fieldUsuario, String fieldDireccion, String fieldEstado, String fieldMail,
			String fieldNumeroDoc, String fieldPass) throws ServiciosException{		
	
	if (fieldNombre.equals("") || fieldApellido.equals("") || fieldUsuario.equals("")|| 
			fieldDireccion.equals("")|| fieldEstado.equals("")|| fieldMail.equals("")|| 
			fieldNumeroDoc.equals("")|| fieldPass.equals("")) {
		
		return true;
	}else {
	return false;}
	}
}
