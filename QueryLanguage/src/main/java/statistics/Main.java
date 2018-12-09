package statistics;

import statistics.matcher.*;

public class Main {
	public static void main(String[] args) {
		Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

		Matcher m = new And(new HasAtLeast(10, "goals"),
				new HasAtLeast(10, "assists"),
				new PlaysIn("PHI")
		);

		for (Player player : stats.matches(m)) {
			System.out.println(player);
		}
		System.out.println("----");
		Matcher m2 = new And(new HasFewerThan(1, "goals"));

		for (Player player : stats.matches(m2)) {
			System.out.println(player);
		}
		System.out.println("----");

		QueryBuilder query = new QueryBuilder();
		Matcher m3 = query.oneOf(
				query.playsIn("PHI")
						.hasAtLeast(10, "goals")
						.hasFewerThan(20, "assists").build(),

				query.playsIn("EDM")
						.hasAtLeast(60, "points").build()
		).build();
		for (Player player : stats.matches(m3)) {
			System.out.println(player);
		}
	}
}
