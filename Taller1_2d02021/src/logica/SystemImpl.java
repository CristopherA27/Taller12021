package logica;

import dominio.Cuenta;
import dominio.Personaje;
import dominio.Skin;

public class SystemImpl implements SystemI{
	
	private ListaCuentas lCuentas;
	private ListaPersonajes lPersonajes;
	private ListaSkins lSkins;
	
	public SystemImpl() {
		lCuentas = new ListaCuentas(100);
		lPersonajes = new ListaPersonajes(100);
		lSkins = new ListaSkins(100);
	}

	public boolean ingresarCuenta(String nombreCuenta, String contraseņa, String nick, int nivelCuenta, int rpCuenta,String region) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta == null) {
			cuenta = new Cuenta(nombreCuenta, contraseņa, nick, nivelCuenta, rpCuenta, region);
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
	
	/*public boolean ingresarPersonaje(String nombrePersonaje,String rol,int recaudacion) {
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
	}*/

	@Override
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String contraseņa,String nick, int nivelCuenta, int rpCuenta,String region,String nombrePersonaje,String rol) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje != null) {
				cuenta = new Cuenta(nombreCuenta, contraseņa, nick, nivelCuenta, rpCuenta, region);
				boolean ingresado = cuenta.getListaPersonajes().ingresar(personaje);
				//Dberia ir?
				//boolean ingreso = lCuentas.ingresar(cuenta);
				if(ingresado) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("No existe la cuenta "+nombrePersonaje);
			}
		}else {
			throw new NullPointerException("No exite el personaje "+nombreCuenta);
		}
	}

	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje, String rol,int recaudacion, String nombreSkin,
			String calidadSkin) {
		Skin skin = lSkins.buscar(nombreSkin);
		if(skin != null) {
			Personaje personaje = lPersonajes.buscar(nombrePersonaje);
			if(personaje!=null) {
				personaje = new Personaje(nombrePersonaje, rol, recaudacion);
				boolean ingresado = personaje.getListaSkins().ingresar(skin);
				if(ingresado) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("No se encontro el personaje con el nombre "+nombrePersonaje);
			}
		}else {
			throw new NullPointerException("No se encontro la skin con el nombre "+nombreSkin);
		}
	}
	
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje!= null) {
				Skin skin = personaje.getListaSkins().buscar(nombreSkin);
				if(skin != null) {
					double pagar = 0;
					
				}
			}
		}
		return false;
	}
	
	
	

	
	
	
	
}
	


