import java.util.ArrayList;

public class MultiLevelPier {
	ArrayList<Pier<ITransport, IDecks>> pierStages;
	private final int countPlaces = 20;
	public MultiLevelPier(int countStages, int pictureWidth, int pictureHeight) {
		pierStages = new ArrayList<>();
		for (int i = 0; i < countStages; i++) {
			pierStages.add(new Pier<ITransport, IDecks>(countPlaces, pictureWidth, pictureHeight));
		}
	}
	public Pier<ITransport, IDecks> getPier(int index){

		if ((index > -1) && (index < pierStages.size()))
		{
			return pierStages.get(index);
		}
		return null;
	}
	public ITransport getShip(int i, int j) {
		if((i > -1) && (i < pierStages.size())) {
			if((j > -1) && (j < pierStages.get(i).maxCount)) {
				ITransport ship = pierStages.get(i).getPlace(j);
				pierStages.get(i).minus(j);
				return ship;
			}
		}
		return null;
	}
	public IDecks getDecks(int i, int j) {
		if(i > -1 && i < pierStages.size()) {
			if(j > -1 && j < pierStages.get(i).maxCount) {
				IDecks decks = pierStages.get(i).getPlacesDeck(j);
				pierStages.get(i).minus(j);
				return decks;
			}
		}
		return null;
	}
}
