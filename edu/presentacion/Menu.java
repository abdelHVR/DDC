/*
 * Esta Clase Menu genera un menu de opciones para la aplicacion
 * 
 */
package edu.presentacion;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import edu.logica.patrones.fachada.*;
import edu.serializar.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JMenuBar que añade menus de opciones para la aplicacion
 * 
 */
public class Menu extends JMenuBar {
	
	private static Object objeto;
	
	/**
	 * Constructor de la clase
	 * @return JMenuBar
	 *
	 */
	public Menu(){
		add(menuArchivo());
		add(menuEditar());
		add(menuAyuda());
	}
	
	public static void setSerializable(Object obj){
		objeto = obj;
	}
	
	/**
	 * Añade al menu archivo los demas menus
	 */
	private JMenu menuArchivo(){
		
		final JMenu archivo = new JMenu();
			
		archivo.setMnemonic('a');
		archivo.setText("Archivo");
		
		JMenuItem nuevo = new JMenuItem();
		nuevo.setMnemonic('n');
		nuevo.setText("Nuevo");
		nuevo.setIcon(new ImageIcon("M-Nuevo.jpg"));
		nuevo.setToolTipText("Crear un nuevo diagrama de clases");
		nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				new ListenerNuevo();
			}
		});
		nuevo.setEnabled(true);
		archivo.add(nuevo);
		
		archivo.add(new JSeparator());
		
		JMenuItem abrir = new JMenuItem();
		abrir.setMnemonic('a');
		abrir.setText("Abrir...");
		abrir.setIcon(new ImageIcon("M-Abrir.jpg"));
		abrir.setToolTipText("Abrir un archivo ya existente");
		abrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				new Abrir();
			}
		});
		archivo.add(abrir);
		
		JMenuItem cerrar = new JMenuItem();
		cerrar.setMnemonic('c');
		cerrar.setText("Cerrar");
		cerrar.setToolTipText("Cerrar el archivo activo");
		cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				MarcoDeGraficacion.vistas.quitarVista();
			}
		});
		archivo.add(cerrar);
		
		archivo.add(new JSeparator());
		
		JMenuItem guardar = new JMenuItem();
		guardar.setMnemonic('g');
		guardar.setText("Guardar");
		guardar.setIcon(new ImageIcon("M-Guardar.jpg"));
		guardar.setToolTipText("Guardar el archivo activo");
		guardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				new Guardar(objeto);
			}
		});
		archivo.add(guardar);
		
		archivo.add(new JSeparator());
		
		JMenuItem salir = new JMenuItem();
		salir.setMnemonic('s');
		salir.setText("Salir");
		salir.setToolTipText("Salir de la aplicacion");
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				System.exit(0);
			}
		});
		archivo.add(salir);
		
		return archivo;		
	}
	
//	----------------------------------------------------------------------------------------------------------------//
	
	/**
	 * Añade al menu editar los demas menus
	 */
	private JMenu menuEditar(){
				
		final JMenu editar = new JMenu();
		
		editar.setMnemonic('e');
		editar.setText("Editar");
		
		JMenuItem deshacer = new JMenuItem();
		deshacer.setMnemonic('d');
		deshacer.setText("Deshacer");
		deshacer.setToolTipText("Deshacer la ultima operacion realizada");
		deshacer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerDeshacer();
			}
		});
		editar.add(deshacer);
		
		JMenuItem rehacer = new JMenuItem();
		rehacer.setMnemonic('r');
		rehacer.setText("Rehacer");
		rehacer.setToolTipText("Rehacer la ultima operacion realizada");
		rehacer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerRehacer();
			}
		});
		editar.add(rehacer);
		
		editar.add(new JSeparator());
		
		JMenuItem cortar = new JMenuItem();
		cortar.setMnemonic('r');
		cortar.setText("Cortar");
		cortar.setToolTipText("Cortar los elementos seleccionados");
		cortar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerCortar();
			}
		});
		editar.add(cortar);
		
		JMenuItem copiar = new JMenuItem();
		copiar.setMnemonic('c');
		copiar.setText("Copiar");
		copiar.setToolTipText("Copiar los elementos seleccionados");
		copiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerCopiar();
			}
		});
		editar.add(copiar);
	
		JMenuItem pegar = new JMenuItem();
		pegar.setMnemonic('p');
		pegar.setText("Pegar");
		pegar.setToolTipText("Pegar los elementos en la pizarra");
		pegar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerPegar();
			}
		});
		editar.add(pegar);
		
		return editar;
	}
//	----------------------------------------------------------------------------------------------------------------//
	
	/**
	 * Añade al menu ayuda los demas menus
	 */
	private JMenu menuAyuda(){
				
		final JMenu menuAyuda = new JMenu();
	
		menuAyuda.setMnemonic('y');
		menuAyuda.setText("Ayuda");
		
		JMenuItem ayuda = new JMenuItem();
		ayuda.setMnemonic('a');
		ayuda.setText("Ayuda");
		ayuda.setToolTipText("Visitar la Ayuda");
		ayuda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				//new ListenerAyuda();
			}
		});
		menuAyuda.add(ayuda);
		
		menuAyuda.add(new JSeparator());
		
		JMenuItem acercaDe = new JMenuItem();
		acercaDe.setMnemonic('c');
		acercaDe.setText("Acerca De...");
		acercaDe.setToolTipText("Los creditos de este programa");
		acercaDe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent AE){
				new ListenerAcercaDe();
			}
		});
		menuAyuda.add(acercaDe);
		
		return menuAyuda;
	}
}
