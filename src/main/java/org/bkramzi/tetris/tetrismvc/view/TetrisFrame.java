/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.bkramzi.tetris.tetrismvc.controller.BoardController;
import org.bkramzi.tetris.tetrismvc.controller.Timer;
import org.bkramzi.tetris.tetrismvc.model.Board;
import org.bkramzi.tetris.tetrismvc.model.S_Tetrimino;
import org.bkramzi.tetris.tetrismvc.model.Tetrimino;
import org.bkramzi.tetris.tetrismvc.model.TetriminoFactory;

/**
 *
 * @author ramzi
 */
public class TetrisFrame extends JFrame{
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new TetrisFrame();
                ((TetrisFrame)frame).intializeComponents();
            }
        });
    }
    
    public void intializeComponents(){
        getContentPane().add(boardView);
        menubar.add(gameMenu);
        gameMenu.add(newGame);
        menubar.add(settingsMenu);
        menubar.add(helpHenu);
        this.setMenuBar(menubar);
        this.addKeyListener(boardController);
        tetrimino = new TetriminoFactory().getTetrimino();
        board.setCurrent(tetrimino);
        timer.start();
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    Board board = new Board();
    Tetrimino tetrimino;
    BoardView boardView = new BoardView(board);
    BoardController boardController = new BoardController(board,boardView);
    Timer timer = new Timer(board);
    MenuBar menubar = new MenuBar();
    Menu gameMenu = new Menu("Game");
    MenuItem newGame = new MenuItem("New");
    Menu settingsMenu = new Menu("Settings");
    Menu helpHenu = new Menu("Help");
}
