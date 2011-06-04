/*
 * Created on 25-abr-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package edu.logica.patrones.comando;

import edu.logica.patrones.fabricaAbstracta.AComponente;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class AComando {

	public abstract AComponente ejecutar(String tipo, int x, int y);

}
