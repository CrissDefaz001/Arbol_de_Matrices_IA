package ejecutable;

import ambiente.Arbol;
import contenedor.Contenedor;

public class App {
	
	public static void main(String[] args) {
		
		//inicializando arbol
		Arbol a = new Arbol();
		a.inicializarArbol(); // crea el arbol
		System.out.println("Total de nodos (matrices) generadas: "+ a.nodosDelArbol.size());
		System.out.println("\nMatriz ideal (Objetivo a buscar):");
		a.imprimirMatriz(a.generarMatrizIdeal()); //muestra la matriz a buscar

		//Utilizando agentes inteligentes:
		System.out.println("|====================== Agentes ======================|");
		Contenedor con = new Contenedor();
		//inicia el contenedor, enviandole como argumento la clase arbol completa:
		con.inicializarContenedor(a); 
	}
}
