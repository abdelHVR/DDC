package edu.presentacion;

import javax.swing.JPanel;

import java.awt.BorderLayout;

/**
 * Marco De Graficacion añade las vistas donde se encuentran la barra de Herramientas
 * y la pizarra.
 * 
 */
public class MarcoDeGraficacion extends JPanel {

	public static Vistas vistas;
	
	/**
	 * Constructor que crea un nuevo Marco De Graficacion, creando un 
	 * nuevo tipo Vistas().
	 *
	 */
	public MarcoDeGraficacion() {
		vistas = new Vistas();
		setLayout(new BorderLayout());
		add(vistas, BorderLayout.CENTER);
	}
}
