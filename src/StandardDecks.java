import java.awt.Color;
import java.awt.Graphics;

public class StandardDecks implements IDecks {

	@Override
	public void drawDecks(Decks numDeck, Graphics g, Color decksColor, Color dopColor, 
			int startPosX, int startPosY) {
		// third deck
        if (numDeck == Decks.Three)
        {
        	g.setColor(decksColor);
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
        // second deck
        if ((numDeck == Decks.Three) || (numDeck == Decks.Two))
        {
        	g.setColor(decksColor);
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
        // first deck
    	g.setColor(decksColor);
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

}
