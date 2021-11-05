package logica;

import java.io.IOException;
import java.util.Scanner;

public class App {
	
	
	public static void leerCuentas(SystemI system) throws IOException{
		System.out.println("Leyendo personajes");
		Scanner s = new Scanner("Cuentas.txt");
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombreCuenta = partes[0];
			String contraseña = partes[1];
			String nick = partes[2];
			int nivelCuenta = Integer.parseInt(partes[3]);
			int rpCuenta = Integer.parseInt(partes[4]);
			int cantPersonajes = Integer.parseInt(partes[5]);
			int avance = 6;
			int acumu = 8;
			for(int i=0;i<cantPersonajes;i++) {
				//HACER UN CONTADOR A LINICIO DE ESTE CILO Y QUE SUME DESPUES DE LEER LAS KSINS DE LOS PERSONAJES Y EL FINAL DE ESE CONTADOR SERA CUANDO EMPEIZE A LEER EL NUEVO PERSONAJE 
				String nombrePersonaje = partes[avance];
				int cantSkins = Integer.parseInt(partes[avance+1]);
				for(int j=0;j<cantSkins;j++) {
					String nombreSkin = partes[acumu];	
					acumu++;
				}
				avance = acumu;
				acumu+=2;
			}
			String region = partes[avance];
			
		}
	}
	
	public static void leerPersonajes(SystemI system)throws IOException{
		System.out.println("Leyendo cuentas");
		Scanner s = new Scanner("Personajes.txt");
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombrePersonaje = partes[0];
			String rol = partes[1];
			try {
				boolean ingreso = system.ingresarPersonaje(nombrePersonaje, rol, 0);
				if(!ingreso) {
					System.out.println("El "+nombrePersonaje+" ya existe");
				}
			}catch (Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
	}
	
	
	
	public static void main(String [] args) {
		SystemI system = new SystemImpl();
	}
}
