package arbol_tarea;

import java.util.ArrayList;

public class Anchura {

	public ArrayList<Nodo> nodosAnchura;
	public ArrayList<Nodo> nodosRecorridos;
	public Arbol arbol;
	public int[][] matObj;

	public Anchura(Arbol arbol, int[][] mat) {
		super();
		this.arbol = arbol;
		nodosAnchura = new ArrayList<>();
		nodosRecorridos = new ArrayList<>();
		matObj = new int[3][4];
		matObj = mat;
	}

//	public void buscarXanchura(Nodo raiz) {
//		int cont = 0; // total de nodos recorridos
//		boolean band = false;
//
//		do {
//
//			if (recorrerMatriz(matObj, raiz)) {
//				band = true;
//			} else {
//				abrirNodos(raiz);
//				nodosRecorridos.add(raiz);
//				raiz = nodosAnchura.get(cont++);
//			}
//
//		} while (band == false);
//
//	}

	public void imprimirAnchura(ArrayList<Nodo> nodos) {

		for (int i = 0; i < nodos.size(); i++) {
			System.out.println(nodosRecorridos.get(i).dato.toString());
		}

		System.out.println("Busqueda en Anchura /////");
		System.out.println("Numero de nodos recorridos = " + (nodosRecorridos.size()));

	}

	public void abrirNodos(Nodo nodo) {

		for (int i = 0; i < nodo.hijos.size(); i++) {
		//	nodosAnchura.add(nodo.hijos.get(i));
		}
	}

//	public boolean recorrerMatriz(int[][] matrizObjetivo, Nodo inicial) {
//
//		for (int i = 0; i < matrizObjetivo.length; i++) {
//			for (int j = 0; j < matrizObjetivo.length; j++) {
//				if (matrizObjetivo[i][j] != inicial.matrizAnt[i][j]) {
//					return false;
//
//				}
//
//			}
//		}
//
//		return true;
//	}

	public void inicializarBusqueda(Nodo n) {

	//	buscarXanchura(n);
		System.out.println("Nodos recorridos: " + nodosRecorridos.size());
	}

}
