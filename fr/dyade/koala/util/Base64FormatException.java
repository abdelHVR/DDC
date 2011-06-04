/*
 * Copyright (c) 1998 by Groupe Bull. All Rights Reserved
 * Base64FormatException.java
 * $Id: Base64FormatException.java,v 1.1 1998/11/29 15:33:46 plehegar Exp $
 */
package fr.dyade.koala.util;

import java.io.IOException;

/**
 * @version $Revision: 1.1 $
 * @author  Philippe Le Hegaret
 */
public class Base64FormatException extends IOException {

    /**
     * Creates a new Base64FormatException
     */
    public Base64FormatException() {
	super();
    }
    
    /**
     * Creates a new Base64FormatException
     */
    public Base64FormatException(String s) {
	super(s);
    }
}
