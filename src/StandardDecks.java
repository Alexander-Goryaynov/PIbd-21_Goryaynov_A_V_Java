import java.awt.Color;
import java.awt.Graphics;

public class StandardDecks implements IDecks {
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
	public void drawPierDecks(Graphics g, int positionX, int positionY, Color decksColor, Color dopColor) {
		g.setColor(decksColor);
        g.fillRect(positionX - 25, positionY - 20, 50, 10);
        g.setColor(Color.black);
        g.drawRect(positionX - 25, positionY - 20, 50, 10);
        for (int i = positionX - 20; i <= positionX + 10; i += 15) {
        	g.setColor(dopColor);
            g.fillRect(i, positionY - 17, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, positionY - 17, 10, 5);                    
        }
        g.setColor(decksColor);
        g.fillRect(positionX - 30, positionY - 10, 60, 10);
        g.setColor(Color.black);
        g.drawRect(positionX - 30, positionY - 10, 60, 10);                
        for (int i = positionX - 25; i <= positionX + 15; i += 20) {
        	g.setColor(dopColor);
            g.fillRect(i, positionY - 7, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, positionY - 7, 10, 5);                    
        }
        g.setColor(decksColor);
        g.fillRect(positionX - 35, positionY, 70, 10);
        g.setColor(Color.black);
        g.drawRect(positionX - 35, positionY, 70, 10);
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
            g.fillRect(startPosX - 25, startPosY - 20, 50, 10);
            g.setColor(Color.black);
            g.drawRect(startPosX - 25, startPosY - 20, 50, 10);
            for (int i = startPosX - 20; i <= startPosX + 10; i += 15) {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 17, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 17, 10, 5);                    
            }
        }
        // second deck
        if ((numDeck == Decks.Three) || (numDeck == Decks.Two)) {
        	g.setColor(decksColor);
            g.fillRect(startPosX - 30, startPosY - 10, 60, 10);
            g.setColor(Color.black);
            g.drawRect(startPosX - 30, startPosY - 10, 60, 10);                
            for (int i = startPosX - 25; i <= startPosX + 15; i += 20) {
            	g.setColor(dopColor);
                g.fillRect(i, startPosY - 7, 10, 5);
                g.setColor(Color.black);
                g.drawRect(i, startPosY - 7, 10, 5);                    
            }
        }
        // first deck
    	g.setColor(decksColor);
        g.fillRect(startPosX - 35, startPosY, 70, 10);
        g.setColor(Color.black);
        g.drawRect(startPosX - 35, startPosY, 70, 10);
        for (int i = startPosX - 30; i <= startPosX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRect(i, startPosY + 3, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, startPosY + 3, 10, 5);                    
        }
	}
	@Override
	public String toString() {
		return "StandardDecks";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		return true;
	}
}
