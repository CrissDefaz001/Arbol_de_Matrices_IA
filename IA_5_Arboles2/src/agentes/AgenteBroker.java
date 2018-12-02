package agentes;

import java.util.Arrays;

import ambiente.Arbol;
import ambiente.DatosSeriales;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

//Agente principal que recibe todos los mensajes de los agentes y los muestra por consola o JOptionPane
@SuppressWarnings("serial")
public class AgenteBroker extends Agent {

	Arbol ar;
	DatosSeriales s;
	Object[][] datos = new Object[3][3]; // matriz que guarda los datos recibidos
	// columna 0: numero de nodos recorridos
	// columna 1: tipo de recorrido
	// columna 2: nombre del agente que envia
	boolean bandera = false;

	protected void setup() {

		addBehaviour(new ComportamientoAgenteBroker());
	}

	protected void takeDown() {
		System.out.println("\n" + getName() + " terminó su tareas y ha muerto :(");
		super.takeDown();

	}

	class ComportamientoAgenteBroker extends Behaviour {

		public void action() {

			int cont = 0; // contador de mensajes
			// Recibiendo datos (listas de recorridos) de cda uno de los agentes:
			// Utilizamos un while para asegurarnos de recibir todos los mensajes del
			// blockingReceive:
			while (cont < 3) {
				// Recibe una cola de mensajes, en un intervalo de 2000 milisegundos
				ACLMessage acl = blockingReceive(2000);
				if (acl.getConversationId().equals("A->B")) { // AgenteAnchura
					try {
						s = (DatosSeriales) acl.getContentObject();
						datos[0][0] = s.getLista().size(); // nodos recorridos
						datos[0][1] = s.getInfo(); // nodos recorridos
						datos[0][2] = acl.getSender().getName(); // quien envia
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
				}

				if (acl.getConversationId().equals("P->B")) { // AgenteProfundidad
					try {
						s = (DatosSeriales) acl.getContentObject();
						datos[1][0] = s.getLista().size(); // nodos recorridos
						datos[1][1] = s.getInfo(); // nodos recorridos
						datos[1][2] = acl.getSender().getName(); // quien envia
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
				}
				if (acl.getConversationId().equals("H->B")) { // AgenteAnchura
					try {
						s = (DatosSeriales) acl.getContentObject();
						datos[2][0] = s.getLista().size(); // nodos recorridos
						datos[2][1] = s.getInfo(); // nodos recorridos
						datos[2][2] = acl.getSender().getName(); // quien envia
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
				}
				cont++; // incrementa el contador
			}

			System.out.println("\n|====================== Resultados ======================|");
			ordenarCaminos();
			bandera = true;
			doDelete(); // finaliza todas las tareas y muere para liberar recursos
		}

		// Metodo adpatado para detectar el mejor y el peor camino recorrido:
		// Es un método útil solamente cuando tenemos 3 recorridos:
		StringBuffer sb = new StringBuffer();

		// Imprime los resultados y determina el mejor y peor camino
		public void ordenarCaminos() {
			int[] n = new int[datos.length];
			for (int i = 0; i < datos.length; i++) {
				n[i] = (int) datos[i][0];
				// Imprimiendo resultados
				sb.append(datos[i][2]).append(datos[i][1]).append("").append(datos[i][0]).append(" nodos");
				System.out.println(sb.toString());
				sb.delete(0, sb.length()); // renicia en blanco la cadena
			}
			Arrays.sort(n); // ordena los datos
			System.out.println("\nMejor camino: " + n[0] + " nodos recorridos por: " + datos[buscarIndice(n[0])][2]);
			System.out.println("Peor camino: " + n[datos.length - 1] + " nodos recorridos por: "
					+ datos[buscarIndice(n[datos.length - 1])][2]);
		}

		// busca los indices del mejor y peor recorrido
		// para extraer toda su informacion de la matriz 'datos'
		public int buscarIndice(int a) {
			for (int i = 0; i < datos.length; i++) {
				if (a == (int) datos[i][0]) {
					return i;
				}
			}
			return 0;
		}

		public boolean done() {
			return bandera;
		}

	}
}
