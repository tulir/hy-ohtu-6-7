package statistics.matcher;

public class QueryBuilder {
	private Matcher matcher = new All();

	public QueryBuilder playsIn(String team) {
		matcher = new And(new PlaysIn(team), matcher);
		return this;
	}

	public QueryBuilder hasAtLeast(int count, String type) {
		matcher = new And(new HasAtLeast(count, type), matcher);
		return this;
	}

	public QueryBuilder hasFewerThan(int count, String type) {
		matcher = new And(new HasFewerThan(count, type), matcher);
		return this;
	}

	public QueryBuilder not(Matcher m) {
		matcher = new And(new Not(m), matcher);
		return this;
	}

	public QueryBuilder oneOf(Matcher... matchers) {
		matcher = new And(new Or(matchers), matcher);
		return this;
	}

	public QueryBuilder allOf(Matcher... matchers) {
		matcher = new And(new And(matchers), matcher);
		return this;
	}

	public Matcher build() {
		Matcher rtrn = matcher;
		matcher = new All();
		return rtrn;
	}
}
