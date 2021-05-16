import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainBoard extends JFrame implements ActionListener {
	/**
	 * wymiary planszy
	 */
	private int n, m;

	/**
	 * k - szybkosc dzialania programu
	 * p - prawdopodobienstwo
	 */
	private double k, p;

	/**
	 * Panel na ktorym wyswietlamy plansze
	 */
	private JPanel mainPanel;

	/**
	 * Tablica ze wszystkimi przyciskami
	 */
	private static JButton[][] buttons;

	/**
	 * Tablica z przyciskami, ktore sa wylaczone
	 */
	private static boolean[][] stopped_buttons;

	/**
	 * Mapa wszystkich watkow uporzadkowana wedlug kordynatow,
	 * czyli ich miejsca na planszy
	 */
	private final Map<Cords, Thread> threadMap = new HashMap<>();

	/**
	 * Lista wszystkich mozliwych kordynatow
	 * Przyklady - (1, 1), (3,2) itd.. (od 0,0)
	 */
	List<Cords> cordsList = new ArrayList<>();

	/**
	 * Konstruktor w ktorym ustawiamy nasze zmienne
	 * I tworzymy plansze z kolorami
	 */
	public MainBoard() {

		try {
			String n = JOptionPane.showInputDialog("Podaj dlugosc planszy");

			this.n = Integer.parseInt(n);

			String m = JOptionPane.showInputDialog("Podaj szerokosc planszy");

			this.m = Integer.parseInt(m);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(this, "Prosze wprowadzic calkowite wymiary planszy!");

			return;
		}

		try {
			String k = JOptionPane.showInputDialog("Podaj szybkosc dzialania programu");

			this.k = Double.parseDouble(k);

			String p = JOptionPane.showInputDialog("Podaj prawdopodobienstwo zmiany koloru");

			this.p = Double.parseDouble(p);

			if(this.p > 1 || this.p <0) {
				throw new Exception("Prawdopodobienstwo musi byc w zakresie od 0 do 1!");
			}

		} catch (Throwable t) {
			JOptionPane.showMessageDialog(this, "Prosze wprowadzic zmiennoprzecinkowa szybkosc i prawdopodobienstwo!");
			return;
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(100*m, 100*n);
		this.setLayout(new BorderLayout());
		this.setTitle("Plansza");

		buttons = new JButton[n][m];
		stopped_buttons = new boolean[n][m];


		//ustawiamy zatrzymane przyciski domyslnie na falsz, czyli na poczatku wszystkie dzialaja
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				stopped_buttons[i][j] = false;
			}
		}

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(000, 000, 000));
		mainPanel.setLayout(new GridLayout(n, m));

		Random random = new Random();

		//tworzymy przyciski i nadajemy im losowe kolory
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFocusable(true);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 30));
				buttons[i][j].setFocusable(false);

				//buttons[i][j].setBackground(colors.get(random.nextInt(colors.size())));
				buttons[i][j].setBackground(new Color(random.nextInt(256),random.nextInt(256), random.nextInt(256)));

				mainPanel.add(buttons[i][j]);
			}
		}

		//tworzymy osobny watek dla kazdego przycisku
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Threads threads = new Threads(getRandomNumber(0.5*k, 1.5 * k), p, buttons[i][j], i, j, n, m);
				Thread thread = new Thread(threads);
				thread.start();

				cordsList.add(new Cords(i,j));

				threadMap.put(getCords(i, j), thread);
			}
		}

		this.add(mainPanel);
		this.setVisible(true);

	}

	/**
	 * Metoda w ktorej zatrzymujemy dzialanie watka
	 * @param e - wydarzenie klikniecia na przycisk
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		loops:
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (e.getSource() == buttons[i][j]) {
					stopped_buttons[i][j] = true;
					buttons[i][j].setText("X");

					threadMap.get(getCords(i, j)).suspend();

					break loops;
				}
			}
		}

	}

	/**
	 * Metoda pobierajca tablice przyciskow
	 * @return - tablica przyciskow
	 */
	public static JButton[][] getButtons() {
		return buttons;
	}

	/**
	 * Metoda pobierajca tablice wylaczonych przyciskow
	 * @return - tablica wylaczonych przyciskow
	 */
	public static boolean[][] getStopped_buttons() {
		return stopped_buttons;
	}

	/**
	 * Klasa pobierajaca losowy numer z danego zakresu
	 * @param min - poczatek przedzialu
	 * @param max - koniec przedzialu
	 * @return - losowy numer
	 */
	public double getRandomNumber(double min, double max) {
		Random random = new Random();
		return min + (random.nextDouble() * (max-min));
	}


	/**
	 * Metoda pobierajaca instancje klasy kordynaty o podanych wspolrzednych
	 * @param i - pierwsza wspolrzedna
	 * @param j - druga wspolrzedna
	 * @return - instancja klasy o kordynaty danych wspolrzednych
	 */
	public Cords getCords(int i, int j) {
		for(Cords cords: cordsList)
			if(cords.getX() == i && cords.getY() == j)
				return cords;
		return null;
	}
}
