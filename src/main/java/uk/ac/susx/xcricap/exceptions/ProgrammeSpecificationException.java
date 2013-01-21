/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.exceptions;

/**
 *
 * @author rjb41
 */
public class ProgrammeSpecificationException extends Exception {

    /**
     * Creates a new instance of <code>ProgrammeSpecificationException</code> without detail message.
     */
    public ProgrammeSpecificationException() {
    }

    /**
     * Constructs an instance of <code>ProgrammeSpecificationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ProgrammeSpecificationException(String msg) {
        super(msg);
    }
}
