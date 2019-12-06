import java.awt.Color;
import java.awt.Graphics;

public interface IDecks {
	public void drawDecks(Decks numDeck, Graphics g, Color decksColor, Color dopColor,
			int startPosX, int startPosY);
	public void drawPierDecks(Graphics g, int positionX, int positionY, 
			Color decksColor, Color dopColor);
	public void setPosition(int positionX, int positionY);
	public int getPositionX();
	public int getPositionY();
	public String toString();
}
