import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MultiLevelPier {
	ArrayList<Pier<ITransport, IDecks>> pierStages;
	private final int countPlaces = 20;
	private int pictureWidth;
    private int pictureHeight;
	public MultiLevelPier(int countStages, int pictureWidth, int pictureHeight) {
		pierStages = new ArrayList<>();
		for (int i = 0; i < countStages; i++) {
			pierStages.add(new Pier<ITransport, IDecks>(countPlaces, pictureWidth, pictureHeight));
		}
		this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
	}
	public Pier<ITransport, IDecks> getPier(int index) {
		if ((index > -1) && (index < pierStages.size())) {
			return pierStages.get(index);
		}
		return null;
	}
	public ITransport getShip(int i, int j) throws PierNotFoundException {
		if((i > -1) && (i < pierStages.size())) {
			if((j > -1) && (j < pierStages.get(i).maxCount)) {
				ITransport ship = pierStages.get(i).getPlace(j);
				try {
					pierStages.get(i).minus(j);
					return ship;
				} catch (PierNotFoundException ex) {
					throw new PierNotFoundException(j);
				}
			}
		}
		return null;
	}
	public IDecks getDecks(int i, int j) {
		if(i > -1 && i < pierStages.size()) {
			if(j > -1 && j < pierStages.get(i).maxCount) {
				IDecks decks = pierStages.get(i).getPlacesDeck(j);
				pierStages.get(i).minusDecks(j);
				return decks;
			}
		}
		return null;
	}

	public boolean loadData(String fileName) throws IOException, PierOccupiedPlaceException {
		String buffer = "";
		int counter = -1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			buffer = br.readLine();
			if (buffer.split(":")[0].equals("CountLevels")) {
				int countLevel = Integer.parseInt(buffer.split(":")[1]);
				if (pierStages != null)
					pierStages.clear();
				pierStages = new ArrayList<>(countLevel);
			} else {
				br.close();
				return false;
			}

			while (br.ready()) {
				buffer = br.readLine();
				ITransport ship = null;
				IDecks decks = null;
				if (buffer.equals("Level")) {
					counter++;
					pierStages.add(new Pier<>(countPlaces, pictureWidth, pictureHeight));
					continue;
				} else if (buffer.split(":")[1].equals("Ship")) {
					ship = new Ship(buffer.split(":")[2]);
					pierStages.get(counter).plus(ship, Integer.parseInt(buffer.split(":")[0]));
				} else if (buffer.split(":")[1].equals("DieselShip")) {
					ship = new DieselShip(buffer.split(":")[3], buffer.split(":")[2]);
					if (buffer.split(":")[2].equals("StandardDecks"))
						decks = new StandardDecks();
					else if (buffer.split(":")[2].equals("RoundedDecks"))
						decks = new RoundedDecks();
					else if (buffer.split(":")[2].equals("TrapezeDecks"))
						decks = new TrapezeDecks();
					pierStages.get(counter).plus(ship, decks, Integer.parseInt(buffer.split(":")[0]));
				}
			}
			br.close();
		} catch (PierOccupiedPlaceException | IOException ex) {
			if (ex instanceof PierOccupiedPlaceException) {
				throw new PierOccupiedPlaceException(counter);
			} else throw new IOException();
		}
		return true;
	}
	public boolean loadLevelData(String fileName, int index) throws IOException, PierOccupiedPlaceException {
		String buffer = "";
		int counter = -1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			buffer = br.readLine();
			if (buffer.equals("SingleLevel")) {
				/*pierStages.set(index, new Pier<>(countPlaces, pictureWidth, pictureHeight));*/				
			} else {
				br.close();
				return false;
			}
			while (br.ready()) {
				buffer = br.readLine();
				ITransport ship = null;
				IDecks decks = null;
				if (buffer.split(":")[1].equals("Ship")) {
					ship = new Ship(buffer.split(":")[2]);
					pierStages.get(index).plus(ship, Integer.parseInt(buffer.split(":")[0]));					
				} else if (buffer.split(":")[1].equals("DieselShip")) {
					ship = new DieselShip(buffer.split(":")[3], buffer.split(":")[2]);
					if(buffer.split(":")[2].equals("StandardDecks")) decks = new StandardDecks();
					else if (buffer.split(":")[2].equals("RoundedDecks")) decks = new RoundedDecks();
					else if (buffer.split(":")[2].equals("TrapezeDecks")) decks = new TrapezeDecks();
					counter = Integer.parseInt(buffer.split(":")[0]);
					pierStages.get(index).plus(ship, decks, counter);					
				}
			}
			br.close();
		} catch (PierOccupiedPlaceException ex) {
			throw ex;			
		} catch (IOException ex) {
			throw ex;
		}
		return true;
	}
	public void saveData(String fileName) throws IOException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write("CountLevels:" + pierStages.size());
			bw.newLine();
			for (Pier<ITransport, IDecks> level : pierStages) {
				bw.write("Level");
				bw.newLine();
				for (int i = 0; i < countPlaces; i++) {
					ITransport ship = level.showPlace(i);
					if (ship != null) {
						if (!(ship instanceof DieselShip)) {
							bw.write(i + ":Ship:" + ship.getConfig());
							bw.newLine();
						} else {
							IDecks decks = level.getPlacesDeck(i);
							if (decks != null) {
								bw.write(i + ":DieselShip:" + decks.toString() + ":" + 
										ship.getConfig());
							} else {
								bw.write(i + ":DieselShip:" + "StandardDecks" + ":" +
										ship.getConfig());
							}
							bw.newLine();
						}
					}
				}
			}
			bw.close();
		}
		catch (IOException ex) {
			throw ex;
		}
		
	}
	public void saveLevelData(String fileName, int index) throws IOException {
		if (!((index > -1) && (index < pierStages.size()))) return;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write("SingleLevel");
			bw.newLine();
			Pier<ITransport, IDecks> level = pierStages.get(index);
			for (int i = 0; i < countPlaces; i++) {
				ITransport ship = level.showPlace(i);
				if (ship != null) {
					if (!(ship instanceof DieselShip)) {
						bw.write(i + ":Ship:" + ship.getConfig());
						bw.newLine();
					} else {
						IDecks decks = level.getPlacesDeck(i);
						if (decks != null) {
							bw.write(i + ":DieselShip:" + decks.toString() + ":" + 
									ship.getConfig());
						} else {
							bw.write(i + ":DieselShip:" + "StandardDecks" + ":" +
									ship.getConfig());
						}
						bw.newLine();
					}
				}
			}
			bw.close();
		} catch (IOException ex) {
			throw ex;
		}
	}
}
