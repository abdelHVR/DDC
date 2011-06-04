package edu.logica.patrones.fabricaAbstracta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import edu.logica.patrones.fachada.ListenerNuevaArista;

public class Nota extends AVertice {
	
	private final Image imagen = Toolkit.getDefaultToolkit().getImage("P-Nota.jpg");
	
	/**
	 * Constructor de la Clase
	 * @param x
	 * @param y
	 * @param an
	 * @param al
	 */
	public Nota(int x, int y, int an, int al){
		super(x, y, an, al);
	}
	
	/**
	 * Constructor de la Clase para Prototipos
	 * @param clon
	 */
	public Nota(Nota clon){
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
	 * Dibuja la Nota
	 */
	public void dibujar(Graphics Componente) {
		Componente.setColor(Color.WHITE);
		Componente.fill3DRect(1,1,ancho-4,alto-4,true);
		Componente.setColor(Color.BLACK);

		Componente.setFont(font);
		
		Componente.drawLine(1,1,1,alto-5);
		Componente.drawLine(1,1,ancho-15,1);
		Componente.drawLine(1,alto-5,ancho-5,alto-5);
		Componente.drawLine(ancho-5,10,ancho-5,alto-5);
		
		Componente.drawLine(ancho-15,0,ancho-5,10);
		Componente.drawLine(ancho-15,0,ancho-15,10);
		Componente.drawLine(ancho-15,10,ancho-5,10);
		
		Componente.drawImage(imagen,6,6,this);
		
		Componente.drawString(texto, 4, 35);
		
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
	 * Muestra el dialogo de edicion de propiedades
	 */
	public void showDialogo() {
		final JDialog menu = new JDialog();
		final JTextArea nombre = new JTextArea();
		final JButton aceptar = new JButton("Aceptar");
		final JButton cancelar = new JButton("Cancelar");
		
		nombre.setText(texto);
		
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
		menu.add(new JLabel("Digite el Texto:"));
		menu.add(nombre);
		menu.add(aceptar);
		menu.add(cancelar);
		
		menu.setTitle("Nota - Propiedades");
		menu.setSize(300,100);
		menu.setLocation(300,300);
		menu.setAlwaysOnTop(true);
		menu.setVisible(true);
		System.out.print("Editando propiedades");
	}
}