package logica;
import dominio.*;
import logica.*;

public interface SystemI {
	//lectura txt
	public boolean ingresarCuenta(String nombreCuenta,String contrase�a,String nick,int nivelCuenta,int rpCuenta,String region);
	public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion);
	
}
