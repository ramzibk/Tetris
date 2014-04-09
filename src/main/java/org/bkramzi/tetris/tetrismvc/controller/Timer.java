/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.bkramzi.tetris.tetrismvc.model.Board;
import org.bkramzi.tetris.tetrismvc.model.GameOverException;

/**
 *
 * @author ramzi
 */
public class Timer extends Thread implements ChangeListener{
    
    private boolean active = false;
    private Board board;
    private int speed = 1000;
    
    public void init(){
        speed=1000;
    }

    public Timer() {
    }
    
    public Timer(Board board) {
        this.board = board;
        board.addChangeListener(this);
    } 

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
        board.addChangeListener(this);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void run(){
        while(true){
            if (active) {
                
                try {
                    board.next();
                } catch (GameOverException ex) {
                    Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void stateChanged(ChangeEvent e) {
        switch (board.getLevel()) {
            case 2:
                speed = 900;
                break;
            case 3:
                speed = 800;
                break;
            case 4:
                speed = 700;
                break;
            case 5:
                speed = 600;
                break;
            case 6:
                speed = 550;
                break;
            case 7:
                speed = 500;
                break;
            case 8:
                speed = 450;
                break;
            case 9:
                speed = 400;
                break;
            case 10:
                speed = 350;
                break;
            default: speed = 1000;
                break;
        }
    }
}
