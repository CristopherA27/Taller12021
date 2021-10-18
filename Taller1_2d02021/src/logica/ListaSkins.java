package logica;

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

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Skin[] getListaSkins() {
		return listaSkins;
	}

	public void setListaSkins(Skin[] listaSkins) {
		this.listaSkins = listaSkins;
	}
	

}
