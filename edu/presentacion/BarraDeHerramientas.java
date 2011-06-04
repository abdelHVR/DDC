package edu.presentacion;

import edu.logica.patrones.fachada.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Clase que crea la Barra de Herramientas para la aplicacion.
 * Su constructor compone la Barra de Herramientas con los botones
 * y las funcionalidades por defecto de la Aplicacion. 
 * 
 */
public class BarraDeHerramientas extends JToolBar {
	
	private MouseListener lis;
	private Pizarra pizarra;
	public JButton asociacion, dependencia, generalizacion, indicacion, paquete, interfaz, clase, nota, deshacer, rehacer;
	
	/**
	 * Construye la Barra de Herramientas para ser usada en la pizarra
	 * que se le pasa por parametro.
	 * @param pizarra
	 */
	public BarraDeHerramientas(Pizarra piz) {
		
		this.pizarra = piz; 
		
		asociacion = new JButton(new ImageIcon("B-Asociacion.jpg"));
		asociacion.setToolTipText("Asociacion");
		asociacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Asociacion");
				soltarBotones();
				asociacion.setSelected(true);
				removerListener(lis);
				lis = null;
				ListenerNuevaArista.nuevaArista("asociacion");
				System.out.println("\t ...OK");
			}
		});
		
		dependencia = new JButton(new ImageIcon("B-Dependencia.jpg"));
		dependencia.setToolTipText("Dependencia");
		dependencia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Dependencia");
				soltarBotones();
				dependencia.setSelected(true);
				removerListener(lis);
				lis = null;
				ListenerNuevaArista.nuevaArista("dependencia");
			}
		});
		
		generalizacion = new JButton(new ImageIcon("B-Generalizacion.jpg"));
		generalizacion.setToolTipText("Generalizacion");
		generalizacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Generalizacion");
				soltarBotones();
				generalizacion.setSelected(true);
				removerListener(lis);
				lis = null;
				ListenerNuevaArista.nuevaArista("generalizacion");
				System.out.println("\t ...OK");
			}
		});

		indicacion = new JButton(new ImageIcon("B-Indicacion.jpg"));
		indicacion.setToolTipText("Indicacion");
		indicacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Indicacion");
				soltarBotones();
				indicacion.setSelected(true);
				removerListener(lis);
				lis = null;
				ListenerNuevaArista.nuevaArista("indicacion");
				System.out.println("\t ...OK");
			}
		});
		
		paquete = new JButton(new ImageIcon("B-Paquete.jpg"));
		paquete.setToolTipText("Crear un Paquete");
		paquete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Paquete");
				soltarBotones();
				paquete.setSelected(true);
				prepararCursor();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				lis = new ListenerPaquete();
				añadirListener(lis);
				System.out.println("\t ...OK");
			}
		});

		interfaz = new JButton(new ImageIcon("B-Interfaz.jpg"));
		interfaz.setToolTipText("Crear una Interfaz");
		interfaz.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Interfaz");
				soltarBotones();
				interfaz.setSelected(true);
				prepararCursor();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				lis = new ListenerInterfaz();
				añadirListener(lis);
				System.out.println("\t ...OK");
			}
		});

		clase = new JButton(new ImageIcon("B-Clase.jpg"));
		clase.setToolTipText("Crear una Clase");
		clase.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Clase");
				soltarBotones();
				clase.setSelected(true);
				prepararCursor();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				lis = new ListenerClase();
				añadirListener(lis);
				System.out.println("\t ...OK");
			}
		});

		nota = new JButton(new ImageIcon("B-Anotacion.jpg"));
		nota.setToolTipText("Crear una nota");
		nota.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Click BarraHerramientas - Nota");
				soltarBotones();
				nota.setSelected(true);
				prepararCursor();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				lis = new ListenerNota();
				añadirListener(lis);
				System.out.println("\t ...OK");
			}
		});
		
		deshacer = new JButton(new ImageIcon("Deshacer.jpg"));
		deshacer.setToolTipText("Deshacer la Ultima Accion");
		deshacer.setEnabled(false);
		deshacer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Deshacer");
				soltarBotones();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				pizarra.momento.deshacer(); 
				System.out.println("\t ...OK");
			}
		});
		
		rehacer = new JButton(new ImageIcon("Rehacer.jpg"));
		rehacer.setToolTipText("Rehacer la ultima accion");
		rehacer.setEnabled(false);
		rehacer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.out.print("Rehacer");
				soltarBotones();
				removerListener(lis);
				ListenerNuevaArista.nuevaArista(null);
				System.out.println("\t ...OK");
			}
		});
		
		add(asociacion);
		add(dependencia);
		add(generalizacion);
		add(indicacion);
		addSeparator();
		add(paquete);
		add(interfaz);
		add(clase);
		add(nota);
		addSeparator();
		add(deshacer);
		add(rehacer);
	}
	
	/**
	 * Prepara el cursor de la pizarra para agregar un componente
	 */
	protected void prepararCursor() {
		pizarra.cambiarCursor("cruz");		
	}
	
	/**
	 * Hace que todos los botones de la barra de Herramientas de deseleccionen.
	 */
	protected void soltarBotones(){
		asociacion.setSelected(false);
		dependencia.setSelected(false);
		generalizacion.setSelected(false);
		indicacion.setSelected(false);
		paquete.setSelected(false);
		interfaz.setSelected(false);
		clase.setSelected(false);
		nota.setSelected(false);
		deshacer.setSelected(false);
		rehacer.setSelected(false);
	}
	
	/**
	 * Añade el listener del parametro a la pizarra.
	 * @param lis
	 */
	protected void añadirListener(MouseListener lis) {
		pizarra.addMouseListener(lis);
		pizarra.repaint();
	}
	
	/**
	 * Quita el listener del parametro de la pizarra
	 * @param lis
	 */
	protected void removerListener(MouseListener lis) {
		pizarra.removeMouseListener(lis);
		pizarra.repaint();
	}
}