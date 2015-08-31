package com.techcust.RPS;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

final class GameEngine{
    //GAME CONSTANTS
    private GameRounds gameRounds;
    private RPSGUI gui;
    
    private ArrayList weapons = new ArrayList();
    
    final private int ROCK = 0;
    final private int PAPER = 1;
    final private int SCISSORS = 2;
    
    //PLAYER DATA
    final private int COMPUTER = 3;
    final private int PERSON = 4;
    
    
    private PlayerData player1 = null;
    private  int p1Decision;
    
    private PlayerData player2 = null;
    private int p2Decision;
    
    public GameEngine(){
        while(true){
            boolean gameLoop = true;
        //init stuff, gui, network
            gui = new RPSGUI();
        
        //GAME LOOP
        do{
            mapLoader();
            
            //PLAYER LOOP
            //if(playerSwitch())
            playersSetup();
            
            //ROUND LOOP
            boolean roundProceed = false;
            do{
                try{
                    setRoundCount();
                    if(gameRounds.getRounds() > 0)
                        roundProceed = true;
                    
                }catch(NumberFormatException ex){
                    roundProceed = false;
                }
                catch(NullPointerException ex){
                    System.exit(0);
                }
            }while(!roundProceed);
            
            log("Playing " + gameRounds.getRounds() + " rounds \n");
            
            //ROUND SWITCH LOOP
            while(true){
                for(int x = 0; x < gameRounds.getRounds(); x++){
                        log("Round - " + (x + 1));
                        underBar();
                        gameRound();
                    }
                break;
            }
            
            underBar();
            decideWinner();
            displayScore(player1);
            displayScore(player2);
            
            int result = JOptionPane.showConfirmDialog(null,
                    "Do you want to play again?",
                    null, JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
                gameLoop = true;
                gameReset();
            }
            if(result == JOptionPane.NO_OPTION){
                gameLoop = false;
                System.exit(0);
            } 
        }while(gameLoop);
    }
        
        
    }
    
    //RESOURCE SETUP
    
    //--WEAPON MAP
    private void mapLoader(){
        weapons.add(ROCK, "ROCK");
        weapons.add(PAPER, "PAPER");
        weapons.add(SCISSORS, "SCISSORS");
    }
    private void mapClear(){
        if(!weapons.isEmpty()){
            weapons.clear();
        }
    }
    private void addWeaponToMap(int ASSIGNEDWEAPONNUMBER, String ASSIGNEDWEAPONNAME){
        if(ASSIGNEDWEAPONNUMBER == ROCK || ASSIGNEDWEAPONNUMBER == PAPER || ASSIGNEDWEAPONNUMBER == SCISSORS){
            System.out.println("ERROR ADDING WEAPON");
        }
        else{
            weapons.add(ASSIGNEDWEAPONNUMBER, ASSIGNEDWEAPONNAME);
        }
    }
    
    //--LOG
    public void log(String string){
        gui.writeToDisplay(string);
    }
    public void underBar(){
        int width = 50;
        for(int x = 0; x < width; x++){
            String underScore = "_";
            gui.addToDisplay(underScore);
        }
        log("");
    }
    
    //--PLAYER
    private boolean playerSwitch(){
        return (JOptionPane.showConfirmDialog(null,"Do You Want To Switch Players?" , null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    private void playersSetup(){
        log("Initializing Players" + "\n");
        //init menu for load or new player
        //player1 init
        if(player1 == null){
            player1Init();
        }else{
            loadPlayer();
        }
        //determine if playing against computer or person
        //generate player 2 based on choice
        //player2 init
        player2Init();
        
    }
    
    //CHOICE GETTERS
    //--OPPONENT
    private Object opponentDecision() throws NullPointerException{
        Object[] opponentChoices = {"COMPUTER", "PERSON"};
            Object opponentChoice = JOptionPane.showInputDialog(null, 
                    "Choose Your Opponent", "Opponent Picker", 
                    JOptionPane.PLAIN_MESSAGE, null, 
                    opponentChoices, opponentChoices[0]);
            
        return opponentChoice;
    }
    //--MAIN DECISION
    public int getDecision() throws NullPointerException{
        String choice = (String) JOptionPane.showInputDialog(null, 
                "ROCK - PAPER - SCISSORS", "WEAPONS", 
                JOptionPane.PLAIN_MESSAGE, null, 
                weapons.toArray(), weapons.get(0));
        return weapons.indexOf(choice);
    }
    //--ROUND
    private void setRoundCount() throws NullPointerException{
        int MINROUND = 1;
        int MAXROUND = 15;
        if(gameRounds == null)
             gameRounds = new GameRounds();
        try{
            String numRounds = JOptionPane.showInputDialog(null, 
                    "Enter Number of Rounds " + "(" + MINROUND + "-" + MAXROUND +")", 
                    "Round Picker", 
                    JOptionPane.PLAIN_MESSAGE);
            
            if(numRounds == null){
                throw new NullPointerException();
            }
            
            try{
                int rounds = Integer.parseInt(numRounds);
                if((rounds >= MINROUND) && (rounds <= MAXROUND)){
                    gameRounds.setRounds(rounds);
                }else{
                    throw new Exception();
                }
                
            }catch(Exception ex){
                log("ERROR - INVALID NUMBER \n");
            }
            
        }catch(HeadlessException ex){}
    }
    
    //PLAYER 1 SETUP
    private void loadPlayer(){
        player1 = new Player("Loaded Player");
        displayScore(player1);
    }
    private void player1Init(){
        //loading player and stats or start new
        player1 = new Player();
        
        displayScore(player1);
        
    }
    private int player1Decision(){
        try{
            log(player1.getPlayerName() + " make a choice");
            p1Decision = (int) getDecision();

            log(player1.getPlayerName() + " Picks: " + weapons.get(p1Decision) + "\n");
        }catch(Exception ex ){
            System.exit(0);
        }
        
        return p1Decision;
    }
    
    //PLAYER 2 SETUP
    private void generateComputer(){
        player2 = (ComputerOpponent) new ComputerOpponent("Computer");
    }
    private void player2Init(){
        //generate computerOpponent or online player
        try{
            if(player2 == null){
            
            String opponent = (String) opponentDecision();

            switch (opponent) {
                case "COMPUTER":
                    generateComputer();
                    break;
                case "PERSON":
                    //load server and check if there are other players waiting for game
                    player2 = new Player("Random Player");
                    break;
                default: 
                    throw new NullPointerException();
            }
            log("Opponent: " + opponent + "\n");
            displayScore(player2);
        }
        }catch(NullPointerException ex){
            System.exit(0);
        }
        
        
    }
    private int player2Decision(){
        if(player2.getClass().equals(ComputerOpponent.class)){
            log(player2.getPlayerName() + "s Turn");
            
            p2Decision = player2.getGuess(); 
            log(player2.getPlayerName() +" Picks: " + weapons.get(p2Decision) + "\n");
        }
        else{
            //wait for player2 move from server
        }
        return p2Decision;
    }
    
    //PLAYER SCORE UPDATE
    private void player1Winner(){
        log(player1.getPlayerName() + " wins");
        log("");
        
        player1.increaseWins();
        player2.increaseLoses();
        generateScores();
    }
    private void player2Winner(){
        log(player2.getPlayerName() + " wins");
        log("");
        
        player2.increaseWins();
        player1.increaseLoses();
        generateScores();
    }
    private void playerTie(){
        log("Players tie");
        log("");
        
        player1.increaseTies();
        player2.increaseTies();
        generateScores();
    }
    
    private void generateScores(){
        player1.calcScore();
        player2.calcScore();
        
        displayScore(player1);
        displayScore(player2);
    }
    private void displayScore(PlayerData player){
        log(player.nameDisplayer());
        log(player.scoreDisplayer());
        log(player.winDisplayer() + " " 
            + player.loseDisplayer() + " "
            + player.tieDisplayer());
        log("");
        
    }
    
    //DECISION TREES
    private void winLogic(){
        if(p1Decision == p2Decision){
            playerTie();
        }
        if(p1Decision == ROCK){
            if(p2Decision == PAPER){
                player2Winner();
            }else if(p2Decision == SCISSORS){
                player1Winner();
            }
        }else if(p1Decision == PAPER){
            if(p2Decision == ROCK){
                player1Winner();
            }else if(p2Decision == SCISSORS){
                player2Winner();
            }
        }else if(p1Decision == SCISSORS){
            if(p2Decision == ROCK){
                player2Winner();
            }else if(p2Decision == PAPER){
                player1Winner();
            }
        }
    }
    private void gameRound(){
        
        player1.turnSignal(true);
        if(player1.signalStatus()){
            log(player1.getPlayerName() + "s turn");
            player1Decision();
            player1.turnSignal(false);
        }
        player2.turnSignal(true);
        if(player2.signalStatus()){
            player2Decision();
            player2.turnSignal(false);
        }
        
        if(!player1.signalStatus() && !player2.signalStatus()){
            winLogic();
        }
    }
    private void decideWinner(){
        if(player1.winCount() > player2.winCount()){
            log(player1.getPlayerName() + " WINS" + "\n");
        }else if(player1.winCount() < player2.winCount()){
            log(player2.getPlayerName() + " WINS" + "\n");
        }else if(player1.winCount() == player2.winCount()){
            if(player1.tieCount() > player2.tieCount()){
                log(player1.getPlayerName() + " WINS" + "\n");
            }else if(player1.tieCount() < player2.tieCount()){
                log(player2.getPlayerName() +" WINS" + "\n");
            }
            else{
                log("PLAYERS TIE" + "\n");
            }
        }
    }
    
    //ENGINE OPTIONS
    private void gameReset(){
        gui.clearDisplay();
        gameRounds = null;
        mapClear();
        player1 = null;
        player2 = null;
    }
    
}