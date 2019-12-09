
@SuppressWarnings("serial")
public class PierNotFoundException extends NullPointerException {
	public PierNotFoundException(int i) {
		super("Не найден корабль по месту " + i);
	}
}
