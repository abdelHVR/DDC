/*
 * Copyright (c) 1998 by Groupe Bull. All Rights Reserved
 * KOMLException.java
 * $Id: KOMLException.java,v 1.2 1998/09/23 09:45:25 plehegar Exp $
 */
package fr.dyade.koala.xml.koml;

import org.xml.sax.SAXException;

/**
 * @version $Revision: 1.2 $
 * @author  Philippe Le Hégaret
 */
class KOMLException extends SAXException {
    public KOMLException(String s) {
	super(s);
    }

    public KOMLException(String s, Exception e) {
	super(s, e);
    }
}
