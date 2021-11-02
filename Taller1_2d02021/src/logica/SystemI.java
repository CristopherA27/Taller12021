package logica;
import dominio.*;
import logica.*;

public interface SystemI {
	//lectura txt
	public boolean ingresarCuenta(String nombreCuenta,String contraseña,String nick,int nivelCuenta,int rpCuenta,String region);
	//public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion);
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String contraseña,String nick, int nivelCuenta, int rpCuenta,String region,String nombrePersonaje,String rol);
	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje,String rol,int recaudacion,String nombreSkin,String calidadSkin);
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin);
	public boolean comprarPersonaje(String nombreCuenta,String nombrePersonaje) ;
	public String obtenerSkinsDisponibles();
	
	
}
