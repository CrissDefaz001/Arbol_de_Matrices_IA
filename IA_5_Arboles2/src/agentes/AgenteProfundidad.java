package agentes;

import java.io.IOException;

import ambiente.Arbol;
import ambiente.Profundidad;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteProfundidad extends Agent {

	@Override
	protected void takeDown() {

		super.takeDown();
	}

	@Override
	protected void setup() {

		addBehaviour(new ComportamientoAgenteProf());
		super.setup();
	}

	class ComportamientoAgenteProf extends Behaviour {

		@Override
		public void action() {

			Object obj = (Object) getArguments()[0]; //capturamos la clase arbol enviada desde el contenedor
			int a = 0;
			//instaciamos la clase profunidad 
			Profundidad prof = new Profundidad(((Arbol) obj).nodosDelArbol, ((Arbol) obj).generarMatrizIdeal());
			//ya podemos iniciar los metodos de arbol en esta clase.
			a = prof.inicializarBusqueda();

			if (a != 0) {
				/* ======== Mensaje ======= */
					enviarMensaje(a, "AgenteBroker", "P->B");
				/* ======== Mensaje ======= */
			}
			doDelete();
		}

		@Override
		public boolean done() {

			return false;
		}

		private void enviarMensaje(int tam, String receptor, String idConv) {
			AID id = new AID(); // id de la conversacion
			id.setLocalName(receptor);
			ACLMessage acl = new ACLMessage(ACLMessage.INFORM);
			acl.addReceiver(id);
			acl.setSender(getAID());
	//		acl.setSender(getAgent().getAID()); // si el comportamiento esta en otro lado.
			try {
				acl.setContentObject(tam);
			} catch (IOException e) {
				e.printStackTrace();
			}
			acl.setLanguage("Spanish");
			acl.setConversationId(idConv);
			send(acl);
		}

	}

}
