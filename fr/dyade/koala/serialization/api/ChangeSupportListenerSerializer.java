/* Copyright (c) 1998 by Groupe Bull. All Rights Reserved */
/* $Id: ChangeSupportListenerSerializer.java,v 1.1 1998/09/25 14:31:10 plehegar Exp $ */
/* Author: Thierry-Kormann@sophia.inria.fr  */

package fr.dyade.koala.serialization.api;

import java.io.IOException;
import fr.dyade.koala.serialization.*;

/**
 * Enables the XML serialization of <code>Objects</code> that use
 * PropertyChangeListener.
 *
 * @author Thierry.Kormann@sophia.inria.fr 
 */
public class ChangeSupportListenerSerializer {
    
    public static synchronized void readObject(GeneratorInputStream s) 
	    throws ClassNotFoundException, IOException {
	s.defaultReadObject();
	while(null != s.readObject());
    }

}
