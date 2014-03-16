/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramzi
 */
public abstract class Tetrimino extends AbstractModel{
    
    protected int color;
    protected int xpos, ypos;
    protected int rotation;
    protected int[][][] shape;
    protected Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void setRotation(int turn){
        this.rotation = (this.rotation+turn)%shape.length;
    }
    public int getRotation(){return rotation;}    
    public int moveX(int steps){ 
        xpos+=steps;
        return xpos;
    }
    public int getXpos(){return xpos;}
    public int moveY(int steps){ 
        ypos+=steps;
        return ypos;
    }
    public int getYpos(){return ypos;}
    public int getValue(int i, int j){
        return shape[rotation][j][i]*color;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int j=0;j<shape.length;j++){
            for(int i=0;i<shape.length;i++){
                builder.append(shape[j][i]);
            }
        }
        return builder.toString();
    }
}
