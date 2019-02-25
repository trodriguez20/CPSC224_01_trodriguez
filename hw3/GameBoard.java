package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class GameBoard extends JPanel 
{
    private static JButton button1;
    private static JButton button2;
    private static JButton button3;
    private static JButton button4;
    private static JButton button5;
    private static JButton button6;
    private static JButton button7;
    private static JButton button8;
    private static JButton button9;
    private static int playerTurn = 0;
    
    // constructor function, sets all buttons to be empty
    // and attachs the actionlistener to each
    public GameBoard()
    {
        setPreferredSize(new Dimension(500, 300));      // sets size of button frame
        setLayout(new GridLayout(3,3));                 // sets grid layout for grid of buttons
        button1=new JButton("");
        button2=new JButton("");
        button3=new JButton("");
        button4=new JButton("");
        button5=new JButton("");
        button6=new JButton("");
        button7=new JButton("");
        button8=new JButton("");
        button9=new JButton("");
        
        button1.addActionListener(new ButtonListener());
        button2.addActionListener(new ButtonListener());
        button3.addActionListener(new ButtonListener());
        button4.addActionListener(new ButtonListener());
        button5.addActionListener(new ButtonListener());
        button6.addActionListener(new ButtonListener());
        button7.addActionListener(new ButtonListener());
        button8.addActionListener(new ButtonListener());
        button9.addActionListener(new ButtonListener());
        
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);
        add(button9);   
    }
    
    // this function resets all buttons to empty and sets the turn back to zero
    public static void resetGameBoard()
    {
        GameBoard.button1.setText("");
        GameBoard.button2.setText("");
        GameBoard.button3.setText("");
        GameBoard.button4.setText("");
        GameBoard.button5.setText("");
        GameBoard.button6.setText("");
        GameBoard.button7.setText("");
        GameBoard.button8.setText("");
        GameBoard.button9.setText("");
        playerTurn = 0;
    }
    
    
    private class ButtonListener extends JButton implements ActionListener
    {
        // whenever a button is pressed, the program assigns it a symbol depending on whose turn it is.
        // then, the board is checked for a victory on either side
        // and if there is no victory the program checks for a "cat's eye" (draw)
        public void actionPerformed(ActionEvent e)
	{
            
            if(MenuButtons.gameStatus)
            {
                JButton buttonClicked = (JButton)e.getSource();             // find exact button that was pressed
                buttonClicked.setFont(new Font("Dialog", 1, 60));           // change font size
                if((playerTurn%2==0) && buttonClicked.getText().equals(""))
                { 
                    buttonClicked.setText("X");
                    playerTurn++;
                    TicTacToe.turnLabel.setText(TicTacToe.playerTwo.playerName.getText() + "'s TURN");     // change turn label for next turn
                }
                else if((playerTurn%2==1) && buttonClicked.getText().equals(""))
                {
                    buttonClicked.setText("O");
                    playerTurn++;
                    TicTacToe.turnLabel.setText(TicTacToe.playerOne.playerName.getText() + "'s TURN");     // change turn label for next turn
                }
            
                // check for player 1 victory
                if((button1.getText().equals("X")&&button2.getText().equals("X")&&button3.getText().equals("X")) || 
                   (button4.getText().equals("X")&&button5.getText().equals("X")&&button6.getText().equals("X")) || 
                   (button7.getText().equals("X")&&button8.getText().equals("X")&&button9.getText().equals("X")) ||
                   (button1.getText().equals("X")&&button4.getText().equals("X")&&button7.getText().equals("X")) ||
                   (button2.getText().equals("X")&&button5.getText().equals("X")&&button8.getText().equals("X")) ||
                   (button3.getText().equals("X")&&button6.getText().equals("X")&&button9.getText().equals("X")) ||
                   (button1.getText().equals("X")&&button5.getText().equals("X")&&button9.getText().equals("X")) ||
                   (button7.getText().equals("X")&&button5.getText().equals("X")&&button3.getText().equals("X")))
                {
                    JOptionPane.showMessageDialog(null,TicTacToe.playerOne.playerName.getText() + " WINS!"); // declare player 1 winner
                    TicTacToe.playerOne.winNumber ++;
                    TicTacToe.playerTwo.lossNumber ++;
                    TicTacToe.playerOne.winCount.setText("" + TicTacToe.playerOne.winNumber);           // update and change win/loss labels
                    TicTacToe.playerTwo.lossCount.setText("" + TicTacToe.playerTwo.lossNumber);
                    MenuButtons.gameStatus = false;
                    playerTurn=0;  
                } // check for player 2 victory
                else if((button1.getText().equals("O")&&button2.getText().equals("O")&&button3.getText().equals("O")) || 
                        (button4.getText().equals("O")&&button5.getText().equals("O")&&button6.getText().equals("O")) || 
                        (button7.getText().equals("O")&&button8.getText().equals("O")&&button9.getText().equals("O")) ||
                        (button1.getText().equals("O")&&button4.getText().equals("O")&&button7.getText().equals("O")) ||
                        (button2.getText().equals("O")&&button5.getText().equals("O")&&button8.getText().equals("O")) ||
                        (button3.getText().equals("O")&&button6.getText().equals("O")&&button9.getText().equals("O")) ||
                        (button1.getText().equals("O")&&button5.getText().equals("O")&&button9.getText().equals("O")) ||
                        (button7.getText().equals("O")&&button5.getText().equals("O")&&button3.getText().equals("O")))
                {
                    JOptionPane.showMessageDialog(null,TicTacToe.playerTwo.playerName.getText() + " WINS!"); // declare player 2 winner
                    TicTacToe.playerTwo.winNumber ++;
                    TicTacToe.playerOne.lossNumber ++;
                    TicTacToe.playerTwo.winCount.setText("" + TicTacToe.playerTwo.winNumber);           // update and change win/loss labels
                    TicTacToe.playerOne.lossCount.setText("" + TicTacToe.playerOne.lossNumber);
                    MenuButtons.gameStatus = false;
                    playerTurn=0;
                }
            }
            if(playerTurn>8 && MenuButtons.gameStatus)      // check for a draw
            {
                JOptionPane.showMessageDialog(null,"CATS EYE!");
                playerTurn=0;
            }
        }
    } 
}