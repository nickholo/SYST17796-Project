/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nick
 */

public class Blackjack {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Create an arrayList to store the games players and a scanner to take user input
        ArrayList<Player> players = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        
        //Gets the number of players
        System.out.println("How many players?");
        int numberOfPlayers = input.nextInt();
        input.nextLine();
        
        //Creates an instance of the dealer to manage the game
        Dealer dealer = new Dealer(numberOfPlayers);
        
        //Gets the starting pot amount for the players
        System.out.println("What pot amount should players have?");
        int potAmount = input.nextInt();
        input.nextLine();
        
        //Adds each player to the arrayList after they enter their name
        for (int i = 0; i < numberOfPlayers; i++) {
            
            System.out.println("Enter player name: ");
            String playerName = input.nextLine();
            
            //Checks if the player enters a valid name, then create and adds them when the name is valid
            if (!players.isEmpty() && !Player.checkNameUniqueAndNotNull(players, playerName)) {
                System.out.println("Name already taken. Enter a new name");
                playerName = input.nextLine();
                players.add(new Player(playerName, potAmount));
            }
            
            //Creates and adds a player if they enter a valid name
            else {
                players.add(new Player(playerName, potAmount));
            }
        }
                
        //Creates an instance of the game and starts it
        Game game = new Game("Blackjack", players, dealer);
        game.startGame();
    }
    
}
