package ambiente;

import java.util.ArrayList;

public class Heuristica {

	public ArrayList<Nodo<int[][]>> nodosArbol; // lista de nodos del arbol a recorrer
	public ArrayList<Nodo<int[][]>> recorrido; // lista de nodos recorridos hasta objetivo
	public int[][] mat_ideal;

	// Contructor
	public Heuristica(ArrayList<Nodo<int[][]>> nodosDelArbol, int[][] m) {
		this.nodosArbol = nodosDelArbol;
		this.recorrido = new ArrayList<>();
		this.mat_ideal = new int[3][4];
		mat_ideal = m;
	}

	public ArrayList<Nodo<int[][]>> iniciarBusqueda() {
		Nodo<int[][]> padre = nodosArbol.get(0);
		System.out.println("\n|================== Búsqueda Heuristica =================|");
		int columna = 0;
		buscarHeuristica(padre, columna);
		System.out.println("\n|======================= Recorrido ======================|");
		imprimirRecorrido(recorrido);
		return recorrido;
	}

	public void buscarHeuristica(Nodo<int[][]> padre, int col) {
		int hijo = 0;
		recorrido.add(padre);
		do {
			for (int i = 0; i < padre.hijos.size(); i++) {
				int pesoHijo = calcularPesoHijo(padre.getHijos().get(i), col);
				System.out.println(padre.getHijos().get(i).info + "  Tiene un peso de: " + pesoHijo);
				// si el nodo hijo recorrido tiene una columna con 3 nodos coincidentes
				// ese nodo hijo pasa a ser el nuevo nodo padre.
				if (pesoHijo == 3) {
					recorrido.add(padre.hijos.get(i));
					hijo = i;
				}
			}
			padre = padre.getHijos().get(hijo); // nodo hijo pasa a ser padre para expandirlo
			col++;
			// el ciclo continua hasta alcanzar la última columna de la matriz ideal
		} while (col <= mat_ideal.length);
		System.out.println("Encontré la matriz con heurística!: " + padre.info);
		return;
	}

	// Muestra las matrices recorridas hasta alcanzar el objetivo
	private void imprimirRecorrido(ArrayList<Nodo<int[][]>> nodoIni) {
		for (int i = 0; i < nodoIni.size(); i++) {
			System.out.println(recorrido.get(i).info);
		}
	}

	// Calcula un score (peso) entre un padre y los nodos hijos
	// Devuelve el numero de elementos coincidentes en una columna col
	// Si coincide la columna completa devolverá 3
	public int calcularPesoHijo(Nodo<int[][]> padre, int col) {
		int cont = 0;
		for (int i = 0; i < padre.getData().length; i++) {
			if (col < 4) {
				// compara columnas de matriz ideal y nodo padre actual
				if (padre.getData()[i][col] == mat_ideal[i][col]) {
					cont++; // el contador aumenta por cada elemento coincidente
				}
			}

		}
		return cont;
	}

}
