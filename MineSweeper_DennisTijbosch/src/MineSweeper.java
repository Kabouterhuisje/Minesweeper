import java.util.Scanner;

public class MineSweeper {
	
	private Scanner reader = new Scanner(System.in);
	private int size;
	private int chance;
	private String answer;
	private boolean isPlaying;
	private boolean withCheats;
	private Field gameField;
	
	MineSweeper(boolean withCheats)
	{	
		gameField = new Field();
		this.withCheats = withCheats;
		isPlaying = true;
		System.out.println("Welkom bij het spelletje Mijnenveger!");
		System.out.println("Probeer alle mijnen te vinden in het mijnenveld!");
		
		setGameValues();
	}
		
	// User input ophalen
	void setGameValues() 
	{	
		System.out.println("Geef de grootte van het veld (5-20): ");
		
		try
		{
			size = reader.nextInt();
			if (size > 4 && size < 21)
			{
				System.out.println("Geef de kans dat een veld een bom bevat in % (10-25): ");
				chance = reader.nextInt();
				if (chance > 9 && chance < 26 && withCheats == false)
				{
					play();
				}
				else if (chance > 9 && chance < 26 && withCheats == true)
				{
					playWithCheats();
				}
				else
				{
					System.err.println("*** De kans moet tussen 10 en 25 liggen!");
					setGameValues();
				}
			}
			else
			{
				System.err.println("*** De grootte moet tussen 5 en 20 liggen! ***");
				setGameValues();
			}
		}
		catch(Exception e)
		{
			System.err.println("Geen geldige gegevens.. Probeer het nog eens");
		}
	}

	void play() 
	{
		gameField.createField(size, chance);
		gameField.printField(size, chance);
		
		playerTurn();
	}
	
	void playWithCheats()
	{
		gameField.createField(size, chance);
		gameField.printField(size, chance);
		System.out.print("\n");
		gameField.printCheatField(size, chance);
		
		playerTurn();
	}
	
	// De beurten van een user in een loop
	void playerTurn()
	{
		while (isPlaying)
		{
			// User input opvragen
			System.out.println("Geef de locatie die je wilt testen: ");
			answer = reader.next();
			
			// Checken of user heeft gewonnen
			if (gameField.getAmountOfBombs() == gameField.getAmountOfFlaggedBombs())
			{
				System.out.println("Gefeliciteerd! Je hebt gewonnen!");
				isPlaying = false;
				
				try
				{
					System.out.println("Wil je nog een keer spelen? (ja/nee): ");
					answer = reader.next();
					if (answer.equals("ja"))
					{
						isPlaying = true;
						MineSweeper m = new MineSweeper(true);
					}
					else
					{
						System.out.println("Jammer.. Houdoe!");
						System.exit(0);
					}
				}
				catch(Exception e)
				{
					System.err.println("Vul ja of nee in!");
				}
			}
			// Checken of user input begint met * / hokje flaggen
			else
			{
				try
				{
					if (answer.startsWith("*"))
					{
						if (gameField.getFieldSquares().get(answer.substring(1, 3)).getGuess())
						{
							gameField.getFieldSquares().get(answer.substring(1, 3)).setGuess(false);
							gameField.printField(size, chance);
						}
						else
						{
							gameField.getFieldSquares().get(answer.substring(1, 3)).setGuess(true);
							gameField.printField(size, chance);
						}	
					}
					else
					{
						// Hokje is geen bom
						if (gameField.getFieldSquares().get(answer).getSquareValue() == ".")
						{
							gameField.getFieldSquares().get(answer).setTested(true);
							gameField.getFieldSquares().get(answer).setHitNormal(true);
							gameField.printField(size, chance);
							System.out.println("Goed gedaan! Geef de volgende locatie!");
						}
						// Hokje is een bom
						else if (gameField.getFieldSquares().get(answer).getSquareValue() == "*")
						{
							gameField.getFieldSquares().get(answer).setTested(true);
							gameField.getFieldSquares().get(answer).setHitBomb(true);
							gameField.printField(size, chance);
							System.out.println("GAME OVER! Je hebt een bom geraakt!");
							isPlaying = false;
							
							try
							{
								System.out.println("Wil je nog een keer spelen? (ja/nee): ");
								answer = reader.next();
								if (answer.equals("ja"))
								{
									isPlaying = true;
									MineSweeper m = new MineSweeper(true);
								}
								else
								{
									System.out.println("Jammer.. Houdoe!");
									System.exit(0);
								}
							}
							catch(Exception e)
							{
								System.err.println("Vul ja of nee in!");
							}
							
						}
						// User heeft er genoeg van en stopt midden in het spel
						else if (answer == "stop")
						{
							isPlaying = false;
							System.out.println("We zien je graag een volgende keer weer terug!");
						}
						else
						{
							System.out.println("U heeft iets fout gedaan.. Probeer het nog eens!");
						}
					}
				}
				catch(Exception e)
				{
					System.err.println("Vul een geldig hokje in!");
				}
				playerTurn();
			}
		}
	}
	
	
	public int getSize()
	{
		return size;
	}
	
	public int getChange()
	{
		return chance;
	}

}
