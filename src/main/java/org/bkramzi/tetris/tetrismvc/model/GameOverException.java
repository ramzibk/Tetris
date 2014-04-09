/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

/**
 *
 * @author ramzi
 */
public class GameOverException extends Exception {

    /**
     * Creates a new instance of
     * <code>GameOverException</code> without detail message.
     */
    public GameOverException() {
    }

    /**
     * Constructs an instance of
     * <code>GameOverException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public GameOverException(String msg) {
        super(msg);
    }
}
