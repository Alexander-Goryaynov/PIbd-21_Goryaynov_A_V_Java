import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	ITransport ship;
	public MyPanel(ITransport input) {
		ship = input;
	}
	public void paint(Graphics g) {
		super.paint(g);
		ship.drawShip(g);
	}
}
