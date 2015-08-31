package com.techcust.RPS;

class Player extends PlayerData{
    public Player(){
        super();
        genName();
    }
    public Player(String givenName){
        super(givenName);
        
    }

    @Override
    public int getGuess() {
        return 0;
    }
    
}