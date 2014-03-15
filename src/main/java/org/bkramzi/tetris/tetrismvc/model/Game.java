/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.List;

/**
 *
 * @author ramzi
 */
public class Game {
    private static int START=1;
    private static int PAUSE=2;
    private static int END=3;
    private int state = START;
    private int players=0;
    
    private List<Board> boards;
    
}
