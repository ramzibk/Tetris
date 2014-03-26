/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bkramzi.tetris.tetrismvc.model.Board;

/**
 *
 * @author ramzi
 */
public class Timer extends Thread{
    
    private boolean active = false;
    private Board board;

    public Timer(Board board) {
        this.board = board;
    }
    
    public void run(){
        while(true){
            if(active)
                board.next();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setActive(boolean active){
        this.active = active;
    }

}
