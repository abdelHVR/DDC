package edu.logica.patrones.momento;

import edu.presentacion.Pizarra;
/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Momento{
	
	private Pizarra pizarra;
	private Cola custodio = new Cola();
	private int i = 0;
	private boolean deshacer = false, rehacer = false;
	
	public Momento(Pizarra pizarra){
		this.pizarra = pizarra;
	}
	
	/**
	 * Almacena un estado de la pizarra en la Cola de eventos
	 * para posteriormente retornar a un estado anterior.
	 * @param estado
	 */
	public void setMomento(Pizarra estado){
		try {
			custodio.añadir(estado.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(custodio);
		deshacer = true;
		i++;
	}
	
	/**
	 * Deshace la ultima accion realizada por la vista seleccionada.
	 *
	 */
	public void deshacer(){
		getMomento(i-1);
		i--;
	}
	
	private void getMomento(int m){
	}
	
	/**
	 * Retorna verdadero si es posible deshacer una accion
	 * y falso si no es posible.
	 * @return
	 */
	public boolean isDeshacerValid(){
		if(!custodio.isEmpty()){
			deshacer = true;
		}
		return deshacer;
	}

	/**
	 * Retorna verdadero si es posible rehacer una accion
	 * y falso si no es posible.
	 * @return
	 */
	public boolean isRehacerValid(){
		return rehacer;
	}
}
