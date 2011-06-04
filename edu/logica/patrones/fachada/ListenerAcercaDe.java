/*
 * Esta Clase lee un archivo del disco en un Buffer y lo muestra
 * en un JDialog, el archivo es de los creditos de los programadores.
 * 
 */
package edu.logica.patrones.fachada;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * JDialog que muestra una ventana con los creditos del programa
 * 
 */
public class ListenerAcercaDe extends JDialog implements Fachada {
	private JButton aceptar = new JButton("Aceptar");
	private JTextArea TextArea = new JTextArea();
	
	/**
	 * Constructor de la Clase
	 *
	 */
	public ListenerAcercaDe(){
		super(new JFrame(), true);
		super.setTitle("Acerca de...");

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				setVisible(false);
			}
		});

		getContentPane().add(aceptar, BorderLayout.SOUTH);
		
		String temp = new String();
		String texto = new String();
		
		try {
			File archivo = new File("readme.txt");
			FileInputStream flujo = new FileInputStream(archivo);
			InputStreamReader inputFlujo = new InputStreamReader(flujo);
			BufferedReader reader = new BufferedReader(inputFlujo);
			while((temp = reader.readLine()) != null){
				texto += temp+"\n";
			}
			temp = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			texto = "Falta el archivo Readme.txt";
		} catch (IOException e) {
			e.printStackTrace();
			texto = "El archivo Readme.txt no es el original";
		}
		TextArea.setText(texto);
		TextArea.setEditable(false);
		getContentPane().add(new JScrollPane(TextArea), BorderLayout.CENTER);

		pack();
		setLocation(300,300);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
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