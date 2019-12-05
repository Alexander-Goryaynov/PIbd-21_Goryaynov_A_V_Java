import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConfigPanel extends JPanel {
	private ITransport ship;
	private IDecks decks;
	public ITransport getShip() {
		return ship;
	}
	public void setShip(ITransport ship) {
		this.ship = ship;
	}
	public IDecks getDecks() {
		return decks;
	}
	public void setDecks(IDecks decks) {
		this.decks = decks;
	}
	public void clearDecks() {
		decks = null;
	}
	public void clear() {
		ship = null;
		decks = null;
	}
	public void paint(Graphics g) {
		super.paint(g);
		if (ship != null) {
			ship.drawBasement(g);
			if (decks != null) {
				g.setColor(Color.black);
		        g.fillRect(ship.getStartPosX() - 20, ship.getStartPosY() - 25, 15, 50);
				decks.setPosition(ship.getStartPosX(), ship.getStartPosY());
				decks.drawPierDecks(g, decks.getPositionX(), decks.getPositionY(),
						ship.getMainColor(), ship.getDopColor());
				ship.drawBasement(g);
			} else {
				ship.drawShip(g);
			}
		}
	}
}
