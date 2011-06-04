/*
 * Esta clase MarcoDeTrabajo es un JSplitPane que añande un explorador
 * y un MarcoDeGraficacion
 * 
 */
package edu.presentacion;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * JSplitPane que Divide el Panel y añade un Explorador y un Marco de Graficacion
 * 
 */
public class MarcoDeTrabajo extends JSplitPane {

	public static Explorador explorador = new Explorador().instanciar();

	public static MarcoDeGraficacion marcoDeGraficacion = new MarcoDeGraficacion();
	
	/**
	 * 
	 * @param menu
	 */
	public MarcoDeTrabajo(Menu menu){
		super(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(explorador), marcoDeGraficacion);	
		setOneTouchExpandable(true);
		setDividerLocation(150);
	}

}
