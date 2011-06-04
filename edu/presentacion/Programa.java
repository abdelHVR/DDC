package edu.presentacion;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Programa {

	public static Menu menu;

	public static MarcoDeTrabajo marcoDeTrabajo;
	
	public static JFrame programa = new JFrame("DDC-Net, Diagramador de clases");

	public static void main(String[] args) {
		menu = new Menu();
		marcoDeTrabajo = new MarcoDeTrabajo(menu);
		
		programa.setJMenuBar(menu);
		programa.add(marcoDeTrabajo);
		
		programa.setSize(new Dimension(800,600));
		programa.setVisible(true);
		
		System.out.println("DDC-Net Cargado");
	}
}