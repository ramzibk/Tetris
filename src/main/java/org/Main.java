/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ramzi
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
    }
    
    void load(){
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/bg.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
