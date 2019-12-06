import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TakePanel extends JPanel {
	public ITransport ship;
	public IDecks deck;
	public void drawShip(ITransport ship) {
		this.ship = ship;
	}
	public void drawShip(ITransport ship, IDecks deck) {
		this.ship = ship;
		this.deck = deck;
	}
	public void clear() {
		ship = null;
		deck = null;
	}
	public void paint(Graphics g) {
		super.paint(g);
		if (ship != null) {
			ship.drawShip(g);
			if (deck != null) {
				deck.setPosition(ship.getStartPosX(), ship.getStartPosY());
				deck.drawPierDecks(g, deck.getPositionX(), deck.getPositionY(),
						ship.getMainColor(), ship.getDopColor());
			}
		}
	}
}
