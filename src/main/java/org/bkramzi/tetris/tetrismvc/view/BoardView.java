/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.bkramzi.tetris.tetrismvc.model.Board;

/**
 *
 * @author ramzi
 */
public class BoardView extends JComponent implements ChangeListener{

    Board board;
    private static final int BLOCK_WIDTH=19;
    private static final int BLOCK_HEIGHT=19;
    private static Color[] colors= {new Color(255,0,0), // red
                                    new Color(255,255,0), //green
                                    new Color(0,0,255), //blue
                                    new Color(0,200,255), //light blue
                                    new Color(128,0,128), //purple
                                    new Color(255,255,0), //yellow
                                    new Color(255,0,255), //magenta
                                    new Color(255,128,0), //orange
                                    };
    
    public BoardView(Board board) {
        this.board = board;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BLOCK_WIDTH*Board.getXBlocks(),BLOCK_HEIGHT*Board.getYBlocks());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if(board!=null){
            int[][] grid = board.getGrid();
            int x1,y1,x2,y2;
            // Clear canvas
            g.setColor(Color.WHITE);
            x1=y1=0;
            x2=BLOCK_WIDTH*grid[0].length;
            y2=BLOCK_HEIGHT*grid.length;
            int width = BLOCK_WIDTH*grid[0].length;
            int height = BLOCK_HEIGHT*grid.length;
            g.fillRect(x1, y1, width, height);
            // Drow grid horizontal lines
            g.setColor(Color.black);
            for(int j=0;j<grid.length;j++){
                x1=0;y1=BLOCK_HEIGHT*j;
                x2=BLOCK_WIDTH*grid[0].length;y2=y1;
                g.drawLine(x1,y1,x2,y2);
            }
            // Drow grid vertical lines
            for(int i=0;i<grid[0].length;i++){
                x1=BLOCK_WIDTH*i;y1=0;
                x2=x1;y2=BLOCK_HEIGHT*grid.length;
                g.drawLine(x1,y1,x2,y2);
            }
            // Fill Mino color
            for(int j=0;j<grid.length;j++){
                for(int i=0;i<grid[0].length;i++){
                    if(grid[j][i]==0) continue;
                    g.setColor(colors[grid[j][i]]);
                    int x=(BLOCK_WIDTH*i);
                    int y=(BLOCK_HEIGHT*j);
                    g.fillRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
                    //g.drawString(board.getValue(i,j), x, y);
                }
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
