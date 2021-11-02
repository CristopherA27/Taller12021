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
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String contraseña,String nick, int nivelCuenta, int rpCuenta,String region,String nombrePersonaje,String rol) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje != null) {
				cuenta = new Cuenta(nombreCuenta, contraseña, nick, nivelCuenta, rpCuenta, region);
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

	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje, String rol,int recaudacion, String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			Skin skin = personaje.getListaSkins().buscar(nombreSkin);
			if(skin != null) {
				personaje = new Personaje(nombrePersonaje, rol, recaudacion);
				boolean ingresado = personaje.getListaSkins().ingresar(skin);
				if(ingresado) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("No se encontro la skin de nombre "+nombreSkin);
			}
		}else {
			throw new NullPointerException("No se encontro el personaje "+nombrePersonaje);
		}
	}
	
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje!= null) {
				Skin skin = personaje.getListaSkins().buscar(nombreSkin);
				if(skin ==null) {
					int pagar = 0;
					int tipoSkin = skin.tipoSkin(skin.getCalidadSkin());
					pagar = tipoSkin;
					if(cuenta.getRpCuenta()>pagar) {
						cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
						personaje.getListaSkins().ingresar(skin);
						//No se  si la linea 106 esl o mismo que la 104
						cuenta.getListaPersonajes().buscar(nombrePersonaje).getListaSkins().ingresar(skin);
						return true;
					}else {
						return false;
					}	
				}else {
					throw new NullPointerException("La skin con nombre "+nombreSkin+" ya existe");
				}
			}else {
				throw new NullPointerException("El "+nombrePersonaje+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}
	
	
}
	

