package logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	
	public static void leerCuentas(SystemI system) throws IOException{
		System.out.println("Leyendo personajes");
		Scanner s = new Scanner(new File("Cuentas.txt"));
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
		Scanner s = new Scanner(new File("Personajes.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombrePersonaje = partes[0];
			String rol = partes[1];
			try {
				boolean ingreso = system.ingresarPersonaje(nombrePersonaje, rol);
				if(ingreso) {
					int cantSkins = Integer.parseInt(partes[2]);
					int cont = 3;
					for(int i=0;i<cantSkins;i++) {
						String nombreSkin = partes[cont];
						String calidadSkin = partes[cont+1];
						cont+=2;
						try {
							boolean ingresoAsocia = system.ingresarAsociarPersonajeSkin(nombrePersonaje, rol, nombreSkin, calidadSkin);
							if(!ingresoAsocia) {
								System.out.println("No se puede ingresar las skin a la cuenta por que no queda espacio");
							}
						}catch(Exception ex) {
							System.out.println("\t"+ex.getMessage());
						}
					}
					
				}
			}catch (Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
	}
	
	public static void leerEstadisticas(SystemI system) throws IOException{
		System.out.println("Leyendo Estadisticas");
		Scanner s = new Scanner(new File("Estadisticas.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombrePersonaje = partes[0];
			double recaudacion = (Integer.parseInt(partes[1])*6.15);
			try {
				boolean ingresado = system.ingresarEstadistica(nombrePersonaje, recaudacion);
				if(!ingresado) {
					System.out.println("No se pudo ingresar la estadística por que no hay más espacio");
				}
			}catch(Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
	}
	
	public static void menuUsuario(SystemI system) {
		boolean cierre = true;
		System.out.println("Bienvenido al Menu Usuario");
		while(cierre) {
			System.out.println("Bienvenido al Menu Usuario estas son las opciones disponibles");
	    	System.out.println("\tA)Comprar Skin");
	    	System.out.println("\tB)Comprar Personaje");
	    	System.out.println("\tC)Skins Disponibles");
	    	System.out.println("\tD)Mostrar Inventario");
	    	System.out.println("\tE)Recargar RP");
	    	System.out.println("\tF)Mostrar Datos");
	    	System.out.println("\tG)Cerrar Sesion");
	    	System.out.print("Seleccione una opción: ");
	    	String opcion = leer.nextLine();
	    	switch(opcion) {
	    		case("A"):
	    			break;
	    		case("B"):
	    			break;
	    		case("C"):
	    			break;
	    		case("D"):
	    			break;
	    		case("E"):
	    			break;
	    		case("F"):
	    			break;
	    		case("G"):
	    			break;
	    		default:System.out.println("Elija A ,B ,C , D , E o G porfavor ");break;
	    	}
	    	if(opcion.equalsIgnoreCase("G")) {
	    		break;
	    	}
		}
		System.out.println("\tSALIENDO DEL MENU USUARIO");
	}
	
	public static void menuAdmin(SystemI system) {
		boolean cierre = true;
		System.out.println("Bienvenido al Menu ADMIN");
		while(cierre) {
			System.out.println("Bienvenido al Menu ADMIN estas son las opciones disponibles");
	    	System.out.println("\tA)Recaudacion de Ventas por Rol");
	    	System.out.println("\tB)Recaudacion de Ventas por Region");
	    	System.out.println("\tC)Recaudacion de Ventas por Personaje");
	    	System.out.println("\tD)Cantidad de Personajes por Rol");
	    	System.out.println("\tE)Agregar Personaje al Juego");
	    	System.out.println("\tF)Agregar Skin al Personaje");
	    	System.out.println("\tG)Bloquear Jugador");
	    	System.out.println("\tH)Desplegar cuentas de Mayor a Menor Nivel");
	    	System.out.println("\tI)Cerrar Sesion");
	    	System.out.print("Seleccione una opción: ");
	    	String opcion = leer.nextLine();
	    	switch(opcion) {
	    		case("A"):
	    			break;
	    		case("B"):
	    			break;
	    		case("C"):
	    			break;
	    		case("D"):
	    			break;
	    		case("E"):
	    			break;
	    		case("F"):
	    			break;
	    		case("G"):
	    			break;
	    		case("H"):
	    			break;
	    		case("I"):
	    			break;
	    		default:System.out.println("Elija A ,B ,C , D , E ,G,H o I porfavor ");break;
	    	}
	    	if(opcion.equalsIgnoreCase("I")) {
	    		break;
	    	}
		}
		System.out.println("\tSALIENDO DEL MENU USUARIO");
	}
	
	
	
	
	
	public static void main(String [] args) {
		SystemI system = new SystemImpl();
	}
	
	public static Scanner leer = new Scanner(System.in);
	
	
}
