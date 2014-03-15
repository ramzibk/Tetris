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
public class J_Tetrimino extends Tetrimino{
    
    public J_Tetrimino(){
        this.shape = new int[][][]{
                        {{0,1,0},
                         {0,1,0},
                         {1,1,0}},
                        //
                        {{1,0,0},
                         {1,1,1},
                         {0,0,0}},
                        //
                        {{0,1,1},
                         {0,1,0},
                         {0,1,0}},
                        //
                        {{0,0,0},
                         {1,1,1},
                         {0,0,1}}};
    
        this.color =2;
    }
}
