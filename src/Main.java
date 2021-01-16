import entidades.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);

		List<Equipo> equipos = new ArrayList<>();
		List<String> globos = new ArrayList<String>();

		globos.add("Rojo");
		globos.add("Amarillo");
		globos.add("Azul");
		globos.add("Verde");

		int opcion = 0;

		while (opcion != 3) {

			System.out.println("--- Menu ---");
			System.out.println("1.- Agregar globo");
			System.out.println("2.- Comenzar concursos!");
			System.out.println("3.- Salir");
			
			opcion = scanner.nextInt();

			switch (opcion) {
				case 1:
					System.out.println("--- Agregar globo ---");
					System.out.println("---> Por defecto existen 4: Rojo, Amarillo, Azul, Verde");
					String color = scanner.next();
					String resultado = agregarGlobos(globos, color);
					System.out.println("-------------------------------");
					System.out.println(resultado);
					System.out.println("							   ");
					break;
				case 2:
					System.out.println("--- Comenzar concursos! ---");
					int numeroEquiposParticipantes=0, numeroGlobosEntregados=0;
					do {
						System.out.println("---> Ingrese cantidad de equipos participantes");
						numeroEquiposParticipantes = scanner.nextInt();

						if(numeroEquiposParticipantes <= 20){
							System.out.println("---> Ingrese cantidad de globos entregados");
							numeroGlobosEntregados = scanner.nextInt();
							comenzarConcurso( equipos, globos, numeroEquiposParticipantes,numeroGlobosEntregados);
						}else{
							System.out.println("Error: Ingrese una cantidad entre 1 a 20 para los equipos!");
						}

						if(!equipos.isEmpty()){
							equipos.forEach(System.out::println);
						}

					} while ( numeroEquiposParticipantes != 0 && numeroGlobosEntregados != 0);

					if(!equipos.isEmpty()){

						resultado = eligirGanador(equipos);

						System.out.println("							           ");
						System.out.println("---------------------------------------");
						System.out.println(resultado);
						System.out.println("--- Adios!, Gracias por participar! ---");
						System.out.println("---------------------------------------");

						equipos.clear();
					}
					break;
				case 3:
					System.out.println("							           ");
					System.out.println("---------------------------------------");
					System.out.println("--- Adios!, Gracias por participar! ---");
					System.out.println("---------------------------------------");
					break;
				default:
					System.out.println("							           ");
					System.out.println("---------------------------------------");
					System.out.println("--- Adios!, Gracias por participar! ---");
					System.out.println("---------------------------------------");
					break;
			}
		}

		scanner.close();

	}
	
	private static String eligirGanador(List<Equipo> equipos) {

		Equipo equipoConMasGlobos = equipos.stream().max(Comparator.comparing(e -> e.getNumeroDeGlobosAsignados())).get();
		equipos.remove(equipoConMasGlobos);
		Equipo segundoEquipoConMasGlobos = equipos.stream().filter( e -> e.getNumeroDeGlobosAsignados() == equipoConMasGlobos.getNumeroDeGlobosAsignados()).findFirst().orElse(null);
		
		if(segundoEquipoConMasGlobos!=null){
			return "Es un empate!";
		}else{
			return "El equipo numero "+ equipoConMasGlobos.getNumeroEquipo() + " con " + equipoConMasGlobos.getNumeroDeGlobosAsignados() + " globos es el ganador!, Felicidades!";
		}
		
	}

	private static String agregarGlobos(List<String> globos, String color) {
		if (globos.contains(color)) {
			return "Este globo ya existe, porfavor, agrege otro.";
		} else if (color.length() > 20) {
			return "El color del globo tiene un limite maximo de 20 letras, porfavor, agrege otro.";
		} else {
			globos.add(color);
			return "El globo fue agregado.";
		}
	}

	private static void comenzarConcurso(List<Equipo> equipos, List<String> globos, int numeroEquiposParticipantes,int numeroGlobosEntregados) {

		Random random = new Random();

		for (int i = 0; i < numeroGlobosEntregados; i++) {

			int numeroEquipoAsignado = random.nextInt(numeroEquiposParticipantes) + 1;
			int numeroGloboAsignado = random.nextInt(globos.size());

			boolean existeEquipo = equipos.stream().anyMatch(e -> e.getNumeroEquipo() == numeroEquipoAsignado);	

			//Para añadir nuevo globo a equipo ya existente o uno nuevo
			if (!existeEquipo) {
				Equipo equipo = new Equipo();
				equipo.setNumeroEquipo(numeroEquipoAsignado);
				equipo.getGlobosAsignados().add(globos.get(numeroGloboAsignado));
				equipos.add(equipo);
			} else {
				Equipo equipo = equipos.stream().filter( e -> e.getNumeroEquipo() == numeroEquipoAsignado).findFirst().get();
				boolean existeGlobo = false; 
				//Para que no se repitan los globos
				do {
					existeGlobo = equipo.getGlobosAsignados().contains(globos.get(numeroGloboAsignado)); 

					if (!existeGlobo) {
						equipo.getGlobosAsignados().add(globos.get(numeroGloboAsignado));
					}else{
						// Validación para cuando un equipo ya tenga todos los globos existentes y se produzca un blucle infinitno buscando los globos que no tienen.
						if(equipo.getGlobosAsignados().size() == globos.size()){
							existeGlobo=false;
						}else{
							numeroGloboAsignado = random.nextInt(globos.size());
						}
					}

				} while (existeGlobo);
			}
		}
	}

}
