package edu.logica.patrones.fabricaAbstracta;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.logica.patrones.fachada.Fachada;
import edu.logica.patrones.fachada.ListenerMenuOpciones;
import edu.logica.patrones.prototipo.Prototipo;
import edu.presentacion.Pizarra;

/**
 * Constructor de los componentes, clase abstracta
 */
public abstract class AComponente extends JPanel implements Fachada, Prototipo{

	protected int posicion_x, posicion_y, ancho, alto;
	protected int pos_x, pos_y;
	protected int[] centro;
	protected boolean seleccionado;
	protected String fontName = "";
	protected int fontStyle = 0;
	protected int fontSize = 12;
	
	protected int indexFuente = 0;
	protected int indexTamaño = 4;
	protected boolean is_negras, is_italicas;

	protected Font font = new Font(fontName, fontStyle, fontSize);
	protected Pizarra pizarra;

	/**
	 * Constructor de la clase
	 * @param posicion_x
	 * @param posicion_y
	 * @param ancho
	 * @param alto
	 */
	public AComponente(int posicion_x, int posicion_y, int ancho, int alto){
		this.posicion_x = posicion_x;
		this.posicion_y = posicion_y;
		this.ancho = ancho;
		this.alto = alto;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setOpaque(false);
		setLocation(posicion_x - (int)(ancho/2), posicion_y - (int)(alto/2));
		setSize(ancho, alto);
		repaint(); 		
	}
	
	/**
	 * Redimensiona el Componente
	 * @param ancho
	 * @param alto
	 */
	public void redimensionar(int ancho, int alto){
		this.ancho = ancho;
		this.alto = alto;
		setSize(ancho, alto);
		repaint();
	}
	
	/**
	 * Retorna el centro del componente
	 * @return int[]
	 */
	public int[] getCentro(){
		centro = new int[2];
		centro[0] = (int)(posicion_x +ancho/2)/2;
		centro[1] = (int)(posicion_y + alto/2)/2;
		return centro;
	}
	
	/**
	 * Mueve el componente a la posicion deseada
	 * @param posicion_x
	 * @param posicion_y
	 */
	public void mover(int posicion_x, int posicion_y){
		this.posicion_x = posicion_x;
		this.posicion_y = posicion_y;
		setLocation(posicion_x - (int)(ancho/2), posicion_y - (int)(alto/2));
		repaint();
	}
	
	/**
	 * Borra el componente de la pizarra
	 *
	 */
	public void borrar(){
		pizarra.removerFachada(this);
		pizarra.removerComponente(this);
		System.out.println("Componente Eliminado");
	}
	
	/**
	 * Se envia a si mismo hacia atras en la pizarra
	 *
	 */
	public void enviarAtras(){
		pizarra.enviarAtras(this);
	}
	
	/**
	 * Se envia a si mismo hacia delante en la pizarra
	 *
	 */
	public void enviarDelante(){
		pizarra.enviarDelante(this);
	}
	
	/**
	 * Dibuja el componente en el panel
	 */
	public void paintComponent(Graphics Componente){
		dibujar(Componente);
	}
	
	/**
	 * Selecciona graficamente el componente
	 * @param valor
	 */
	public void setSeleccionado(boolean valor){
		seleccionado = valor;
		if(seleccionado)
			requestFocus();
		repaint();
	}
	
	/**
	 * Hace una copia de la pizarra donde fue añadido
	 * @param pizarra
	 */
	public void setPizarra(Pizarra pizarra){
		this.pizarra = pizarra;
	}
	
	/**
	 * Devuelve la copia de la pizarra donde esta añadido el componente
	 * @return
	 */
	public Pizarra getPizarra(){
		return pizarra;
	}
	
	/**
	 * Muestra el dialogo para cambiar la fuente en el componente
	 *
	 */
	public void cambiarFuente(){
		final JDialog menuFuente = new JDialog();
		final String[] tiposFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		final String[] tiposTamaño = {"8","9","10","11","12","13","14","16","18","20","22","24","26"};
		final JComboBox listaFuentes = new JComboBox(tiposFuentes);
		final JComboBox listaTamaño = new JComboBox(tiposTamaño);
		final JCheckBox negrita = new JCheckBox("Negrita");
		final JCheckBox italica = new JCheckBox("Italica");

		final JButton aceptar = new JButton("Aceptar");
		final JButton cancelar = new JButton("Cancelar");
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				indexFuente = listaFuentes.getSelectedIndex();
				indexTamaño = listaTamaño.getSelectedIndex();
				is_negras = negrita.isSelected();
				is_italicas = italica.isSelected();
				fontName = tiposFuentes[indexFuente];
 
				if(is_negras)
					fontStyle = Font.BOLD;
				if(is_italicas)
					fontStyle = Font.ITALIC;
				if(is_negras && is_italicas)
					fontStyle =Font.BOLD + Font.ITALIC;
				
				fontSize = Integer.parseInt(tiposTamaño[indexTamaño]);
				
				setFuente(fontName, fontStyle, fontSize);
				
				menuFuente.setVisible(false);
				repaint();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFuente.setVisible(false);
				menuFuente.dispose();
				repaint();
			}
		});
		
		menuFuente.setLayout(new GridLayout(4,2));
		
		listaFuentes.setSelectedIndex(indexFuente);
		listaTamaño.setSelectedIndex(indexTamaño);
		negrita.setSelected(is_negras);
		italica.setSelected(is_italicas);

		menuFuente.add(new JLabel("Fuente:"));
		menuFuente.add(listaFuentes);
		menuFuente.add(new JLabel("Tamaño:"));
		menuFuente.add(listaTamaño);
		menuFuente.add(negrita);
		menuFuente.add(italica);
		menuFuente.add(aceptar);
		menuFuente.add(cancelar);
		
		menuFuente.setTitle("Cambiar Fuente");
		menuFuente.setAlwaysOnTop(true);
		menuFuente.setSize(250,120);
		menuFuente.setLocation(300,300);
		menuFuente.setVisible(true);
	}
	
	/**
	 * Cambia la fuente del componente sujeta a los parametros
	 * @param name
	 * @param style
	 * @param size
	 */
	public void setFuente(String name, int style, int size){
		font = new Font(name, style, size);
	}
	
	/**
	 * Abstracto. Dibuja el componente para colocarlo en el panel
	 * @param Componente
	 */
	public abstract void dibujar(Graphics Componente);
	
	/**
	 * Abstracto. Cambia las propiedades de los Componentes
	 *
	 */
	public abstract void showDialogo();
	
	/**
	 * Evento que muestra el dialogo de edicion
	 */
	public void mouseClicked(MouseEvent ME){
		if(ME.getButton() == MouseEvent.BUTTON1 && ME.getClickCount()==2)
			showDialogo();
	}
	
	/**
	 * Evento que cambia el cursor a normal despues de mover el componente
	 */
	public void mouseReleased(MouseEvent ME){
		pizarra.cambiarCursor(null);
	}
	
	/**
	 * Evento que selecciona el componente
	 */
	public void mouseEntered(MouseEvent ME){
		setSeleccionado(true);
	}
	
	/**
	 * Evento que deselecciona el componente
	 */
	public void mouseExited(MouseEvent ME){
		setSeleccionado(false);
	}
	
	/**
	 * Evento que actualiza la posicion del Componente
	 */
	public void mousePressed(MouseEvent ME){
		pos_x = ME.getX(); 
		pos_y = ME.getY();
		
		if(ME.getButton() == MouseEvent.BUTTON3){
			addMouseListener(new ListenerMenuOpciones());
		}
	}
	
	/**
	 * Evento que mueve el componente en la pizarra
	 */
	public void mouseDragged(MouseEvent ME){
		pizarra.cambiarCursor("mover");
				
		int dx = posicion_x + ME.getX() - pos_x;
		int dy = posicion_y + ME.getY() - pos_y;
		
		if(dx < (int)(ancho/2) )
			dx=(int)(ancho/2);
		else 
			if(dx+(int)(ancho/2) > getParent().getWidth())
				dx=getParent().getWidth()-(int)(ancho/2);
			if(dy < (int)(alto/2) )
				dy=(int)(alto/2);
			else
				if(dy+(int)(alto/2) > getParent().getHeight())
					dy=getParent().getHeight()-(int)(alto/2);
		
		mover(dx,dy);
	}
	
	public void mouseMoved(MouseEvent ME){}

	/**
	 * Evento que borra el componente
	 */
	public void keyReleased(KeyEvent KE){
		if(KeyEvent.VK_DELETE == KE.getKeyCode()){
			if(seleccionado)
				borrar();
		}
	}

	public void keyPressed(KeyEvent KE){}
	public void keyTyped(KeyEvent KE){}
}
