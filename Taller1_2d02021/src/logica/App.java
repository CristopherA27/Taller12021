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
			try {
				boolean ingresado = system.ingresarCuenta(nombreCuenta, contraseña, nick, nivelCuenta, rpCuenta);
				if(ingresado) {
					int cantPersonajes = Integer.parseInt(partes[5]);
					int avance = 6;
					int acumu = 8;
					for(int i=0;i<cantPersonajes;i++) {
						String nombrePersonaje = partes[avance];
						int cantSkins = Integer.parseInt(partes[avance+1]);
						for(int j=0;j<cantSkins;j++) {
							String nombreSkin = partes[acumu];	
							acumu++;
						}
						avance = acumu;
						acumu+=2;
						try {
							boolean ingresoAsociar = system.ingresarAsociarCuentaPersonaje(nombreCuenta, nombrePersonaje);
							if(!ingresoAsociar) {
								System.out.println("El personaje no se pudo ingresar a la cuenta por que no queda espacio");
							}
						}catch(Exception ex) {
							System.out.println(ex.getMessage());
						}
					}
					String region = partes[avance];
					system.asignarRegion(nombreCuenta, region);
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
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
				boolean ingreso = system.ingresarPersonaje(nombrePersonaje, rol);
				if(ingreso) {
					
					
					try {
						
					}catch(Exception ex) {
						System.out.println("\t"+ex.getMessage());
					}
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
