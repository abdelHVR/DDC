/*
 * Esta clase concreta es la representacion de las Interfaces en la aplicacion
 * la cual es la ultima descendiente de la dinastia Componente-Vertice, sus metodos
 * son: Interfaz, dibujar, showDialogo
 * 
 */
package edu.logica.patrones.fabricaAbstracta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.logica.patrones.fachada.ListenerNuevaArista;

/**
 * Interfaz concreta
 * @param posicion en x
 * @param posicion en y
 * 
 */
public class Interfaz extends AVertice {

	private final Image imagen = Toolkit.getDefaultToolkit().getImage("P-Interfaz.jpg");

	/**
	 * Constructor de la Clase
	 * @param x
	 * @param y
	 * @param an
	 * @param al
	 */
	public Interfaz(int x, int y, int an, int al) {
		super(x,y,an,al);
	}
	
	/**
	 * Constructor de la Clase para Prototipos
	 * @param clon
	 */
	public Interfaz(Interfaz clon){
		super(clon.posicion_x, clon.posicion_y, clon.ancho, clon.alto);
		this.pizarra = clon.pizarra;
		this.texto = clon.texto;
		this.font = clon.font;
		this.fontName = clon.fontName;
		this.fontSize = clon.fontSize;
		this.fontStyle = clon.fontStyle;
		this.indexFuente = clon.indexFuente;
		this.indexTamaño = clon.indexTamaño;
		this.is_italicas = clon.is_italicas;
		this.is_negras = clon.is_negras;
	}

	/**
	 * Dibuja la Interfaz
	 */
	
	public void dibujar(Graphics Componente) {
		Componente.setColor(Color.WHITE);
		Componente.fill3DRect(1,1,ancho-5,alto-5,true);
		Componente.setColor(Color.BLACK);
		
		Componente.setFont(font);
		
		Componente.drawImage(imagen,5,5,this);
		
		Componente.drawRect(1,1,ancho-5,alto-5);
		Componente.drawLine(1,(int)(alto/4),ancho-5,(int)(alto/4));
		
		Componente.drawString(texto,(int)ancho/3-5,18);
		
		if(seleccionado){
			Componente.setColor(Color.blue);
			Componente.drawRect(0, 0, ancho-1, alto-1);
		}	
		if(ListenerNuevaArista.getTipo() != null){
			Componente.setColor(Color.RED);
			Componente.drawOval((int)(ancho/2)-5,(int)(alto/2)-5,10,10);
		}
	
	}

	/**
	 * Muestra el dialogo para edicion de propiedades
	 */

	public void showDialogo() {
		final JDialog menu = new JDialog();
		final JTextField nombre = new JTextField();
		final JButton aceptar = new JButton("Aceptar");
		final JButton cancelar = new JButton("Cancelar");
		
		nombre.setText(texto);
		
		nombre.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					texto = nombre.getText();
					System.out.println("\t ..OK");
					menu.setVisible(false);
					repaint();
				}
			}
		});

		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				texto = nombre.getText();
				System.out.println("\t ..OK");
				menu.setVisible(false);
				repaint();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.dispose();
				System.out.println("\t ...CANCEL");
				menu.setVisible(false);
				menu.dispose();
				repaint();
			}
		});
		
		menu.setLayout(new GridLayout(2,2));
		menu.add(new JLabel("Nombre:"));
		menu.add(nombre);
		menu.add(aceptar);
		menu.add(cancelar);
		
		menu.setTitle("Interfaz - Propiedades");
		menu.setSize(200,80);
		menu.setLocation(300,300);
		menu.setAlwaysOnTop(true);
		menu.setVisible(true);
		System.out.print("Editando propiedades");
	}
}