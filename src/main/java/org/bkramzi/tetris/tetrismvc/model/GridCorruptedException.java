/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

/**
 *
 * @author ramzi
 */
public class GridCorruptedException extends java.lang.Exception{
    
    public GridCorruptedException(String msg){
        super(msg);
    }
    public GridCorruptedException(){
        this("GridCorruptedException occured!");
    }
    
}
