/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramzi
 */
public class TetriminoFactory {
    
    List<Tetrimino> bucket;
    
    public TetriminoFactory(){
        this.bucket = new ArrayList<Tetrimino>();
        bucket.add(new I_Tetrimino());
        bucket.add(new S_Tetrimino());
        bucket.add(new Z_Tetrimino());
        bucket.add(new J_Tetrimino());
        bucket.add(new L_Tetrimino());
        bucket.add(new T_Tetrimino());
        bucket.add(new O_Tetrimino());
    }
    
    public Tetrimino getTetrimino(){
        Tetrimino tetrimino;
        // Random generator
        int type = (int)(Math.random()*((double)bucket.size()));
        return (Tetrimino)bucket.get(type).clone();
    }
}
