import java.util.Random;
import java.util.Scanner;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
/**
 * Week 3 - Using API
 * CSCI 112- online
 * Last Modified 2/10/2016 11:00 AM
 * @author Leo Vergnetti
 * */

public class GuessingGame {
	/**
	 * Guessing game 
	 * The program picks a number between 1 and 100
	 * The user guesses a number
	 * the program outputs the user's guess along with
	 * whether the guess is too high or too low
	 * when user guesses correctly, program confirms guess
	 * offers user to play again.
	 * */
	
		public static void main(String[] args){
			//declare vars
			
			boolean userQuit = false;
			boolean guessedAnswer = false;
			int correctGuess;
			int userGuess;
			String message;
			String userInput;
			Voice voice;
			VoiceManager voiceManager = VoiceManager.getInstance();
			voice = voiceManager.getVoice("kevin16");
			voice.allocate();
			//construct random generator
			Random ranGen = new Random();
			Scanner kb = new Scanner(System.in);
			
			
			
			
			//begin loop (userQuit)
			while (!userQuit){
				//pick a number
				correctGuess = ranGen.nextInt(101);
				//begin guessing sequence(guessedAnswer)
				message = ("I'm thinking of a number between 1 and 100.  Try to " +
									"guess what it is.  I will tell you if you are correct, high or low.");
				System.out.println(message);
				voice.speak(message);
				
				while(!guessedAnswer){
					message = "What is your guess?";
					System.out.println(message);
					voice.speak(message);
					userGuess = kb.nextInt();
					
					if (userGuess == correctGuess){
						message = "Correct! " + userGuess + " is the right answer!";
						System.out.println(message);
						voice.speak(message);
						guessedAnswer = true;
					}
					else if (userGuess < correctGuess){
						message = "Sorry, " + userGuess + " is too low. ";
						System.out.println(message);
						voice.speak(message);
					}
					else {
						message = "Sorry, " + userGuess + " is too high!";
						System.out.println(message);
						voice.speak(message);
					}
				}//round ended
				//when correct
				message = "Press 1 to quit or any key to play again";
				System.out.println(message);
				voice.speak(message);
				userInput = kb.next();
				//user wants to quit
			    if (userInput.compareTo("1") == 0){
			    	userQuit = true;
			    	message = "Goodbye!";
			    	System.out.println(message);
			    	voice.speak(message);
			    }
			    //user wants to play again
			    else{
			    	guessedAnswer = false;
			    }
			}//game loop end
			kb.close();//close scanner
		}	
}
