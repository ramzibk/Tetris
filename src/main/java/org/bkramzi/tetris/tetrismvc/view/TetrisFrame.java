/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.bkramzi.tetris.tetrismvc.controller.BoardController;
import org.bkramzi.tetris.tetrismvc.model.Board;

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
        menubar.add(gameMenu);
        gameMenu.add(newGame);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardController.start();
            }
        });
        menubar.add(settingsMenu);
        menubar.add(helpHenu);
        this.setMenuBar(menubar);
        board = new Board();
        board.initBoard();
        boardView = new BoardView(board);
        boardController = new BoardController(board,boardView);
        this.addKeyListener(boardController);
        getContentPane().add(boardView);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    Board board;
    BoardView boardView;
    BoardController boardController;
    MenuBar menubar = new MenuBar();
    Menu gameMenu = new Menu("Game");
    MenuItem newGame = new MenuItem("New");
    Menu settingsMenu = new Menu("Settings");
    Menu helpHenu = new Menu("Help");
}
