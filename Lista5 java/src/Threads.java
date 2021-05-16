import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa tworząca wątki
 */
public class Threads implements Runnable{
	/**
	 * k - szybkosc dzialania programu
	 * p - prawdopodobienstwo
	 */
	private final double k, p;

	/**
	 * przycisk
	 */
	private final JButton button;

	/**
	 * pozycja danego przycisku - kordynaty
	 */
	private final int i,j;

	/**
	 * wymiary planszy
	 */
	private final int m,n;

	/**
	 * Konstruktor - ustawiamy wiekszosc zmiennych
	 * @param k - szybkosc zmiany koloru
	 * @param p - prawdopodobienstwo
	 * @param button - przycisk
	 * @param i - pozycja danego przycisku - wspolrzedna y
	 * @param j - pozycja danego przycisku - wspolrzedna x
	 * @param n - dlugosc planszy
	 * @param m - szerokosc planszy
	 */
	public Threads(double k, double p, JButton button , int i, int j, int n, int m) {
		this.k = k;
		this.p = p;

		this.i = i;
		this.j = j;

		this.n = n;
		this.m = m;

		this.button = button;

	}

	/**
	 * Metoda, w ktorej dokonuje sie zmiana koloru danego przycisku
	 */
	@Override
	public void run() {

		while (true) {

			Random random = new Random();

			if(random.nextDouble() < p) {
				button.setBackground(new Color(random.nextInt(256),random.nextInt(256), random.nextInt(256)));
			} else {
				button.setBackground(getAverageColor(i,j, button));
			}

			try {
				Thread.sleep( (long) (1/k * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metoda odpowiadajaca za znalezienie sredniego koloru
	 *
	 * @param i - wspolrzedna y danego przycisku
	 * @param j - wspolrzedna x danego przycisku
	 * @param button - przycisk
	 * @return - usredniony kolor
	 */
	private Color getAverageColor(int i, int j, JButton button) {
		int r = 0, g = 0, b = 0;
		List<Color> colors = new ArrayList<>();

		if(j == 0 && i == 0) {
			if(!MainBoard.getStopped_buttons()[i][j + 1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[i][m - 1])
				colors.add(MainBoard.getButtons()[i][m-1].getBackground());
			if(!MainBoard.getStopped_buttons()[n-1][j])
				colors.add(MainBoard.getButtons()[n-1][j].getBackground());


		} else if(j == 0 && i == n-1) {
			if(!MainBoard.getStopped_buttons()[i][j+1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[0][j])
				colors.add(MainBoard.getButtons()[0][j].getBackground());
			if(!MainBoard.getStopped_buttons()[n-1][m-1])
				colors.add(MainBoard.getButtons()[n-1][m-1].getBackground());

		} else if(j == m-1 && i == n-1) {
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[n-1][0])
				colors.add(MainBoard.getButtons()[n-1][0].getBackground());
			if(!MainBoard.getStopped_buttons()[0][m-1])
				colors.add(MainBoard.getButtons()[0][m-1].getBackground());

		} else if(j == m-1 && i == 0) {
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[i][0])
				colors.add(MainBoard.getButtons()[i][0].getBackground());
			if(!MainBoard.getStopped_buttons()[n-1][m-1])
				colors.add(MainBoard.getButtons()[n-1][m-1].getBackground());

		} else if(i > 0 && j > 0 && i < n - 1 && j < m - 1) {
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j+1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());

		} else if(i == 0 && j > 0 && j < m - 1) {
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j+1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[n-1][j])
				colors.add(MainBoard.getButtons()[n-1][j].getBackground());

		} else if(i == n - 1 && j > 0 && j < m - 1) {
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j+1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[0][j])
				colors.add(MainBoard.getButtons()[0][j].getBackground());

		} else if(j == 0 && i > 0 && i < n - 1) {
			if(!MainBoard.getStopped_buttons()[i][m-1])
				colors.add(MainBoard.getButtons()[i][m-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j+1])
				colors.add(MainBoard.getButtons()[i][j+1].getBackground());
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());

		} else if(j == m-1 && i > 0 && i < n - 1) {
			if(!MainBoard.getStopped_buttons()[i][0])
				colors.add(MainBoard.getButtons()[i][0].getBackground());
			if(!MainBoard.getStopped_buttons()[i][j-1])
				colors.add(MainBoard.getButtons()[i][j-1].getBackground());
			if(!MainBoard.getStopped_buttons()[i-1][j])
				colors.add(MainBoard.getButtons()[i-1][j].getBackground());
			if(!MainBoard.getStopped_buttons()[i+1][j])
				colors.add(MainBoard.getButtons()[i+1][j].getBackground());
		}

		if(colors.isEmpty()) {
			return button.getBackground();
		}

		for(Color color: colors) {
			r += color.getRed();
			g += color.getGreen();
			b += color.getBlue();
		}

		return new Color(r/colors.size(), g/colors.size(), b/colors.size());

	}
}
