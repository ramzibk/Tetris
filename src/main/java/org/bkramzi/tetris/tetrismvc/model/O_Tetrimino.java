/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.awt.Color;

/**
 *
 * @author ramzi
 */
public class O_Tetrimino extends Tetrimino{
    
    public O_Tetrimino(){
        this.shape = new int[][][]{
                        {{1,1},
                         {1,1}}};
    
        this.color =4;
    }
}
