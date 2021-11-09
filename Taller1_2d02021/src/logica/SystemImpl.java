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
	
	/**
	 * this function enter the user account you want
	 * @param nombreCuenta
	 * @param contraseña
	 * @param nick
	 * @param nivelCuenta
	 * @param rpCuenta
	 * return true if the account exist
	 * else return false
	 */
	
	public boolean ingresarCuenta(String nombreCuenta, String contraseña, String nick, int nivelCuenta, int rpCuenta) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta == null ) {
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
	
	/**
	 *this function assigns a region to the account 
	 * @param nombreCuenta
	 * @param region
	 * return true if the account exist
	 */
	
	public boolean asignarRegion(String nombreCuenta,String region) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			cuenta.setRegion(region);
			return true;
		}else {
			throw new NullPointerException("La "+nombreCuenta+" no existe");
		}
	}
	
	/**
	 * this function enters a character
	 * @param nombrePersonaje
	 * @param rol
	 * return lPersonajes
	 */
	
	public boolean ingresarPersonaje(String nombrePersonaje,String rol) {
		Personaje personaje = new Personaje(nombrePersonaje,rol);
		return lPersonajes.ingresar(personaje);
	}

	/**
	 * this function associates a character with an account
	 * @param nombreCuenta
	 * @param nombrePersonaje
	 * return true if the account exist
	 * else return false
	 */
	
	public boolean ingresarAsociarCuentaPersonaje(String nombreCuenta,String nombrePersonaje) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			Personaje personaje = lPersonajes.buscar(nombrePersonaje);
			if(personaje != null) {
				boolean ingresar = cuenta.getListaPersonajes().ingresar(personaje);
				if(ingresar) {
					return true;
				}else {
					return false;
				}
			}else {
				throw new NullPointerException("El personaje "+nombrePersonaje+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no exisite");
		}
		
	}	
	/**
	 *this function associates a character with its respective skin
	 * @param nombrePersonaje
	 * @param rol
	 * @param nombreSkin
	 * @param calidadSkin
	 * return true if the character and the skin exist
	 * else return false
	 */
	
	public boolean ingresarAsociarPersonajeSkin(String nombrePersonaje, String rol, String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			Skin skin = personaje.getListaSkins().buscar(nombreSkin);
			if(skin == null) {
				skin = new Skin(nombreSkin, calidadSkin);
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
	
	/**
	 *enter the character and its respective collection
	 * @param nombrePersonaje
	 * @param recaudacion
	 * return true if the character exist
	 * else return false 
	 */
	
	public boolean ingresarEstadistica(String nombrePersonaje,double recaudacion) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje!=null) {
			personaje.setRecaudacion(personaje.getRecaudacion()+recaudacion);
			return true;
		}
		return false;
	}
	
	/**
	 *with this function you can buy a skin
	 * @param nombreCuenta
	 * @param nombrePersonaje
	 * @param nombreSkin
	 * return true if the rp of the account is greater than zero
	 * else return false
	 */
	
	public boolean comprarSkin(String nombreCuenta,String nombrePersonaje,String nombreSkin) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null ) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje!= null) {
				Skin skin = personaje.getListaSkins().buscar(nombreSkin);
				if(skin ==null) {
					int pagar = 0;
					int tipoSkin = skin.tipoSkin(skin.getCalidadSkin());
					pagar = tipoSkin;
					if(cuenta.getRpCuenta()>pagar) {
						cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
						cuenta.setRecaudacionRegion(cuenta.getRecaudacionRegion()+(pagar));
						personaje.getListaSkins().ingresar(skin);
						cuenta.getListaPersonajes().buscar(nombrePersonaje).getListaSkins().ingresar(skin);
						return true;
					}else {
						return false;
					}	
				}else {
					throw new NullPointerException("La skin con nombre "+nombreSkin+" ya existe en el personaje");
				}
			}else {
				throw new NullPointerException("El "+nombrePersonaje+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}
	
	/**
	 *with this function you can buy a character
	 * @param nombreCuenta
	 * @param nombrePersonaje
	 * return true if the rp of the account is greater than zero
	 * else return false
	 */
	
	public boolean comprarPersonaje(String nombreCuenta,String nombrePersonaje) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null ) {
			Personaje personaje = cuenta.getListaPersonajes().buscar(nombrePersonaje);
			if(personaje ==null) {
				int pagar = 975;
				if(cuenta.getRpCuenta()>pagar) {
					cuenta.getListaPersonajes().ingresar(personaje);
					personaje.setRecaudacion(personaje.getRecaudacion()+(pagar));
					cuenta.setRpCuenta(cuenta.getRpCuenta()-pagar);
					cuenta.setNivelCuenta(cuenta.getNivelCuenta()+1);
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
	
	/**
	 * this function shows the available skins
	 * @param nombreCuenta
	 * return String dato
	 */
	
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null ) {
			ListaSkins ls = cuenta.getListaSkins();
			for(int i=0;i<lSkins.getCant();i++) {
				if(!ls.getElementoI(i).equals(lSkins.getElementoI(i))) {
					dato += lSkins.getElementoI(i)+"\n";
				}
			}
		}
		return dato;
	}

	
	/**
	 * shows the account characters with their respective skins
	 * @param nombreCuenta
	 * return String dato
	 */
	
	public String obtenerInventario(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null ) {
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
	
	/**
	 * allows to recharge rp to the selected account
	 * @param nombreCuenta
	 * @param dinero
	 * return true if the account exist and the account status is true
	 */
	
	public boolean recargarRp(String nombreCuenta,int dinero) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null  ) {
			int saldo = cuenta.getRpCuenta()+dinero;
			cuenta.setRpCuenta(saldo);
			return true;
		}else {
			throw new NullPointerException("La cuenta de nombre "+nombreCuenta+" no existe");
		}
	}
	
	/**
	 * get the account data
	 * @param nombreCuenta
	 * return null if the account don't exist or the account status is false
	 */
	
	public String obtenerDatosCuenta(String nombreCuenta) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null  ) {
			String contraseña = cuenta.getContraseña();
			int cantCaracteres = contraseña.length();
			//ojo aca
			dato+= cuenta.getNombreCuenta()+" "+cuenta.getNick()+" "+cuenta.getContraseña()+" "+contraseña.substring(cantCaracteres-3,cantCaracteres);
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
		return dato;
	}
	
	/**
	 * allows you to change the password of the selected account
	 * @param nombreCuenta
	 * @param contraseñaVieja
	 * @param contraseñaNueva
	 * return true if the old password is wrong
	 */
	
	public boolean cambiarContraseña(String nombreCuenta,String contraseñaVieja,String contraseñaNueva) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null  ) {
			if(cuenta.getContraseña().equals(contraseñaVieja)) {
				cuenta.setContraseña(contraseñaNueva);
				return true;
			}else {
				throw new NullPointerException("La "+contraseñaVieja+" no existe");
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}

	/**
	 * get the earnings per role
	 * return String dato
	 */
	
	public String obtenerVentasPorRol() {
		String dato = "";
		double recaudacionSUP =0;
		double recaudacionADC = 0;
		double recaudacionTOP =0;
		double recaudacionMID=0;
		double recaudacionJG = 0;
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			double recaudacion = personaje.getRecaudacion()*6.15;
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
	
	/**
	 * this function converts the Chilean peso to Rp
	 * return double montoCLP
	 */
	
	public double convertirCLP(int monto) {
		double montoCLP = monto*6.15;
		return montoCLP;
	}

	/*
	 * get profit by region 
	 * return String dato
	 */
	
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
			double recaudacion = cuenta.getRecaudacionRegion()*6.15;
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
	
	/**
	 * The total collected by each character will be obtained. 
	 * return String dato
	 */
	
	public String obtenerVentasPorPersonaje() {
		String dato = "";
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje personaje = lPersonajes.getElementoI(i);
			double recaudacion = personaje.getRecaudacion()*6.15;
			dato+=personaje.getNombrePersonaje()+" recaudo "+recaudacion+"\n";
		}
		return dato;
	}
	
	/**
	 * The amount of existing characters will be obtained for each role. 
	 * return String dato
	 */
	
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
	
	/**
	 * It will search if the character exists, if not, the character will be added to the list of characters, it will
	 * search if the skin exists, if not, the character with their respective skins will be added to the system.
	 * @param nombrePersonaje
	 * @param rol
	 * @param nombreSkin
	 * @param calidadSkin
	 * return true if skin is null
	 * else return false
	 */
	
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
	
	/**
	 * It will search if the skin exists in the character's Skins list, if not, it will search
	 * if the character exists, if so, the skin will be added to the character's skins list
	 * @param nombrePersonaje
	 * @param nombreSkin
	 * @param calidadSkin
	 * return true if the skin is null
	 * else return false
	 */
	
	
	public boolean agregarSkin(String nombrePersonaje,String nombreSkin,String calidadSkin) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			Skin skin = personaje.getListaSkins().buscar(nombreSkin);
			if(skin ==null) {
				skin = new Skin(nombreSkin, calidadSkin);
				personaje.getListaSkins().ingresar(skin);
				lSkins.ingresar(skin);
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("El personaje de nombre "+nombrePersonaje+" no existe");
		}
	}
	
	/**
	 * It will search if the account exists, so it will remove it from the system account list.
	 * @param nombreCuenta
	 * return false if the account does not exist
	 */
	
	public boolean bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			cuenta.setEstadoCuenta(false);
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
		return false;
	}
	
	/**
	 * All accounts will be obtained from highest to lowest level.
	 * return String dato 
	 */
	
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
	
	/**
	 * It is verified if the account entered exists
	 * @param nombreCuenta
	 * return true if the account does not exist
	 * else return false
	 */
	
	public boolean existeCuenta(String nombreCuenta) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * it is verified if the password corresponds to the account
	 * @param nombreCuenta
	 * @param contraseña
	 * return true if the password is the same as the one entered
	 * else return false
	 */
	
	public boolean contraseñaCorrecta(String nombreCuenta,String contraseña) {
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		if(cuenta != null) {
			if(cuenta.getContraseña().equalsIgnoreCase(contraseña)) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new NullPointerException("La cuenta "+nombreCuenta+" no existe");
		}
	}
	
	/**
	 * this function gets the character skins
	 * @param nombreCuenta
	 * @param nombrePersonaje
	 * return String dato
	 */
	
	public String obtenerSkinsPersonaje(String nombreCuenta,String nombrePersonaje) {
		String dato = "";
		Cuenta cuenta = lCuentas.buscar(nombreCuenta);
		Personaje personajeCuenta = cuenta.getListaPersonajes().buscar(nombrePersonaje);
		Personaje personajeLista = lPersonajes.buscar(nombrePersonaje);
		if(personajeCuenta != null) {
			ListaSkins lskinsCuenta = personajeCuenta.getListaSkins();
			ListaSkins lskinsListaGeneral = personajeLista.getListaSkins();
			dato += "Las skins de "+personajeCuenta+" son: "+"\n";
			for(int i=0;i<lskinsListaGeneral.getCant();i++) {
				Skin skinCuenta = lskinsCuenta.getElementoI(i);
				Skin skinGeneral = lskinsListaGeneral.getElementoI(i);
				if(!skinCuenta.getNombreSkin().equals(skinGeneral.getNombreSkin())) {
					dato+="\t"+skinGeneral.getNombreSkin()+"\n";
				}
			}
		}else {
			throw new NullPointerException("El "+personajeCuenta+" no existe");
		}
		return dato;
	}
	
	/**
	 * this function checks if the character exists
	 * @param nombrePersonaje
	 * return true if personaje does not exist
	 * else return false
	 */
	
	public boolean existePersonaje(String nombrePersonaje) {
		Personaje personaje = lPersonajes.buscar(nombrePersonaje);
		if(personaje != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * this function checks if the skin exists
	 * @param nombrePersonaje
	 * return true if skin does not exist
	 * else return false
	 */
	
	public boolean existeSkin(String nombreSkin) {
		Skin skin = lSkins.buscar(nombreSkin);
		if(skin != null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * This function your respective account details
	 * @return account and their information
	 */
	
	public String obtenerCuentas() {
		String dato = "";
		for(int i=0;i<lCuentas.getCant();i++) {
			Cuenta c = lCuentas.getElementoI(i);
			dato+=c.getNombreCuenta()+","+c.getContraseña()+","+c.getNick()+","+c.getNivelCuenta()+","+c.getRpCuenta()+"\n";
			ListaPersonajes lp = c.getListaPersonajes();
			for(int j=0;j<lp.getCant();j++) {
				Personaje p = lp.getElementoI(j);
				dato += "\t"+p.getNombrePersonaje()+","+p.getRol();
				ListaSkins ls = p.getListaSkins();
				for(int k=0;k<ls.getCant();k++) {
					Skin s = ls.getElementoI(k);
					dato +="\t"+s.getNombreSkin()+","+s.getCalidadSkin()+"\n";
				}
			}
		}
		return dato;
	}
	
	/**
	 * this function obtains a character and their respective kins and data
	 * @return character and their information
	 */
	
	public String obtenerPersonajes() {
		String dato = "";
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje p = lPersonajes.getElementoI(i);
			dato+=p.getNombrePersonaje()+","+p.getRol();
			ListaSkins ls = p.getListaSkins();
			for(int j=0;j<ls.getCant();j++) {
				Skin s = ls.getElementoI(i);
				dato+="\t"+s.getNombreSkin()+","+s.getCalidadSkin()+"\n";
			}
		}
		return dato;
	}
	/**
	 * get the character and its respective collection
	 * @return character and its collection
	 */
	
	public String obtenerEstadisticas() {
		String dato = "";
		for(int i=0;i<lPersonajes.getCant();i++) {
			Personaje p = lPersonajes.getElementoI(i);
			dato+=p.getNombrePersonaje()+","+p.getRecaudacion()+"\n";
		}
		return dato;
	}
	
	
}
	

