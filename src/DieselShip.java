import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class DieselShip extends Ship {
	private Decks numDeck;
	private Color lightsColor;
	private boolean pipe;
	private boolean lights;
	private IDecks decks;
	public DieselShip (int maxSpeed, int weight, Decks numDeck, Color mainColor, 
            Color dopColor, Color lightsColor, boolean pipe, boolean lights)
        {
			super(maxSpeed, weight, mainColor, dopColor);
            this.setLightsColor(lightsColor);
            this.setPipe(pipe);
            this.setLights(lights);
            this.setNumDeck(numDeck);
            Random rnd = new Random();
            switch (rnd.nextInt(3)) {
			case 0:
				decks = new StandardDecks();
				break;
			case 1:
				decks = new RoundedDecks();
				break;
			case 2:
				decks = new TrapezeDecks();
				break;
			}
        }
	private void setLightsColor(Color lightsColor) {
		if (lightsColor != Color.black) this.lightsColor = lightsColor;
	}
	private void setPipe(boolean pipe) {
		this.pipe = pipe;
	}
	private void setLights(boolean lights) {
		this.lights = lights;
	}
	public Color getLightsColor() {
		return lightsColor;
	}
	public boolean getPipe() {
		return pipe;
	}
	public boolean getLights() {
		return lights;
	}
	public Decks getNumDeck() {
		return numDeck;
	}
	private void setNumDeck(Decks numDeck) {
		this.numDeck = numDeck;
	}
	@Override
	public void drawShip(Graphics g) {
		// pipe
		if (getPipe())
        {
        	g.setColor(Color.black);
            g.fillRect(startPosX - 20, startPosY - 25, 15, 50);
        }		
        // basement
        super.drawShip(g);
        decks.drawDecks(numDeck, g, Color.gray, super.dopColor, startPosX, startPosY);
        
        // lights
        if (getLights())
        {
        	g.setColor(lightsColor);
            g.fillOval(startPosX + 35, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 5, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 43, startPosY + 10, 5, 5);
        }
	}
	
}
