 package com.techcust.RPS;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public final class RPSGUI{
    //COMMAND LINE VARIABLES
    private JFrame textMainFrame;
    private JPanel textMainPane;
    private JTextArea textArea;
    
    //GUI VARIABLES
    private JFrame mainFrame;
    private JPanel mainPane;
    
    
    private JLabel nameDisplay;
    private JLabel scoreDisplay;
    private JLabel winDisplay;
    private JLabel loseDisplay;
    private JLabel tieDisplay;
    
    private JButton gameButton;
    private JTextField nameInput;
    private JButton nameConfirm;
    private JTextField roundInput;
    private JButton roundConfirm;
    
    private boolean textGui = true, graphicGui = false;
    public RPSGUI(){
        if(textGui){
            setupTextGUI();
        }else if(graphicGui){
            setupGUI();
        }
    }
    
    
    //COMMAND LINE TEXT AREA DISPLAY
    public void setupTextGUI(){
        textMainFrame = new JFrame();
        textMainFrame.setTitle("ROCK - PAPER - SCISSORS");
        textMainFrame.setSize(500,500);
        textMainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        textMainPane = new JPanel(new BorderLayout());
        textArea = new JTextArea(300,300);
        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setEditable(false);
        
        textMainPane.add(scroll);
        textMainFrame.add(textMainPane);
        
        textMainFrame.setVisible(true);
    }
    public void setTitleOfTextGUI(String name){
        textMainFrame.setTitle(name);
    }
    public void clearDisplay(){
        textArea.setText(null);
    }
    public void writeToDisplay(String string){
        try{
            DefaultCaret caret = (DefaultCaret) textArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            textArea.append(string + "\n");
        }catch(NullPointerException ex){
            System.exit(0);
        }
    }
    public void addToDisplay(String string){
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.append(string);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
    }
    public int screenWidth(){
        return textArea.getWidth();
    }
    
    //GUI DISPLAY
    private void setupGUI(){
        mainFrame = new JFrame();
        mainFrame.setTitle("ROCK - PAPER - SCISSORS");
        mainFrame.setSize(500,500);
        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        
        
        
        mainPane = new JPanel(new BorderLayout());
        mainFrame.getContentPane().add(mainPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    
    //LABEL SETTERS
    public void labelName(String name){
        nameDisplay.setText(name);
    }
    public void labelScore(int score){
        scoreDisplay.setText(Integer.toString(score));
    }
    public void labelWin(int win){
        winDisplay.setText(Integer.toString(win));
    }
    public void labelLose(int lose){
        loseDisplay.setText(Integer.toString(lose));
    }
    public void labelTie(int tie){
        tieDisplay.setText(Integer.toString(tie));
    }
}