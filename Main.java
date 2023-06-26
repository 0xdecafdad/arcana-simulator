import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Select an option:");
		System.out.println("\t1 - Enter character");
		System.out.println("\t2 - Proceed with defaults");
		System.out.print("> ");
		int userInput = scan.nextInt();
		
		int str;
		int dex;
		int numDice;
		int numSides;
		int lowerHPBound = 10;
		int upperHPBound = 12;
		ArrayList<Character> userCharacters = new ArrayList<Character>();
		while(userInput == 1) {
			System.out.print("Enter strength modifier > ");
			str = scan.nextInt();
			System.out.print("Enter dexterity modifier > ");
			dex = scan.nextInt();
			System.out.print("Enter number of damage dice > ");
			numDice = scan.nextInt();
			System.out.print("Enter number of sides of damage dice > ");
			numSides = scan.nextInt();
			userCharacters.add(new Character(str, dex, numDice, numSides));
			System.out.print("Would you like to enter another character? 1 = YES, 2 = NO > ");
			userInput = scan.nextInt();
			if(userInput == 2) {
				System.out.print("Define target HP range (lower bound) > ");
				lowerHPBound = scan.nextInt();
				System.out.print("Define target HP range (upper bound) > ");
				upperHPBound = scan.nextInt();
			}
		}
		
		ArrayList<Character> defaultCharacters = new ArrayList<Character>();
		Character defGuy = new Character(1, 10);
		defaultCharacters.add(defGuy);
		Character dexGuy = new Character(0, 1, 1, 10);
		defaultCharacters.add(dexGuy);
		Character strGuy = new Character(1, 0, 1, 10);
		defaultCharacters.add(strGuy);

		ArrayList<Character> characterSetToDisplay;
		if(userCharacters.size() == 0)
			characterSetToDisplay = defaultCharacters;
		else
			characterSetToDisplay = userCharacters;

		System.out.println("");
		System.out.printf("| Strength | Dexterity | Damage Dice | DPR  | Target HP - Avg. RTK/Chance to OHK \n");
		for(int i=0; i<characterSetToDisplay.size(); i++) {
			Character currChar = characterSetToDisplay.get(i);
			double dpr = currChar.calculateDPR();
			System.out.printf("| %-8d | %-9d | %-11s | %.2f | %s \n", 
				currChar.getStr(),
				currChar.getDex(),
				currChar.getDamageDice(),
				dpr,
				currChar.calculateRTK(lowerHPBound, upperHPBound));
		}
	}
}
