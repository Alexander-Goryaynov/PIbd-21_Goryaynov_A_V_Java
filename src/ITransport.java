import java.awt.*;

public interface ITransport {
	void setPosition(int x, int y, int width, int height);
    void drawShip(Graphics g);
    void moveShip(Direction direction);
    Color getMainColor();
    Color getDopColor();
    int getStartPosX();
    int getStartPosY();
}
