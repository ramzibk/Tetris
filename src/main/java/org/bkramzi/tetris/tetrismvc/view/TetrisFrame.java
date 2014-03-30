/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.view;

import java.awt.CardLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ramzi
 */
public class TetrisFrame extends JFrame{
    
    private boolean pause = true;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new TetrisFrame();
                ((TetrisFrame)frame).intializeComponents();
            }
        });
    }
    public void intializeComponents(){
        menubar.add(m_game);
        m_game.add(mi_new);
        mi_new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((GamePanel)gamePanel).newGame();
                showCard("game");
                mi_pause.setEnabled(true);
            }
        });
        m_game.add(mi_pause);
        mi_pause.setEnabled(false);
        mi_pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!pause){
                    ((GamePanel)gamePanel).pause();
                    pause=true;
                    mi_pause.setLabel("Resume");
                }else {
                    showCard("game");
                    ((GamePanel)gamePanel).resume();
                    pause=false;
                    mi_pause.setLabel("Pause");
                }
            }
        });
        mi_settings.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                showCard("settings");
            }
        });
        m_help.add(mi_settings);
        m_help.add(mi_about);
        mi_about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("about");
            }
        });
        menubar.add(m_help);
        this.setMenuBar(menubar);
        getContentPane().setLayout(new CardLayout());
        getContentPane().add(gamePanel,"game");
        getContentPane().add(settingsPanel,"settings");
        getContentPane().add(aboutPanel,"about");
        gamePanel.setFocusable(true);
        showCard("game");
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void showCard(String card){
        CardLayout layout = (CardLayout)getContentPane().getLayout();
        layout.show(getContentPane(), card);
        if("game".equals(card)){
            mi_pause.setLabel("Pause");
            gamePanel.setFocusable(true);
            pause = false;
        }else{
            ((GamePanel)gamePanel).pause();
            mi_pause.setLabel("Resume");
            gamePanel.setFocusable(true);
            pause = true;
        }
    }

    MenuBar menubar = new MenuBar();
    Menu m_game = new Menu("Game");
    MenuItem mi_pause = new MenuItem("Pause");
    MenuItem mi_new = new MenuItem("New");
    MenuItem mi_settings = new MenuItem("Settings");
    Menu m_help = new Menu("Help");
    MenuItem mi_about = new MenuItem("about");
    ActionListener game_pause_listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ((GamePanel)gamePanel).pause();
            mi_pause.setLabel("Resume");
            pause = true;
        }
    };
    ActionListener game_resume_listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ((GamePanel)gamePanel).resume();
            mi_pause.setLabel("Pause");
            pause = false;
        }
    };    
    JPanel gamePanel = new GamePanel();
    JPanel settingsPanel = new SettingsPanel();
    JPanel aboutPanel = new HelpPanel();
}
