package com.techcust.RPS;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class MainFrame extends JFrame{
    public static int boardWidth = 400;
    public static int boardHeight = 600;
    
    public MainFrame(){
        super();
        
        SwingUtilities.invokeLater(() -> {
            setupGUI();
        });
    }
    
    private void setupGUI(){
        this.setTitle("RPS");
        this.setSize(400,600);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        MainFrame mf = new MainFrame();
    }
}