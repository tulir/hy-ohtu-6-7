package laskin;

public class Sovelluslogiikka {
	private int sum;
	private int prevDiff = 0;

	public void plus(int luku) {
		sum += luku;
		prevDiff = luku;
	}

	public void miinus(int luku) {
		sum -= luku;
		prevDiff = -luku;
	}

	public void nollaa(int luku) {
		nollaa();
	}

	public void nollaa() {
		prevDiff = -sum;
		sum = 0;
	}

	public void peru(int luku) {
		peru();
	}

	public void peru() {
		sum -= prevDiff;
		prevDiff =- prevDiff;
	}

	public int tulos() {
		return sum;
	}
}