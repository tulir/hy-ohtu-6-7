package statistics.matcher;

public class HasFewerThan extends Not {
	public HasFewerThan(int value, String category) {
		super(new HasAtLeast(value, category));
	}
}
