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
    
    private static final String GAME="game";
    private static final String HOWTO="howto";
    private static final String ABOUT="about";
    
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
        mi_howto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                showCard(HOWTO);
            }
        });
        m_help.add(mi_howto);
        m_help.add(mi_about);
        mi_about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(ABOUT);
            }
        });
        menubar.add(m_help);
        this.setMenuBar(menubar);
        getContentPane().setLayout(new CardLayout());
        getContentPane().add(gamePanel,GAME);
        getContentPane().add(controlsPanel,HOWTO);
        getContentPane().add(aboutPanel,ABOUT);
        gamePanel.setFocusable(true);
        showCard(GAME);
        setVisible(true);
        setResizable(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void showCard(String card){
        CardLayout layout = (CardLayout)getContentPane().getLayout();
        layout.show(getContentPane(), card);
        if(GAME.equals(card)){
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
    MenuItem mi_howto = new MenuItem("How TO");
    Menu m_help = new Menu("Help");
    MenuItem mi_about = new MenuItem("About");
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
    JPanel controlsPanel = new ControlsPanel();
    JPanel aboutPanel = new HelpPanel();
}
