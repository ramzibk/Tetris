/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bkramzi.tetris.tetrismvc.controller.BoardController;

/**
 *
 * @author ramzi
 */
public class Board extends AbstractModel implements Cloneable{
    
    private static int XBlocks=12;
    private static int YBlocks=20;
    private int[][] grid;
    private int score;
    private int level;
    private int deleted;
    
    private Tetrimino current;
    private TetriminoFactory factory;
    
    public Board(){
        grid = new int[YBlocks][XBlocks];
        factory = new TetriminoFactory();
    }
    
    // Soft clone
    @Override
    protected Object clone(){
        Board clone=null;
        try {
            clone = (Board)super.clone();
            clone.setGrid(this.getGrid().clone());
            for(int j=0;j<this.getGrid().length;j++){
                clone.getGrid()[j] = this.getGrid()[j].clone();
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clone;
    }
    
    synchronized public boolean left(){
        if(current==null) return true;
        Board back = (Board) this.clone();
        drag(current);
        current.moveX(-1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(+1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(+1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    synchronized public boolean right(){
        if(current==null)return true;
            Board back = (Board) this.clone();
        drag(current);
        current.moveX(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    synchronized public boolean down(){
        if(current==null) return true;
        Board back = (Board) this.clone();
        drag(current);
        current.moveY(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveY(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveY(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    
    synchronized public boolean rotate(){
        if(current==null) return true;
        Board back = (Board) this.clone();
        drag(current);
        current.setRotation(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.setRotation(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.setRotation(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    public void hardDrop(){
        if (current == null) {
            return;
        }
        while (down()){}
        fireChange();
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
    
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    public void drag(Tetrimino tetrimino){
        if(tetrimino!=null){
            for(int j=0;j<tetrimino.shape[0].length;j++){
                for(int i=0;i<tetrimino.shape[0][0].length;i++){
                    if(tetrimino.getValue(i,j)!=0)
                        this.clear(tetrimino.getXpos()+i,tetrimino.getYpos()+j);
                }
            }
        }
    }
    
    public boolean drop(Tetrimino tetrimino)throws IndexOutOfBoundsException, GridCorruptedException{
        if(tetrimino!=null){
            for(int j=0;j<tetrimino.shape[0].length;j++){
                for(int i=0;i<tetrimino.shape[0][0].length;i++){
                    if(tetrimino.getValue(i, j)==0) continue;
                    try{
                        this.setValue(tetrimino.getXpos()+i,tetrimino.getYpos()+j,tetrimino.getValue(i,j));;
                    }catch(IndexOutOfBoundsException iobe){
                        throw iobe;
                    } catch (GridCorruptedException ex) {
                        throw ex;
                    }
                }
            }
        }
        return true;
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
                clear(i,j);
            }
        }
    }
    
    public int clear(int x, int y) {
        int last = grid[y][x];
        grid[y][x]=0;
        return last;
    }

    public int getValue(int i, int j) {
        return grid[j][i];
    }
    
    public void setValue(int x, int y, int value) throws GridCorruptedException{
        if(grid[y][x]!=0) throw new GridCorruptedException("GridCorruptedException at (x,y):"+x+","+y);
        grid[y][x] = value;
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

    synchronized public void next() {
        if(!down()){
            current = factory.getTetrimino();
        }
        fireChange();
    }
    
    public static int getXBlocks() {
        return XBlocks;
    }

    public static int getYBlocks() {
        return YBlocks;
    }
}
