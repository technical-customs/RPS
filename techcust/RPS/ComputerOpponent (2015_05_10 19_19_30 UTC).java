package com.techcust.RPS;

import java.util.Random;

public class ComputerOpponent extends PlayerData{
    private boolean signal = false;
    
    public ComputerOpponent(){
        super();
    }
    public ComputerOpponent(String givenName){
        super(givenName);
        
    }
    
    /**
     *Take turn signals computer and waits for response
     */
    @Override
    public synchronized void takeTurn(){
        if(signalStatus()){
            //notify();
            //randomGuess();
            //turnSignal(false);
        }else if(!signalStatus()){
            try {
                //wait for turn
                
            } catch (Exception ex) {
                
            }
        }
    }
    
    
    private int randomGuess(){
        Random rand = new Random();
        int randVal = (int) rand.nextInt(3);
        return randVal;
    }
    @Override
    public int getGuess(){
        return randomGuess();
    }
}