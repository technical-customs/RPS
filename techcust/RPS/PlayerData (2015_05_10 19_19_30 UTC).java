package com.techcust.RPS;

import java.io.Serializable;
import javax.swing.JOptionPane;

abstract class PlayerData implements Serializable{
    //SCORING
    final private int WINCALC = 1000;
    final private int LOSECALC = 500;
    final private int TIECALC = 50;
    
    //PLAYER CONSTANTS
    private String name = null;
    private int wins = 0;
    private int loses = 0;
    private int ties = 0;
    private int score = 0;
    
    //PLAYER CONTROLS
    private boolean signal = false;
    
    //DISPLAY INFO
    private String nameDisplay = "Name: " + getPlayerName();
    private String scoreDisplay = "Score: " + scoreCount();
    private String winDisplay = "Wins: " + winCount();
    private String loseDisplay = "Loses: " + loseCount();
    private String tieDisplay = "Ties: " + tieCount();
    
    
    public PlayerData(){
        
    }
    public PlayerData(String givenName){
        name = givenName;
        
    }
    private PlayerData(String givenName, int givenWins, int givenLoses, int givenScore){
        name = givenName;
        wins = givenWins;
        loses = givenLoses;
        score = givenScore;
    }
    
    public void playerInfoView(){
        System.out.println("Name: " + name);
        System.out.println("Score: " + score);
        System.out.print("Wins: " + winCount() + " ");
        System.out.print("Loses: " + loseCount() + " ");
        System.out.println("Ties: " + tieCount());
        System.out.println();
    }
    public String nameDisplayer(){
        nameDisplay = "Name: " + getPlayerName();
        return nameDisplay;
    }
    public String scoreDisplayer(){
        scoreDisplay = "Score: " + scoreCount();
        return scoreDisplay;
    }
    public String winDisplayer(){
        winDisplay = "Wins: " + winCount();
        return winDisplay;
    }
    public String loseDisplayer(){
        loseDisplay = "Loses: " + loseCount();
        return loseDisplay;
    }
    public String tieDisplayer(){
        tieDisplay = "Ties: " + tieCount();
        return tieDisplay;
    }
    
    public void turnSignal(boolean signal){
        this.signal = signal;
    }
    public synchronized boolean signalStatus(){
        return signal;
    }
    public synchronized void takeTurn(){
        if(signalStatus()){
            //notify();
            //randomGuess();
            turnSignal(false);
        }else if(!signalStatus()){
            try {
                //wait for turn
                wait();
                
            } catch (Exception ex) {
                
            }
        }
    }
    
    
    public void genName(){
        try{
            if(name == null){
                String typedName = JOptionPane.showInputDialog(null, "Input name", "Name Chooser", JOptionPane.PLAIN_MESSAGE);

                if(typedName != null){
                    name = typedName;
                }
                else{
                    throw new NullPointerException();
                }
            }else{
                System.out.println("You already have an assigned name");
            }
        }catch(NullPointerException ex){
            System.exit(0);
        }
        
    }
    public String getPlayerName(){
        return name;
    }
    
    
    public void increaseWins(){
        wins += 1;
    }
    public int winCount(){
        return wins;
    }
    
    public void increaseLoses(){
        loses += 1;
    }
    public int loseCount(){
        return loses;
    }
    
    public void increaseTies(){
        ties += 1;
    }
    public int tieCount(){
        return ties;
    }
    
    public void calcScore(){
        score = (winCount()*WINCALC) - ( (loseCount()*LOSECALC) + (tieCount()*TIECALC) );
    }
    public int scoreCount(){
        return score;
    }
    abstract public int getGuess();
   
}