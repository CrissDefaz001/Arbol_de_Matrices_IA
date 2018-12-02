package ambiente;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {

	Nodo<int[][]> raiz = new Nodo<int[][]>(null, "matriz"); // nodo padre inicial

	Random r = new Random();
	// Arreglo de valores randomicos, valor randómico por funda
	int[] vrf = { generarRandom(), generarRandom(), generarRandom() };

	// estructura de arbol
	public ArrayList<Nodo<int[][]>> nodosDelArbol = new ArrayList<>();

	// Insertar la primera matriz vacia (raiz)

	// Retorna un arraylist con el arbol de matrices
	public void iniciarArbol() {
		int[][] inicial = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }; // encerando raiz
		crearMatricesHijos(inicial, raiz, 0); // genera primeros hijos del padre
		raiz.setData(inicial);
		nodosDelArbol.add(raiz);// agregando a un arbol
		generarArbol(raiz, 1); // hijos de hijos, desde la columna 1
		imprimirArbol(raiz, 1); // imprime el arbol, desde el nivel 1
		System.out.println("Total de nodos (matrices) generadas: " + nodosDelArbol.size());
	}

	// metodo recursivo para crear el arbol y guardarlo en un ArrayList
	public void generarArbol(Nodo<int[][]> padre, int col) {

		for (Nodo<int[][]> each : padre.getHijos()) {
			nodosDelArbol.add(each);
			if (col < 4) {
				crearMatricesHijos(each.getData(), each, col);
				generarArbol(each, col + 1);
			}
		}
	}

	// Genera un randómico entre 1500 y 2500 para rellenar las matrices
	public int generarRandom() {
		return 1500 + r.nextInt(2500);
	}

	// Matriz ideal para busqueda en anchura, (por criterio empírico)
	public int[][] generarMatrizIdeal() {
		int[][] m = { { vrf[0], 0, vrf[0], vrf[0] }, { vrf[1], vrf[1], vrf[1], 0 }, { 0, vrf[2], 0, vrf[2] } };
		return m;
	}

	// Genera una lista de nodos hijos a partir de un nodo Padre
	public void crearMatricesHijos(int[][] ant, Nodo<int[][]> padre, int col) {

		ArrayList<Nodo<int[][]>> listaHijos = new ArrayList<>();

		int nh = 1; // cuenta los hijos

		for (int i = ant.length - 1; i >= 0; i--) { // genera 6 matrices
			int[][] n = new int[3][4];
			copiarMatriz(n, ant);
			for (int j = i; j < ant.length; j++) {
				n[j][col] = vrf[j];
			}
			listaHijos.add(new Nodo<int[][]>(n, padre.info.toString() + " " + nh++));

			int[][] m = new int[3][4];
			copiarMatriz(m, ant);
			for (int k = 0; k <= i; k++) {
				m[k][col] = vrf[k];
			}
			listaHijos.add(new Nodo<int[][]>(m, padre.info.toString() + " " + nh++));
		}

		int[][] m1 = new int[3][4], m2 = new int[3][4];
		// generar matriz 7
		copiarMatriz(m1, ant);
		m1[0][col] = vrf[0];
		m1[2][col] = vrf[2];
		listaHijos.add(new Nodo<int[][]>(m1, padre.info.toString() + " " + nh++));
		// generar matriz 8
		copiarMatriz(m2, ant);
		m2[1][col] = vrf[1];
		// en total genera una lista de 8 hijos posibles sin control de repetidos
		// depurando la lista de hijos segun criterios y agregandolos a la lista de
		// hijos
		listaHijos.add(new Nodo<int[][]>(m2, padre.info.toString() + " " + nh++));

		// creando hijos válidos del nodo pasado como parámetro
		padre.crearHijos((validarLista(listaHijos, col)));
	}

	// Metodo para copiar la matriz anterior para generar los hijos en la columna
	// siguiente
	public void copiarMatriz(int[][] matriz1, int[][] matriz2) {
		if (matriz1 != null) {
			for (int i = 0; i < matriz1.length; i++) {
				for (int j = 0; j < matriz1.length + 1; j++) {
					matriz1[i][j] = matriz2[i][j];
				}
			}
		}
	}

	// Validación de criterios a partir de una lista de hijos posibles:
	/*
	 * criterio 1: Utilizar máximo hasta 2 fundas por cada trimestre criterio 2:
	 * Serán matrices válidas si en cada trimestre (columna) la utilización de
	 * ciertas fundas generan ganancias mayores a $3500 Si cumple la comdición, se
	 * almacena en la lista de matrices válidas
	 */
	// Retornará una lista depurada
	public ArrayList<Nodo<int[][]>> validarLista(ArrayList<Nodo<int[][]>> listaIni, int col) {
		ArrayList<Nodo<int[][]>> listaFin = new ArrayList<>();
		int utilidad = 0, cont = 0;
		for (int i = 0; i < listaIni.size(); i++) { // recorre la lista
			for (int j = 0; j < 3; j++) {
				if (listaIni.get(i).getData()[j][col] != 0) { // recorre la columna de cada matriz
					cont++;
					utilidad += listaIni.get(i).getData()[j][col];
				}
			}
			if (utilidad >= 3500 && cont < 3) { // validacion de criterios
				listaFin.add(new Nodo<int[][]>(listaIni.get(i).getData(), listaIni.get(i).info));
			}
			utilidad = 0;
			cont = 0; // reiniciando contadores
		}
		return listaFin;
	}

	// Imprimir matriz
	public void imprimirMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {// recorro las filas
			System.out.println();
			for (int j = 0; j <= matriz.length; j++) { // recorro columnas
				System.out.printf("%-5s", matriz[i][j] + " ");
			}
		}
		System.out.println();
	}

	// imprime el arbol como tal, agregando espacios por niveles
	public void imprimirArbol(Nodo<int[][]> padre, int level) {
		// System.out.println(); //descomentar para separar matrices
		for (int i = 1; i < level; i++) {
			System.out.print("    ");
		}
		System.out.println(padre.info);
		for (Nodo<int[][]> each : padre.getHijos()) {
			// imprimirMatriz(each.getData()); //descomentar para ver matrices generadas
			imprimirArbol(each, level + 1);
		}
	}

}// fin clase Arbol
