
public class Square {

    private boolean tested;
    private String squareValue;
    private boolean hitBomb;
    private boolean hitNormal;
    private boolean guess;

    public void setTested (boolean s) {
        tested = s;
    }

    public boolean getTested () {
        return tested;
    }

    public void setSquareValue (String s) {
        squareValue = s;
    }

    public String getSquareValue () {
        return squareValue;
    }
    
    public void setHitBomb(boolean s)
    {
    	hitBomb = s;
    }
    
    public boolean getHitBomb()
    {
    	return hitBomb;
    }
    
    public void setHitNormal(boolean s)
    {
    	hitNormal = s;
    }
    
    public boolean getHitNormal()
    {
    	return hitNormal;
    }
    
    public void setGuess(boolean s)
    {
    	guess = s;
    }
    
    public boolean getGuess()
    {
    	return guess;
    }
}
