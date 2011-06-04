package edu.presentacion;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.plaf.metal.MetalTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import edu.logica.patrones.fabricaAbstracta.*;

import edu.logica.patrones.mediador.Colega;
import edu.logica.patrones.mediador.Mediador;

/**
 * JTree escucha a la pizarra y muestra el estado de la aplicacion
 */
public class Explorador extends JTree implements Colega{

	private Explorador explorador;
	
	private Vector tabs =  new Vector();
	
	private static DefaultTreeModel modelo;
	private static DefaultMutableTreeNode raiz;
	
	/**
	 * Constructor de la clase
	 */
	public Explorador (){
		super(modelo);
		
		this.setRootVisible(false);
				
		this.setUI(new MetalTreeUI());
		this.setEditable(true);
	}
	
	/**
	 * Crea un padre en el arbol y añade el string de parametro al vector
	 * tabs para mostrarlo en el arbol, aplicado para la creacion de nuevos
	 * proyectos para la aplicacion.
	 * @param nombre
	 */
	public void crearPadre(String nombre){
		if(!this.isRootVisible()){
			this.setRootVisible(true);			
		}
		
		this.expandRow(1);
		
		tabs.add(new DefaultMutableTreeNode(nombre));
		raiz.removeAllChildren();
		
		for(int i=0;i<tabs.size(); i++){
			raiz.insert((MutableTreeNode)tabs.get(i),i);
		}
		
		modelo.reload();
	}
	
	/**
	 * Busca en el vector tabs si existe el padre que coincide con el entero
	 * del parametro para luego eliminarlo del vector y mostrar el arbol actualizado.
	 * @param nombre
	 */
	public void quitarPadre(int index){
		if(!this.isRootVisible()){
			this.setRootVisible(true);			
		}
		
		this.expandRow(0);
		
		tabs.removeElementAt(index);
		raiz.removeAllChildren();
		
		for(int i=0;i<tabs.size(); i++){
			raiz.insert((MutableTreeNode)tabs.get(i),i);
		}
		
		modelo.reload();
	}
	
	/**
	 * Crea un Hijo para un padre seleccionado
	 * @param index
	 * @param nodo
	 */
	private void crearHijo(int index, String nodo){
		((DefaultMutableTreeNode)tabs.get(index)).add(new DefaultMutableTreeNode(nodo));
		
		modelo.reload();
	}
	
	/**
	 * Quita un Hijo del arbol
	 * @param index
	 * @param childIndex
	 */
	public void removerHijo(int index, int childIndex){
		((DefaultMutableTreeNode)tabs.get(index)).remove(childIndex);
		
		modelo.reload();
	}
			
	/**
	 * Añade los componentes a los vectores de cada quien
	 * @param componente
	 */	
	public void añadirComponente(int tab, AComponente componente){
		if (componente instanceof Nota) {
			Nota nota = (Nota) componente;
			crearHijo(tab, "Nota");
			System.out.println("Nota Añadida al Arbol");
		}
		if (componente instanceof Clase) {
			Clase clase = (Clase) componente;
			crearHijo(tab, "Clase");
			System.out.println("Clase Añadida al Arbol");
		}
		if (componente instanceof Paquete) {
			Paquete paquete = (Paquete) componente;
			crearHijo(tab, "Paquete");
			System.out.println("Paquete Añadido al Arbol");
		}
		if (componente instanceof Interfaz) {
			Interfaz interfaz = (Interfaz) componente;
			crearHijo(tab, "Interfaz");
			System.out.println("Interfaz Añadida al Arbol");
		}
	}
	
	/**
	 * Hace una y solo una instancia del Explorador
	 * @return Explorador
	 */
	public Explorador instanciar() {
		if (explorador == null) {
			raiz = new DefaultMutableTreeNode("DDC-Net");
			modelo = new DefaultTreeModel(raiz);
			explorador = new Explorador();
		}
		return explorador;
	}
	public Mediador getMediador() {
		return null;
	}
	public void setMediador(Mediador mediador) {
	}
}