package edu.serializar;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import fr.dyade.koala.xml.koml.KOMLSerializer;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Guardar extends JFileChooser {

	private int eleccion;
	
	/**
	 * Constructor de la clase
	 */
	public Guardar(Object objeto) {	
		setDialogTitle("Guardar Archivo de DDC-Net");
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
		eleccion = showSaveDialog(this);
		
		if(eleccion == JFileChooser.APPROVE_OPTION){
			try {
				KOMLSerializer save = new KOMLSerializer(getSelectedFile().getAbsolutePath(), false);
				save.addObject(objeto);
				save.close();
			} catch (IOException e) {
				System.err.println("Error al guardar archivo");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
