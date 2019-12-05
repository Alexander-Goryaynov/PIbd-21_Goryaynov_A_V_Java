import java.awt.Color;
import java.awt.Graphics;

public class TrapezeDecks implements IDecks {
	private int positionX;
	private int positionY;
	public int getPositionX() {
		return positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	@Override
	public void setPosition(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	@Override
	public void drawPierDecks(Graphics g, int positionX, int positionY, Color decksColor, 
			Color dopColor) {
		g.setColor(decksColor);
    	int[] xPoints = {positionX - 25, positionX - 20, 
        		positionX + 20, positionX + 25};
        int[] yPoints = {positionY - 10, positionY - 20, 
        		positionY - 20, positionY - 10};
        int nPoints = 4;
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints);
        for (int i = positionX - 20; i <= positionX + 10; i += 15) {
        	g.setColor(dopColor);
            g.fillRect(i, positionY - 17, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, positionY - 17, 10, 5);                    
        }
        xPoints = new int[] {positionX - 30, positionX - 25, 
        		positionX + 25, positionX + 30};
        yPoints = new int[] {positionY, positionY - 10, 
        		positionY - 10, positionY};
        nPoints = 4;
    	g.setColor(decksColor);
    	g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints);                
        for (int i = positionX - 25; i <= positionX + 15; i += 20) {
        	g.setColor(dopColor);
            g.fillRect(i, positionY - 7, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, positionY - 7, 10, 5);                    
        }
        xPoints = new int[] {positionX - 40, positionX - 35, 
        		positionX + 35, positionX + 40};
        yPoints = new int[] {positionY + 10, positionY, 
        		positionY, positionY + 10};
        nPoints = 4;
    	g.setColor(decksColor);
    	g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints); 
        for (int i = positionX - 30; i <= positionX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRect(i, positionY + 3, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, positionY + 3, 10, 5);                    
        }
	}
	@Override
	public void drawDecks(Decks numDeck, Graphics g, Color decksColor, Color dopColor, 
			int startPosX, int startPosY) {
		// third deck
        if (numDeck == Decks.Three) {
        	g.setColor(decksColor);
        	int[] xPoints = {startPosX - 25, startPosX - 20, 
            		startPosX + 20, startPosX + 25};
            int[] yPoints = {startPosY - 10, startPosY - 20, 
            		startPosY - 20, startPosY - 10};
            int nPoints = 4;
            g.fillPolygon(xPoints, yPoints, nPoints);
            g.setColor(Color.black);
            g.drawPolygon(xPoints, yPoints, nPoints);
            for (int i = startPosX - 20; i <= startPosX + 10; i += 15) {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 17, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 17, 10, 5);                    
            }
        }
        // second deck
        if ((numDeck == Decks.Three) || (numDeck == Decks.Two)) {
        	int[] xPoints = {startPosX - 30, startPosX - 25, 
            		startPosX + 25, startPosX + 30};
            int[] yPoints = {startPosY, startPosY - 10, 
            		startPosY - 10, startPosY};
            int nPoints = 4;
        	g.setColor(decksColor);
        	g.fillPolygon(xPoints, yPoints, nPoints);
            g.setColor(Color.black);
            g.drawPolygon(xPoints, yPoints, nPoints);                
            for (int i = startPosX - 25; i <= startPosX + 15; i += 20) {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 7, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 7, 10, 5);                    
            }
        }
        // first deck
        int[] xPoints = {startPosX - 40, startPosX - 35, 
        		startPosX + 35, startPosX + 40};
        int[] yPoints = {startPosY + 10, startPosY, 
        		startPosY, startPosY + 10};
        int nPoints = 4;
    	g.setColor(decksColor);
    	g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints); 
        for (int i = startPosX - 30; i <= startPosX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRect(i, startPosY + 3, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, startPosY + 3, 10, 5);                    
        }
	}
	@Override
	public String toString() {
		return "TrapezeDecks";
	}
}
