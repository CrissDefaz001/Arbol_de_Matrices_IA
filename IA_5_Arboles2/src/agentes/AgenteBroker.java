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
			int a1 = 0, a2 = 0, a3 = 0,cont =0;
			String a = "", b = "", c = "";
			StringBuffer cadena = new StringBuffer(); //recolecta los resultados de cada agente
			cadena.append("\n|===================== Resultados =====================|\n");
			while(cont < 3) {
				ACLMessage acl = blockingReceive();
				if (acl.getConversationId().equals("A->B")) { //AgenteAnchura
					try {
						mensajes.add((Integer) acl.getContentObject());
						a1 = (int) acl.getContentObject();
						a = acl.getSender().getName();
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
					cadena.append(a).append(": recorrió en anchura: ").append(a1).append(" nodos\n");
				}
				
				if (acl.getConversationId().equals("P->B")) { //AgenteProfundidad
					try {
						mensajes.add((Integer) acl.getContentObject());
						a2 = (int) acl.getContentObject();
						b = acl.getSender().getName();
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
					cadena.append(b).append(": recorrió en profundidad: ").append(a2).append(" nodos\n");
				}
				
				if (acl.getConversationId().equals("H->B")) { //AgenteAnchura
					try {
						mensajes.add((Integer) acl.getContentObject());
						a3 = (int) acl.getContentObject();
						c = acl.getSender().getName();
					} catch (UnreadableException e1) {
						e1.printStackTrace();
					}
					cadena.append(c).append(": recorrió en heuristica: ").append(a3).append(" nodos\n");
				}
				cont++;
			}
		
			bandera = true;
			
			if (mensajes.size() == 3) {
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
