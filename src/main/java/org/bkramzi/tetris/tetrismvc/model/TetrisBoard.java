/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramzi
 */
public class TetrisBoard extends AbstractModel{
    
    private static int width=20;
    private static int height=12;
    int[][] matrix;
    /**
     * ^ j
     * | 
     * |       i
     * +-------->
     */
    Tetrimino current;
    
    public TetrisBoard(){
        matrix = new int[height][width];
    
    }
    public Tetrimino getCurrent() {
        return current;
    }
    public void setCurrent(Tetrimino current) {
        this.current = current;
    }
    public List getFullLines(){
        return new ArrayList();
    }
    public void removeLine(int line){
        for(int i=0;i<width;i++){
            matrix[line][i]=0;
        }
    }
    public void removeLines(List<Integer> lines){
        for(int i:lines){
            removeLine(i);
        }
    }
    
    public void hold(Tetrimino t){}
    public void drop(Tetrimino t){}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int j=height-1;j>0;j--){
            for(int i=0;i<width-1;i++){
               sb = sb.append(matrix[j][i]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
