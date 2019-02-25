package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuButtons extends JPanel 
{
    private JButton playButton;
    private JButton resetButton;
    private JButton exitButton;
    public static boolean gameStatus = false;
    
    // constructor for MenuButtons
    // sets names for each of the buttons and adds actionlisteners for them
    public MenuButtons()
    {
        setPreferredSize(new Dimension(500, 35));
        setLayout(new FlowLayout());
        
        playButton = new JButton("New Game");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");
        
        add(playButton);
        add(resetButton);
        add(exitButton);
        
        playButton.addActionListener(new gameListener());
        exitButton.addActionListener(new exitListener());
        resetButton.addActionListener(new resetListener());
    }
    
    private class gameListener extends JButton implements ActionListener
    {
        // this is the  action listener for starting a new game
        // it checks if the names of player 1 and 2 are valid (they must have been editted
        // and starts a new game so
        public void actionPerformed(ActionEvent e)
        {
           if(TicTacToe.playerOne.playerName.getText().equals("Player1") || TicTacToe.playerOne.playerName.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null, "Player 1's name is invalid. Enter a new name.","Invalid Name", JOptionPane.ERROR_MESSAGE);
           }
           else if(TicTacToe.playerTwo.playerName.getText().equals("Player2") || TicTacToe.playerTwo.playerName.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null, "Player 2's name is invalid. Enter a new name.","Invalid Name", JOptionPane.ERROR_MESSAGE);
           }
           else
           {
               gameStatus = true;
               GameBoard.resetGameBoard();
           }
        }
        
    }
    
    private class resetListener extends JButton implements ActionListener
    {
        // this is the action listener for resetting the game
        // it checks with the user if they are ok with losing their win/loss counts
        // and then it resets everything to the way it was before the game started
        public void actionPerformed(ActionEvent e)
        {
             int choice=JOptionPane.showConfirmDialog(null, "Would you like to reset all playerStats and game board?", "RESET", JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_OPTION)
                {
                    GameBoard.resetGameBoard();
                    TicTacToe.playerTwo.winNumber = 0;
                    TicTacToe.playerOne.lossNumber = 0;
                    TicTacToe.playerTwo.winCount.setText("" + TicTacToe.playerTwo.winNumber);
                    TicTacToe.playerOne.lossCount.setText("" + TicTacToe.playerOne.lossNumber);
                    TicTacToe.playerOne.winNumber = 0;
                    TicTacToe.playerTwo.lossNumber = 0;
                    TicTacToe.playerOne.winCount.setText("" + TicTacToe.playerOne.winNumber);
                    TicTacToe.playerTwo.lossCount.setText("" + TicTacToe.playerTwo.lossNumber);
                    TicTacToe.playerTwo.playerName.setText("Player2");
                    TicTacToe.playerOne.playerName.setText("Player1");
                    TicTacToe.turnLabel.setText("Welcome to Tic-Tac-Toe!");
                }
        }
    }
    
    private class exitListener extends JButton implements ActionListener
    {
        // this is the action listener for exiting the game
        // it exits the game
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}
