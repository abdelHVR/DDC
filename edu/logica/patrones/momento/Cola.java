package edu.logica.patrones.momento;

import java.util.Vector;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Cola extends Vector{

	private Momento momento;
	protected int count;
	private static final int SIZE = 6;			//Cola de tamaño 5
	
	private Momento momento1;

	protected Cola(){
		count = 0;
	}
	
	protected void añadir(Object estado){
		if(count < SIZE){
			count++;
		}
		if(count == SIZE){
			removeElementAt(0);
		}
		add(estado);
		trimToSize();
		System.out.println(count);
	}
	
	protected void quitar(){
		if(count > 0){
			remove(count);
			count--;
		}
		if(count == 0){
			removeAllElements();
		}
		trimToSize();
	}
}
