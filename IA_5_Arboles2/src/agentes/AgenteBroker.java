package agentes;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

@SuppressWarnings("serial")
public class AgenteBroker extends Agent {
	
	boolean bandera=false;
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
			int a1 = 0, a2 = 0, cont =0;
			String a = "", b = "";
			StringBuffer cadena = new StringBuffer();
			cadena.append("\n|===================== Resultados =====================|\n");
			while(cont < 2) {
				ACLMessage acl = blockingReceive();
				if (acl.getConversationId().equals("A->B")) {
					try {
						mensajes.add((Integer) acl.getContentObject());
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
					try {
						a1 = (int) acl.getContentObject();
						a = acl.getSender().getName();
					} catch (UnreadableException e) {
						e.printStackTrace();
					}
					cadena.append(a).append(": recorri� en anchura: ").append(a1).append(" nodos\n");

				}
				
				if (acl.getConversationId().equals("P->B")) {
					try {
						mensajes.add((Integer) acl.getContentObject());
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
					try {
						a2 = (int) acl.getContentObject();
						b = acl.getSender().getName();
					} catch (UnreadableException e) {
						e.printStackTrace();
					}
					cadena.append(b).append(": recorri� en profundidad: ").append(a2).append(" nodos\n");

				}
				cont++;
			}
		
			bandera = true;
			
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
		}

		@Override
		public boolean done() {
			return bandera;
		}

	}
}
