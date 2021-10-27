package logica;
import dominio.*;

public class ListaCuentas {
	private int cant;
	private int max;
	private Cuenta[] listaCuentas;
	
	public ListaCuentas(int max) {
		cant =0;
		this.max = max;
		listaCuentas = new Cuenta[max];
	}
	
	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Cuenta cuenta) {
		if(cant<max) {
			listaCuentas[cant] = cuenta;
			cant++;
			return true;
		}
		return false;
	}
	
	public Cuenta buscar(String nombreCuenta) {
		for(int i=0;i<cant;i++) {
			if(listaCuentas[i].getNombreCuenta().equals(nombreCuenta)) {
				return listaCuentas[i];
			}
		}
		return null;
	}
	
	public boolean eliminar(String nombreCuenta) {
		int i;
		for(i=0;i<cant;i++) {
			if(listaCuentas[i].getNombreCuenta().equals(nombreCuenta)) {
				break;
			}
		}
		if(i==cant) {
			return false;
		}else {
			for(int k=i;k<cant;k++) {
				listaCuentas[k] = listaCuentas[k+1];
			}
			cant--;
			return true;
		}
	}
	
	public Cuenta getElementoI(int posicion) {
		return listaCuentas[posicion];
	}

}
