/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.bkramzi.tetris.tetrismvc.model.Board;
import org.bkramzi.tetris.tetrismvc.model.Tetrimino;

/**
 *
 * @author ramzi
 */
public class TetriminoPeview extends JComponent implements ChangeListener{

    private Board board;
    private Tetrimino currrent;
    
    public TetriminoPeview(){
        super();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        if(board==null) {
        } else {
            this.board = board;
            board.addChangeListener(this);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(board==null) return;
        currrent = (Tetrimino) board.getBuffer().get(0);
        int[][] shape = currrent.getShape()[0];
        g.setColor(Color.white);
        for(int j=0;j<shape.length;j++){
            for(int i=0;i<shape[0].length;i++){
                if(shape[j][i]!=0)
                    g.fillRect(i*15, j*15, 15, 15);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        if(board.getBuffer().get(0)!=currrent)
            repaint();
    }
}
