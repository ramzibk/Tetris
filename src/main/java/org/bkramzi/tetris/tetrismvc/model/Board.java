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

/**
 *
 * @author ramzi
 */
public class Board extends AbstractModel implements Cloneable{
    
    private static int XBlocks=12;
    private static int YBlocks=20;
    private static int BUFFER_SIZE=4;
    private int[][] grid;
    private int score;
    private int level;
    private List fullLines = new ArrayList<Integer>();
    private boolean gameover = false;
    
    private Tetrimino current;
    private TetriminoFactory factory;
    private static Logger logger = Logger.getLogger(Board.class.getName());
    
    public Board(){
        grid = new int[YBlocks+BUFFER_SIZE][XBlocks];
        factory = new TetriminoFactory();
        initBoard();
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
            logger.log(Level.SEVERE, null, ex);
        }
        return clone;
    }
    public void initBoard(){
        gameover=false;
        score=0;
        level=0;
        fullLines.clear();
        for(int j=0;j<grid.length;j++){
            for(int i=0;i<grid[0].length;i++){
                grid[j][i]=0;            
            }
        }
        factory= new TetriminoFactory();
        setCurrent(factory.getTetrimino());
    }
    public synchronized boolean left(){
        if(current==null) throw new NullPointerException("Current tetrimino is null");
        System.out.println("left");
        Board back = (Board) this.clone();
        drag(current);
        current.moveX(-1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(+1);
            logger.log(Level.SEVERE, "x,y:"+current.getXpos()+","+current.getYpos(), ex);
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
    public synchronized boolean right(){
        if(current==null) throw new NullPointerException("Current tetrimino is null");
        Board back = (Board) this.clone();
        drag(current);
        current.moveX(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveX(-1);
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE,  "x,y:"+current.getXpos()+","+current.getYpos(), ex);
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
    public synchronized boolean down(){
        if(current==null) throw new NullPointerException("Current tetrimino is null");
        Board back = (Board) this.clone();
        drag(current);
        current.moveY(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveY(-1);
            logger.log(Level.SEVERE, "x,y:"+current.getXpos()+","+current.getYpos(), ex);
            return false;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.moveY(-1);
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    public synchronized boolean rotate(){
        if(current==null) throw new NullPointerException("Current tetrimino is null");
        Board back = (Board) this.clone();
        drag(current);
        current.setRotation(+1);
        try {
            drop(current);
        } catch (IndexOutOfBoundsException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.setRotation(-1);
            logger.log(Level.SEVERE, "x,y:"+current.getXpos()+","+current.getYpos(), ex);
            return false;
        } catch (GridCorruptedException ex) {
            // rollback
            this.setGrid(back.getGrid());
            current.setRotation(-1);
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
        fireChange();
        return true;
    }
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
        synchronized public void next() {
        if(current!=null && !down()){
            getFullLines();
            updateScore(clearFullLines());
            if(current.getYpos()<BUFFER_SIZE){
                endGame();
            }else{
                current = factory.getTetrimino();
            }
        }
        fireChange();
    }
    public void drag(Tetrimino tetrimino){
        if(tetrimino!=null){
            System.out.println("drag");
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
            System.out.println("drop");
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
        for(int j=0;j<grid.length;j++){
            boolean full = true;
            for(int i=0;i<grid[0].length;i++){
                if(grid[j][i]==0){
                    full = false;
                    break;
                }
            }
            if(full) fullLines.add(j);
        }
        fireChange();
        return fullLines;
    }
    public int clearFullLines(){
        int deleted  = 0;
        Iterator it = fullLines.iterator();
        while(it.hasNext()){
            int j = (Integer)it.next();
            for(int i=0;i<grid[j].length;i++){
                clear(i,j);
            }
            deleted++;
            // repack
            for(int k=j;k>0;k--){
                for(int i=0;i<grid[j].length;i++){
                 grid[k][i] = grid[k-1][i];
                }
            }
            // add empty line at the top
            for(int i=0;i<grid[j].length;i++){
                 grid[0][i] = 0;
            }
        }
        fullLines.clear();
        fireChange();
        return deleted;
    }
    public void hardDrop(){
        if (current == null) {
            throw new NullPointerException("Current tetrimino is null");
        }
        while (down()){}
        fireChange();
    }
    public Tetrimino getCurrent() {return current;}
    public void setCurrent(Tetrimino current) {
        this.current = current;
        if(current!=null)
            current.setBoard(this);
    }
    public int[][] getGrid() {
        return grid;
    }
    public int clear(int x, int y) {
        int last = grid[y][x];
        grid[y][x]=0;
        return last;
    }
    public int getValue(int i, int j) {return grid[j][i];}
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
    public void endGame(){
        current = null;
        gameover = true;
    }
    private void updateScore(int deleted) {
        this.score += deleted*(20+(20*deleted));
        setLevel((int)(score%3000));
    }
    public boolean isGameover() {return gameover;}
    public int getScore() {return score;}
    public int getLevel() {return level;}
    public void setScore(int score) {this.score = score;}
    public void setLevel(int level) {this.level = level;}
    public static int getXBlocks() {return XBlocks;}
    public static int getYBlocks() {return YBlocks+BUFFER_SIZE;}
}
