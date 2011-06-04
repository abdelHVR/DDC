package edu.logica.patrones.composite;

import edu.logica.patrones.fabricaAbstracta.AComponente;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HojaClases extends AComposite {

	public void adicionar(AComponente componente) {
		add(componente);
	}

	public void remover(AComponente componente) {
		remove(componente);
	}

}