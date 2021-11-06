package logica;
import dominio.*;
import logica.*;

public interface SystemI {
	//lectura txt
	public boolean ingresarCuenta(String nombreCuenta,String contraseña,String nick,int nivelCuenta,int rpCuenta);
	public boolean ingresarPersonaje(String nombrePersonaje,String rol);
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String nombrePersonaje);
	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje,String rol,String nombreSkin,String calidadSkin);
	public boolean asignarRegion(String nombreCuenta,String region);
	public boolean ingresarEstadistica(String nombrePersonaje,double recaudacion);
	
	//Menu cliente
	
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin);
	public boolean comprarPersonaje(String nombreCuenta,String nombrePersonaje) ;
	public String obtenerSkinsDisponibles(String nombreCuenta);
	public String obtenerInventario(String nombreCuenta);
	public boolean recargarRp(String nombreCuenta,int dinero);
	public boolean cambiarContraseña(String nombreCuenta,String contraseñaVieja,String contraseñaNueva);
	
	//menu admin
	public String obtenerVentasPorRol();
	public String obtenerVentasPorRegion();
	public String obtenerVentasPorPersonaje();
	public String obtenerPersonajesPorRol();
	public boolean agregarPersonaje(String nombrePersonaje,String rol,String nombreSkin,String calidadSkin);
	public String obtenerCuentasMayorAMenor();

}

