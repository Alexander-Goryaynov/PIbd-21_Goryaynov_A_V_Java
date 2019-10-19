import java.awt.*;

public class Ship {
	private int startPosX;
	private int startPosY;
    private int pictureWidth;
    private int pictureHeight;
    private int maxSpeed;
    private int weight;
    private Color mainColor;
    private Color dopColor;
    private Decks numDeck;
    private boolean pipe;
    private boolean lights;
    private final int shipWidth = 90;
    private final int shipHeight = 50;
    public Ship(int maxSpeed, int weight, Color mainColor, Color dopColor, 
            Decks numDeck, boolean pipe, boolean lights) {
    	this.setMaxSpeed(maxSpeed);
    	this.setWeight(weight);
    	this.setMainColor(mainColor);
    	this.setDopColor(dopColor);
    	this.setNumDeck(numDeck);
    	this.setPipe(pipe);
    	this.setLights(lights);
    }
    public void setMaxSpeed (int maxSpeed) {
    	this.maxSpeed = maxSpeed;
    }
    public int getMaxSpeed () {
    	return maxSpeed;
    }
    public void setWeight (int weight) {
    	this.weight = weight;
    }
    public int getWeight () {
    	return weight;
    }
    public void setMainColor (Color mainColor) {
    	this.mainColor = mainColor;
    }
    public Color getMainColor () {
    	return mainColor;
    }
    public void setDopColor (Color dopColor) {
    	this.dopColor = dopColor;
    }
    public Color getDopColor () {
    	return dopColor;
    }
    public void setNumDeck (Decks numDeck) {
    	this.numDeck = numDeck;
    }
    public Decks getNumDeck() {
    	return numDeck;
    }
    public void setPipe (boolean pipe) {
    	this.pipe = pipe;
    }
    public boolean getPipe() {
    	return pipe;
    }
    public void setLights (boolean lights) {
    	this.lights = lights;
    }
    public boolean getLights() {
    	return lights;
    }
    public void setPosition(int x, int y, int width, int height)
    {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
    }
    public void moveShip (Direction dir)
    {
        int step = maxSpeed * 100 / weight;
        switch (dir)
        {
            case Right:
                if (startPosX + step < pictureWidth - shipWidth)
                {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - 4*step > 0)
                {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - 3*step > 0)
                {
                    startPosY -= step;
                }
                break;
            case Down:
                if (startPosY + step < pictureHeight - shipHeight)
                {
                    startPosY += step;
                }
                break;
        }
    }
    public void draw (Graphics g)
    {	
        
        if (getPipe())
        {
        	g.setColor(Color.black);
            g.fillRect(startPosX - 20, startPosY - 25, 15, 50);
        }
        if (numDeck == Decks.Three)
        {
        	g.setColor(mainColor);
            g.fillRect(startPosX - 25, startPosY - 20, 50, 10);
            g.setColor(Color.black);
            g.drawRect(startPosX - 25, startPosY - 20, 50, 10);
            for (int i = startPosX - 20; i <= startPosX + 10; i += 15) 
            {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 17, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 17, 10, 5);                    
            }
        }
        if ((numDeck == Decks.Three) || (numDeck == Decks.Two))
        {
        	g.setColor(mainColor);
            g.fillRect(startPosX - 30, startPosY - 10, 60, 10);
            g.setColor(Color.black);
            g.drawRect(startPosX - 30, startPosY - 10, 60, 10);                
            for (int i = startPosX - 25; i <= startPosX + 15; i += 20)
            {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 7, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 7, 10, 5);                    
            }
        }
        if ((numDeck == Decks.One)||(numDeck == Decks.Two) || 
        		(numDeck == Decks.Three))
        {
        	g.setColor(mainColor);
            g.fillRect(startPosX - 35, startPosY, 70, 10);
            g.setColor(Color.black);
            g.drawRect(startPosX - 35, startPosY, 70, 10);
            for (int i = startPosX - 30; i <= startPosX + 20; i += 25)
            {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY + 3, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY + 3, 10, 5);                    
            }
        }        
        int[] xPoints = {startPosX - 45, startPosX + 45, 
        		startPosX + 30, startPosX - 40};
        int[] yPoints = {startPosY + 10, startPosY + 10, 
        		startPosY + 25, startPosY + 25};
        int nPoints = 4;
        g.setColor(mainColor);
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints);
        for (int i = startPosX-35; i <= startPosX + 25; i += 15)
        {
        	g.setColor(dopColor);
            g.fillOval(i, startPosY + 17, 3, 3);
            g.setColor(Color.black);
            g.drawOval(i, startPosY + 17, 3, 3);
        }
        if (getLights())
        {
        	g.setColor(Color.yellow);
            g.fillOval(startPosX + 35, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 5, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 43, startPosY + 10, 5, 5);
        }
    }
}
