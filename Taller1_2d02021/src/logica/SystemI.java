package logica;
import dominio.*;
import logica.*;

public interface SystemI {
	//lectura txt
	public boolean ingresarCuenta(String nombreCuenta,String contraseņa,String nick,int nivelCuenta,int rpCuenta,String region);
	public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion);
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String contraseņa,String nick, int nivelCuenta, int rpCuenta,String region,String nombrePersonaje,String rol);
	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje,String rol,int recaudacion,String nombreSkin,String calidadSkin);
	
	
}
