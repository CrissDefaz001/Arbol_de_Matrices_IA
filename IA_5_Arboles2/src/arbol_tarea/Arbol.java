package arbol_tarea;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {

	Random r = new Random();

	// Insertar la primera matriz vacia (raiz)
	public void inicializarArbol() {
		int[][] m_vacia = new int[3][4];
		Nodo<int[][]> raiz = new Nodo<int[][]>(null, "matriz"); //nodo padre inicial
		crearMatricesHijos(m_vacia, raiz, 0); //genera primeros hijos del padre

		generarArbol(raiz, 1); //hijos de hijos
		System.out.println("======================= Arbol =======================");
		imprimirArbol(raiz, 1);
		
	}
	
	public void generarArbol(Nodo<int[][]> padre, int col) {
			
		for (Nodo<int[][]> each : padre.getHijos()) {
			if(col < 4) {
				crearMatricesHijos(each.getData(), each,  col);
				generarArbol(each,  col+1);
			}
		}	
	}

	
	// Genera un randómico entre 1500 y 2500 para rellenar las matrices
	public int generarRandom() {
		
		return 1500 + r.nextInt(2500);
	}
	
	// Genera una lista de nodos hijos a partir de un nodo Padre
	public void crearMatricesHijos(int[][] ant, Nodo<int[][]> padre, int col) {

		ArrayList<Nodo<int[][]>> listaHijos = new ArrayList<>();
		// Arreglo de valores randomicos
		int[] valores = { generarRandom(), generarRandom(), generarRandom() };
		int nh = 1; //cuenta los hijos

		for (int i = ant.length - 1; i >= 0; i--) {
			int[][] n = new int[3][4];
			copiarMatriz(n, ant);
			for (int j = i; j < ant.length; j++) {
				n[j][col] = valores[j];
			}
			listaHijos.add(new Nodo<int[][]>(n, padre.info.toString() +" " +nh++));

			int[][] m = new int[3][4];
			copiarMatriz(m, ant);
			for (int k = 0; k <= i; k++) {
				m[k][col] = valores[k];
			}
			listaHijos.add(new Nodo<int[][]>(m,  padre.info.toString() +" " +nh++));
		}

		int[][] m1 = new int[3][4], m2 = new int[3][4];
		copiarMatriz(m1, ant);
		m1[0][col] = valores[0];
		m1[2][col] = valores[2];
		listaHijos.add(new Nodo<int[][]>(m1,  padre.info.toString() +" " +nh++));
		copiarMatriz(m2, ant);
		m2[1][col] = valores[1];
		listaHijos.add(new Nodo<int[][]>(m2,  padre.info.toString() +" " +nh++));
		//en total genera una lista de 8 hijos posibles
		//depurando la lista de hijos segun criterios
		//solo se crean hijos válidos
		padre.crearHijos((validarLista(listaHijos, col)));
		// System.out.println(padre.getHijos().size());
	}
	
	// Metodo para copiar la matriz anterior para generar los hijos
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
	criterio 1: Utilizar máximo hasta 2 fundas por cada trimestre
	criterio 2: Serán matrices válidas si en cada trimestre (columna)
	la utilización de ciertas fundas generan ganancias mayores a $3000
	Si cumple la comdición, se almacena en la lista de matrices válidas
	*/
	//Retornará una lista depurada
	public ArrayList<Nodo<int[][]>> validarLista(ArrayList<Nodo<int[][]>> listaIni, int col){
		ArrayList<Nodo<int[][]>> listaFin = new ArrayList<>();
		int utilidad = 0, cont = 0;
		for (int i = 0; i < listaIni.size(); i++) {		//recorre la lista
			for (int j = 0; j < 3; j++) {	
				if (listaIni.get(i).getData()[j][col] != 0) {	//recorre la columna de cada matriz
					cont++;
					utilidad += listaIni.get(i).getData()[j][col];
				}
			}
			if (utilidad >= 3000 && cont < 3) {
			//	int nh = 0;
				listaFin.add(new Nodo<int[][]>(listaIni.get(i).getData(), listaIni.get(i).info));
				//System.out.println(listaFin.size());
			}
			utilidad = 0; cont = 0;
		}
		return listaFin;
	}
		
	public void imprimirMatriz(int[][] matriz) {
		// Imprimir matriz
		for (int i = 0; i < matriz.length; i++) {// recorro las filas
			System.out.println();
			for (int j = 0; j <= matriz.length; j++) {
				System.out.printf("%-5s", matriz[i][j] + " ");
			}
		}
	}

	public void imprimirHijos(Nodo<int[][]> padre) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < padre.hijos.size(); i++) {
			buffer.append("\n").append(padre.hijos.get(i).info.toString()).append(" de ").append(padre.info);
			System.out.print(buffer.toString());
			imprimirMatriz(padre.hijos.get(i).getData());
			System.out.println();
		}
		System.out.println();
	}

	public void imprimirArbol(Nodo<int[][]> padre, int level) {
		for (int i = 1; i < level; i++) {
			System.out.print("    ");
		}
		System.out.println(padre.info);
	//	imprimirHijos(padre);
		for (Nodo<int[][]> each : padre.getHijos()) {
			imprimirArbol(each, level + 1);
		}
		
	}


}//fin clase Arbol
