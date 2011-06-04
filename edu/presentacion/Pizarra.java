package edu.presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import edu.logica.patrones.composite.Vectores;
import edu.logica.patrones.fachada.*;
import edu.logica.patrones.fabricaAbstracta.AComponente;
import edu.logica.patrones.fabricaAbstracta.FabricaAbstracta;
import edu.logica.patrones.mediador.Colega;
import edu.logica.patrones.mediador.Mediador;
import edu.logica.patrones.momento.Momento;
import edu.logica.patrones.prototipo.Prototipo;

/**
 * Crea una nueva Pizarra que hereda de la clase JPanel e implementa
 * las interfaces Fachada y Colega
 */
public class Pizarra extends JPanel implements Fachada, Colega, Prototipo{

	private FabricaAbstracta fabricaAbstracta;
	private BarraDeHerramientas barra;
	protected Vectores vectores;
	protected Momento momento;
	
	int x1,y1;
	int x2,y2;

	/**
	 * Constructor de la clase, crea una pizarra añadiendose una Fachada
	 * para el manejo de los listeners, la cual consta de un MouseListener, 
	 * de un MouseMotionListener y de un KeyListener. Luego cambia el color
	 * de fondo de la pizarra.
	 */
	public Pizarra() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setBackground(new Color(250,248,238));
	}
	
	public void setVectores(Vectores newVectores){
		vectores = newVectores;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * Hace una copia de la barra de herramientas para su posterior
	 * modificacion por parte de los listeners del programa
	 * @param barra
	 */
	public void setMenu(BarraDeHerramientas barra){
		this.barra = barra;
	}
	
	public void setMomento(Momento momento){
		this.momento = momento;
	}
	
	/**
	 * Deselecciona un boton que se identifica con el parametro
	 * @param componente
	 */
	public void soltarBoton(String boton){
		if(boton.equals("asociacion")){
			barra.asociacion.setSelected(false);
		}
		if(boton.equals("dependencia")){
			barra.dependencia.setSelected(false);
		}
		if(boton.equals("generalizacion")){
			barra.generalizacion.setSelected(false);
		}
		if(boton.equals("indicacion")){
			barra.indicacion.setSelected(false);
		}
		if(boton.equals("paquete")){
			barra.paquete.setSelected(false);
		}
		if(boton.equals("interfaz")){
			barra.interfaz.setSelected(false);
		}
		if(boton.equals("clase")){
			barra.clase.setSelected(false);
		}
		if(boton.equals("nota")){
			barra.nota.setSelected(false);
		}
		barra.repaint();
	}

	/**
	 * Añade un componente a la Pizarra y devuelve el estado
	 * del cursor al de por defecto.
	 * @param componente
	 */
	public void añadirComponente(AComponente componente){
		momento.setMomento(this);
		add(componente, 0);
		vectores.adicionar(componente);
		MarcoDeTrabajo.explorador.añadirComponente(MarcoDeGraficacion.vistas.getSelectedIndex(), componente);
		cambiarCursor(null);
		repaint();
	}
	
	/**
	 * Quita un componente de la pizarra
	 * @param componente
	 */
	public void removerComponente(AComponente componente){
		momento.setMomento(this);
		remove(componente);
		repaint();
	}
	
	/**
	 * Envia un componente al frente de los demas en la pizarra
	 * @param componente
	 */
	public void enviarDelante(AComponente componente){
		momento.setMomento(this);
		setComponentZOrder(componente, 0);
		repaint();
		System.out.println("\t ...OK");
	}
	
	/**
	 * Envia un componente detras de los demas en la pizarra
	 * @param componente
	 */
	public void enviarAtras(AComponente componente){
		momento.setMomento(this);
		setComponentZOrder(componente, getComponentCount()-1);
		repaint();
		System.out.println("\t ...OK");
	}
	
	/**
	 * Cambia el tipo del cursor en la pizarra basandose en el parametro
	 * recibido. Los tipos aceptados son <strong>null</strong> que cambia el
	 * cursor al tipo normal, <strong>cruz</strong> que cambia el cursor al 
	 * tipo cruz, <strong>mover</strong> que cambia el cursor al tipo mover, 
	 * <strong>mano</strong> que cambia el cursor al tipo mano.
	 * @param cursor
	 */
	public void cambiarCursor(String cursor){
		if(cursor == null){
			setCursor(Cursor.getDefaultCursor());
		}
		if(cursor == "cruz"){
			setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		if(cursor == "mover"){
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		if(cursor == "mano"){
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	/**
	 * Quita una Fachada (listeners) de la pizarra, quitando el 
	 * MouseListener, el MouseMotionListener y el KeyListener
	 * @param listener
	 */
	public void removerFachada(Fachada listener){
		removeMouseListener((MouseListener)listener);
		removeMouseMotionListener((MouseMotionListener)listener);
		removeKeyListener((KeyListener)listener);
	}

	public Mediador getMediador() {
		return null;
	}

	public void setMediador(Mediador mediador) {
	}
	
	public Vector asociaciones = new Vector();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(!asociaciones.isEmpty()){
			dibujar(g);
		}
	}

	void dibujar(Graphics g){
		for (int i=0; i < asociaciones.size(); i++) {
			Rectangle p = (Rectangle)asociaciones.elementAt(i);
			if (p.width != -1) {
				g.drawLine(p.x, p.y, p.width, p.height);
			} 
		}
		if (x2 != -1) {
			g.drawLine(x1, y1, x2, y2);
		}
	}

	public void mousePressed(MouseEvent ME) {
		x1 = ME.getX();
		y1 = ME.getY();
		x2 = -1;

		if(ME.getButton() == MouseEvent.BUTTON3){
			addMouseListener(new ListenerMenuPizarra());
		}
	}
	public void mouseReleased(MouseEvent ME) {
		asociaciones.addElement(new Rectangle(x1, y1, ME.getX(), ME.getY()));
		x2 = -1;
		repaint();
	}
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		repaint();
	}
	public void mouseClicked(MouseEvent ME) {
		ListenerNuevaArista.nuevaArista(null);
		repaint();
	}
	public void mouseEntered(MouseEvent ME) {
		repaint();
	}
	public void mouseExited(MouseEvent ME) {
		repaint();
	}
	public void mouseMoved(MouseEvent e) {
		if(momento.isDeshacerValid())
			barra.deshacer.setEnabled(true);
		if(momento.isRehacerValid())
			barra.rehacer.setEnabled(true);
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}