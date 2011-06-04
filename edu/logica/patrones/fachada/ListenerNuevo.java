package edu.logica.patrones.fachada;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JTextField;

import edu.presentacion.MarcoDeGraficacion;

/**
 * JDialog que muestra el dialogo de nueva Vista
 * 
 */
public class ListenerNuevo extends JDialog implements Fachada {
	
	private JTextField entrada;
	private JLabel label;
	private JButton aceptar, cancelar;
	
	/**
	 * Constructor de la clase
	 *
	 */
	public ListenerNuevo(){
		label = new JLabel("Nombre:");
		entrada = new JTextField("Untitled",30);
		entrada.addKeyListener(this);
		entrada.setSelectionStart(0);
		entrada.setSelectionEnd(30);
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MarcoDeGraficacion.vistas.nuevaVista(entrada.getText());
				dispose();
				setVisible(false);
			}
		});
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
			}
		});
		
		setTitle("Crear Nueva Vista");
		setSize(new Dimension(200,90));
		setLocation(300,300);
		setLayout(new GridLayout(2,2));
		add(label);
		add(entrada);
		add(aceptar);
		add(cancelar);
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			MarcoDeGraficacion.vistas.nuevaVista(entrada.getText());
			setVisible(false);
		}
	}
}