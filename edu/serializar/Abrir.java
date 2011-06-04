package edu.serializar;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import edu.presentacion.MarcoDeGraficacion;
import fr.dyade.koala.xml.koml.KOMLDeserializer;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Abrir extends JFileChooser{
	
	private int eleccion;
	
	/**
	 * Constructor de la Clase
	 */
	public Abrir() {
		setDialogTitle("Abrir Archivo de DDC-Net");
		setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				if(f.toString().endsWith(".ddc")){
					return true;
				}
				if(f.isDirectory()){
					return true;
				}
				return false;
			}

			public String getDescription() {
				return "DDC-Net archivos";
			}
		});
		setMultiSelectionEnabled(false);
		eleccion = showOpenDialog(this);
		
		if(eleccion == JFileChooser.APPROVE_OPTION){
			try {
				KOMLDeserializer open = new KOMLDeserializer(getSelectedFile().getAbsolutePath(), false);
				MarcoDeGraficacion.vistas.abrirVista(open.readObject());
				open.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
