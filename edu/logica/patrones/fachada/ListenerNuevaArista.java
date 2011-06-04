package edu.logica.patrones.fachada;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListenerNuevaArista implements Fachada {

	public static String tipoArista = null;
	
	public static void nuevaArista(String tipo){
		tipoArista = tipo;
	}
	
	public static String getTipo(){
		return tipoArista;
	}

	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
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