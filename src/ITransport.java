import java.awt.Graphics;

public interface ITransport {
	void setPosition(int x, int y, int width, int height);
    void drawShip(Graphics g);
    void moveShip(Direction direction);
}
