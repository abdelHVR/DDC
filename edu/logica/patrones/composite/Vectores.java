package edu.logica.patrones.composite;

import java.awt.Component;

import edu.logica.patrones.fabricaAbstracta.AComponente;
import edu.logica.patrones.fabricaAbstracta.Clase;
import edu.logica.patrones.fabricaAbstracta.Interfaz;
import edu.logica.patrones.fabricaAbstracta.Nota;
import edu.logica.patrones.fabricaAbstracta.Paquete;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Vectores extends AComposite {

	private HojaPaquetes paquetes;
	private HojaInterfaces interfaces;
	private HojaClases clases;
	private HojaNotas notas;

	public Vectores(){
		paquetes = new HojaPaquetes();
		interfaces = new HojaInterfaces();
		clases = new HojaClases();
		notas = new HojaNotas();
	}
	
	public void adicionar(AComponente componente) {
		if (componente instanceof Paquete) {
			paquetes.adicionar(componente);
		}
		if (componente instanceof Interfaz) {
			interfaces.adicionar(componente);
		}
		if (componente instanceof Clase) {
			clases.adicionar(componente);
		}
		if (componente instanceof Nota) {
			notas.adicionar(componente);
		}
		
	}

	public void remover(AComponente componente) {
		if (componente instanceof Paquete) {
			paquetes.remover(componente);
		}
		if (componente instanceof Interfaz) {
			interfaces.remover(componente);
		}
		if (componente instanceof Clase) {
			clases.remover(componente);
		}
		if (componente instanceof Nota) {
			notas.remover(componente);
		}
	}

	/**
	 * @return
	 */
	public Component[] getComponentes() {
		return null;
	}

}