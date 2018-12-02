package agentes;

import java.io.IOException;
import java.io.Serializable;

import ambiente.Arbol;
import ambiente.DatosSeriales;
import ambiente.Profundidad;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

//Agente que recorre en profundidad usando la clase Profundidad sobre el argumento 'Arbol' recibido desde el contenedor:
@SuppressWarnings("serial")
public class AgenteProfundidad extends Agent {

	Arbol ar;
	DatosSeriales s;

	protected void takeDown() {
		// ultimas palabras del agente antes de morir:
		System.out.println(getName() + " ha muerto :(");
		super.takeDown();
	}

	protected void setup() {
		// comportamiento del agente:
		addBehaviour(new ComportamientoAgenteProf());
		super.setup();
	}

	class ComportamientoAgenteProf extends Behaviour {

		public void action() {
			// Recibimos al Arbol enviado desde el contenedor con getArguments
			// Luego podremos usar las funciones publicas de la clase Arbol en este agente
			ar = (Arbol) getArguments()[0];
			// Enviamos la lista que retorna la clase profundidad al constructor de la clase
			// serializada: DatosSeriales
			// Invocando al metodo iniciarBusqueda, obtenemos la lista recorrida por
			// profundidad:
			s = new DatosSeriales(new Profundidad(ar.nodosDelArbol, ar.generarMatrizIdeal()).iniciarBusqueda(),
					" recorrió en profundidad: ");
			// Enviamos los datos serializados al agente broker
			enviarMensaje(s, "AgenteBroker", "P->B");
			// Cuando finalice el envio, mata al agente
			doDelete();

		}

		public boolean done() {

			return false;
		}

		private void enviarMensaje(Serializable lista, String receptor, String idConv) {
			AID id = new AID(); // id de la conversacion
			id.setLocalName(receptor);
			ACLMessage acl = new ACLMessage(ACLMessage.INFORM);
			acl.addReceiver(id);
			acl.setSender(getAID());
			try {
				acl.setContentObject(lista);
			} catch (IOException e) {
				e.printStackTrace();
			}
			acl.setLanguage("Spanish");
			acl.setConversationId(idConv);
			send(acl);
		}

	}

}
