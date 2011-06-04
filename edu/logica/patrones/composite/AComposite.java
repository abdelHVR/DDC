package edu.logica.patrones.composite;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import edu.logica.patrones.fabricaAbstracta.AComponente;
import fr.dyade.koala.serialization.GeneratorInputStream;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class AComposite extends Vector implements Serializable{
	
	public abstract void adicionar(AComponente componente);

	public abstract void remover(AComponente componente);
	
	// Java requirements
	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
	}
	public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this = (AComposite)in.readObject();
	}

	// Koala XML serialization requirements
	public static void readObject(GeneratorInputStream in) throws IOException, ClassNotFoundException {
		in.readObject();
	}

}
