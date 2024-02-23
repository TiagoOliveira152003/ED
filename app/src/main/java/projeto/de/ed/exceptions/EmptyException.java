/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.de.ed.exceptions;

/**
 *
 * @author 8150121 e 8150133
 */
public class EmptyException extends Exception {

    /**
     * Creates a new instance of <code>EmptyException</code> without detail
     * message.
     */
    public EmptyException() {
    }

    /**
     * Constructs an instance of <code>EmptyException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EmptyException(String msg) {
        super(msg);
    }
}

