/*
 * Homework #2
 * Due Date: 2/11/19
 * Names: Jared Elliott & Timothy Rodriguez
 */
package hangman;

import javax.swing.JOptionPane;
import java.lang.String;
import java.util.concurrent.ThreadLocalRandom;

public class Hangman 
{

    public static void main(String[] args) 
    {
        String word=menu();
        boolean play = true;
        System.out.println(word);
        while(play){
            play = turn(word);
            if(play){
                word = menu();
            }
        }
        System.exit(0);
    }
    
    // This function presents the user with a menu, allowing them to choose between a random word 
    // or a word of their own choosing to play with, and it returns that word
    public static String menu()
    {
        String Choice;
        Choice=JOptionPane.showInputDialog(null, "Enter 'r' for random chosen word " + "Enter 't' to enter your own " + "Enter 'e' to exit");
        System.out.println("userChoice: " + Choice);
        if(Choice.equals("r"))
        {
            String[] words={"apple", "bicyle", "chimp", "gundam", "eagle", "hippopotomonstrosesquippedaliophobia", "plane"};
            int rand=ThreadLocalRandom.current().nextInt(0, 7);
            return words[rand];
        }
        else if(Choice.equals("t"))
        {
            return JOptionPane.showInputDialog("Enter your word");
        }
        else if(Choice.equals("e"))
        {
                System.exit(0);
        }
        
        return menu();
        
    }
    
    // This function is the main body of the game, executing the turn loop and calling other functions to 
    // update the line of blanks and show the hangman, and it returns true or false depending on
    // if the user wishes to play again
    public static boolean turn(String word){
        String usedLetters = " ";
        String allowedLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String hiddenWord = "";
        String letter;
        String endMessage = "";
        boolean gameOver = false;
        int strike = 0;
        
        for(int i = 0; i < word.length(); i++){
            hiddenWord += "-";
        }
        
        while(!gameOver){
            
            letter = JOptionPane.showInputDialog(null, "    HANGMAN     " + "\n\n" + hiddenWord + "\n\n" + strikeOutput(strike));
            
            if((!usedLetters.contains(letter)) && allowedLetters.contains(letter)){// checks if letter has been guesssed already 
                
                if(word.contains(letter)){    // checks if guessed letter is in the word
                    hiddenWord = updateHidden(letter, word, hiddenWord);
                    if(hiddenWord.equals(word)){
                        gameOver = true;
                        endMessage += ("Congratulations! You have guessed the word " + word);
                    }
                } else {                      // guessed letter is not in the word
                    strike++;
                    if(strike > 5){
                        gameOver = true;
                        endMessage += (strikeOutput(strike) + "\nYou have ran out of guesses\nThe word was " + word);
                    }
                }
                
                usedLetters += letter;
                
            } else {                          // letter has been guessed already
                JOptionPane.showMessageDialog(null, "This is an invalid letter.\nYou may have already guessed it\nor you may have input a number/symbol");
            }
        }
        endMessage += "\nWould you like to play again? (y/n): ";
        letter = JOptionPane.showInputDialog(null, endMessage);
        
        if(letter.equals("y")){
            return true;
        } else {
            return false;
        }
        
    }
    
    // This function updates the line of blanks when a correct letter is guessed,
    // changing the blank to the correct letter
    public static String updateHidden(String letter, String word, String hiddenWord){
        String newWord = "";
        int base = 0;
        int letterCount = 0;
        int index = word.indexOf(letter);
        while(index >= 0){
            for(int i = base + letterCount; i < index; i++){
                newWord += hiddenWord.charAt(i);
            }
            newWord += letter;
            base = index;
            index = word.indexOf(letter, index + 1);
            letterCount++;
        }
        
        for(int k = 1; k < (word.length() - base); k++){
            newWord += hiddenWord.charAt(base + k);
        }
        return newWord;
    }
    
    // This function prints the hangman drawing, which is more complete when the user has more strikes
    public static String strikeOutput(int strike){
        String noose="";
        if(strike==0)
        {
            noose+="|---|\n|\n|\n|\n|_____\n";
        }
        else if(strike==1)
        {
            noose+="|---|\n|   O\n|\n|\n|_____\n";
        }
        else if(strike==2)
        {
             noose+="|---|\n|   O\n|   |\n|\n|_____\n";
        }
        else if(strike==3)
        {
             noose+="|---|\n|   O\n|  -|\n|\n|_____\n";
        }
        else if(strike==4)
        {
             noose+="|---|\n|   O\n|  -|-\n|\n|_____\n";
        }
        else if(strike==5)
        {
             noose+="|---|\n|   O\n|  -|-\n|  / \n|_____\n";
        }
        else
        {
             noose+="|---|\n|   O\n|   -|-\n|   / \\ \n|_____\n";
        }
        return noose;
    }
}
