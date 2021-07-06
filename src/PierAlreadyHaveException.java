
@SuppressWarnings("serial")
public class PierAlreadyHaveException extends Exception {
	public PierAlreadyHaveException() {
		super("На пристани уже есть такой корабль");
	}
}
