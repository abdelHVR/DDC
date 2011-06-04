/* Copyright (c) 1998 by Groupe Bull. All Rights Reserved */
/* $Id: SimpleTimeZoneSerializer.java,v 1.2 1998/09/23 13:41:45 plehegar Exp $ */
/* Author: Thierry-Kormann@sophia.inria.fr  */

package fr.dyade.koala.serialization.api;

import java.io.IOException;
import fr.dyade.koala.serialization.*;

/**
 * Enables the XML serialization of the <code>java.util.SimpleTimeZone</code>
 * class.
 *
 * @author Thierry.Kormann@sophia.inria.fr 
 */
public class SimpleTimeZoneSerializer {

    public static void readObject(GeneratorInputStream s) 
	    throws ClassNotFoundException, IOException {
	s.defaultReadObject();

	int length = s.readInt();
	byte[] rules = new byte[length];
	s.readFully(rules);
    }
}
