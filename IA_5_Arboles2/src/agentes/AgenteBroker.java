package agentes;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

@SuppressWarnings("serial")
public class AgenteBroker extends Agent {

	// Object[] args;

	@Override
	protected void setup() {

		// msj ;
		addBehaviour(new ComportamientoAgenteBroker());
	}

	@Override
	protected void takeDown() {
		super.takeDown();
	}

	class ComportamientoAgenteBroker extends Behaviour {

		@Override
		public void action() {

			ArrayList<Integer> mensajes = new ArrayList<>();
//			ArrayList<Nodo<int[][]>> lista2 = new ArrayList<>();
			int a1 = 0;
			int a2 = 0;
			String a = "";
			String b = "";
			// System.out.println("Hola ! soy: " + getName());
			StringBuffer cadena = new StringBuffer();
			cadena.append("\n|===================== Resultados =====================|\n");
			ACLMessage acl = blockingReceive();
//			try {
//				mensajes.add((Integer) acl.getContentObject());
//			} catch (UnreadableException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			if (acl.getConversationId().equals("A->B")) {
				try {
					mensajes.add((Integer) acl.getContentObject());
				} catch (UnreadableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					a1 = (int) acl.getContentObject();
					a = acl.getSender().getName();
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
				cadena.append(a).append(": recorrió en anchura: ").append(a1).append(" nodos\n");

			}
			// ACLMessage acl2 = blockingReceive();
			if (acl.getConversationId().equals("P->B")) {
				try {
					mensajes.add((Integer) acl.getContentObject());
				} catch (UnreadableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					a2 = (int) acl.getContentObject();
					b = acl.getSender().getName();
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
				cadena.append(b).append(": rercorrió en profundidad: ").append(a2).append(" nodos\n");

			}

			
			if (mensajes.size() == 2) {
				int max = 45646546;
				for (int i = 0; i < mensajes.size(); i++) {
					if (mensajes.get(i) < max) {
						max = mensajes.get(i);
					}
				}
				
				cadena.append("\nMejor camino: ").append(max).append(" nodos");	
			}//fin if
			
			System.out.println(cadena.toString());
		//	System.out.println("Lista: "+mensajes.size());
			// doDelete();
		}

		@Override
		public boolean done() {
			return false;
		}

	}
}
