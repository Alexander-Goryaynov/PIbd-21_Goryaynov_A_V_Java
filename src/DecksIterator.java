import java.util.Iterator;

public class DecksIterator implements Iterator<Decks> {
	private Decks[] decks;
	private int curIndex = -1;
	public DecksIterator() {
		decks = Decks.values();
	}
	@Override
	public boolean hasNext() {
		if((curIndex + 1) >= decks.length) {
			curIndex = -1;
			return false;
		}
		return true;
	}
	@Override
	public Decks next() {
		curIndex++;
		return decks[curIndex];
	}
}
