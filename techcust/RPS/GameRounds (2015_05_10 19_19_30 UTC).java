package com.techcust.RPS;

final class GameRounds {
    final private int THREE = 3;
    final private int FIVE = 5;
    final private int SEVEN = 7;
    final private int NINE = 9;
    final private int ELEVEN = 11;
    
    private int numRounds;
    
    public GameRounds(){
        //default game rounds
        //setRounds(FIVE);
    }
    
    public void setRounds(int numRounds){
        this.numRounds = numRounds;
    }
    public int getRounds(){
        return numRounds;
    }
}