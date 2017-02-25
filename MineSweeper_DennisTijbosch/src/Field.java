import java.util.HashMap;

public class Field {

	private HashMap<String, Square> fieldSquares;
    private int randomNumber;
    private int surroundingBombs;
    private int amountOfBombs;
    private int amountOfFlaggedBombs;

    Field()
    {
    	fieldSquares = new HashMap<String, Square>();
    }
    
    // Veld aanmaken / HashMap vullen
    void createField(int size, int chance)
    {
    	for (int rowNumber = size; rowNumber > 0; rowNumber--)
    	{
    		char letter = 'a';
    		for (int counter = 1; counter <= size; counter++)
    		{
    			String squareNumber = "" + letter + rowNumber;
    			
    			randomNumber = getRandomInteger();
    			if (chance < randomNumber)
    			{
    				Square s = new Square();
    				s.setTested(false);
    				s.setSquareValue(".");
    				fieldSquares.put(squareNumber, s);
    			}
    			else
    			{
    				BombSquare bs = new BombSquare();
    				bs.setTested(false);
    				bs.setSquareValue("*");
    				fieldSquares.put(squareNumber, bs);
    				amountOfBombs++;
    			}
				letter++;
    		}
    	}
    }
    
    int getRandomInteger () 
    {
        int range = 100;
        return (int)(Math.random() * range) + 1;
    }
  
    // Veld printen
    public void printField (int size, int chance) 
    {
    	// Verticale laag
    	for (int rowNumber = size; rowNumber > 0; rowNumber--)
    	{
    		char letter = 'a';
    		System.out.print(rowNumber + " ");
    		
    		// Horizontale laag
    		for (int counter = 1; counter <= size; counter++)
    		{
    			String squareNumber = "" + letter + rowNumber;
    			
    			// Bijhouden hoeveel bommen de user al heeft geraden
    			if (fieldSquares.get(squareNumber).getGuess())
    			{
    				if (fieldSquares.get(squareNumber).getSquareValue().equals("*"))
    				{
    					amountOfFlaggedBombs++;
    				}
    				System.out.print("*");
    			}
    			else
    			{
	    			if (fieldSquares.get(squareNumber).getHitBomb() == false)
	    			{
	    				// De ongeteste hokjes in het veld printen met een punt
	    				if (fieldSquares.get(squareNumber).getHitNormal() == false)
	    				{
	    					System.out.print(".");
	    				}
	    				// Checken op bommen om een vakje 
	    				else
	    				{
	    	    			surroundingBombs = 0;
	    					char rowLetterSquare = (char) (letter - 1);
	    					int rowNumberSquare = rowNumber - 1;
	    					int surroundCounterA = 1;
	    					int surroundCounterB = 1;
	    					
	    					while (surroundCounterA <= 3){
	    						surroundCounterB = 1;
	    						rowNumberSquare = rowNumber - 1;
	    						while(surroundCounterB <= 3){
	    							if (fieldSquares.get(Character.toString(rowLetterSquare) + rowNumberSquare)instanceof BombSquare) {
	    								surroundingBombs++;
	    							}
	    							rowNumberSquare ++;
	    							surroundCounterB++;
	    						}
	    						rowNumberSquare = letter - 1;
	    						rowLetterSquare +=1;
	    						surroundCounterA++;
	    					}
	    					System.out.print(surroundingBombs);	
	    				}
	    			}
	    			// User raakt een bom
	    			else
	    			{
	    				System.out.print("*");
	    			}
    			}
				letter++;
    		}
    		System.out.println("");
    	}
    	
    	// Letters printen onderaan het veld
    	char squareLetter = 'A';
    	System.out.print("  ");
    	for (int c = 0; c < size; c++)
    	{
    		System.out.print(squareLetter);
    		squareLetter++;
    	}
    	System.out.println("");
    }
    
    // Veld printen met cheats
    public void printCheatField(int size, int chance)
    {   
    	// Verticale laag
    	for (int rowNumber = size; rowNumber > 0; rowNumber--)
    	{
    		char letter = 'a';
    		System.out.print(rowNumber + " ");
    		
    		// Horizontale laag
    		for (int counter = 1; counter <= size; counter++)
    		{
    			String squareNumber = "" + letter + rowNumber;
    			if (fieldSquares.get(squareNumber).getSquareValue().equals("."))
    			{
    				System.out.print(".");
    			}
    			else
    			{
    				System.out.print("*");
    			}
				letter++;
    		}
    		System.out.println("");
    	}
    	
    	// Letters printen onderaan het veld
    	char squareLetter = 'A';
    	System.out.print("  ");
    	for (int c = 0; c < size; c++)
    	{
    		System.out.print(squareLetter);
    		squareLetter++;
    	}
    	System.out.println("");
    }
    
    public HashMap<String, Square> getFieldSquares()
    {
    	return fieldSquares;
    }
    
    public int getAmountOfBombs()
    {
    	return amountOfBombs;
    }
    
    public int getAmountOfFlaggedBombs()
    {
    	return amountOfFlaggedBombs;
    }
}
