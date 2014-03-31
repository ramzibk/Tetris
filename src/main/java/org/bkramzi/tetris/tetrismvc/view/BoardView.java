/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private static Color BG_COLOR = Color.white;
    private static Color GRID_COLOR= new Color(50, 50, 50);
    private static Color[] colors= {new Color(255,0,0), // red
                                    new Color(255,255,0), //green
                                    new Color(0,0,255), //blue
                                    new Color(0,200,255), //light blue
                                    new Color(128,0,128), //purple
                                    new Color(255,255,0), //yellow
                                    new Color(255,0,255), //magenta
                                    new Color(255,128,0), //orange
                                    };
    Image img;
    
    public BoardView() {
        super();
        init();
    }
    public void init(){
        try {       
            img = ImageIO.read(this.getClass().getResource("/spacebg.png"));
        } catch (IOException ex) {
            Logger.getLogger(BoardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BLOCK_WIDTH*Board.getXBlocks(),BLOCK_HEIGHT*Board.getYBlocks());
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
        if(board!=null){
            board.addChangeListener(this);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if(board!=null){
            int[][] grid = board.getGrid();
            int x1,y1,x2,y2;
            // Clear canvas
            g.setColor(BG_COLOR);
            x1 = y1 = 0;
            x2 = BLOCK_WIDTH * grid[0].length;
            y2 = BLOCK_HEIGHT * grid.length;
            int width = BLOCK_WIDTH * grid[0].length;
            int height = BLOCK_HEIGHT * grid.length;
            g.fillRect(x1, y1, width, height);
            g.drawImage(img, 0, 0, this);
            // Draw GAME OVER
            if(board.isGameover()){
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED,Font.BOLD, 34));
                g.drawString("GAME OVER", 20,height/2);
                return;
            }
            
            // Fill Mino color
            for(int j=0;j<grid.length;j++){
                for(int i=0;i<grid[0].length;i++){
                    if(grid[j][i]==0) continue;
                    g.setColor(colors[grid[j][i]]);
                    int x=(BLOCK_WIDTH*i);
                    int y=(BLOCK_HEIGHT*j);
                    g.fillRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
                }
            }
        }
    }
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
