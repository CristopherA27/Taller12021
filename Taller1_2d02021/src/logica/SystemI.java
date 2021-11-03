package logica;
import dominio.*;
import logica.*;

public interface SystemI {
	//lectura txt
	public boolean ingresarCuenta(String nombreCuenta,String contrase�a,String nick,int nivelCuenta,int rpCuenta,String region);
	//public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion);
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String contrase�a,String nick, int nivelCuenta, int rpCuenta,String region,String nombrePersonaje,String rol);
	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje,String rol,int recaudacion,String nombreSkin,String calidadSkin);
	
	//Menu cliente
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin);
	public boolean comprarPersonaje(String nombreCuenta,String nombrePersonaje) ;
	public String obtenerSkinsDisponibles(String nombreCuenta);
	public String obtenerInventario(String nombreCuenta);
	public boolean recargarRp(String nombreCuenta,int dinero);
	public boolean cambiarContrase�a(String nombreCuenta,String contrase�aVieja,String contrase�aNueva);

	//menu admin
	public String obtenerVentasPorRol();
	public String obtenerVentasPorRegion();

}

