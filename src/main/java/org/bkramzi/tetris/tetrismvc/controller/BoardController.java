package org.bkramzi.tetris.tetrismvc.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bkramzi.tetris.tetrismvc.model.Board;
import org.bkramzi.tetris.tetrismvc.view.BoardView;

public class BoardController implements KeyListener,Runnable{

    private boolean active = true;
    private boolean right = false;
    private boolean left = false;
    private boolean down = false;
    private boolean rotate = false;
    private boolean hardrop = false;

    private Thread t;
    private Board board;
    private BoardView boardView;
    private int[] keys={KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN, KeyEvent.VK_UP,KeyEvent.VK_SPACE};
	
    public BoardController(Board board, BoardView boardView){
        this.board = board;
        this.boardView = boardView;
	t = new Thread (this);
        t.start();
    }
	
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	while(active){
            handleKeyBord();
            try{
                Thread.sleep(120);
            }catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    public void start(){
        this.active= true;
        t.start();
    }
    
    public void stop(){
        this.active = false;
        run();
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
                    if(!board.down()) board.next();
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
        Logger.getLogger(BoardController.class.getName()).log(Level.INFO, "KeyPressed time:"+System.currentTimeMillis());
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
