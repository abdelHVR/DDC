package edu.logica.patrones.fachada;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.logica.patrones.comando.ComandoCreador;
import edu.logica.patrones.fabricaAbstracta.Nota;
import edu.presentacion.Pizarra;

/**
 * ListenerNota implementa Fachada, ya que su funcion es ser el listener
 * de la Barra de Herramientas en la Pizarra, hace uso del comando creador
 * para construir un componente de tipo Nota, el cual es añadido a la pizarra
 * propia de la Barra de Herramientas que a llamado a esta clase.
 * 
 */
public class ListenerNota implements Fachada{
	
	private Nota elemento;

	public void mouseReleased(MouseEvent ME) {
		if (ME.getSource() instanceof Pizarra) {
			Pizarra pizarra = (Pizarra) ME.getSource();
			elemento = (Nota)new ComandoCreador().ejecutar("nota", ME.getX(), ME.getY());
			elemento.setPizarra(pizarra);
			pizarra.soltarBoton("nota");
			pizarra.añadirComponente(elemento);
			pizarra.removerFachada(this);
			System.out.println("Vertice Nota Añadido\t ...OK");	
		}
	}
	public void mouseClicked(MouseEvent ME) {	
	}
	public void mousePressed(MouseEvent ME) {	
	}
	public void mouseEntered(MouseEvent ME) {	
	}
	public void mouseExited(MouseEvent ME) {	
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}