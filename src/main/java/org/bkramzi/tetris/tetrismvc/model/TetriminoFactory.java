/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ramzi
 */
public class TetriminoFactory {
    
    Map<Integer,Tetrimino> bucket;

    public TetriminoFactory() {
        this.bucket = new HashMap<Integer, Tetrimino>();
    }
    
    private void TetriminoFactory(){
        bucket.put(1, new I_Tetrimino());
        bucket.put(1, new S_Tetrimino());
    }
    
    public Tetrimino getTetrimino(){
        Tetrimino tetrimino;
        // Random generator
        return bucket.get(1);
    }
}
