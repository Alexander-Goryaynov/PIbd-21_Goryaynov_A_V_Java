import java.awt.*;

public interface ITransport {
	void setPosition(int x, int y, int width, int height);
    void drawShip(Graphics g);
    void drawBasement(Graphics g);
    void moveShip(Direction direction);
    void setMainColor(Color color);
    void setDopColor(Color color);
    Color getMainColor();
    Color getDopColor();
    int getStartPosX();
    int getStartPosY();
}
