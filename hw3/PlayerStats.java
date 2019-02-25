package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerStats extends JPanel {
    public JTextField playerName;
    public int winNumber = 0;
    public int lossNumber = 0;
    public JLabel winCount;
    public JLabel lossCount;
    
    // constructor for playerStats
    // sets grid layout for panel, so labels are evenly spaced
    // sets titled border and text field for player
    // and sets labels for the rest of the panel
    public PlayerStats(int playerNumber){
        setPreferredSize(new Dimension(200, 80));
        setLayout(new GridLayout(3, 2));
        setBorder(BorderFactory.createTitledBorder("Player " + playerNumber));
        playerName = new JTextField("Player" + playerNumber, 8);
        winCount = new JLabel("" + winNumber);
        lossCount = new JLabel("" + lossNumber);
        
        add(new JLabel("Name:           "));
        add(playerName);
        add(new JLabel("Wins:           "));
        add(winCount);
        add(new JLabel("Losses:         "));
        add(lossCount);
    }

}