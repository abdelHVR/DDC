package edu.logica.patrones.fachada;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import edu.logica.patrones.fabricaAbstracta.AComponente;
import edu.logica.patrones.fabricaAbstracta.AVertice;
import edu.presentacion.Pizarra;

/**
 * MouseAdapter que muestra un menu de opciones
 */
public class ListenerMenuOpciones extends MouseAdapter implements Fachada {
	
	private JPopupMenu menu = new JPopupMenu();
	private JMenuItem propiedades = new JMenuItem("Propiedades");
	private JMenuItem fuente = new JMenuItem("Cambiar Fuente");
	private JMenuItem enviarAtras = new JMenuItem("Enviar Atras");
	private JMenuItem enviarDelante = new JMenuItem("Traer al Frente");
	private JMenuItem copiar = new JMenuItem("Copiar");
	private JMenuItem cortar = new JMenuItem("Cortar");
	private JMenuItem pegar = new JMenuItem("Pegar");
	private JMenuItem eliminar = new JMenuItem("Eliminar");
	private JMenuItem redimensionar = new JMenuItem("Redimensionar");

	private AComponente componente;
	
	private Pizarra pizarra;
	
	public static AComponente Componente;				//Almacena el componente que ha sido copiado "Es preciso que sea estatico"

	/**
	 * Prepara y Muestra el Menu en pantalla de el Componente
	 * @param ME
	 */
	public void mostrarMenu(MouseEvent ME){
		
		if (ME.getSource() instanceof AComponente) {
			componente = (AComponente) ME.getSource();
			pizarra = componente.getPizarra();
		}
		
		propiedades.setToolTipText("Editar Componente");
		propiedades.setMnemonic('p');
		propiedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Dialogo propiedades");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					vertice.showDialogo();
				}
			}
		});
		menu.add(propiedades);
		
		fuente.setToolTipText("Cambiar el tipo de Fuente");
		fuente.setMnemonic('f');
		fuente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Dialogo Fuente");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					vertice.cambiarFuente();
				}
			}
		});
		menu.add(fuente);

		menu.addSeparator();
				
		enviarAtras.setToolTipText("Enviar Atras el Componente");
		enviarAtras.setMnemonic('a');
		enviarAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Componente Enviado Atras");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					vertice.enviarAtras();
				}
			}
		});
		menu.add(enviarAtras);

		enviarDelante.setToolTipText("Traer al Frente al Componente");
		enviarDelante.setMnemonic('f');
		enviarDelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Componente Enviado Delante");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					vertice.enviarDelante();
				}
			}
		});
		menu.add(enviarDelante);
		
		redimensionar.setToolTipText("Redimensionar Componente");
		redimensionar.setMnemonic('r');
		redimensionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JDialog menu = new JDialog();
				final JTextField tam_x = new JTextField();
				final JTextField tam_y = new JTextField();
				final JButton aceptar = new JButton("Aceptar");
				final JButton cancelar = new JButton("Cancelar");
		
				tam_x.setText(String.valueOf(componente.getWidth()));
				tam_y.setText(String.valueOf(componente.getHeight()));
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String ancho = tam_x.getText();
						String alto = tam_y.getText();
						componente.redimensionar(Integer.parseInt(ancho), Integer.parseInt(alto));
				
						menu.setVisible(false);
					}
				});
		
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						menu.dispose();
						menu.setVisible(false);
					}
				});
		
				menu.setLayout(new GridLayout(3,2));
				menu.add(new JLabel("Ancho:"));
				menu.add(tam_x);
				menu.add((new JLabel("Alto:")));
				menu.add(tam_y);
				menu.add(aceptar);
				menu.add(cancelar);
		
				menu.setTitle("Redimensionar");
				menu.setSize(200,100);
				menu.setLocation(300,300);
				menu.setAlwaysOnTop(true);
				menu.setVisible(true);
			}
		});
		menu.add(redimensionar);
		
		menu.addSeparator();
		
		copiar.setToolTipText("Copiar Componente");
		copiar.setMnemonic('c');
		copiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Copiando Vertice");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					try {
						Componente = (AComponente)vertice.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		menu.add(copiar);
		
		cortar.setToolTipText("Cortar Componente");
		cortar.setMnemonic('t');
		cortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Cortando Vertice");
				if (componente instanceof AVertice) {
					AVertice vertice = (AVertice) componente;
					try {
						Componente = (AComponente)vertice.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				componente.borrar();
			}
		});
		menu.add(cortar);

		pegar.setToolTipText("Pegar Componente");
		pegar.setMnemonic('p');
		pegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pizarra.añadirComponente(Componente);
				Componente = null;
				System.out.println("Componente Pegado");
			}
		});
		
		if(Componente == null)
			pegar.setEnabled(false);

		menu.add(pegar);
		
		menu.addSeparator();
		
		eliminar.setToolTipText("Eliminar Componente");
		eliminar.setMnemonic('e');
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				componente.borrar();	
			}
		});
		menu.add(eliminar);
		
		menu.show(ME.getComponent(), ME.getX(), ME.getY());
	}
	
	/**
	 * Evento que muestra el menu de opciones al hacer click derecho
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