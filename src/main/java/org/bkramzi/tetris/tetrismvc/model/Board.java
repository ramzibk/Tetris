/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ramzi
 */
public class Board extends AbstractModel{
    
    private static int XBlocks=12;
    private static int YBlocks=20;
    private int[][] grid;
    private int score;
    private int level;
    private int deleted;
    
    private Tetrimino current;

    public Board() {
        grid = new int[YBlocks][XBlocks];
    }
    
    public Tetrimino getCurrent() {
        return current;
    }
    
    public void setCurrent(Tetrimino current) {
        this.current = current;
        if(current!=null)
            current.setBoard(this);
    }

    public int[][] getGrid() {
        return grid;
    }

    public static int getXBlocks() {
        return XBlocks;
    }

    public static int getYBlocks() {
        return YBlocks;
    }
    
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    public List getFullLines(){
        List lines = new ArrayList();
        for(int j=0;j<grid.length;j++){
            boolean full = true;
            for(int i=0;i<grid[0].length;i++){
                if(grid[j][i]!=1){
                    full = false;
                    break;
                }
            }
            if(full) lines.add(j);
        }
        return lines;
    }
    
    public void clearFullLines(List lines){
        Iterator it = lines.iterator();
        while(it.hasNext()){
            int j = (Integer)it.next();
            for(int i=0;i<grid[j].length;i++){
                grid[j][i]=0;
            }
        }
    }
    
    int clear(int x, int y) {
        int last = grid[y][x];
        grid[y][x]=0;
        return last;
    }

    int setValue(int x, int y, int value) {
        int last = grid[y][x];
        grid[y][x] = value;
        fireChange();
        return last;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int j=0;j<grid.length;j++){
            for(int i=0;i<grid[0].length;i++){
                builder.append(grid[j][i]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void next() {
        current.down();
        fireChange();
    }

    public String getValue(int i, int j) {
        return String.valueOf(grid[j][i]);
    }
}
