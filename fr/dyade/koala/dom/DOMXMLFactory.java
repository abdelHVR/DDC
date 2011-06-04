/*
 * Copyright (c) 1998 by Groupe Bull. All Rights Reserved
 * DOMParse.java
 * $Id: DOMXMLFactory.java,v 1.1 1998/11/24 19:24:44 plehegar Exp $
 */
package fr.dyade.koala.dom;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.Reader;
import java.io.IOException;
import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;

import org.xml.sax.DocumentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.Locator;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.AttributeList;

import fr.dyade.koala.xml.sax.ParserFactory;

/**
 * This class parses an XML document and return a DOM Document.
 * This factory use SAX and DOM.
 * 
 * <p>uses properties :
 * <ul>
 * <li><code>org.xml.sax.parser</code> for the SAX parser
 * <li><code>org.w3c.dom.document</code> for the DOM document
 * </ul>
 * @author Philippe Le Hegaret
 */
public class DOMXMLFactory {

    /**
     * no DOMFactory should be instanciate !
     */
    private DOMXMLFactory() {       
    }
    

    /**
     * Creates a DOM Document from an object source.
     * If the object source is a String, the DOMFactory converts it into an URL.
     *
     * <pre class="example">
     *  Document url1 = DOMFactory.getDocument("http://www.inria.fr/fake.xml");
     *  Document url2 
     *     = DOMFactory.getDocument(new URL("http://www.inria.fr/fake.xml"));
     *  Document file1 = DOMFactory.getDocument("foo.xml");
     *  Document file2 = DOMFactory.getDocument(new FileInputStream("foo.xml");
     * </pre>
     *
     * @param source an object (a Reader, an InputStream, a String, ...).
     * @return a DOM Document or null.
     * @exception DOMException any DOM Exception.
     * @exception SAXException a parse error.
     * @exception IOException an I/O error.
     */    
    public static Document getDocument(Object source) 
	    throws DOMException, SAXException, IOException {
	if (source == null) {
	    return null;
	} else {
	    return (new DOMSAXHandler())
		.getDocument(createInputSource(source), null);
	} 
    }

    /**
     * Creates a DOM Document from an object o with a specified SAX ErrorHandler.
     * If the object o is a String, the DOMFactory converts it into an URL.
     *
     * @param source an object (a Reader, an InputStream, a String, ...).
     * @return a DOM Document or null.
     * @exception DOMException any DOM Exception.
     * @exception SAXException a parse error.
     * @exception IOException an I/O error.
     * @see DOMFactory#getDocument(Object)
     */    
    public static Document getDocument(Object source, ErrorHandler errHandler) 
	    throws DOMException, SAXException, IOException {
	if (source == null) {
	    return null;
	} else {
	    return (new DOMSAXHandler())
		.getDocument(createInputSource(source), errHandler);
	} 
    } 

    private final static String[] allDOMs =
    {
	"com.docuverse.dom.BasicDocument",
	"com.ibm.xml.parser.TXDocument",
	"com.sun.xml.tree.XmlDocument"
    };

    /**
     * Creates an empty DOM Document using the system property
     * `org.w3c.dom.document'.
     * If no system property found, try to find a Dom document, here is the
     * search list :
     * <ol>
     * <li>"com.docuverse.dom.BasicDocument"
     * <li>"com.ibm.xml.parser.TXDocument"
     * <li>"com.sun.xml.tree.XmlDocument"
     * </ol>
     *
     * @exception java.lang.ClassNotFoundException The SAX parser
     *            class was not found (check your CLASSPATH).
     * @exception IllegalAccessException The SAX parser class was
     *            found, but you do not have permission to load
     *            it.
     * @exception InstantiationException The SAX parser class was
     *            found but could not be instantiated.
     * @exception java.lang.ClassCastException The SAX parser class
     *            was found and instantiated, but does not implement
     *            org.xml.sax.Parser.
     */   
    public static Document createDocument()
            throws ClassNotFoundException, 
                   IllegalAccessException, 
                   InstantiationException,
                   ClassCastException {
	String _t = null;
	try {
	    _t = System.getProperty("org.w3c.dom.document");
	} catch (SecurityException e) {
	    // ignore
	}
	if (_t != null) {
	    return (Document) Class.forName(_t).newInstance();
	}

	for (int i = 0; i < allDOMs.length; i++) {
	    try {
		return (Document) Class.forName(allDOMs[i]).newInstance();
	    } catch (ClassNotFoundException ex) {
		// ignore
	    }
	}

	throw new ClassNotFoundException("No DOM implementation found.");
    }

    /** Create a SAX InputSource from an Object */
    private static InputSource createInputSource(Object source)
	    throws IOException {
	if (source instanceof InputSource) {
	    return (InputSource) source;
	} else if (source instanceof InputStream)
	    return new InputSource((InputStream) source);
	else if (source instanceof URL) {
		return new InputSource(((URL) source).toString());
	} else if (source instanceof Reader) {
	    return new InputSource((Reader) source);
	} else if (source instanceof String) {
	    URL url = null;
	    try {
		url = new URL(source.toString());
	    } catch (MalformedURLException ex) {
		File f = new File(source.toString());
		url = new URL("file", null, -1, f.getCanonicalPath());
	    } 
	    return new InputSource(((URL) url).toString());
	} else {
	    return new InputSource(source.toString());
	}
    } 
}

/**
 * This class is used to construct the DOM Document
 */
class DOMSAXHandler implements DocumentHandler {

    /** The SAX Parser */
    Parser parser;
    Document document;
    Node current;
    
    private static final ErrorHandler defaultErrorHandler;

    Document getDocument(InputSource source, ErrorHandler errHandler) 
	    throws DOMException, SAXException, IOException {

	// create the parser and the DOM document
	try {
	    parser = ParserFactory.makeParser();
	    document = DOMXMLFactory.createDocument(); 
	    this.current = document;
	} catch(ClassNotFoundException e) {
	    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, 
				   e.toString()) {};
	} catch(ClassCastException e) {
	    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, 
				   e.toString()) {};
	} catch(IllegalAccessException e) {
	    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, 
				   e.toString()) {};
	} catch(InstantiationException e) {
	    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, 
				   e.toString()) {};
	}	

	parser.setDocumentHandler(this);
	if (errHandler != null) {
	    parser.setErrorHandler(errHandler);
	} else {
	    parser.setErrorHandler(defaultErrorHandler);
	}
	parser.parse(source);

        return document;
    } 
    
    /* ***
	 SAX HANDLER SECTION
    *****/

    /* Sax Handlers */
    public void setDocumentLocator(Locator locator) {
    } 

    /* Sax Handlers */
    public void startDocument() throws SAXException {	
    } 

    /* Sax Handlers */
    public void endDocument() throws SAXException {
    } 

    /* Sax Handlers */
    public void startElement(String name, AttributeList atts)
	    throws SAXException {
	Element elem = document.createElement(name);
	if (atts != null) {
	    int count = atts.getLength();
	    for (int i = 0; i < count; i++) {
		elem.setAttribute(atts.getName(i), atts.getValue(i));
	    }
	} 	
	current.appendChild(elem);
	current = elem;
    } 

    /* Sax Handlers */
    public void endElement(String name) throws SAXException {
	current = current.getParentNode();
    }

    /* Sax Handlers */
    public void characters(char ch[], int start, int length)
	    throws SAXException {
	current.appendChild(document
			    .createTextNode(new String(ch, start, length)));
    } 

    /* Sax Handlers */
    public void ignorableWhitespace(char ch[], int start, int length)
	    throws SAXException {
	current.appendChild(document
			    .createTextNode(new String(ch, start, length)));
    } 

    /* Sax Handlers */
    public void processingInstruction(String name, String remainder)
	    throws SAXException {
	current.appendChild(document
			    .createProcessingInstruction(name, remainder));
    } 

    static {
	defaultErrorHandler = new InternalErrorHandler();
    } 
} 

class InternalErrorHandler implements ErrorHandler {
  public void warning (SAXParseException exception)
      throws SAXException {
  }
  public void error (SAXParseException exception)
      throws SAXException {
  }
  public void fatalError (SAXParseException exception)
      throws SAXException {
  }
    
}
