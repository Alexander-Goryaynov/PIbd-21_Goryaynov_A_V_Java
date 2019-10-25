import java.awt.Graphics;

import javax.swing.JPanel;

public class PierPanel extends JPanel {
	private Pier<ITransport, IDecks> pier;
	public PierPanel(Pier<ITransport, IDecks> pier) {
		this.pier = pier;
	}
	public void paint(Graphics g) {
		super.paint(g);
		pier.draw(g);
	}
}
