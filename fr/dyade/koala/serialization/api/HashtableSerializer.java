/* Copyright (c) 1998 by Groupe Bull. All Rights Reserved */
/* $Id: HashtableSerializer.java,v 1.3 1998/09/23 13:42:56 plehegar Exp $ */
/* Author: Thierry-Kormann@sophia.inria.fr  */

package fr.dyade.koala.serialization.api;

import java.io.IOException;

import fr.dyade.koala.serialization.*;

/**
 * Enables the XML serialization of the <code>java.util.Hashtable</code> class.
 *
 * @author Thierry.Kormann@sophia.inria.fr 
 */
public class HashtableSerializer {
    
    public static void readObject(GeneratorInputStream s) 
	    throws ClassNotFoundException, IOException {
	s.defaultReadObject();
	int origlength = s.readInt();
	int elements = s.readInt();
	
	for (; elements > 0; elements--) {
	    s.readObject();
	    s.readObject();
	}
    }
}
