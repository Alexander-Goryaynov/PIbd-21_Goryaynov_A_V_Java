import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;

public class Pier<T extends ITransport, U extends IDecks> implements
			Comparable<Pier<T, U>>, Iterable<T>, Iterator<T> {
	private HashMap<Integer, T> places;
	private HashMap<Integer, U> placesDecks;
	private int pictureWidth;
    private int pictureHeight;
    public int maxCount;
    private int curIndex;
    
    
	public T getPlace(int i) throws PierNotFoundException {
		if (places.get(i) != null) {
			return places.get(i);
		}
		throw new PierNotFoundException(i);
	}
	public T showPlace(int i) {
		if (places.get(i) != null) {
			return places.get(i);
		}
		return null;	// without any exceptions
	}
	public U getPlacesDeck(int i) {
		return placesDecks.get(i);
	}
	
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
    public Pier(int sizes, int pictureWidth, int pictureHeight) {
        this.places = new HashMap<>(sizes);
        this.placesDecks = new HashMap<>(sizes);
        setPictureWidth(pictureWidth);
        setPictureHeight(pictureHeight);
        this.maxCount = sizes;
        curIndex = -1;
    }
    public int plus(T ship) throws PierOverflowException, PierAlreadyHaveException {
    	if (places.containsValue(ship)) throw new PierAlreadyHaveException();
    	for (int i = 0; i < maxCount; i++) {
            if (checkFreePlace(i)) {
                places.put(i, ship);
                places.get(i).setPosition(5 + i / 5 * placeSizeWidth + 50, 
                    i % 5 * placeSizeHeight + 45, this.pictureWidth, this.pictureHeight);
                return i;
            }
        }
        throw new PierOverflowException();
    }
    public int plus(T ship, U decks) throws PierOverflowException, PierAlreadyHaveException {
    	if((places.containsValue(ship)) && (placesDecks.containsValue(decks)))
			throw new PierAlreadyHaveException();
    	for (int i = 0; i < maxCount; i++) {
            if (checkFreePlace(i)) {
                places.put(i, ship);
                places.get(i).setPosition(5 + i / 5 * placeSizeWidth + 50, 
                    i % 5 * placeSizeHeight + 45, this.pictureWidth, this.pictureHeight);
                placesDecks.put(i, decks);
                placesDecks.get(i).setPosition(5 + i / 5 * placeSizeWidth + 50,
                		i % 5 * placeSizeHeight + 45);
                return i;
            }
        }
        throw new PierOverflowException();
    }
    public T minus(int index) throws PierNotFoundException {
    	if (index < 0 || index > maxCount) return null;
        if (!checkFreePlace(index)) {
            T ship = places.get(index);
            places.remove(index);
            return ship;
        }
        throw new PierNotFoundException(index);
    }
    public U minusDecks(int index) {
    	if (index < 0 || index > maxCount) {
            return null;
        }
        if (placesDecks.containsKey(index)) {
            U decks = placesDecks.get(index);
            placesDecks.remove(index);
            return decks;
        }
        return null;
    }
    private boolean checkFreePlace(int index){
        return !(places.containsKey(index));
    }
    public int plus(T ship, int index) throws PierOccupiedPlaceException{
    	if (checkFreePlace(index)) {
    		places.put(index, ship);
    		places.get(index).setPosition(65 + index / 5 * placeSizeWidth,
    				index % 5 * placeSizeHeight + 50, pictureWidth, pictureHeight);
    		return index;
    	}
    	throw new PierOccupiedPlaceException(index);
    }    
    public int plus(T ship, U decks, int index) throws PierOccupiedPlaceException {
    	if (checkFreePlace(index)) {
    		places.put(index, ship);
    		places.get(index).setPosition(65 + index / 5 * placeSizeWidth, 
    				index % 5 * placeSizeHeight + 50, pictureWidth, pictureHeight);
    		placesDecks.put(index, decks);
            placesDecks.get(index).setPosition(places.get(index).getStartPosX(),
            		places.get(index).getStartPosY());
    		return index;
    	}
    	throw new PierOccupiedPlaceException(index);
    }
    public void draw(Graphics g){
    	drawMarking(g);
        for (int i = 0; i < maxCount; i++) {
            if (!checkFreePlace(i)) {
                places.get(i).drawBasement(g);
                if (placesDecks.containsKey(i)) {
                	if(places.get(i) instanceof DieselShip) {
                		DieselShip temp = (DieselShip) places.get(i);
                		temp.drawPipe(g);
                	}
                	placesDecks.get(i).drawPierDecks(g, placesDecks.get(i).getPositionX(),
                			placesDecks.get(i).getPositionY(), places.get(i).getMainColor(), 
                			places.get(i).getDopColor()
                	);
                	if(places.get(i) instanceof DieselShip) {
                		DieselShip temp = (DieselShip) places.get(i);
                		temp.drawLights(g);
                	}
                } else {
                	places.get(i).drawShip(g);
                }
            }
        }        
    }
    private void drawMarking(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setStroke((new BasicStroke(3f)));
        g2.drawRect(0, 0, (maxCount / 5) * placeSizeWidth, 480);
        for (int i = 0; i < maxCount / 5; i++) {
            for (int j = 0; j < 6; j++) {
                g2.drawLine(i * placeSizeWidth, j * placeSizeHeight,
                i * placeSizeWidth + 110, j * placeSizeHeight);
            }
            g2.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, 400);
        }
        g2.setStroke((new BasicStroke(1f)));
    }
    public int getKey() {
    	return (int)places.keySet().toArray()[curIndex];
    }
    public int compareTo(Pier<T, U> other) {
    	if (places.size() > other.places.size()) return -1;
        else if (places.size() < other.places.size()) return 1;
        else if (places.size() > 0) {
        	Object[] thisKeys = places.keySet().toArray();
        	Object[] otherKeys = other.places.keySet().toArray();
            for (int i = 0; i < places.size(); ++i) {
                if (places.get(thisKeys[i]).getClass().getName().equals("Ship") && 
                		other.places.get(thisKeys[i]).getClass().getName().equals("DieselShip"))
                	return 1;
                if (places.get(thisKeys[i]).getClass().getName().equals("DieselShip") &&
                		other.places.get(thisKeys[i]).getClass().getName().equals("Ship"))
                	return -1;
                if (places.get(thisKeys[i]).getClass().getName().equals("Ship") &&
                		other.places.get(thisKeys[i]).getClass().getName().equals("Ship")) {
                	Ship thisShip = (Ship)places.get(thisKeys[i]);
                    Ship otherShip = (Ship)other.places.get(otherKeys[i]);
                    return thisShip.compareTo(otherShip);
                }
                if(places.get(thisKeys[i]).getClass().getName().equals("DieselShip") &&
                		other.places.get(thisKeys[i]).getClass().getName().equals("DieselShip")) {
                	DieselShip thisShip = (DieselShip)places.get(thisKeys[i]);
                    DieselShip otherShip = (DieselShip)other.places.get(otherKeys[i]);
                    return thisShip.compareTo(otherShip);
                }
            }
        }
        return 0;
    }

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if ((curIndex + 1) >= places.size()) {
			curIndex = -1;
			return false;
		} else return true;
	}
	@Override
	public T next() {
		curIndex++;
		return places.get(curIndex); 
	}
}
