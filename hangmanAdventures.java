package hangman;

import java.util.*;

public class hangmanAdventures {

	// global variables make it easier as most variables are used in multiple
	// methods
	public static int hearts = 3;

	// items
	public static int healthPot = 3;
	public static int greaterHealthPot = 3;
	public static int bread = 3;
	public static int statResetScroll = 3;
	public static int luckyGuess = 3;

	// affects progress of game
	public static int coin = 0;
	public static int encounterNumber = 0;
	public static int loveEncounter = 0;
	public static int coinsNeeded = 10;
	public static int skillPoints = 25;

	// skill points
	public static int strength;
	public static int health;
	public static int luck;
	public static int charm;

	// for hangman, global because it is cleared outside of the Hangman method
	public static char letter;
	public static String word;

	// boolean can bypass words
	public static boolean WordKeeperNotCharmed = true;

	// keep track of charmed or guessed WordKeepers, this will alter ending
	public static int charmedKeepers;
	public static int guessedKeepers;
	public static int day = 1;
	public static int lovePoints = 0;

	public static Scanner sn = new Scanner(System.in);

	// StringBuilders
	public static StringBuilder sb = new StringBuilder(); // Hangman hiddenWord
	public static StringBuilder sbHearts = new StringBuilder(); // hearts
	public static StringBuilder incorrectGuesses = new StringBuilder();
	// keeps track of incorrect guesses

	public static Random rando = new Random();

	public static String name = "";

	// usually used when asking player a yes or no questions, reused in multiple
	// sections of code
	public static String goodName;
	public static boolean adventureStart = true;

	public static void main(String[] args) {

		// do while loops game while player agrees to play again
		do {
			adventureStart = true;
			day = 1;
			coin = 0;
			encounterNumber = 0;
			loveEncounter = 0;
			guessedKeepers = 0;
			charmedKeepers = 0;
			lovePoints = 0;
			skillPoints = 25;
			// resetting variables back to default (clears game history
			// essentially)

			System.out.println("Welcome to Hangman Adventures: A Love Story in Beta~");

			while (adventureStart == true) {
				do {
					System.out.println("Enter the name of your character:");
					name = sn.nextLine();
				} while (name.equals(""));

				do {
					// this do while loop won't take anything except 'y' or 'n'
					// as an answer, it is used mans times throughout the code
					// for yes and no questions
					System.out.println("\nYoung hero, are you happy with your name? (y/n)");
					System.out.println(name);
					goodName = sn.nextLine();

				} while (goodName.equals("") || !(goodName.equals("y")) && (!(goodName.equals("n"))));

				if (goodName.equals("y")) {
					System.out.println("THEN ONWARDS TO LOVE AND POWE-- I MEAN LITERATURE");
					adventureStart = false;
					// boolean is easiest way to keep track of 'yes' or 'no' as
					// they both only have 2 values
				}

			}

			// Backstory
			System.out.println("WordKeepers have been trapped under the power of the 'Great One' for centuries.");
			dots();
			System.out.println(
					"The 'Great One' has sealed off the WordKeepers true personalities, making them mindless drones wanting nothing more than to seal their secrets up.");
			dots();
			System.out.println(
					"The 'Great One' gave every wordKeeper a word relating to their past, just to remind them that they can never return to the good days.");
			dots();
			System.out.println(
					"Now, it is up to you, to release the spell and free these WordKeepers. Who knows, maybe you'll even find love on the way.");
			dots();
			System.out.println(
					"You must not waste anymore time, go and free these Keepers, and defeat the boss. You have just 30 DAYS. May luck be by your side.");
			dots();
			// dots build suspense

			assignSkillPoints();

			// time user
			long startTime = System.nanoTime();
			// being timing

			while (coinsNeeded != 0 && hearts != 0 && day <= 30) {
				// conditions to CONTINUE playing
				// when it breaks out of while, win/lose check occurs
				encounterNumber++;

				if (encounterNumber % 4 == 0.00) { // every 4 encounters,
													// player
													// must
													// sleep

					System.out.println("It's been a long day, you must rest");
					day++;
					skillPoints++;
					sn.nextLine();
					System.out.println("1 skill point added");
					sn.nextLine();

					System.out.println("1. Health");
					System.out.println("2. Strength");
					System.out.println("3. Luck");
					System.out.println("4. Charm");
					System.out.println("Type the number of the skill you want to improve on");

					// Names below are due to variables first being used in
					// backpack
					// vars reused to avoid too many vars, it works because they
					// serve the same purpose
					char choiceBackpack;
					int choiceNumberBackpack;
					do {
						System.out.println("\nType a number between 1 to 4 please");
						choiceBackpack = sn.next().charAt(0);
					} while (!(choiceBackpack <= 52 && choiceBackpack >= 49));
					// above is 1 to 4 in ASCI
					choiceNumberBackpack = Character.getNumericValue(choiceBackpack);
					// above ensures the input is an int using ASCI values, this
					// method is repeated for most cases where one int is
					// required

					// switch case to assign point
					switch (choiceNumberBackpack) {
					case 1:
						addHeart();
						System.out.println("Health has increased by 1");
						System.out.println("Hearts has increased by 1");
						break;
					case 2:
						strength++;
						System.out.println("Strength has increased by 1");
						break;
					case 3:
						luck++;
						System.out.println("Luck has increased by 1");
						break;
					case 4:
						charm++;
						System.out.println("Charm has increased by 1");
						break;
					}

				}

				// below prints out the Hangman category for the day, categories
				// clearer later on in code
				System.out.println("_______________________________________________");
				if (day <= 5) {
					System.out.println("\nDAY " + day + ": Animals");
				} else if (day <= 10) {
					System.out.println("\nDAY " + day + ": Instruments");
				} else if (day <= 15) {
					System.out.println("\nDAY " + day + ": Countries");
				} else if (day <= 20) {
					System.out.println("\nDAY " + day + ": TV Shows");
				} else if (day <= 25) {
					System.out.println("\nDAY " + day + ": Singers");
				} else if (day <= 30) {
					System.out.println("\nDAY " + day + ": Capital Cities");
				}

				if (encounterNumber % 5 == 0.00) {
					// every 5 encounters, player meets 'love interest'
					// (villager)
					int musicBox = 0;
					loveEncounter++;

					if (loveEncounter == 1) {
						// first love encounter is just given, the rest will
						// only occur if the right stats are high enough
						System.out.println("A cute shy villager approaches you");
						System.out.println(
								"They mumble words to you and hurridley shove bread into your hands before running off");
						sn.nextLine();
						bread++;
						System.out.println("Bread has been added to your inventory! It heals 1 heart upon use.");
						sn.nextLine();
						charm++;
						System.out.println("Charm has increased by 1");
						sn.nextLine();

					} else if (loveEncounter == 2 && charm > 10 && strength > 7) {
						// strength is mainly a check to encounter the lover
						// essentially, having balanced statistics means a love
						// encounter, which leads to free items
						lovePoints++;
						System.out.println(
								"The same cute villager from the other day stars awestruck at you from behind a tree.");
						sn.nextLine();
						System.out.println("You flex to see if you'll garner a reaction");
						sn.nextLine();
						System.out.println("They marvel at you but notice you looking at them and run away");
						sn.nextLine();
						System.out.println(
								"You try to chase after and find a small music box. You keep it in hopes of returning it");
						musicBox++;
						sn.nextLine();
					}

					else if (loveEncounter == 3 && charm > 12 && strength > 8) {
						lovePoints++;
						System.out.println("You see the villager approach you, looking very shy");
						System.out.println("Villager: " + name + "... ");
						sn.nextLine();
						System.out.println(
								"Villager: I've seen your work, and I reall appreciate what youve been doing *blushes furiously*");
						if (musicBox == 1) {
							lovePoints++;
							sn.nextLine();
							System.out.println(
									name + ": Thanks ;). O that reminds me, I saw you drop someting the other day");
							sn.nextLine();
							System.out.println(
									"Villager: OH! YOU SAW THAT?! ///o///o///. How embarrassing, let me give you something in return");
							sn.nextLine();
							luckyGuess += 3;
							System.out.println(
									"Villager hands you 3 'Lucky Guesses'. They run off before you can thank them");
							sn.nextLine();
						}

						System.out.println(name + ": I really appreciate that");
						dots();
						System.out
								.println("Villager: Well anyways, BYE *tosses a 'Lucky Guess' at you and scurries off");
						luckyGuess++;
						sn.nextLine();
						System.out.println("Hmmm. Strange person.");
						sn.nextLine();
					}

					else if (loveEncounter == 4 && charm > 13 && strength > 9) {
						lovePoints++;
						System.out.println("You hear the");
						if (musicBox == 1) {
							// if you have had the music box before, it will be
							// familiar, just a cute anecdote
							System.out.print(" familiar");
						}
						System.out.print(" faint sound of a music box in the distance");
						System.out.println(
								"You see the villager working with medicine with their music box open and approach");
						sn.nextLine();
						System.out.println(
								"Villager looks very focused and doesn't even notice you, they look confident.");
						System.out.println("You sit down next to them and they heal you to max health of " + health
								+ " hearts and give you bread for the road");
						while (hearts != health) {
							// adds hearts until hearts reaches max health
							addHeart();
						}
						bread++;

					}

					else if (loveEncounter == 5 && charm > 15 && strength > 10) {
						lovePoints++;
						System.out.println(
								"The villager seems to be more comfortable around you. They give you 3 'Lucky Guesses' and 1 'Greater Health Pot'");
						sn.nextLine();
						luckyGuess += 3;
						greaterHealthPot++;
						charm++;
						;
						luck++;
						System.out.println(
								"With them, you feel like you have all the time in the world. Time is set back 2 days");
						day -= 2;

					}

					else if (loveEncounter == 6 && charm > 17 && strength > 12) {
						lovePoints++;
						System.out.println();
					}

				}

				String[] wordBankAnimals = { "ALPACA", "Aardvark", "Alligator", "Anaconda", "Antlion", "Antelope",
						"Anteater", "Ape", "Arctic fox", "Armadillo", "Baboon", "Badger", "Bald eagle", "Bandicoot",
						"Barnacle", "Beaver", "Bison", "Beetle", "Black panther", "Blue jay", "Bobolink", "Bobcat",
						"Butterfly", "Camel", "Cardinal", "Caribou", "Capybara", "Carp", "Catfish", "Centipede",
						"Chameleon", "Cheetah", "Chimpanzee", "Chickadee", "Chipmunk", "Cobra", "Cuckoo", "Deer",
						"Damselfly", "Dinosaur", "Dolphin", "Dung beetle", "Echidna", "Elephant", "Earthworm", "Falcon",
						"Ferret", "Flamingo", "Firefly", "Giraffe", "Gopher", "Guppy", "Gecko", "Gerbil", "Halibut",
						"Hammerhead shark", "Hamster", "Hippopotamus", "Hedgehog", "Hyena", "Hummingbird", "Iguana",
						"Kangaroo", "Jellyfish", "Komodo dragon", "Ladybug", "Lamprey", "Lemur", "Llama", "Lobster",
						"Mackerel", "Manta ray", "Meadowlark", "Mockingbird", "Narwhal", "Ocelot", "Octopus", "Opossum",
						"Ostrich", "Panda", "Panther", "Parrot", "Pelican", "Penguin", "Piranha", "Porcupine", "Rabbit",
						"Raccoon", "Rattlesnake", "Reindeer", "Salamander", "Seahorse", "Shrimp", "Snail", "Spider",
						"Squirrel", "Starfish", "Tarantula", "Walrus", "Wombat", "Zebra" };

				String[] wordBankInstruments = { "Piano", "Guitar", "Violin", "Drums", "Saxophone", "Flute", "Cello",
						"Clarinet", "Trumpet", "Harp", "Mandolin", "Voice", "Trombone", "French Horn", "Viola", "Bass",
						"Ukulele", "Piccolo", "bagpipes", "Oboe", "harmonica", "Euphonium", "Baritone", "Banjo",
						"ocarina", "RECORDER", "Marimba", "Timpani", "bugle", "cornet", "fiddle", "Bassoon", "Triangle",
						"Xylophone" };

				String[] wordBankCountries = { "Afghanistan", "Austria", "Albania", "Angola", "Australia", "Costa Rica",
						"Cambodia", "Chile", "Cuba", "Cameroon", "China", "Cyprus", "Canada", "Colombia",
						"Czech Republic", "Denmark", "Dominican Republic", "Dominica", "Estonia", "Ecuador", "Ethiopia",
						"Egypt", "Estonia", "Fiji", "Finland", "France", "Germany", "Grenada", "Ghana", "Guyana",
						"Greece", "Haiti", "Honduras", "Hungary", "Iceland", "Israel", "Italy", "India", "Indonesia",
						"Ireland", "Jamaica", "Japan", "Latvia", "Luxembourg", "Malaysia", "Monaco", "Netherlands",
						"New Zealand", "Nicaragua", "Nepal", "Norway", "Pakistan", "Poland", "Papua New Guinea",
						"Qatar", "Saudi Arabia", "russia", "Singapore", "Sweden", "Syria", "Sri Lanka", "Thailand",
						"Turkmenistan", "Turkey", "Uganda", "Vietnam", "Yemen", "Zimbabwe" };

				String[] wordBankTVshows = { "House of Cards", "Game of Thrones", "Orange Is the New Black",
						"the Walking Dead", "Riverdale", "Gotham", "The Flash", "Arrow", "Better Call Saul",
						"Supernatural", "Pretty Little Liars", "Prison Break", "The Big Bang Theory", "Friends",
						"Doctor Who", "Breaking Bad", "Sherlock", "Sutis", "The Office", "Stranger Things",
						"Unbreakable Kimmy Schmidt", "Criminal Minds", "The Vampire Diaries", };

				String[] wordBankSingers = { "Taylor Swift", "Rihanna", "Lady Gaga", "Katy Perry", "Elvis Presley",
						"Beyonce", "Michael Jackson", "Justin Bieber", "Adele", "Madonna", "Ariana Grande",
						"Miley Cyrus", "Hannah Montana", "Britney Spears", "Selena Gomez", "Whitney Houston",
						"Whitney Houston", "Frank Sinatra", "Shakira", "Bruno Mars", "Stevie Wonder", "Nicki Minaj",
						"Sia", "Ed Sheeran", "Chris Brown", "Drake", "Ray Charles", "Kris Kross", "Vanilla Ice",
						"MC Hammer", "outkast" };

				String[] wordBankCities = { "Amsterdam", "Bridgetown", "Bras�lia", "Georgetown", "Guatemala City",
						"Helsinki", "Hong Kong", "Jerusalem", "Kabul", "Kingston", "London", "Luxembourg",
						"Mexico City", "Monaco", "New Delhi", "Panama City", "Oslo", "Paris", "San Jose", "Seoul",
						"Singapore", "Stockholm", "Tokyo", "Vatican City", "Ottawa" };

				// each word bank requires at least 20 words because 4
				// encounters per day for 5 days. Repetition is okay because
				// player may use charm to skip over words, and repeating a word
				// they never saw is not truly repeating
				// word banks get progressively harder as days increase
				if (day <= 5) {
					word = wordBankAnimals[rando.nextInt(wordBankAnimals.length)];
				} else if (day <= 10) {
					word = wordBankInstruments[rando.nextInt(wordBankInstruments.length)];
				} else if (day <= 15) {
					word = wordBankCountries[rando.nextInt(wordBankCountries.length)];
				} else if (day <= 20) {
					word = wordBankTVshows[rando.nextInt(wordBankTVshows.length)];
				} else if (day <= 25) {
					word = wordBankSingers[rando.nextInt(wordBankSingers.length)];
				} else if (day <= 30) {
					word = wordBankCities[rando.nextInt(wordBankCities.length)];
				}

				else {
					break;
					// if day is greater than 30, then game is lost, break out
					// of game loop before hangman is played
				}

				// just some banter from the WordKeeper before player guesses
				String[] encounter = { "Heh, aren't you a chump, I won't go easy on you.",
						"Oi, what do you think you're looking at", "I hate cocky kids the most",
						"Don't even think of trying to escape me",
						"Pfft, you're just some kid, what do you think you can even do",
						"Once you're as old as me, you'll wipe that stupid grin off your face",
						"Life is short kid, such a shame I gotta waste it dealing with you", "Why try anymore",
						"I miss home" };
				String encounterIntro = encounter[rando.nextInt(encounter.length)];
				System.out.println("\nWordKeeper: " + encounterIntro);

				playHangman(word);

				sb.delete(0, word.length());
				sbHearts.delete(0, hearts);
				incorrectGuesses.delete(0, incorrectGuesses.length());
				// clears information for the next word

				WordKeeperNotCharmed = true;

			}

			if (hearts == 0 || day > 30)

			{
				adventureStart = true;
				playAgain();
			} else

			{
				long endTime = System.nanoTime();
				double duration = (double) ((endTime - startTime) / 1e9); // nanoseconds
																			// ->
				// seconds
				System.out.println("You won the game in just " + duration + " seconds");
				win();
				adventureStart = true;
				playAgain();
			}

		} while (adventureStart == false);

	} // end of code, rest are methods

	/**
	 * this function plays a game of hangman, and will return true if the human
	 * wins, or return false is the human loses
	 */
	public static boolean playHangman(String word) {

		// clear previous data from stringbuilder, does not change the true
		// value stored in their respective variables
		// ex. hearts would still be 4 after clearing its stringbuilder

		word = word.toUpperCase();

		// Player enters letter, have loop to cycle through each index until the
		// end is reached. Once a letter is found at any point, replace that
		// index of with the letter guessed unless there is already a char
		// there.

		// method below adds hearts to the heart stringbuilder
		// unicode symbold, encoding must be set to 'UTF-8' to view
		for (int h = 0; h < hearts; h++) {
			sbHearts.append('\u2764');
		}

		// this fills the StringBuilder with the appropriate number of dashes
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				sb.append(" ");
			} else {
				sb.append("-");
			}
		}

		while (true) {

			String returnedVal = encounterMenu();
			// calling encounter menu
			System.out.println(returnedVal);
			// prints out returned value from encounterMenu method
			if (WordKeeperNotCharmed == false) {
				return true;
			}

			// record whether the guess was used or not.
			boolean isGuessCorrect = false;

			// take letter input as string
			// do all condition for letter.charAt(0)
			String letter;

			System.out.println(sb);
			letter = sn.next().toUpperCase();

			for (int i = 0; i < word.length(); i++) {

				if (letter.charAt(0) == word.charAt(i) && (sb.charAt(i) == '-')) {
					sb.setCharAt(i, letter.charAt(0));
					isGuessCorrect = true;
				}
			}

			// checks if the user has won the game
			if (isGameDone(sb.toString())) {

				// chance for player to get lucky items is set to false,
				// switched to true if it passes probability test condition
				boolean luckyItems = false;

				// WordKeeper thankful for player saving them
				String[] winningDialogue = { "Thank you kind soul, for free me of this curse",
						"*Tears up* I-I remember it all now", "Not like you even did that much but... Thanks kid",
						"How could I ever repay you!", "I feel like a burden has been lifted" };
				String win = winningDialogue[rando.nextInt(winningDialogue.length)];

				// player won through guessing, add to total
				guessedKeepers++;
				System.out.println(win);

				// every 2 encounters, player earns a coins
				if (encounterNumber % 2 == 0.00) {
					coin++;
					coinsNeeded--;
					System.out.println("You have earned a coin! " + coinsNeeded + " more to go!");
				}

				sn.nextLine();
				System.out.println("The WordKeeper shows gratitude by giving you the following items.");
				sn.nextLine();

				// items found are based on encounterNumber and and probability
				// associated with luck
				// near the beginning of the game, players are given items
				// generously to help them
				// health pot awarded every time

				// below tests if player is lucky enough for random extra items
				// and stats. Mimics a lottery, where there is a #% chance to
				// get extra items
				int chanceLetter = rando.nextInt(100);
				int x = luck * 10;
				if (chanceLetter <= x) {
					luckyItems = true;
				}

				// as player progresses, the frequency of obtaining items
				// decreases
				if (encounterNumber < 30) {

					System.out.println("Attained 'Healing Pot'! It heals for 1 heart");
					healthPot++;
					sn.nextLine();

					if (encounterNumber % 2 == 0.00) {
						System.out.println(
								"Attained 'Lucky Guess'! Whatever you guess will be turned into a char of the word you are guessing. Good for one guess.");
						luckyGuess++;
						sn.nextLine();
					}
					if (encounterNumber % 3 == 0.00) {
						System.out.println("Attained 'Bread'! It heals for 1 heart");
						bread++;
						sn.nextLine();
					}

					if (encounterNumber % 5 == 0.00) {
						System.out.println(
								"Attained 'Stat Reset Scroll'! Gives you a chance to modify your stats to suit your current sitchuation. Changes are permanent unless modified again by the same item");
						statResetScroll++;
						sn.nextLine();
					}

					if (luckyItems == true) {
						System.out.println(
								"You walk away but see a gleam in the corner of your eye. You approach the WordKeeper again and they relectuntly give you 2 'Lucky Guesses', as if to acknwoledge your valor");
						luckyGuess += 2;
						sn.nextLine();
						System.out.println("Charm increased by 1");
						charm++;
						sn.nextLine();
					}

				}

				// since words get harder with encounter number, player awarded
				// greater health pot from this point on
				else {

					System.out.println("Attained 'Greater Healing Pot'! It heals for 2 hearts");
					greaterHealthPot++;
					sn.nextLine();

					if (encounterNumber % 3 == 0.00) {
						System.out.println(
								"Attained 'Lucky Guess'! Whatever you guess will be turned into a char of the word you are guessing. Good for one guess.");
						luckyGuess++;
						sn.nextLine();
					}
					if (encounterNumber % 4 == 0.00) {
						System.out.println("Attained 'Bread'! It heals for 1 heart");
						bread++;
						sn.nextLine();
					}

					if (encounterNumber % 6 == 0.00) {
						System.out.println(
								"Attained 'Stat Reset Scroll'! Gives you a chance to modify your stats to suit your current sitchuation. Changes are permanent unless modified again by the same item");
						statResetScroll++;
						sn.nextLine();
					}

					if (luckyItems == true) {
						System.out.println(
								"As you walk away, you see something on the floor. On closer inspection, you realize it's a 'Lucky Guess'!");
						luckyGuess++;
						sn.nextLine();
					}

				}

				return true;
			}

			// if the guess this round wasn't correct,
			if (!isGuessCorrect) {

				// checks if letter has already been guessed
				if ((String.valueOf(incorrectGuesses)).contains((String.valueOf(letter.charAt(0))).toUpperCase())) {
					System.out.println("You have already guessed this letter\n");
				}

				// checks if guessed letter is a number
				else if (letter.charAt(0) <= 57 && letter.charAt(0) >= 48) {
					System.out.println("Guess a LETTER, not a number\n");
					sn.nextLine();
				}

				// if neither true, then incorrect guess is valid and player
				// loses a heart
				else {
					incorrectGuesses.append((String.valueOf(letter.charAt(0))).toUpperCase() + ' ');
					System.out.println("Incorrect guesses: " + incorrectGuesses);
					removeHeart();
					System.out.println(sbHearts + "\n");
					sn.nextLine();
				}
				// if there are no attempts left, end the game.
				if (hearts == 0) {
					return false;
				}
			}
		}

	}

	static boolean isGameDone(String word) {
		for (int i = 0; i < word.length(); i++) {
			// if there are underscores left, the game is not done
			if (word.charAt(i) == '-') {
				return false;
			}
		}
		return true;
	}

	public static String encounterMenu() {

		String[] guess = { "1. Guess", "Ohoho~ A confident hero I see, go ahead, amuse me.",
				"Heh, I hate cocky people, do your worst", "You think it'll be easy?", "Good luck, you're gonna need it" };
		String[] backpack = { "2. Backpack", "Health Pot", "Greater Healing Pot", "Bread", "Stat Reset Scroll",
				"Lucky Guess", "back" };
		String[] charmWordKeeper = { "3. Charm", "Y-you're really cute...",
				"I don't think I've seen eyes that vibrant since my mom", "You're really beautiful, y'know that?",
				"Heh, you've really got something special about you" };
		String[] flee = { "4. Flee" };

		char choice;
		int choiceNumber;
		// made a char because it's easier to ensure that the input is actually
		// a number using ASCI
		// turned into an int after anyways

		do { // do loop so it repeats until the player decides to guess, then it
				// will return to the playhangman method

			System.out.println("\n" + sb);
			System.out.println(sbHearts);
			System.out.println("Game Menu:");
			System.out.println(guess[0]);
			System.out.println(backpack[0]);
			System.out.println(charmWordKeeper[0]);
			System.out.println(flee[0]);

			do {
				System.out.println("\nChoose an option by typing the number next to your desired choice.");
				choice = sn.next().charAt(0);
			} while (!(choice <= 57 && choice >= 48));
			// above is 0 to 9 in ASCI
			choiceNumber = Character.getNumericValue(choice);

			if (choiceNumber == 1) {
				return (guess[1]);
			}

			if (choiceNumber == 2) {
				for (int i = 1; i < backpack.length; i++) {
					System.out.println(i + ". " + backpack[i]);
				}
				// print out contents of backpack

				char choiceBackpack;
				int choiceNumberBackpack;
				// Checks if integer, different variables just keep it neat as
				// the 'int check' occurs twice here
				do {
					System.out.println("\nType the number of the item you want to use.");
					choiceBackpack = sn.next().charAt(0);
				} while (!(choiceBackpack <= 57 && choiceBackpack >= 48));
				// above is 0 to 9 in ASCI
				choiceNumberBackpack = Character.getNumericValue(choiceBackpack);

				if (choiceNumberBackpack == 1) {
					if (health == hearts) {
						// health is max. number of hearts allowed
						System.out.println("You already have the the maximum number of " + hearts + " hearts.");
					} else if (healthPot > 0) {
						addHeart();
						System.out.println("\n" + sbHearts + "\n");
						System.out.println("You have used 1 of your " + healthPot + " 'Health Pot(s)'");
						healthPot--;
						System.out.println("You have been healed for 1 heart");
						sn.nextLine();
					} else {
						System.out.println("You have none of this item");
					}
				}

				if (choiceNumberBackpack == 2) {
					if (health == hearts) {
						System.out.println("You already have the the maximum number of " + hearts + " hearts.");
					} else if (greaterHealthPot > 0) {
						hearts += 2;
						addHeart();
						addHeart();
						System.out.println("\n" + sbHearts + "\n");
						System.out.println("You have used 1 of your " + greaterHealthPot + " 'Greater Health Pot(s)'");
						greaterHealthPot--;
						System.out.println("You have been healed for 2 hearts");
						sn.nextLine();
					} else {
						System.out.println("You have none of this item");
					}
				}

				if (choiceNumberBackpack == 3) {
					if (health == hearts) {
						System.out.println("You already have the the maximum number of " + hearts + " hearts.");
					} else if (bread > 0) {
						System.out.println("\n" + sbHearts + "\n");
						System.out.println("You have used 1 of your " + bread + " 'Bread(s)'");
						bread--;
						System.out.println("You have been healed for 1 heart");
						sn.nextLine();

					} else {
						System.out.println("You have none of this item");
					}

				}

				if (choiceNumberBackpack == 4) {
					if (statResetScroll > 0) {
						System.out.println("You have used 1 of your " + statResetScroll + " 'Stat Reset Scroll(s)'");
						statResetScroll--;
						assignSkillPoints();
						System.out.println("Health has been restored to " + hearts);
						sn.nextLine();
						sbHearts.delete(0, hearts);
						for (int h = 0; h < hearts; h++) {
							sbHearts.append('\u2764');
						}
						// above ensures that hearts is restored
					} else {
						System.out.println("You have none of this item");
					}
				}

				if (choiceNumberBackpack == 5) {
					if (luckyGuess > 0) {
						System.out.println("You have used 1 of your " + luckyGuess + " 'Lucky Guess(es)'");
						luckyGuess--;
						System.out.println("Enter a random letter and see what you get!");
						letter = sn.next().charAt(0);

						// if player luck is less than 10, then they will be
						// given a random letter, whether that letter has been
						// guessed or not.
						System.out.println("Let's see if luck is by your side~");

						// below is a probability check
						int chanceLetter = rando.nextInt(100);
						int x = luck * 10;

						// timer to add suspense
						for (int i = 0; i < 3; i++) {
							System.out.print(".");
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();

							}
						}

						// if chanceLetter is outside the probability range,
						// then the player will be awarded a random letter,
						// regardless of whether it was previously guessed or
						// not
						if (chanceLetter >= x) {
							letter = word.charAt(rando.nextInt(word.length()));
						}

						// otherwise, chanceLetter is WITHIN the probability
						// range, and player will be guaranteed an unguessed
						// letter
						else {
							for (int i = 0; i < word.length(); i++) {
								if (sb.charAt(i) == '_') {
									letter = word.charAt(i);
									break;
								}

							}
						}

						// prints out awarded letter for player to guess
						System.out.println("\nAwarded letter: " + letter);
						sn.nextLine();

					} else {
						System.out.println("You have none of this item");
					}
				}

				if (choiceNumberBackpack == 6) {
					System.out.println("");
				}

			}

			if (choiceNumber == 3) {
				String playerCharmDialogue = charmWordKeeper[rando.nextInt((charmWordKeeper.length) - 1) + 1];

				System.out.println(playerCharmDialogue);
				sn.nextLine();
				// good ending (bypass WordKeeper) is based on luck, and actual
				// luck and charm skill points
				// building up the suspense while periodically printing dots
				for (int i = 0; i < 3; i++) {
					System.out.println(".");
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}

				String[] charmedWordkeeper = { "Heh, I guess they don't call you a romantic for nothing",
						"W-well, I guess I can make an exception for you...", "I guess we got a casanova on our hands",
						"Heh, I appreciate valor when I see it", "I know potential when I see it",
						"Maybe you're not just a pretty face ;)" };
				String[] notCharmedWordkeeper = { "lol ok...", "I find that distasteful", "You sure have some audacity",
						"Did you really think that would work?", "PFFT, better luck next time kid",
						"Please tell me that was a joke", "I mean, I'd give you an 'N' for effort",
						"How cute, you thought you could get actually get me", "Was that an attempt or...?" };

				int chanceLetter = rando.nextInt(100);
				int x = luck * 10;
				int chanceCharm = rando.nextInt(100);
				int y = charm * 10;

				if (chanceLetter <= x || chanceCharm <= y) {
					System.out.println("WordKeeper: " + charmedWordkeeper[rando.nextInt(charmedWordkeeper.length)]);
					sn.nextLine();
					System.out.println("The WordKeeper was moved by your charm! You bypass the word");
					sn.nextLine();

					charmedKeepers++;

					charm++;
					System.out.println("Charm increased by 1");
					sn.nextLine();
					// every 3rd encounter, the player will get a coin, IF CHARM
					// METHOD IS USED
					// keeps it so that a skilled player is rewarded more highly
					if (encounterNumber % 3 == 0.00) {
						coin++;
						System.out.println("You have earned a coin! " + (coinsNeeded--)
								+ " more to go till you're ready for the boss...");
						sn.nextLine();

					}
					WordKeeperNotCharmed = false;
					charmedKeepers++;
				}

				else {
					System.out
							.println("WordKeeper: " + notCharmedWordkeeper[rando.nextInt(notCharmedWordkeeper.length)]);
					sn.nextLine();
					System.out.println(
							"Wordkeeper was unimpressed. You are throughly embarrassed, motivation decreases and you lose 1 heart");

					removeHeart();
					System.out.println(sbHearts);

					sn.nextLine();
					System.out.println(
							"(Better luck next time, maybe check your own SKILLS before attmepting to do something like that...)");
					sn.nextLine();
				}

			}

			if (choiceNumber == 4) {

				// add timer, system at fixed rate
				System.out.println("You back away slowly, hoping the WordKeeper won't notice");
				System.out.println("And the verdict is...");

				for (int i = 0; i < 3; i++) {
					System.out.println(".");
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}

				// below is a probability check
				int chanceLetter = rando.nextInt(100);
				int x = luck * 10;

				// if chanceLetter is within the probability range,
				// then the player will be able to flee the WordKeeper
				if (chanceLetter <= x) {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
					System.out.println("*sigh of relief*");
					sn.nextLine();
					sn.nextLine();
					System.out.println(
							"You have successfully evaded the WordKeeper, but don't feel safe yet, there's many more awaiting you.");

					WordKeeperNotCharmed = false;
					// reusing variables because they both exit the current word
					// to guess
					// charmedWordKeepers is not increaseed because it is a flee

				}

				else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("!!!");
					sn.nextLine();
					System.out.println("Wordkeeper has noticed you trying to escape! Penalty.");
					removeHeart();
					sn.nextLine();
				}

			}

		} while ((choiceNumber != 1) && (WordKeeperNotCharmed == true));

		return "";

	}

	public static void removeHeart() { // method created to add and remove heart
		// because it happens a lot
		hearts--;
		sbHearts.deleteCharAt(0);
		if (hearts == 0) {
			System.out.println(
					"Young Hero... You have been bested.\nA true legend always gets back up, restart and try again from square 1.");
			sn.nextLine();
		} else {
			System.out.println("You lose 1 heart");

		}
	}

	public static void addHeart() {
		hearts++;
		sbHearts.append('\u2764');
	}

	public static boolean isNumeric(String tempTrait) {

		Scanner sn = new Scanner(tempTrait);

		while (sn.hasNext()) {

			if (sn.hasNextInt() == true) {
				return true;
			} else {
				return false;
			}
		}
		sn.close();
		return true;
	}

	public static void assignSkillPoints() {

		System.out.println(
				"\nPlease assign points to your desired skills, keep in mind that your total points must sum to "
						+ skillPoints + ". Note the 4 skills...");
		System.out.println("Health, Strength, Luck, Charm");
		sn.nextLine();
		adventureStart = true;
		String tempTrait;
		while (adventureStart == true) {

			do {
				// must do health first because it has a min. requirement
				System.out.println(
						"Health must be at least 8. You will begin your journey with this many hearts. Reassigning restores hearts to number chosen here, regardless if you were at a higher number of hearts before reassigning. This also represents your max number of hearts.");
				do {
					do {
						System.out.println("Enter health: ");
						tempTrait = sn.nextLine();
					} while (tempTrait.equals(""));
					isNumeric(tempTrait);
					if (isNumeric(tempTrait) == true) {
						health = Integer.parseInt(tempTrait);
					} else {
						health = -1;
						// random invalid output forces do while to loop again
					}
				} while (health > skillPoints || health < 8);
				System.out.println("Skill points left: " + (skillPoints -= health));

				System.out.println("\nIt's mysterious why we value power so much...");
				do {
					do {
						System.out.println("Enter strength: ");
						tempTrait = sn.nextLine();
					} while (tempTrait.equals(""));
					isNumeric(tempTrait);
					if (isNumeric(tempTrait) == true) {
						strength = Integer.parseInt(tempTrait);
					} else {
						strength = -1;
						// random invalid output forces do while to loop again
					}

				} while (strength > skillPoints || strength < 0);
				System.out.println("Skill points left: " + (skillPoints -= strength));

				System.out.println(
						"\nOne half's timing, and the other half's luck. You may want to be generous to this category");
				do {
					do {
						System.out.println("Enter luck: ");
						tempTrait = sn.nextLine();
					} while (tempTrait.equals(""));
					isNumeric(tempTrait);
					if (isNumeric(tempTrait) == true) {
						luck = Integer.parseInt(tempTrait);
					} else {
						luck = -1;
						// random invalid output forces do while to loop again
					}
				} while (luck > skillPoints || luck < 0);
				System.out.println("Skill points left: " + (skillPoints -= luck));

				System.out.println("\nOh charm, well it wouldn't be a love story without it, now would it?");
				System.out.println("Enter charm: ");
				sn.nextLine();
				charm = skillPoints;
				// because remaining points must go into the last category

				skillPoints -= charm;
			} while (!(skillPoints == 0));

			// code will loop until all skill points total to 15

			System.out.println("\nCharacter information for " + name + " is as follows:");
			System.out.println("Health: " + health);
			System.out.println("Strength: " + strength);
			System.out.println("Luck: " + luck);
			System.out.println("Charm: " + charm);

			do {
				System.out.println("Young hero, are you happy with your stats? (y/n)");
				goodName = sn.nextLine();
				// just reusing variables goodName and adventureStart because
				// they serve the same purpose
			} while (goodName.equals("") || !(goodName.equals("y")) && (!(goodName.equals("n"))));

			if (goodName.equals("y")) {
				System.out.println("Then let us enter, the gate to the ~WordKeepers~");
				adventureStart = false;
			}
			sn.nextLine();
		}
		skillPoints = strength + health + luck + charm;
		hearts = health;
		// when reassigning, all health is restored.

	}

	public static void dots() {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		sn.nextLine();
	}

	static public void playAgain() {

		while (adventureStart == true) {

			do {
				System.out.println("Young hero, are you ready to embark on this journey once more? (y/n)");
				goodName = sn.nextLine();
				// reusing variables because same purpose as before, to get a
				// yes or no

			} while (goodName.equals("") || !(goodName.equals("y")) && (!(goodName.equals("n"))));

			if (goodName.equals("y")) {
				System.out.println("THEN FROM THE TOP SHALL WE!\n");
				adventureStart = false;
			} else if (goodName.equals("n")) {
				System.exit(0);
			}

		}

	}

	static public void win() {

		System.out.println(name
				+ ", despit all odds, you have bested the 'Great One' and released many WordKeepers from hardships");
		dots();
		System.out.println("There will always be WordKeepers to free, but you have definately made a difference");
		dots();
		System.out.println("Maybe one day, you can face the boss, but let's leave that to another year");

		if (guessedKeepers > charmedKeepers) {
			// intellect ending
			System.out.print(
					"Your intellect shone through all as your primary method was guessing the words of the Keepers, ");
			if (lovePoints < 4) {
				// lonely ending
				System.out.print(
						"but you weren't very smart with that villager. YOu can't get through everything with wits alone. ");
			} else {
				// lover ending
				System.out.print("a true balance between wits and charm as even the villager couldn't resist you.");
			}
		}

		else if (charmedKeepers > guessedKeepers) {
			// Casanova ending
			System.out.println(name
					+ ". You sure were a casanova with the WordKeepers, charming your way through most situations to get what you wanted, ");
			if (lovePoints < 4) {
				// lonely ending
				System.out.print(
						"yet you still couldn't get that cute villager. Maybe you should balance your skills more.");
			} else {
				// lover ending
				System.out.print(
						"even mangaing to charm that villager. They sure helped you a lot through your journey, it goes to show, balance is key.");
			}
		}

		System.out
				.println("Never stop doing what you do best " + name + ". I hope to see you helping others very soon.");

	}

}