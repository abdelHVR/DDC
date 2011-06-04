package edu.logica.patrones.fachada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import edu.logica.patrones.fabricaAbstracta.AComponente;
import edu.presentacion.Pizarra;

/**
 * Esta clase ListenerMenuPizarra, implementa la interfaz fachada
 * con lo que al ser llamada por un evento esta muestra el menu de opciones
 * en pantalla de la pizarra en uso. 
 */
public class ListenerMenuPizarra extends MouseAdapter implements Fachada {
	
	private JPopupMenu menu = new JPopupMenu();
	private JMenuItem ordenar = new JMenuItem("Ordenar");
	private JMenuItem pegar = new JMenuItem("Pegar");
	private int x = 100, y = 100;

	private Pizarra pizarra;
	private AComponente componente = ListenerMenuOpciones.Componente;

	/**
	 * Prepara y Muestra el Menu de opciones en pantalla de la pizarra
	 * @param ME
	 */
	public void mostrarMenu(final MouseEvent ME){
		
		if (ME.getSource() instanceof Pizarra) {
			pizarra = (Pizarra) ME.getSource();
		}

		/**
		 * Si existen componentes en la pizarra ordena los componentes
		 * de lo contrario, se desactiva.
		 */
		ordenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < pizarra.getComponentCount(); i++) {
					AComponente componente = (AComponente)pizarra.getComponent(pizarra.getComponentCount()-1-i);
					componente.mover(x,y);
					if(x < pizarra.getWidth()-100){
						x += 5+componente.getWidth();
					}
					else{
						x = 100;
						y += 10+componente.getHeight();
					}
					pizarra.repaint();
				}
				ListenerMenuOpciones.Componente = null;
				System.out.println("Componentes Ordenados");
			}
		});
		
		if(pizarra.getComponentCount() == 0)
			ordenar.setEnabled(false);

		/**
		 * Si existe un componente copiado o cortado lo pega de nuevo 
		 * en la pizarra, de lo contrario se desactiva.
		 */
		pegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pizarra.añadirComponente(componente);
				ListenerMenuOpciones.Componente = null;
				System.out.println("Componente Pegado");
			}
		});
		
		if(componente == null)
			pegar.setEnabled(false);
		
		menu.add(ordenar);
		menu.addSeparator();
		menu.add(pegar);
				
		menu.show(ME.getComponent(), ME.getX(), ME.getY());
	}
	
	/**
	 * Evento que muestra el menu de opciones al hacer clik derecho
	 */
	public void mouseReleased(MouseEvent ME) {
		if(ME.isPopupTrigger()){
			mostrarMenu(ME);
		}
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