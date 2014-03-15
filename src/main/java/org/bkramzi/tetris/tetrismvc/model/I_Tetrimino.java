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
public class I_Tetrimino extends Tetrimino{
    
    public I_Tetrimino(){
        this.shape = new int[][][]{
                        {{0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0}},
                        //
                        {{0,0,0,0},
                         {1,1,1,1},
                         {0,0,0,0},
                         {0,0,0,0}},
                        //
                        {{0,0,1,0},
                         {0,0,1,0},
                         {0,0,1,0},
                         {0,0,1,0}},
                        //
                        {{0,0,0,0},
                         {0,0,0,0},
                         {1,1,1,1},
                         {0,0,0,0}}};
    
        this.color=1;
    }
}
