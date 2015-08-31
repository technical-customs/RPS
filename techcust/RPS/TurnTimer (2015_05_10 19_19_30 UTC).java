package com.techcust.RPS;

class TurnTimer implements Runnable{
    private int turnTime;
    final private static int DEFAULTTIME = 4000;
    
    public TurnTimer(){
        
    }
    
    private void setTime(int time){
        turnTime = time;
        
    }
    private int getTime(){
        return turnTime;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(getTime());
        }catch(Exception ex){}
    }
    
}