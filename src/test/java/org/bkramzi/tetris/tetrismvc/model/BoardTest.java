/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author ramzi
 */
public class BoardTest {
    
    Tetrimino tetrimino;
    Board board;
    
    public BoardTest() {
    }

    @Before
    public void setBefore(){
        tetrimino =  new T_Tetrimino();
        board=new Board();
    }
    
    @After
    public void setAfter(){
        
    }
    
    @Test
    public void testClone() throws CloneNotSupportedException {
        Object clone = board.clone();
        Assert.assertNotNull(clone);
        Assert.assertNotSame(board, clone);
        Assert.assertNotSame(((Board)clone).getGrid(), board.getGrid());
        for(int j=0;j<board.getGrid().length;j++){
            Assert.assertNotSame(((Board)clone).getGrid()[j], board.getGrid()[j]);
        }
    }
    @Test
    public void testDrop() {
        tetrimino.setXpos(0);
        tetrimino.setYpos(0);
        tetrimino.setRotation(0);
        tetrimino.setColor(1);
        tetrimino.setShape(new int[][]{{1,1,1},{0,1,0},{0,0,0}},0);
        try {
            board.drop(tetrimino);
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Exception caught:"+ex.getMessage());
        } catch (GridCorruptedException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Exception caught:"+ex.getMessage());
        }
        Assert.assertEquals(board.getGrid()[0][0], 1);
        Assert.assertEquals(board.getGrid()[0][1], 1);
        Assert.assertEquals(board.getGrid()[0][2], 1);
        Assert.assertEquals(board.getGrid()[1][0], 0);
        Assert.assertEquals(board.getGrid()[1][1], 1);
        Assert.assertEquals(board.getGrid()[1][2], 0);
        Assert.assertEquals(board.getGrid()[2][0], 0);
        Assert.assertEquals(board.getGrid()[2][1], 0);
        Assert.assertEquals(board.getGrid()[2][2], 0);
    }
    @Test
    public void testDropOutside(){
        try {
            tetrimino.setXpos(-4);
            tetrimino.setYpos(-5);
            board.drop(tetrimino);
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (GridCorruptedException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Assert.fail("Exceeption not caught");
    }
    @Test
    public void testGridCurruptedException(){
        int[][] grid = board.getGrid();
        for(int j=0;j<grid.length;j++){
            for(int i=0;i<grid[0].length;i++){
                grid[j][i]=1;
            }
        }
        try {
            board.drop(tetrimino);
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (GridCorruptedException ex) {
            Logger.getLogger(BoardTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Assert.fail("GridCorruptedException not caught!");
    }
}