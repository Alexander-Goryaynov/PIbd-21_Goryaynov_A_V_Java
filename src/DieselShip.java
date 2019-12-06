import java.awt.Color;
import java.awt.Graphics;

public class DieselShip extends Ship {
	private Decks numDeck;
	private Color lightsColor;
	private boolean pipe;
	private boolean lights;
	private IDecks decks;
	public DieselShip (int maxSpeed, int weight, Decks numDeck, Color mainColor, 
            Color dopColor, Color lightsColor, boolean pipe, boolean lights) {
		super(maxSpeed, weight, mainColor, dopColor);
        this.setLightsColor(lightsColor);
        this.setPipe(pipe);
        this.setLights(lights);
        this.setNumDeck(numDeck);
        decks = new StandardDecks();
    }
	public DieselShip(String config, String decksTypeConfig) {
		super(config);
		String[] params = config.split(";");
		if (params.length == 7) {
			maxSpeed = Integer.parseInt(params[0]);
    		weight = Integer.parseInt(params[1]);
    		mainColor = new Color(Integer.parseInt(params[2]));
    		dopColor = new Color(Integer.parseInt(params[3]));    		
    		lightsColor = new Color(Integer.parseInt(params[4]));
    		pipe = Boolean.parseBoolean(params[5]);
    		lights = Boolean.parseBoolean(params[6]);
    		numDeck = Decks.Three;
    		switch (decksTypeConfig) {
			case "StandardDecks":
				decks = new StandardDecks();
				break;
			case "RoundedDecks":
				decks = new RoundedDecks();
				break;
			case "TrapezeDecks":
				decks = new TrapezeDecks();
			default:
				decks = new StandardDecks();
				break;
			}
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
		if (getPipe()) {
        	g.setColor(Color.black);
            g.fillRect(startPosX - 20, startPosY - 25, 15, 50);
        }		
        // basement
        super.drawBasement(g);
        decks.drawDecks(numDeck, g, Color.gray, super.dopColor, startPosX, startPosY);
        
        // lights
        if (getLights()) {
        	g.setColor(lightsColor);
            g.fillOval(startPosX + 35, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 5, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 43, startPosY + 10, 5, 5);
        }
	}
	public void drawPipe(Graphics g) {
		if(getPipe()) {
			g.setColor(Color.black);
			g.fillRect(startPosX - 20, startPosY - 25, 15, 5);
		}
	}
	public void drawLights(Graphics g) {
		if (getLights()) {
			g.setColor(lightsColor);
            g.fillOval(startPosX + 35, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 5, startPosY + 10, 5, 5);
            g.fillOval(startPosX - 43, startPosY + 10, 5, 5);
		}
	}
	@Override
	public String getConfig() {
		return (super.getConfig() + ";" + dopColor.getRGB() + ";" + lightsColor.getRGB() +
				";" + pipe + ";" + lights); 
	}
}
