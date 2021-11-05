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
			String region = partes[5];
			try {
				boolean ingresado =system.ingresarCuenta(nombreCuenta, contraseña, nick, nivelCuenta, rpCuenta, region, 0, true);
				
				if(!ingresado) {
					System.out.println("La cuenta "+nombreCuenta+" ya existe");
				}
			}catch (Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}	
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
