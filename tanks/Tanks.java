/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.PopupMenu;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.max;
import static java.lang.Math.min;
import javax.imageio.*;

/**
 *
 * @author Jared
 */
public class Tanks extends JFrame {
    
    public Tanks()
    {
        JPanel title = new titlePanel();
        //GameBoard game = new GameBoard();
        add(title);
        
        
        setTitle("Tanks");
        setSize(1600, 900);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Tanks();
    }
    
}