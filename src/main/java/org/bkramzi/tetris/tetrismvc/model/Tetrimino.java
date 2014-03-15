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
public abstract class Tetrimino extends AbstractModel{
    
    protected int color;
    protected int xpos, ypos;
    protected int rotation;
    protected int[][][] shape;
    protected Board board;

    public int moveX(int steps){ return xpos+=steps;}
    public int moveY(int steps){ return ypos+=steps;}

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    synchronized public boolean left(){
        drag(board);
        moveX(-1);
        drop(board);
        return true;
    }
    
    synchronized public boolean right(){ 
        drag(board);
        moveX(+1);
        drop(board);
        return true;
    }
    
    synchronized public boolean down(){ 
        drag(board);
        moveY(+1); 
        drop(board);
        return true;
    }
        
    synchronized public boolean rotate(){
        drag(board);
        rotation = (rotation++) % shape.length;
        drop(board);
        return true;
    }
    
    public void drag(Board board){
        if(board!=null){
            for(int j=0;j<shape[0].length;j++){
                for(int i=0;i<shape[0][0].length;i++){
                    int value = board.clear(xpos+i,ypos+j);
                }
            }
        }
    }
    
    public void drop(Board board)throws IndexOutOfBoundsException{
        if(board!=null){
            for(int j=0;j<this.shape[0].length;j++){
                for(int i=0;i<this.shape[0][0].length;i++){
                    try{
                        board.set(xpos+i,ypos+j,this.shape[rotation][j][i]*this.color);
                    }catch(IndexOutOfBoundsException iobe){
                        throw iobe;
                    }
                }
            }
        }
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

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
}
