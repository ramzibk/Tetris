package org.bkramzi.tetris.tetrismvc.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bkramzi.tetris.tetrismvc.model.Board;
import org.bkramzi.tetris.tetrismvc.model.GameOverException;
import org.bkramzi.tetris.tetrismvc.view.BoardView;

public class BoardController implements KeyListener,Runnable{

    private boolean active = false;
    private boolean right = false;
    private boolean left = false;
    private boolean down = false;
    private boolean rotate = false;
    private boolean hardrop = false;
    
    private Thread tread;
    private Timer timer;
    private Board board;
    private BoardView boardView;
    private int[] keys={KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN, KeyEvent.VK_UP,KeyEvent.VK_ENTER};
    
    public int[] getKeys() {
        return keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }
	
    public BoardController(Board board, BoardView boardView){
        this.board = board;
        this.boardView = boardView;
	tread = new Thread (this);
        tread.start();
        timer = new Timer(board);
        timer.start();
    }
	
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	while(true){
            if(active)
            handleKeyBord();
            try{
                Thread.sleep(100);
            }catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    public void startNew() {
        board.initBoard();
        timer.init();
        resume();
    }
    
    public void resume(){
        active = true;
        timer.setActive(true);
    }
    
    public void pause(){
        this.active = false;
        timer.setActive(false);
    }
    
    public void handleKeyBord() {
        // TODO Auto-generated method stub
        if(board!=null && board.getCurrent() != null){
                if(rotate == true){
                    board.rotate();
		}else if(right == true) {
                    board.right();
		}else if(left == true){
                    board.left();
                }else if(down == true){
                    if(!board.down()) 
                        try {
                        board.next();
                    } catch (GameOverException ex) {
                        Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}if(hardrop  == true){
                    board.hardDrop();
                }
                hardrop = false;
                right=false;
                left=false;
                down=false;
                rotate=false;
            }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key pressed");
        if(e.getKeyCode() == keys[0]){
            left = true;
        }else if(e.getKeyCode() == keys[1]){
            right = true;
        }else if(e.getKeyCode() == keys[2]){
            down = true;
        }else if(e.getKeyCode() == keys[3]){
            rotate = true;
        }else if(e.getKeyCode() == keys[4]){
            hardrop = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub	
    }
    
}
