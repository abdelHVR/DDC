package edu.logica.patrones.comando;

import edu.logica.patrones.fabricaAbstracta.*;

/**
 * @author N-Solutions
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ComandoCreador extends AComando {

	private FabricaAbstracta fabricaAbstracta;

	private AComponente aComponente;
	
	/**
	 * Metodo que selecciona la clase de producto a ser creado
	 */
	public AComponente ejecutar(String tipo, int pos_x, int pos_y){
		if(tipo.equals("paquete")){
			return new FabricaPaquetes().crearProducto(pos_x, pos_y, 85, 85);
		}
		if(tipo.equals("clase")){
			return new FabricaClases().crearProducto(pos_x, pos_y, 85, 85);
		}
		if(tipo.equals("interfaz")){
			return new FabricaInterfazes().crearProducto(pos_x, pos_y, 85, 85);
		}
		if(tipo.equals("nota")){
			return new FabricaNotas().crearProducto(pos_x, pos_y, 85, 85);
		}
		return null;
	}
}