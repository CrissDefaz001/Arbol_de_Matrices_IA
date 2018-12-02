package ejecutable;

import ambiente.Arbol;
import contenedor.Contenedor;

//Ejecutable de la aplicaci�n, el �rbol se crea fuera de los agenes para evitar problemas de tiempos
//es posible hacer que el agente broker genere el �rbol
//pero los resultados en consola no se muestran en orden

public class App {

	public static void main(String[] args) {

		Arbol ar = new Arbol();
		// generando �rbol
		System.out.println("|============================== �rbol ===============================|");
		ar.iniciarArbol(); // Imprime el arbol
	
		// Generando matriz ideal:
		System.out.println("\n|================= Matriz ideal (Objetivo a buscar) =================|");
		ar.imprimirMatriz(ar.generarMatrizIdeal()); // muestra la matriz a buscar
		//Inicia el contenedor utilizando agentes inteligentes con el �rbol como par�metro:
		System.out.println("\n|============================= Agentes ==============================|");
		new Contenedor().inicializarContenedor(ar); 
	}
}
