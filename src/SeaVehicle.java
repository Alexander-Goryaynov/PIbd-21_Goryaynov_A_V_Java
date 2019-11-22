import java.awt.Color;
import java.awt.Graphics;

public abstract class SeaVehicle implements ITransport {
	protected int startPosX;
    protected int startPosY;
    protected int pictureWidth;
    protected int pictureHeight;
    protected int maxSpeed;
    protected int weight;
    protected Color mainColor;
    protected Color dopColor;
    public int getStartPosX() {
		return startPosX;
	}
	public int getStartPosY() {
		return startPosY;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	protected void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getWeight() {
		return weight;
	}
	protected void setWeight(int weight) {
		this.weight = weight;
	}
	public Color getMainColor() {
		return mainColor;
	}
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	public Color getDopColor() {
		return dopColor;
	}
	public void setDopColor(Color dopColor) {
		this.dopColor = dopColor;
	}
	public void setPosition(int x, int y, int width, int height)
    {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
    }
    public abstract void drawShip(Graphics g);
    public abstract void moveShip(Direction direction);
}
