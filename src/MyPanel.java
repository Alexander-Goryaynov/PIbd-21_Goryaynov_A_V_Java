import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	Ship ship;
	public MyPanel(Ship input) {
		ship = input;
	}
	public void paint(Graphics g) {
		super.paint(g);
		ship.draw(g);
	}
}
