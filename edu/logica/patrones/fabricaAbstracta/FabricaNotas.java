/*
 * Created on 25-abr-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package edu.logica.patrones.fabricaAbstracta;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FabricaNotas extends FabricaAbstracta {
	
	private Nota producto;

	public FabricaNotas(){
	}
	
	public AComponente crearProducto(int posicion_x, int posicion_y, int ancho, int alto) {
		producto = new Nota(posicion_x, posicion_y, ancho, alto);
		
		return producto;
	}
}