/*
 * Esta clase Abstracta es una generalizacion de los componentes de tipo Vertice
 * la cual marca una diferencia entre los metodos de los Vertices y las Aristas, 
 * sus metodos son: AVertice
 * 
 */
package edu.logica.patrones.fabricaAbstracta;

/**
 * 
 * Constructor de los Vertices, Hijo de los Componentes
 * 
 * @param posicion en x
 * @param posicion en y
 * @param ancho del Vertice
 * @param alto del Vertice
 *  
 */
public abstract class AVertice extends AComponente{
	
	protected String texto = new String();
	
	/**
	 * Constructor de la clase
	 * @param posicion_x
	 * @param posicion_y
	 * @param ancho
	 * @param alto
	 */
	public AVertice(int posicion_x, int posicion_y, int ancho, int alto){
		super(posicion_x, posicion_y, ancho, alto);
		setName(texto);
	}
	
	/**
	 * Repinta los Vertices
	 */
	public void repaint() {
		setLocation(posicion_x - (int)(ancho/2), posicion_y - (int)(alto/2));
		setSize(ancho, alto);
		setName(texto);
		super.repaint();
	}
	
	/**
	 * Devuelve una copia de si mismo
	 */
	public Object clone() throws CloneNotSupportedException {

		System.out.println("\t ...OK");
		
		AVertice prototipo = (AVertice)super.clone();
		
		if (prototipo instanceof Clase) {
			prototipo = new Clase((Clase)prototipo);
		}
		if (prototipo instanceof Interfaz) {
			prototipo = new Interfaz((Interfaz) prototipo);
		}
		if (prototipo instanceof Paquete) {
			prototipo = new Paquete((Paquete) prototipo);
		}
		if (prototipo instanceof Nota) {
			prototipo = new Nota((Nota) prototipo);
		}
		
		return prototipo;
	}
}
