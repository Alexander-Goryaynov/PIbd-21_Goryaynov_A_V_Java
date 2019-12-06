import java.awt.Color;
import java.awt.Graphics;

public class RoundedDecks implements IDecks {
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
        g.fillRoundRect(positionX - 25, positionY - 20, 50, 10, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(positionX - 25, positionY - 20, 50, 10, 5, 5);
        for (int i = positionX - 20; i <= positionX + 10; i += 15) {
        	g.setColor(dopColor);
            g.fillRoundRect(i, positionY - 17, 10, 5, 3, 3);
            g.setColor(Color.black);
            g.drawRoundRect(i, positionY - 17, 10, 5, 3, 3);                    
        }
        g.setColor(decksColor);
        g.fillRoundRect(positionX - 30, positionY - 10, 60, 10, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(positionX - 30, positionY - 10, 60, 10, 5, 5);                
        for (int i = positionX - 25; i <= positionX + 15; i += 20) {
        	g.setColor(dopColor);
            g.fillRoundRect(i, positionY - 7, 10, 5, 3, 3);
            g.setColor(Color.black);
            g.drawRoundRect(i, positionY - 7, 10, 5, 3, 3);                    
        }
        g.setColor(decksColor);
        g.fillRoundRect(positionX - 38, positionY, 76, 10, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(positionX - 38, positionY, 76, 10, 5, 5);
        for (int i = positionX - 30; i <= positionX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRoundRect(i, positionY + 3, 10, 5, 3, 3);
            g.setColor(Color.black);
            g.drawRoundRect(i, positionY + 3, 10, 5, 3, 3);                    
        }
	}
	@Override
	public void drawDecks(Decks numDeck, Graphics g, Color decksColor, Color dopColor, 
			int startPosX, int startPosY) {
		// third deck
        if (numDeck == Decks.Three) {
        	g.setColor(decksColor);
            g.fillRoundRect(startPosX - 25, startPosY - 20, 50, 10, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(startPosX - 25, startPosY - 20, 50, 10, 5, 5);
            for (int i = startPosX - 20; i <= startPosX + 10; i += 15) {
            	g.setColor(dopColor);
                g.fillRoundRect(i, startPosY - 17, 10, 5, 3, 3);
                g.setColor(Color.black);
                g.drawRoundRect(i, startPosY - 17, 10, 5, 3, 3);                    
            }
        }
        // second deck
        if ((numDeck == Decks.Three) || (numDeck == Decks.Two)) {
        	g.setColor(decksColor);
            g.fillRoundRect(startPosX - 30, startPosY - 10, 60, 10, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(startPosX - 30, startPosY - 10, 60, 10, 5, 5);                
            for (int i = startPosX - 25; i <= startPosX + 15; i += 20) {
            	g.setColor(dopColor);
                g.fillRoundRect(i, startPosY - 7, 10, 5, 3, 3);
                g.setColor(Color.black);
                g.drawRoundRect(i, startPosY - 7, 10, 5, 3, 3);                    
            }
        }
        // first deck
    	g.setColor(decksColor);
        g.fillRoundRect(startPosX - 38, startPosY, 76, 10, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(startPosX - 38, startPosY, 76, 10, 5, 5);
        for (int i = startPosX - 30; i <= startPosX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRoundRect(i, startPosY + 3, 10, 5, 3, 3);
            g.setColor(Color.black);
            g.drawRoundRect(i, startPosY + 3, 10, 5, 3, 3);                    
        }
	}
	@Override
	public String toString() {
		return "RoundedDecks";
	}
}
