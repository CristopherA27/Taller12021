package logica;

import dominio.Cuenta;
import dominio.Personaje;

public class SystemImpl implements SystemI{
	
	private ListaCuentas lCuentas;
	private ListaPersonajes lPersonajes;
	private ListaSkins lSkins;
	
	public SystemImpl() {
		lCuentas = new ListaCuentas(100);
		lPersonajes = new ListaPersonajes(100);
		lSkins = new ListaSkins(100);
	}

	public boolean ingresarCuenta(String nombreCuenta, String contraseña, String nick, int nivelCuenta, int rpCuenta,String region) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta == null) {
			cuenta = new Cuenta(nombreCuenta, contraseña, nick, nivelCuenta, rpCuenta, region);
			boolean ingresado = lCuentas.ingresar(cuenta);
			if(ingresado) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("La Cuenta con el nombre "+nombreCuenta+" ya existe");
		}	
	}
	
	public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje == null) {
			personaje = new Personaje(nombrePersonaje, rol, recaudacion);
			boolean ingresado = lPersonajes.ingresar(personaje);
			if(ingresado) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("El persona con el nombre "+nombrePersonaje+" ya existe");
		}
		
		
	}
	
	
	
	
}
	


