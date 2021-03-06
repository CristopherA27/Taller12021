package logica;
import ucn.*;	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	/**
	 * procedure that reads the Accounts text file
	 * @param system
	 * @throws IOException
	 */
	
	public static void leerCuentas(SystemI system) throws FileNotFoundException{
		Scanner s = new Scanner(new File("Cuentas.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombreCuenta = partes[0];
			String contraseņa = partes[1];
			String nick = partes[2];
			int nivelCuenta = Integer.parseInt(partes[3]);
			int rpCuenta = Integer.parseInt(partes[4]);
			try {
				boolean ingresado = system.ingresarCuenta(nombreCuenta, contraseņa, nick, nivelCuenta, rpCuenta);
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
	
	/**
	 * procedure that reads the Characters text file
	 * @param system
	 * @throws IOException
	 */
	
	public static void leerPersonajes(SystemI system)throws FileNotFoundException{
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
	
	/**
	 * procedure that reads the Statistics text file
	 * @param system
	 * @throws IOException
	 */
	
	public static void leerEstadisticas(SystemI system) throws FileNotFoundException{
		System.out.println("Leyendo Estadisticas");
		Scanner s = new Scanner(new File("Estadisticas.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombrePersonaje = partes[0];
			double recaudacion = Integer.parseInt(partes[1]);
			try {
				boolean ingresado = system.asociarEstadistica(nombrePersonaje, (recaudacion*6.15));
				if(!ingresado) {
					System.out.println("No se pudo ingresar la estadística por que no hay más espacio");
				}
			}catch(Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
	}
	
	/**
	 * procedure used to login
	 * @param system
	 * return true if the password is "ADMIN" or if the password belongs to an account
	 * else return false
	 */
	
	public static boolean inicioSesion(SystemI system) {
		System.out.println("BIENVENIDO A LA TIENDA ONLINNE DE RIOT GAMES");
		System.out.println();
		System.out.print("Ingrese el NombreCuenta: ");
		String nombreCuenta = leer.nextLine();
		if(nombreCuenta.equalsIgnoreCase("ADMIN")) {
			System.out.print("Ingrese la contraseņa: ");
			String contraseņa = leer.nextLine();
			if(contraseņa.equalsIgnoreCase("ADMIN")) {
				menuAdmin(system);
				return true;
			}else {
				System.out.println("Contraseņa incorrecta....");
				return false;
			}
		}else {
			boolean existeLaCuenta = system.existeCuenta(nombreCuenta);
			if(existeLaCuenta) {
				System.out.print("Ingrese la contraseņa: ");
				String contraseņa = leer.nextLine();
				boolean contraCorrect = system.contraseņaCorrecta(nombreCuenta, contraseņa);
				if(contraCorrect) {
					menuUsuario(system, nombreCuenta);
					return true;
				}else {
					System.out.println("Contraseņa incorrecta...");
					return false;
				}
			}else {
				System.out.print("Desea registrar una nueva cuenta? (SI-NO)");
				String respuesta = leer.nextLine();
				while(!respuesta.equalsIgnoreCase("Si") && !respuesta.equalsIgnoreCase("No")) {
					System.out.println("Ingrese (SI) o (NO) por favor.....");
					System.out.print("Desea registrar una nueva cuenta? (SI-NO)");
					respuesta = leer.nextLine();
				}
				if(respuesta.equalsIgnoreCase("Si")) {
					System.out.print("NombreCuenta: "); nombreCuenta = leer.nextLine();
					System.out.println("Contraseņa: "); String contraseņa = leer.nextLine();
					System.out.println("Nick: ");String nick = leer.nextLine();
					int nivelCuenta=0;
					int rpCuenta= 0;
					boolean ingresado = system.ingresarCuenta(nombreCuenta, contraseņa, nick, nivelCuenta, rpCuenta);
					if(ingresado) {
						System.out.println("Region: "); String region = leer.nextLine();
						boolean ingresoRegion = system.asignarRegion(nombreCuenta, region);
						if(ingresoRegion) {
							System.out.println("Cuenta ingresada con exito");
						}else {
							System.out.println("No se pudo ingresar la cuenta");
						}
					}else {
						System.out.println("Error al ingresar la cuenta, No queda espacio suficiente");
		
					}
					return false;
				}else {
					System.out.println("Usted no ingreso la cuenta");
					return false;
				}	
			}
			
		}

	}
	
	/**
	 * This procedure shows all the options available when you log in.
	 * @param system
	 * @param nombreCuenta
	 */
	
	public static void menuUsuario(SystemI system,String nombreCuenta) {
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
	    			System.out.print("Ingrese el nombre del personaje al cual desea comprarle la Skin: ");
	    			String nombrePersonaje = leer.nextLine();
	    			boolean existePersonaje = system.existePersonaje(nombrePersonaje);
	    			if(existePersonaje) {
	    				System.out.println(system.obtenerSkinsPersonaje(nombreCuenta, nombrePersonaje));
	    				System.out.print("Ingrese el nombre de la skin que desea comprar: ");
	    				String nombreSkin = leer.nextLine();
	    				boolean existeSkin = system.existeSkin(nombreSkin);
	    				if(existeSkin) {
	    					try {
	    						boolean comprar = system.comprarSkin(nombreCuenta, nombrePersonaje, nombreSkin);
	    						if(comprar) {
	    							System.out.println("La skin fue comprada con exito..");
	    						}else {
	    							System.out.println("El cliente no tiene suficiente dinero como pra comprar la skin");
	    						}
		    				}catch(Exception ex) {
		    					System.out.println("\t"+ex.getMessage());
		    				}
	    				}else {
	    					System.out.println("Error al ingresar la skin a comprar");
	    				}
	    			}else {
	    				System.out.println("Error al ingresar el personaje");
	    			}	
	    			break;
	    		case("B"):
	    			System.out.println("Ingrese el nombre del personaje que desea comprar: ");
	    			nombrePersonaje = leer.nextLine();
	    			boolean existeElPersonaje = system.existePersonaje(nombrePersonaje);
	    			if(existeElPersonaje) {
	    				try {
	    					boolean comprarPersonaje = system.comprarPersonaje(nombreCuenta, nombrePersonaje);
	    					if(comprarPersonaje) {
	    						System.out.println("Personaje comprado con exito");
	    					}else {
	    						System.out.println("No tiene suficiente dinero para comprar al personaje");
	    					}
	    				}catch(Exception ex) {
	    					System.out.println("\t"+ex.getMessage());
	    				}
	    			}
	    			break;
	    		case("C"):
	    			System.out.println(system.obtenerSkinsDisponibles(nombreCuenta));
	    			break;
	    		case("D"):
	    			System.out.println(system.obtenerInventario(nombreCuenta));
	    			break;
	    		case("E"):
	    			System.out.println("Cuantos RP desea agregar a la cuenta: ");
	    		    int dinero = leer.nextInt();
	    		    try {
	    		    	boolean agregarSaldo = system.recargarRp(nombreCuenta, dinero);
	    		    	if(agregarSaldo) {
	    		    		System.out.println("RP agregados a su cuenta");
	    		    	}else {
	    		    		System.out.println("No se pudo agregar los RP a la cuenta");
	    		    	}
	    		    }catch(Exception ex) {
	    		    	System.out.println("\t"+ex.getMessage());
	    		    }
	    			break;
	    		case("F"):
	    			System.out.println(system.obtenerDatosCuenta(nombreCuenta));
	    			System.out.print("Desea cambiar su contraseņa? (SI) o (NO) : ");
	    			String resp = leer.nextLine();
	    			if(resp.equalsIgnoreCase("SI")) {
	    				System.out.println("Ingrese su contraseņa actual: ");
	    				String contraseņaAntigua = leer.nextLine();
	    				boolean contraCorrecta = system.contraseņaCorrecta(nombreCuenta, contraseņaAntigua);
	    				if(contraCorrecta) {
	    					System.out.println("Ingrese la nueva contraseņa que desee: ");
	    					String contraseņaNueva = leer.nextLine();
	    					System.out.println("Confirme la nueva contraseņa ingresada: ");
	    					String contraseņaNueva1 = leer.nextLine();
	    					if(contraseņaNueva.equals(contraseņaNueva1)) {
	    						try {
	    							boolean nuevaContra = system.cambiarContraseņa(nombreCuenta, contraseņaAntigua, contraseņaNueva);
	    							if(nuevaContra) {
	    								System.out.println("Contraseņa cambiada con exito");
	    							}else {
	    								System.out.println("Error al ingresar la contraseņa");
	    							}
	    						}catch(Exception ex) {
	    							System.out.println("\t"+ex.getMessage());
	    						}
	    					}else {
	    						System.out.println("Las contraseņas nuevas no coinciden");
	    					}
	    				}else {
	    					System.out.println("La contraseņa ingresada no es la correcta");
	    				}
	    			}else {
	    				System.out.println("Datos desplegados con exito");
	    			}
	    			break;
	    		case("G"):
	    			cierre = false;
	    			break;
	    		default:System.out.println("Elija A ,B ,C , D , E o G porfavor ");break;
	    	}
	    	if(opcion.equalsIgnoreCase("G")) {
	    		break;
	    	}
		}
		System.out.println("\tSALIENDO DEL MENU USUARIO");
	}
	
	/**
	 * this procedure shows all the options available when logging in as admin
	 * @param system
	 */
	
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
	    			System.out.println(system.obtenerVentasPorRol());
	    			break;
	    		case("B"):
	    			System.out.println(system.obtenerVentasPorRegion());
	    			break;
	    		case("C"):
	    			System.out.println(system.obtenerVentasPorPersonaje());
	    			break;
	    		case("D"):
	    			System.out.println(system.obtenerPersonajesPorRol());
	    			break;
	    		case("E"):
	    			agregarPersonajeSkin(system);
	    			break;
	    		case("F"):
	    			System.out.print("Ingrese el nombre del personaje al cual desea agregarle Skins");
	    			String nombrePersonaje = leer.nextLine();
	    			boolean reconocerPersonaje = system.existePersonaje(nombrePersonaje);
	    			if(reconocerPersonaje) {
	    				System.out.print("Cuantas skins desea agregar: ");
	    				int cantSkins = leer.nextInt();
	    				leer.nextLine();
	    				for(int i=0; i<cantSkins;i++) {
	    					System.out.print("Ingrese el nombre de la skin a agregar: ");
	    					String nombreSkin = leer.nextLine();
	    					System.out.print("Ingrese la calidad de la skin a agregar: ");
	    					String calidadSkin = leer.nextLine();
	    					try {
	    						boolean ingresarSkin = system.agregarSkin(nombrePersonaje, nombreSkin, calidadSkin);
	    						if(ingresarSkin) {
	    							System.out.println("Skin ingresada con exito");
	    						}else {
	    							System.out.println("No se pudo ingresar la skin ");
	    						}
	    					}catch(Exception ex) {
	    						System.out.println("\t"+ex.getMessage());
	    					}
	    				}
	    			}
	    			break;
	    		case("G"):
	    			System.out.print("Ingrese el nombre de la Cuenta que desea bloquear:");
	    			String nombreCuenta = leer.nextLine();
	    			try {
	    				boolean bloquear = system.bloquearJugador(nombreCuenta);
	    				if(bloquear) {
	    					System.out.println("Cuenta bloqueada con exito");
	    				}else {
	    					System.out.println("No se pudo bloquear la cuenta por que no existe");
	    				}
	    			}catch(Exception e) {
	    				System.out.println("\t"+e.getMessage());
	    			}
	    			break;
	    		case("H"):
	    			System.out.println(system.obtenerCuentasMayorAMenor());
	    			break;
	    		case("I"):
	    			cierre = false;
	    			break;
	    		default:System.out.println("Elija A ,B ,C , D , E ,G,H o I porfavor ");break;
	    	}
	    	if(opcion.equalsIgnoreCase("I")) {
	    		break;
	    	}
		}
		System.out.println("\tSALIENDO DEL MENU CLIENTE");
	}
	
	/**
	 * allows you to add a champion along with their skins to an account
	 * @param system
	 */
	
	public static void agregarPersonajeSkin(SystemI system) {
		System.out.print("Ingrese el nombre del personaje que desea agregar: ");
		String nombrePersonaje = leer.nextLine();
		System.out.print("Ingrese el rol del personaje que desea agregar: ");
		String rol = leer.nextLine();
		System.out.print("Cuantas skins desea agregar al personaje?: ");
		int cantSkins = leer.nextInt();
		leer.nextLine();
		while(cantSkins <= 0) {
			System.out.print("Cuantas skins desea agregar al personaje?: ");
			cantSkins = leer.nextInt();
			if(cantSkins>0) {
				break;
			}
		}
		if(cantSkins==1 && cantSkins>0) {
			System.out.print("Ingrese el nombre de la skin a agregar: ");
			String nombreSkin = leer.nextLine();
			System.out.print("Ingrese la calidad de la skin a agregar: ");
			String calidadSkin = leer.nextLine();
			boolean agregarPerso = system.agregarPersonaje(nombrePersonaje, rol, nombreSkin, calidadSkin);
			if(agregarPerso) {
				System.out.println("Personaje agregado con exito.");
			}else {
				System.out.println("No se pudo agregar el personaje con sus skins");
			}
		}else if (cantSkins>1){
			for(int i=0;i<cantSkins;i++) {
				System.out.print("Ingrese el nombre de la skin a agregar: ");
				String nombreSkin = leer.nextLine();
				System.out.print("Ingrese la calidad de la skin a agregar: ");
				String calidadSkin = leer.nextLine();
				try {
					boolean agregarSkin = system.agregarSkin(nombrePersonaje, nombreSkin, calidadSkin);
					if(agregarSkin) {
						System.out.println("Skin agregada con exito al personaje");
					}else {
						System.out.println("No se pudo agregar la skin al personaje");
					}
				}catch(Exception ex) {
					System.out.println("\t"+ ex.getMessage());
				}
				
			}
		}
			
	}
	
	/**
	 * It will overwrite the new data in the corresponding txt
	 * @param system
	 * @throws IOException
	 */
	
	public static void sobreescribir(SystemI system) throws IOException {
		ArchivoSalida arch = new ArchivoSalida("Cuentas.txt");
		Registro registroSalida = new Registro(1);
		registroSalida.agregarCampo(system.obtenerCuentas());
		arch.writeRegistro(registroSalida);
		arch.close();
		
		ArchivoSalida arch1 = new ArchivoSalida("Personajes.txt");
		Registro registroSalida1 = new Registro(2);
		registroSalida1.agregarCampo(system.obtenerPersonajes());
		arch1.writeRegistro(registroSalida1);
		arch1.close();
		
		ArchivoSalida arch2 = new ArchivoSalida("Estadisticas.txt");
		Registro registroSalida2 = new Registro(3);
		registroSalida2.agregarCampo(system.obtenerEstadisticas());
		arch2.writeRegistro(registroSalida2);
		arch2.close();
		
	}
	
	/**
	 * main function
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String [] args) throws IOException {
		SystemI system = new SystemImpl();
		leerPersonajes(system);
		leerEstadisticas(system);
		leerCuentas(system);
		
		while(true) {
			boolean usuario = inicioSesion(system);
			if(usuario) {
				System.out.println("Desea cerrar el sistema? (Si) o (No)");
				String resp = leer.nextLine();
				while(!resp.equalsIgnoreCase("Si") && !resp.equalsIgnoreCase("No")) {
					System.out.println("Desea cerrar el sistema? (Si) o (No)");
					resp = leer.nextLine();
				}
				if(resp.equalsIgnoreCase("Si")) {
					break;
				}
			}
		}
		sobreescribir(system);
		leer.close();
	}
	
	/**
	 * this this function overwrites the changed data
	 * @param system
	 * @throws IOException 
	 */
	

	public static Scanner leer = new Scanner(System.in);
	
	
}
