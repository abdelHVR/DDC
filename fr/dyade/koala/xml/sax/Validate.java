/*
 * Copyright (c) 1998 by Groupe Bull. All Rights Reserved
 * Validate.java
 * $Id$
 */
package fr.dyade.koala.xml.sax;

import java.io.File;
import java.net.URL;

import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.ParserFactory;

/**
 * @version $Revision$
 * @author  Philippe Le Hégaret
 */
public class Validate implements DocumentHandler, ErrorHandler {
    String baseDir;

    int level;

    Parser parser;

    // use my own parser if any
    static final String SAX_DEFAULT = "fr.dyade.koala.xml.sax.SAXParser";

    private static final boolean KoalaDebug = false;

    /**
     * Creates a new Validate.
     *
     * <p>Levels for errors and warnings</p>
     * <dl>
     *  <dt>0
     *  <dd>Show only fatal errors
     *  <dt>1
     *  <dd>Show errors and fatal errors
     *  <dt>2
     *  <dd>Show warnings, errors and fatals errors
     * </dl>
     * 
     * @param level the level for errors and warnings
     */
    public Validate(int level) throws Exception {
	this.level = level;

	// initialize the SAX parser

	String className = System.getProperty("org.xml.sax.parser");
        if (className == null) {
            className = SAX_DEFAULT;
        }
        try {
            parser = ParserFactory.makeParser(className);
            parser.setDocumentHandler(this);
        } catch (Exception e) {
            System.err.println("can't create parser <" + className +">");
	    System.err.println("\t" + e.getMessage());
	    //	    e.printStackTrace();
        }

	// add a document handler (show nothing!)
	parser.setDocumentHandler(this);	

	// add an error handler
	parser.setErrorHandler(this);
    }
    
    // Document Handler section

    public void setDocumentLocator (Locator locator) {
    }
    
    public void startDocument () throws SAXException {
    }
    
    public void endDocument () throws SAXException {
    }
    
    public void startElement (String name, AttributeList atts) 
	throws SAXException {
    }
    
    public void endElement (String name) throws SAXException {
    }
    
    public void characters (char ch[], int start, int length)
	throws SAXException {
    }
    
    public void ignorableWhitespace (char ch[], int start, int length)
	throws SAXException {
    }
    
    public void processingInstruction (String target, String data)
	throws SAXException {
    }

    // Error Handler section
    
    /**
     * Suppress the base directory of a file.
     *
     * <pre>
     * suppressBaseDir("/tmp/math.xml") -> "math.xml"
     * </pre>
     */    
    private String suppressBaseDir(String file) {
	if ((file != null) && (file.startsWith(baseDir))) {
	    file = file.substring(baseDir.length());
	}
	return file;
    }

    private void showMessage(String kind, SAXParseException e) {
	String file = suppressBaseDir(e.getSystemId());
	
	System.err.println(file 
			   + ":" + e.getLineNumber() 
			   + ":" + e.getColumnNumber());
	
	System.err.println("\t" + kind + ": " + e.getMessage());
	if (KoalaDebug) {
	    if (e.getException() != null) {
		e.getException().printStackTrace();
	    } else {
		e.printStackTrace();
	    }
	}
    }

    public void warning(SAXParseException e) throws SAXException {
	if (level > 1) {
	    showMessage("\tWarning: ", e);
	}
    }
    
    public void error(SAXParseException e) throws SAXException {
	if (level > 0) {
	    showMessage("\tError: ", e);
	}
    }
    
    public void fatalError(SAXParseException e) throws SAXException {
	showMessage("\tFatal Error: ", e);
    }

    // Main section

    /**
     * Parse an XML document
     *
     * @param location where to find the document
     */    
    public void parseDocument(URL location) {
	java.io.InputStream f;

	try {
	    f = location.openStream();
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
	try {
	    InputSource source = new InputSource(f);
	    String file = location.toString();
	    int index = file.lastIndexOf(File.separator);
	    source.setSystemId(file);
	    if (index != -1) {
		baseDir = file.substring(0, index + 1);
	    } else {
		baseDir = "";
	    }
	    parser.parse(source);
	} catch (Exception e) {
	}
    }
    
    public static void main(String args[]) throws Exception {
	String doc = null;
	Validate valide = null;
	int i = 0;
	int level = 0;

	// parse options ...

        try {
            doc = args[i];
            
            while (args[i].charAt(0) == '-') {
                String argument = args[i].substring(1).toLowerCase();
                if (argument.equals("e")) {
                    level = 1;
                } else if (argument.equals("w")) {
		    level = 2;
                } else {
                    throw new Exception();
                }
                i++;
            }
        } catch (Exception e) {
	    Validate.showHelp();
        }

	// process documents ...

	// I want to valide documents ...
	System.getProperties().put("org.xml.sax.validation", "true");

	valide = new Validate(level);
	try {
	    do {
		doc = args[i++];
		File f = new File(doc);
		valide.parseDocument(new URL("file", null, 
					     -1, f.getAbsolutePath()));
	    } while(i < args.length);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Validate.showHelp();
        } catch (Exception e) {
	    System.err.println(e);
	    System.exit(1);
	}
    }

    private static void showHelp() {
	System.out.println( "Usage: java Validate " + 
			    " [ -e || -w || -sax <ClassName>] [<file>]+");
	System.out.println( "\t-e\tShow errors");
	System.out.println( "\t-w\tShow errors and warnings.");
	System.exit(1);
    }

}
