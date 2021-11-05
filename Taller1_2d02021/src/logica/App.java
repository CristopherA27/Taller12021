package logica;

import java.io.IOException;
import java.util.Scanner;

public class App {
	
	
	public static void leerPersonaje(SystemI system) throws IOException{
		System.out.println("Leyendo personajes");
		Scanner s = new Scanner("Personajes.txt");
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			
		}
		
	}
	
	public static void main(String [] args) {
		SystemI system = new SystemImpl();
	}
}
