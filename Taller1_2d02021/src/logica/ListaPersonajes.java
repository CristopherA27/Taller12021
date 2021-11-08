package logica;

import dominio.*;

public class ListaPersonajes {
	private int cant;
	private int max;
	private Personaje[] listaPersonajes;
	
	public ListaPersonajes(int max) {
		cant = 0;
		this.max = max;
		listaPersonajes = new Personaje[max];
	}

	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Personaje personaje) {
		boolean existe = false;
		for(int i=0;i<cant;i++) {
			if(listaPersonajes[i].getNombrePersonaje().equals((personaje.getNombrePersonaje()))) {
				existe = true;
			}
		}
		if(existe == false) {
			if(cant<max) {
				listaPersonajes[cant] = personaje;
				cant++;
				return true;
			}
		}
		return false;
	}

	public Personaje buscar(String nombrePersonaje) {
		for(int i=0;i<cant;i++) {
			if(listaPersonajes[i].getNombrePersonaje().equals(nombrePersonaje)) {
				return listaPersonajes[i];
			}
		}
		return null;
	}
	
	public boolean eliminar(String nombrePersonaje) {
		int i;
		for(i=0;i<cant;i++) {
			if(listaPersonajes[i].getNombrePersonaje().equals(nombrePersonaje)) {
				break;
			}
		}
		if(i == cant) {
			return false;
		}else {
			for(int k=i;k<cant;i++) {
				listaPersonajes[k]= listaPersonajes[k+1];
			}
			cant--;
			return true;
		}
	}
	
	public Personaje getElementoI(int posicion) {
		return listaPersonajes[posicion];
	}
	
	
	

}
