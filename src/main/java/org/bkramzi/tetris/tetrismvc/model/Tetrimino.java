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
public abstract class Tetrimino extends AbstractModel implements Cloneable{
    
    protected int color;
    protected int xpos=0, ypos=0;
    protected int rotation;
    protected int[][][] shape;
    protected Board board;

    public Board getBoard() {
        return board;
    }
    
    public Object clone(){
        Object clone=null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Tetrimino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clone;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setShape(int[][] shape,int rotation){
        this.shape[rotation]=shape;
    }

    public int[][][] getShape() {
        return shape;
    }
    
    public void setRotation(int turn){
        this.rotation = (this.rotation+turn)%shape.length;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public int getRotation(){return rotation;}    
    public int moveX(int steps){ 
        System.out.println("moveX "+steps +"steps");
        xpos+=steps;
        return xpos;
    }
    public void setXpos(int xpos){ this.xpos=xpos;}
    public int getXpos(){return xpos;}
    public int moveY(int steps){
        System.out.println("MoveY "+steps+" steps");
        ypos+=steps;
        return ypos;
    }
    public void setYpos(int ypos){ this.ypos=ypos;}
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
