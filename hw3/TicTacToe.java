/*
 Homework 3
 Due Date: 2/24/19
Names: Timothy Rodriguez & Jared Elliott
*/
package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class TicTacToe extends JFrame {

    public static PlayerStats playerOne;
    public static PlayerStats playerTwo;
    
    private GameBoard gameBoard;
    
    private MenuButtons buttonPanel;
    
    public static JLabel turnLabel;

    /**
       Constructor
    */
    public TicTacToe(){
        
        setTitle("Tic Tac Toe");        // set title
        setSize(500, 500);              // set size of window to be 500 pixels
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout());    // flow layout is used as window is to remain at 500x500
        
        playerOne = new PlayerStats(1); // set up JPanels, one for each player
        playerTwo = new PlayerStats(2);
        add(playerOne);
        add(playerTwo);
        
        gameBoard = new GameBoard();    // set up grid of buttons for actual tic tac toe game
        add(gameBoard);
        
        buttonPanel = new MenuButtons();// set up three menu buttons underneath grid
        add(buttonPanel);
        
        turnLabel = new JLabel("Welcome to Tic-Tac-Toe!");  // make turn label at bottom of everything
        turnLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        turnLabel.setPreferredSize(new Dimension(470, 15));
        add(turnLabel);
        
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TicTacToe();
    }
    
}