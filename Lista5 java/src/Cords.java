/**
 * Klasa przetrzymujaca kordynaty, pomocna przy wylaczaniu watkow
 */
public class Cords {

	/**
	 * pierwsza wspolrzedna
	 */
	int x;

	/**
	 * druga wspolrzedna
	 */
	int y;

	/**
	 * Konstruktor, ustawianie parametrow
	 * @param x - wspolrzedna
	 * @param y - wpolrzedna
	 */
	public Cords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return - x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return - y
	 */
	public int getY() {
		return y;
	}
}
