/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ramzi
 */
public class BoardTest {
    
    Board board = new Board();
    
    public BoardTest() {
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
    public void testLeft() {
    }

    @Test
    public void testRight() {
    }

    @Test
    public void testDown() {
    }

    @Test
    public void testRotate() {
    }

    @Test
    public void testHardDrop() {
    }

    @Test
    public void testClearFullLines() {
    }
}