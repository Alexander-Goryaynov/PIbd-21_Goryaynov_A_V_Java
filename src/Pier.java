import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pier<T extends ITransport, U extends IDecks> {
	private T[] places;
	private U[] placesDecks;
	private int pictureWidth;
    private int pictureHeight;
	public int getPictureWidth() {
		return pictureWidth;
	}
	private void setPictureWidth(int pictureWidth) {
		this.pictureWidth = pictureWidth;
	}
	public int getPictureHeight() {
		return pictureHeight;
	}
	private void setPictureHeight(int pictureHeight) {
		this.pictureHeight = pictureHeight;
	}
	private final int placeSizeWidth = 210;
    private final int placeSizeHeight = 80;
    @SuppressWarnings("unchecked")
	public Pier(int sizes, int pictureWidth, int pictureHeight)
    {
        this.places = (T[]) new ITransport[sizes];
        this.placesDecks = (U[]) new IDecks[sizes];
        setPictureWidth(pictureWidth);
        setPictureHeight(pictureHeight);
        for (int i = 0; i < places.length; i++)
        {
            places[i] = null;
            placesDecks[i] = null;
        }
    }
    public int plus(T ship) {
    	for (int i = 0; i < places.length; i++)
        {
            if (this.checkFreePlace(i))
            {
                places[i] = ship;
                places[i].setPosition(5 + i / 5 * placeSizeWidth + 50, 
                    i % 5 * placeSizeHeight + 45, this.pictureWidth, this.pictureHeight);
                return i;
            }
        }
        return -1;
    }
    public int plus(T ship, U decks) {
    	for (int i = 0; i < places.length; i++)
        {
            if (this.checkFreePlace(i))
            {
                places[i] = ship;
                places[i].setPosition(5 + i / 5 * placeSizeWidth + 50, 
                    i % 5 * placeSizeHeight + 45, this.pictureWidth, this.pictureHeight);
                placesDecks[i] = decks;
                placesDecks[i].setPosition(5 + i / 5 * placeSizeWidth + 50,
                		i % 5 * placeSizeHeight + 45);
                return i;
            }
        }
        return -1;
    }
    public T minus(int index) {
    	if (index < 0 || index > places.length)
        {
            return null;
        }
        if (!this.checkFreePlace(index))
        {
            T ship = places[index];
            places[index] = null;
            return ship;
        }
        return null;
    }
    public U minusDecks(int index) {
    	if (index < 0 || index > places.length)
        {
            return null;
        }
        if (placesDecks[index] != null)
        {
            U box = placesDecks[index];
            placesDecks[index] = null;
            return box;
        }
        return null;
    }
    private boolean checkFreePlace(int index)
    {
        return (places[index] == null);
    }
    public void draw(Graphics g)
    {
    	drawMarking(g);
        for (int i = 0; i < places.length; i++)
        {
            if (!checkFreePlace(i))
            {
                places[i].drawShip(g);
                if (placesDecks[i] != null) {
                	placesDecks[i].drawPierDecks(g, placesDecks[i].getPositionX(),
                			placesDecks[i].getPositionY(), places[i].getMainColor(), 
                			places[i].getDopColor());
                }
            }
        }        
    }
    private void drawMarking(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setStroke((new BasicStroke(3f)));
        g2.drawRect(0, 0, (places.length / 5) * placeSizeWidth, 480);
        for (int i = 0; i < places.length / 5; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                g2.drawLine(i * placeSizeWidth, j * placeSizeHeight,
                i * placeSizeWidth + 110, j * placeSizeHeight);
            }
            g2.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, 400);
        }
        g2.setStroke((new BasicStroke(1f)));
    }
}
