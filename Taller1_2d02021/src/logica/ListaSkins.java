package logica;

import dominio.Personaje;
import dominio.Skin;

public class ListaSkins {
	private int cant;
	private int max;
	private Skin [] listaSkins;
	
	public ListaSkins(int max) {
		cant =0;
		this.max = max;
		listaSkins = new Skin[max];
	}

	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Skin skin) {
		if(cant<max) {
			listaSkins[cant] = skin;
			cant++;
			return true;
		}
		return false;
	}
	
	public Skin buscar(String nombreSkin) {
		for(int i=0;i<cant;i++) {
			if(listaSkins[i].getNombreSkin().equals(nombreSkin)) {
				return listaSkins[i];
			}
		}
		return null;
	}
	
	public boolean eliminar(String nombreSkin) {
		int i;
		for(i=0;i<cant;i++) {
			if(listaSkins[i].getNombreSkin().equals(nombreSkin)) {
				break;
			}
		}
		if(i == cant) {
			return false;
		}else {
			for(int k=i;k<cant;k++) {
				listaSkins[k]= listaSkins[k+1];
			}
			cant--;
			return true;
		}
	}
	
	public Skin getElementoI(int posicion) {
		return listaSkins[posicion];
	}

	

}
