
@SuppressWarnings("serial")
public class PierOccupiedPlaceException extends Exception {
	public PierOccupiedPlaceException(int i) {
		super("На месте " + i + " уже стоит корабль");
	}
}
