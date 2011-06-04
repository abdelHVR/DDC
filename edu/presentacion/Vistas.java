package edu.presentacion;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.logica.patrones.composite.Vectores;
import edu.logica.patrones.momento.Momento;

/**
 * JTabbedPane que añade la Barra de Herramientas y la Pizarra
 * 
 */
public class Vistas extends JTabbedPane {

	public BarraDeHerramientas barraDeHerramientas;
	public Pizarra pizarra;
	
	/**
	 * Constructor de la clase
	 * @return JTabbedPane
	 */
	public Vistas() {
		add("Untitled.ddc", crearPanel());
		MarcoDeTrabajo.explorador.crearPadre("Untitled.ddc");
		repaint();
	}

	/**
	 * Crea una nueva vista para el Marco de Graficacion y la añade al vector
	 * que muestra el arbol
	 */
	public void nuevaVista(String nombre) {
		MarcoDeTrabajo.explorador.crearPadre(nombre+".ddc");
		add(nombre+".ddc", crearPanel());
		setSelectedIndex(getTabCount()-1);
		repaint();
	}
	
	public void abrirVista(Object archivo){
		MarcoDeTrabajo.explorador.crearPadre(".ddc");
		add(".ddc", crearPanel(archivo));
		setSelectedIndex(getTabCount()-1);
		repaint();
	}
	
	/**
	 * Quita la vista seleccionada y la quita del vector que muestra el arbol
	 */
	public void quitarVista(){
		MarcoDeTrabajo.explorador.quitarPadre(getSelectedIndex());
		remove(getSelectedComponent());
		repaint();
	}

	/**
	 * Cambia el nombre de la vista seleccionada
	 * @param nombre
	 */
	public void cambiarNombre(String nombre){
		setTitleAt(getSelectedIndex(), nombre);
		setName(nombre);
	}
	
	/**
	 * Compone el panel que se añadira a la Vista
	 * @return JPanel
	 */
	private JPanel crearPanel() {
		JPanel panel = new JPanel();
		pizarra = new Pizarra();
		Momento momento = new Momento(pizarra);
		Vectores vectores = new Vectores();
		barraDeHerramientas = new BarraDeHerramientas(pizarra);
		
		Menu.setSerializable(vectores);
		
		pizarra.setVectores(vectores);
		pizarra.setMomento(momento);
		pizarra.setMenu(barraDeHerramientas);
		
		panel.setLayout(new BorderLayout());
		panel.add(barraDeHerramientas, BorderLayout.NORTH);
		panel.add(pizarra, BorderLayout.CENTER);
		
		return panel;
	}
	private JPanel crearPanel(Object archivo) {
		JPanel panel = new JPanel();
		pizarra = (Pizarra) archivo;
		Momento momento = new Momento(pizarra);
		Vectores vectores = new Vectores();
		barraDeHerramientas = new BarraDeHerramientas(pizarra);
		
		Menu.setSerializable(vectores);
		
		pizarra.setVectores(vectores);
		pizarra.setMomento(momento);
		pizarra.setMenu(barraDeHerramientas);
		
		panel.setLayout(new BorderLayout());
		panel.add(barraDeHerramientas, BorderLayout.NORTH);
		panel.add(pizarra, BorderLayout.CENTER);
		
		return panel;
	}	
}
