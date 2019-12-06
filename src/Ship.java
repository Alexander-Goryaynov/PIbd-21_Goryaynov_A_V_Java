import java.awt.*;

public class Ship extends SeaVehicle {
    protected final int shipWidth = 90;
    protected final int shipHeight = 50;
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
}
