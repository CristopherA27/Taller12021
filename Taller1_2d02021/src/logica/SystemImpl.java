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

	public boolean ingresarCuenta(String nombreCuenta, String contraseña, String nick, int nivelCuenta, int rpCuenta) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta == null && cuenta.getEstadoCuenta()==true) {
			cuenta = new Cuenta(nombreCuenta, contraseña, nick, nivelCuenta, rpCuenta);
			if(cuenta.getNombreCuenta().equals(nombreCuenta) && cuenta.getEstadoCuenta()==true) {
				boolean ingresado = lCuentas.ingresar(cuenta);
				if(ingresado) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("La cuenta ingresada esta inhabilitada");
			}
		}else {
			throw new NullPointerException("La Cuenta con el nombre "+nombreCuenta+" ya existe");
		}
	}
	
	public boolean asignarRegion(String nombreCuenta,String region) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			cuenta.setRegion(region);
			return true;
		}else {
			throw new NullPointerException("La "+nombreCuenta+" no existe");
		}
	}
	
	public boolean ingresarPersonaje(String nombrePersonaje,String rol) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje == null) {
			personaje = new Personaje(nombrePersonaje, rol);
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

	@Override
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String nombrePersonaje) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null && cuenta.getEstadoCuenta()==true) {
			Personaje personaje = lPersonajes.buscar(nombrePersonaje);
			if(personaje !=null) {
				personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
				if(personaje == null) {
					Personaje p = new Personaje(nombrePersonaje, personaje.getRol());
					boolean ingresado = cuenta.getListaPersonajes().ingresar(p);
					if(ingresado) {
						return true;
					}else {
						return false;
					}
				}else {
					throw new NullPointerException("Ya existe el"+nombrePersonaje+" en la cuenta");
				}
			}
			else {
				throw new NullPointerException("El personaje "+nombrePersonaje+" no existe en el juego");
			}
		}else {
			throw new NullPointerException("No existe la cuenta "+nombreCuenta);
		}
	}

	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje, String rol, String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			Skin skin = personaje.getListaSkins().buscar(nombreSkin);
			if(skin != null) {
				boolean ingresado = personaje.getListaSkins().ingresar(skin);
				boolean ingresarSkin = lSkins.ingresar(skin);
				if(ingresado && ingresarSkin) {
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
	
	public boolean ingresarEstadistica(String nombrePersonaje,double recaudacion) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje!=null) {
			personaje.setRecaudacion(recaudacion);
			return true;
		}
		return false;
	}
	
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null && cuenta.getEstadoCuenta()==true) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje!= null) {
				Skin skin = personaje.getListaSkins().buscar(nombreSkin);
				if(skin ==null) {
					int pagar = 0;
					int tipoSkin = skin.tipoSkin(skin.getCalidadSkin());
					pagar = tipoSkin;
					if(cuenta.getRpCuenta()>pagar) {
						cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
						cuenta.setRecaudacionRegion(cuenta.getRecaudacionRegion()+(pagar*6.15));
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
		if(cuenta != null && cuenta.getEstadoCuenta() == true) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje ==null) {
				int pagar = 975;
				if(cuenta.getRpCuenta()>pagar) {
					cuenta.getListaPersonajes().ingresar(personaje);
					personaje.setRecaudacion(personaje.getRecaudacion()+(pagar*6.15));
					cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("El "+nombrePersonaje+" ya existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}
	
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null && cuenta.getEstadoCuenta()==true) {
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
		if(cuenta != null && cuenta.getEstadoCuenta()==true) {
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
		if(cuenta != null  && cuenta.getEstadoCuenta()==true) {
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
		if(cuenta != null  && cuenta.getEstadoCuenta()==true) {
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
		if(cuenta != null  && cuenta.getEstadoCuenta()==true) {
			if(cuenta.getContraseña().equals(contraseñaVieja)) {
				if(cuenta.getContraseña().equals(contraseñaVieja)) {
					cuenta.setContraseña(contraseñaNueva);
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("La "+contraseñaVieja+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}

	@Override
	public String obtenerVentasPorRol() {
		String dato = "";
		double recaudacionSUP =0;
		double recaudacionADC = 0;
		double recaudacionTOP =0;
		double recaudacionMID=0;
		double recaudacionJG = 0;
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			double recaudacion = personaje.getRecaudacion();
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
	
	public double convertirCLP(int monto) {
		double montoCLP = monto*6.15;
		return montoCLP;
	}

	@Override
	public String obtenerVentasPorRegion() {
		String dato = "";
		double recaudacionLAS =0;
		double recaudacionLAN = 0;
		double recaudacionEUW =0;
		double recaudacionKR=0;
		double recaudacionNA = 0;
		double recaudacionRU = 0;
		for(int i=0;i<lCuentas.getCant();i++) {
			Cuenta cuenta = lCuentas.getElementoI(i);
			String regionCuenta = cuenta.getRegion();
			double recaudacion = cuenta.getRecaudacionRegion();
			switch(regionCuenta) {
			case("LAS"):
				recaudacionLAS+= recaudacion;
				break;
			case("LAN"):
				recaudacionLAN+=recaudacion;
				break;
			case("EUW"):
				recaudacionEUW+=recaudacion;
				break;
			case("KR"):
				recaudacionKR+=recaudacion;
				break;
			case("NA"):
				recaudacionNA+=recaudacion;
				break;
			case("RU"):
				recaudacionRU+=recaudacion;
				break;
			default: break;
			}
		}
		dato+="LAS:"+recaudacionLAS+" LAN:"+recaudacionLAN+" EUW:"+recaudacionEUW+" KR:"+recaudacionKR+" NA:"+recaudacionNA+" RU:"+recaudacionRU;
		return dato;
	}
	
	public String obtenerVentasPorPersonaje() {
		String dato = "";
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			double recaudacion = personaje.getRecaudacion();
			dato+=personaje.getNombrePersonaje()+" recaudo "+recaudacion+"\n";
		}
		return dato;
	}
	
	public String obtenerPersonajesPorRol() {
		String dato = "";
		int cantSUP =0;
		int cantADC =0;
		int cantTOP =0;
		int cantMID =0;
		int cantJG =0;
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			String rol = personaje.getRol();
			switch(rol) {
			case("SUP"):
				cantSUP+=1;
				break;
			case("ADC"):
				cantADC +=1;
				break;
			case("TOP"):
				cantTOP+=1;
				break;
			case("MID"):
				cantMID+=1;
				break;
			case("JG"):
				cantJG+=1;
				break;
			default: break;
			}	
		}
		dato+="SUP:"+cantSUP+" ADC:"+cantADC+" TOP:"+cantTOP+" MID:"+cantMID+" JG:"+cantJG;
		return dato;
	}
	
	public boolean agregarPersonaje(String nombrePersonaje,String rol,String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje ==null) {
			personaje = new Personaje(nombrePersonaje, rol);
			lPersonajes.ingresar(personaje);
			Skin skin = lSkins.buscar(nombreSkin);
			if(skin == null) {
				skin = new Skin(nombreSkin, calidadSkin);
				personaje.getListaSkins().ingresar(skin);
				lSkins.ingresar(skin);
				return true;
			}
		}
		return false;
	}
	
	public boolean agregarSkin(String nombrePersonaje,String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			Skin skin = personaje.getListaSkins().buscar(nombreSkin);
			if(skin ==null) {
				skin = new Skin(nombreSkin, calidadSkin);
				personaje.getListaSkins().ingresar(skin);
				//lSkins.ingresar(skin);
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("El personaje de nombre "+nombrePersonaje+" no existe");
		}
	}
	
	public boolean bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			cuenta.setEstadoCuenta(false);
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
		return false;
	}
	
	public String obtenerCuentasMayorAMenor() {
		String dato = "";
		int aux=0;
		String aux2 = "";
		for(int i=0;i<lCuentas.getCant()-1;i++) {
			for(int j=i+1;j<lCuentas.getCant();j++) {
				Cuenta cuenta1 = lCuentas.getElementoI(i);
				Cuenta cuenta2 = lCuentas.getElementoI(j);
				int nv1 = cuenta1.getNivelCuenta();
				int nv2 = cuenta2.getNivelCuenta();
				String name1 = cuenta1.getNombreCuenta();
				String name2 = cuenta2.getNombreCuenta();
				if(cuenta1.getNivelCuenta()>cuenta2.getNivelCuenta()) {
					aux = nv1;
					nv1 = nv2;
					nv2= aux;
					
					aux2 = name1;
					name1=name2;
					name2=aux2;
				}
				dato+= aux2+" "+aux+"\n";
			}
		}
		return dato;
	}
	
	public boolean existeCuenta(String nombreCuenta) {
		Cuenta cuenta = 
		
	}

	
	
	
}
	

