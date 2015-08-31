package com.techcust.RPS;

import java.awt.BorderLayout;

class MainPanel extends PanelTemplate{
    public MainPanel(){
        super();
        
    }
    
    private void setupGUI(){
        this.setLayout(new BorderLayout());
        
    }
    
    public static void main(String[] args){
        MainPanel mp = new MainPanel();
    }
}