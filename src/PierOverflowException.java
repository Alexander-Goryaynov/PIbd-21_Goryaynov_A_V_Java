
@SuppressWarnings("serial")
public class PierOverflowException extends IndexOutOfBoundsException {
	public PierOverflowException() {
		super("На пристани нет свободных мест");
	}
}
