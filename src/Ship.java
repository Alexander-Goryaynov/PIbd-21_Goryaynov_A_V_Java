import java.awt.*;
import java.util.Iterator;

import java.lang.Class;

public class Ship extends SeaVehicle implements Comparable<Ship>, 
			Iterable<Integer>, Iterator<Integer> {
    protected final int shipWidth = 90;
    protected final int shipHeight = 50;
    private int curIndex = -1;
    public Ship(int maxSpeed, int weight, Color mainColor, Color dopColor) {
    	this.setMaxSpeed(maxSpeed);
    	this.setWeight(weight);
    	this.setMainColor(mainColor);
    	this.setDopColor(dopColor);
    }
    public Ship(String config) {
    	String[] params = config.split(";");
    	if (params.length >= 3) {
    		maxSpeed = Integer.parseInt(params[0]);
    		weight = Integer.parseInt(params[1]);
    		mainColor = new Color(Integer.parseInt(params[2]));
    		dopColor = Color.blue;
    	}
    }
    @Override
    public void moveShip (Direction dir) {
        int step = maxSpeed * 100 / weight;
        switch (dir) {
            case Right:
                if (startPosX + step < pictureWidth - shipWidth) {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - 4*step > 0) {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - 3*step > 0) {
                    startPosY -= step;
                }
                break;
            case Down:
                if (startPosY + step < pictureHeight - shipHeight) {
                    startPosY += step;
                }
                break;
        }
    }
    @Override
    public void drawShip (Graphics g) {	
        // first deck
    	g.setColor(mainColor);
        g.fillRect(startPosX - 35, startPosY, 70, 10);
        g.setColor(Color.black);
        g.drawRect(startPosX - 35, startPosY, 70, 10);
        for (int i = startPosX - 30; i <= startPosX + 20; i += 25) {
        	g.setColor(dopColor);
            g.fillRect(i, startPosY + 3, 10, 5);
            g.setColor(Color.black);
            g.drawRect(i, startPosY + 3, 10, 5);                    
        }
        // basement     
        int[] xPoints = {startPosX - 45, startPosX + 45, 
        		startPosX + 30, startPosX - 40};
        int[] yPoints = {startPosY + 10, startPosY + 10, 
        		startPosY + 25, startPosY + 25};
        int nPoints = 4;
        g.setColor(mainColor);
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints);
        for (int i = startPosX-35; i <= startPosX + 25; i += 15) {
        	g.setColor(dopColor);
            g.fillOval(i, startPosY + 17, 3, 3);
            g.setColor(Color.black);
            g.drawOval(i, startPosY + 17, 3, 3);
        }
        
    }
    public void drawBasement(Graphics g) {
    	int[] xPoints = {startPosX - 45, startPosX + 45, 
        		startPosX + 30, startPosX - 40};
        int[] yPoints = {startPosY + 10, startPosY + 10, 
        		startPosY + 25, startPosY + 25};
        int nPoints = 4;
        g.setColor(mainColor);
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, nPoints);
        for (int i = startPosX-35; i <= startPosX + 25; i += 15) {
        	g.setColor(dopColor);
            g.fillOval(i, startPosY + 17, 3, 3);
            g.setColor(Color.black);
            g.drawOval(i, startPosY + 17, 3, 3);
        }
    }	
	public String getConfig() {
		return (maxSpeed + ";" + weight + ";" + mainColor.getRGB());
	}
	@Override
	public int compareTo(Ship other) {
		if (other == null) return 1;
		if (maxSpeed != other.maxSpeed) return Integer.compare(maxSpeed, other.maxSpeed);
        if (mainColor != other.mainColor) return Integer.compare(mainColor.getRGB(), 
        		other.mainColor.getRGB());
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Ship other = (Ship) obj;
		if (!mainColor.equals(other.mainColor)) return false;
		if (maxSpeed != other.maxSpeed) return false; 
		return true;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean hasNext() {
		if (curIndex + 1 >= 4) {
			curIndex = -1;
			return false;
		}
		return true;
	}
	@Override
	public Integer next() {
		curIndex++;
		switch (curIndex) {
		case 0:
			return maxSpeed;			
		case 1:
			return weight;
		case 2:
			return mainColor.getRGB();
		case 3:
			return dopColor.getRGB();
		default:
			return 0;
		}
	}
	@Override
	public Iterator<Integer> iterator() {
		return this;
	}
}
