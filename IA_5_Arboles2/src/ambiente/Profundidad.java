package ambiente;

import java.util.ArrayList;
import java.util.Stack;

public class Profundidad {

	public ArrayList<Nodo<int[][]>> nodosDelArbol;
	public ArrayList<Nodo<int[][]>> recorrido;
	public int[][] mat_ideal;
	Arbol a = new Arbol();

	// Constructor que recibe el arbol y la matriz ideal
	public Profundidad(ArrayList<Nodo<int[][]>> nodosDelArbol, int[][] m) {

		this.nodosDelArbol = nodosDelArbol; // Recibe al arraylist de la clase Arbol
		recorrido = new ArrayList<>(); // ArrayList que almacenará el recorido de nodos
		mat_ideal = new int[3][4];
		mat_ideal = m; // guarda la matriz ideal m en una variable para esta clase
	}

	// Inicia la búsqueda y retorna el tamaño de nodos recorridos
	public int inicializarBusqueda() {
		Nodo<int[][]> padre = nodosDelArbol.get(0);
		System.out.println("|======== Búsqueda en Profundidad =======|");
		buscarDFS(padre);
		System.out.println("Total nodos recorridos: " + recorrido.size());
		imprimirRecorrido(recorrido);
		return recorrido.size();
	}

	// La busqueda en profundidad utiliza una pila para guardar los nodos y
	// recorrelas
	private void buscarDFS(Nodo<int[][]> raiz) {
		Stack<Nodo<int[][]>> pila = new Stack<>();
		pila.push(raiz); //Añade la raiz a la pila
		recorrido.add(raiz); //Añade la raiz al recorrido
		while (pila.size() > 0) {
			Nodo<int[][]> temp = pila.pop();  //nodo temporal para recorrer hijos
			for (Nodo<int[][]> each : temp.getHijos()) {
				recorrido.add(each); // añade sus hijos al recorrido
				if (buscarMatriz(mat_ideal, each)) {
					System.out.println("¡¡Encontré la matriz con profundidad!!");
					return;
				} else {
					pila.push(each); // Agrega el nodo a la cola hasta hallar el ideal
				}
			}
		}

	}

	// Muestra las matrices recorridas hasta alcanzar el objetivo
	private void imprimirRecorrido(ArrayList<Nodo<int[][]>> nodoIni) {
		for (int i = 0; i < nodoIni.size(); i++) {
			System.out.println(recorrido.get(i).info);
		}
	}

	// Compara la matriz contenida en el nodo con la matriz ideal
	private boolean buscarMatriz(int[][] m_ideal, Nodo<int[][]> nodo) {
		for (int i = 0; i < m_ideal.length; i++) {
			for (int j = 0; j < m_ideal.length + 1; j++) {
				if (m_ideal[i][j] != nodo.getData()[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
