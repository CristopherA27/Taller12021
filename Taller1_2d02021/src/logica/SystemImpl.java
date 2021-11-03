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
	
	public boolean comprarPersonaje(String nombreCuenta,String nombrePersonaje) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje ==null) {
				int pagar = 975;
				if(cuenta.getRpCuenta()>pagar) {
					cuenta.getListaPersonajes().ingresar(personaje);
					personaje.setRecaudacion(personaje.getRecaudacion()+(int)(pagar*6.15));
					cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("El "+nombrePersonaje+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}
	
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			ListaSkins ls = cuenta.getListaSkins();
			for(int i=0;i<lSkins.getCant();i++) {
				if(!ls.getElementoI(i).equals(lSkins.getElementoI(i))) {
					dato += lSkins.getElementoI(i)+"\n";
				}
			}
		}
		return dato;
	}

	@Override
	public String obtenerInventario(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			ListaPersonajes listapersonajes = cuenta.getListaPersonajes();
			for(int i=0;i<listapersonajes.getCant();i++) {
				Personaje personaje = listapersonajes.getElementoI(i);
				ListaSkins listaskins = personaje.getListaSkins();
				dato+= personaje.getNombrePersonaje()+"";
				for(int j=0;j<listaskins.getCant();i++) {
					Skin skin = listaskins.getElementoI(i);
					dato+=skin.getNombreSkin()+"\n";
				}
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
		return dato;
	}
	
	public boolean recargarRp(String nombreCuenta,int dinero) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			int saldo = cuenta.getRpCuenta()+dinero;
			cuenta.setRpCuenta(saldo);
			return true;
		}else {
			throw new NullPointerException("La cuenta de nombre "+nombreCuenta+" no existe");
		}
	}
	
	public String obtenerDatosCuenta(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			String contraseña = cuenta.getContraseña();
			int cantCaracteres = contraseña.length();
			//ojo aca
			dato+= cuenta.getNombreCuenta()+" "+cuenta.getNick()+" "+cuenta.getContraseña()+" "+contraseña.substring(cantCaracteres-3,cantCaracteres);
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
		return null;
	}
		
	public boolean cambiarContraseña(String nombreCuenta,String contraseñaVieja,String contraseñaNueva) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			if(cuenta.getContraseña().equals(contraseñaVieja)) {
				cuenta.setContraseña(contraseñaNueva);
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}

	@Override
	public String obtenerVentasPorRol() {
		String dato = "";
		int recaudacionSUP =0;
		int recaudacionADC = 0;
		int recaudacionTOP =0;
		int recaudacionMID=0;
		int recaudacionJG = 0;
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			int recaudacion = personaje.getRecaudacion();
			String tipo = personaje.getRol();
			switch(tipo) {
			case("SUP"):
				recaudacionSUP +=recaudacion;
				break;
			case("ADC"):
				recaudacionADC+=recaudacion;
				break;
			case("TOP"):
				recaudacionTOP+=recaudacion;
				break;
			case("MID"):
				recaudacionMID+=recaudacion;
				break;
			case("JG"):
				recaudacionJG+=recaudacion;
				break;
			default:break;
			}
		}
		dato+="SUP:"+recaudacionSUP+" ADC:"+recaudacionADC+" TOP:"+recaudacionTOP+" MID:"+recaudacionMID+" JG:"+recaudacionJG;
		return dato;
	}

	@Override
	public String obtenerVentasPorRegion() {
		for(int i=0;i<lCuentas.getCant();i++) {
			Cuenta cuenta = lCuentas.getElementoI(i);
			
			
		}
		
		return null;
	}
	
	
	
}
	

