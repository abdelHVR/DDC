/*
 * Esta clase es el Applet que se ejecuta al inicio de la aplicacion
 * Añade el marco de trabajo.
 * 
 */
package edu.presentacion;

import javax.swing.JApplet;

/**
 * Applet que inicia la aplicacion
 * 
 */
public class Web extends JApplet {

	private Menu menu;

	private MarcoDeTrabajo marcoDeTrabajo;

	/**
	 * Constructor del Applet
	 */
	public void init() {
		menu = new Menu();
		marcoDeTrabajo = new MarcoDeTrabajo(menu);
		
		setName("DDC-Net, Diagramador de clases");
		setJMenuBar(menu);
		add(marcoDeTrabajo);
		
		System.out.println("DDC-Net Cargado");
	}
	
	public String getAppletInfo(){
		return "DDC-Net Diagramador de clases.";
	}
}
