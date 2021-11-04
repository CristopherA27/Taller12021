package dominio;

import logica.*;

public class Cuenta {
	private String nombreCuenta;
	private String contrase�a;
	private String nick;
	private int nivelCuenta;
	private int rpCuenta;
	private String region;
	private double recaudacionRegion;
	private boolean estadoCuenta;
	
	private ListaPersonajes listaPersonajes;
	private ListaSkins listaSkins;
	
	public Cuenta(String nombreCuenta,String contrase�a,String nick,int nivelCuenta,int rpCuenta,String region,double recaudacionRegion,boolean estadoCuenta) {
		this.nombreCuenta = nombreCuenta;
		this.contrase�a = contrase�a;
		this.nick = nick;
		this.nivelCuenta = nivelCuenta;
		this.rpCuenta = rpCuenta;
		this.region = region;
		this.recaudacionRegion = 0;
		this.estadoCuenta = true;
		
		listaPersonajes = new ListaPersonajes(100);
		listaSkins = new ListaSkins(100);
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getNivelCuenta() {
		return nivelCuenta;
	}

	public void setNivelCuenta(int nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}

	public int getRpCuenta() {
		return rpCuenta;
	}

	public void setRpCuenta(int rpCuenta) {
		this.rpCuenta = rpCuenta;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

	public boolean getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public ListaPersonajes getListaPersonajes() {
		return listaPersonajes;
	}

	public ListaSkins getListaSkins() {
		return listaSkins;
	}

	public double getRecaudacionRegion() {
		return recaudacionRegion;
	}

	public void setRecaudacionRegion(double recaudacionRegion) {
		this.recaudacionRegion = recaudacionRegion;
	}

	

	
	
}
