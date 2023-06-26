import java.util.concurrent.ThreadLocalRandom;
import java.lang.StringBuilder;

public class Character {
	private int strengthModifier;
	private int dexterityModifier;
	private int defaultInitiative = 10;
	private int initiative;
	private int defaultHealth = 10;
	private int maxHealth;
	private int tempHealth;
	private int numDice;
	private int numSides;
	private int minDamage;
	private int maxDamage;

	public Character(int numDice, int numSides) {
		strengthModifier = 0;
		dexterityModifier = 0;
		this.numDice = numDice;
		this.numSides = numSides;
		minDamage = numDice + strengthModifier;
		maxDamage = numSides + strengthModifier;

		initiative = defaultInitiative + dexterityModifier;

		maxHealth = defaultHealth + strengthModifier;
		tempHealth = maxHealth;
	}

	public Character(int strengthModifier, int dexterityModifier, int numDice, int numSides) {
		this.strengthModifier = strengthModifier;
		this.dexterityModifier = dexterityModifier;
		this.numDice = numDice;
		this.numSides = numSides;
		minDamage = numDice + strengthModifier;
		maxDamage = (numDice*numSides) + strengthModifier;

		initiative = defaultInitiative + dexterityModifier;

		maxHealth = defaultHealth + strengthModifier;
		tempHealth = maxHealth;
	}

	public String calculateRTK(int lowerBound, int upperBound) {
		int range = upperBound - lowerBound;
		StringBuilder rtkString = new StringBuilder(range*13-2);
		double dpr;
		int rtk;
		String temp;
		double oneHitChance;
	
		for(int i = lowerBound; i <= upperBound; i++) {
			dpr = this.calculateDPR();
			rtk = (int)Math.ceil(i/dpr);
			oneHitChance = (initiative/20.0)*((maxDamage+1-i)/(double)(maxDamage+1-minDamage))*100;
			oneHitChance = oneHitChance > 0 ? oneHitChance : 0;
			temp = String.format("%2d - %2d/%2.0f%%", i, rtk, oneHitChance);
			if(i == upperBound)
				rtkString.append(temp);
			else
				rtkString.append(temp + ", ");
		}

		return rtkString.toString();
	}	

	public double calculateDPR() {
		return ((minDamage + maxDamage)/2.0)*(initiative/20.0);
	}
	/*	
	public void attack(Character target) {
		int hitDieResult = rollDie(20);
		int hitDieResultWithModifier = hitDieResult + dexterityModifier;
		int damageDieResult = rollDie(10);
		int damageDieResultWithModifier = damageDieResult + strengthModifier;

		//if(hitDieResultWithModifier > 10) {
		//	target.takeDamage(damageDieResultWithModifier)
		//}
	}
	
	private static int rollDie(int numSides) {
		int min = 1;
	
		int rollResult = ThreadLocalRandom.current().nextInt(min, numSides + 1);
		
		return rollResult;
	}
	*/
	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public String getDamageDice() {
		return String.format("%dd%d", numDice, numSides);
	}

	public int getDex() {
		return dexterityModifier;
	}

	public int getStr() {
		return strengthModifier;
	}

	public int getInitiative() {
		return initiative;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getTempHealth() {
		return tempHealth;
	}
}
