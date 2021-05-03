import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiadajaca za zamykanie okna
 */
class MenuDemoWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) { System.exit(0); }
}

/**
 * Klasa służąca nam do stworzenia menu
 */
public class MenuDemo extends Frame implements ActionListener {

	/**
	 * caly ekran aplikacji
	 */
	Surface surface;

	/**
	 * Konstruktor - ustawiamy cale menu
	 */
	public MenuDemo() {
		MenuBar myMenu = new MenuBar();
		Menu menu1 = new Menu("Wybierz figure");
		Menu menu2 = new Menu("Zapisz/wczytaj");
		Menu menu3 = new Menu("Info");

		myMenu.add(menu1);
		myMenu.add(menu2);
		myMenu.add(menu3);

		surface = new Surface();
		add(surface);

		MenuItem prostokatItem = new MenuItem("Prostokat");
		prostokatItem.addActionListener(this);
		MenuItem okragItem = new MenuItem("Okrag");
		okragItem.addActionListener(this);
		MenuItem trojkatItem = new MenuItem("Trojkat");
		trojkatItem.addActionListener(this);

		MenuItem saveItem = new MenuItem("Zapisz");
		saveItem.addActionListener(this);
		MenuItem loadItem = new MenuItem("Wczytaj");
		loadItem.addActionListener(this);

		MenuItem infoItem = new MenuItem("Zobacz informacje");
		infoItem.addActionListener(this);
		MenuItem userManual = new MenuItem("Instrukcja uzytkowania");
		userManual.addActionListener(this);
		MenuItem exit = new MenuItem("Zakoncz");
		exit.addActionListener(this);

		menu1.add(prostokatItem);
		menu1.add(okragItem);
		menu1.add(trojkatItem);

		menu2.add(saveItem);
		menu2.add(loadItem);

		menu3.add(infoItem);
		menu3.add(userManual);
		menu3.addSeparator();
		menu3.add(exit);

		setLayout(new GridLayout(1,1));
		setMenuBar(myMenu);

		addWindowListener(new MenuDemoWindowAdapter());
	}

	/**
	 * Metoda odpowiadajaca za interakcje z menu
	 * @param e - wywołana akcja
	 */
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Zakoncz":
				System.exit(0);
				break;
			case "Prostokat": {

				Surface.ZRectangle zrect = new Surface.ZRectangle();
				zrect.setFrameFromDiagonal((new Point2D.Double(50, 50)), new Point2D.Double(150, 100));
				Point2D[] points = new Point2D[2];
				points[0] = new Point2D.Double(50, 50);
				points[1] = new Point2D.Double(150, 100);
				FigureManagement figure = new FigureManagement(zrect, points);
				figure.setColor(Color.DARK_GRAY);

				surface.repaint();
				break;
			}
			case "Okrag": {
				Surface.ZEllipse zell = new Surface.ZEllipse(10, 10, 30, 30);

				FigureManagement figureManagement = new FigureManagement(zell);
				figureManagement.setColor(Color.DARK_GRAY);

				surface.repaint();
				break;
			}
			case "Trojkat": {
				Surface.PolyTest poly = new Surface.PolyTest(new int[]{10, 20, 30}, new int[]{100, 20, 100}, 3);

				Point2D[] points = new Point2D[3];
				points[0] = new Point2D.Double(10, 100);
				points[1] = new Point2D.Double(20, 20);
				points[2] = new Point2D.Double(30, 100);

				FigureManagement figureManagement = new FigureManagement(poly, points);

				figureManagement.setColor(Color.DARK_GRAY);

				surface.repaint();

				break;
			}
			case "Zapisz": {

				String fileName = JOptionPane.showInputDialog("Wprowadz nazwe pliku");
				if (fileName == null) {
					JOptionPane.showMessageDialog(surface, "Wprowadz poprawna nazwe pliku!");
					return;
				}

				writeObjectToFile(fileName);

				JOptionPane.showMessageDialog(surface, "Poprawnie zapisano figury.");
				break;
			}
			case "Wczytaj": {
				String fileName = JOptionPane.showInputDialog("Wprowadz nazwe pliku");

				if (fileName == null) {
					JOptionPane.showMessageDialog(surface, "Wprowadz poprawna nazwe pliku!");
					return;
				}

				List<FigureManagement> figures = readObjectFromFile(fileName);
				if (figures == null) {
					JOptionPane.showMessageDialog(surface, "Nie ma takiego pliku!");
					return;
				}

				FigureManagement.setFigures(figures);
				surface.repaint();
				JOptionPane.showMessageDialog(surface, "Poprawnie wczytano figury.");

				break;
			}
			case "Zobacz informacje": {

				String msg = "<html>Informacje:" +
						"<ul><li><b>Nazwa:</b> Figure Paint "
						+ "<li><b>Przeznaczenie: </b>Program mozna uzywac do rysowania prostych <br>"
						+ "figur geometrycznych"
						+ "<li><b>Autor: </b>Mikołaj Dyblik</ul></html>";
				JLabel label = new JLabel(msg);
				label.setFont(new Font("serif", Font.PLAIN, 15));
				JOptionPane.showMessageDialog(surface, label);

				break;
			}
			case "Instrukcja uzytkowania": {

				String msg = "<html>Instrukcja: " +
						"<ul><b>Tworzenie nowej figury:</b>"
						+ "<li>Wybieramy z menu \"Wybierz figure\" figure, ktora chcemy narysowac <br>"
						+ "<li>Dopasowujemy ja do naszych potrzeb - zwiekszamy, przesuwamy, zmieniamy kolor <br>"
						+ "figur geometrycznych <br><br>"
						+ "<b>Zapisywanie: </b>"
						+ "<li>Wybieramy z menu \"Zapisz/wczytaj \" opcje zapisz<br>"
						+ "<li>Wprowadzamy nazwe pliku (bez.txt)<br>"
						+ "<li>Jezeli wyswietli sie komunikat o sukcesie, to wszystko poszlo pomyslnie<br><br>"
						+ "<b>Wczytywanie: </b>"
						+ "<li>Wybieramy z menu \"Zapisz/wczytaj \" opcje wczytaj<br>"
						+ "<li>Wprowadzamy nazwe pliku (bez .txt)<br>"
						+ "<li>Jezeli plik istnieje, to wyswietli sie komunikat o sukcesie i figury zostana wczytane<br><br>"
						+ "<b>Konczenie pracy programu: </b>"
						+ "<li>Otwieramy menu \"Info\"<br>"
						+ "<li>Wybieramy opcje zakoncz<br>"
						+ "</ul></html>";
				JLabel label = new JLabel(msg);
				label.setFont(new Font("serif", Font.PLAIN, 17));
				JOptionPane.showMessageDialog(surface, label);
				break;
			}
		}
	}

	/**
	 *
	 * @param fileName - nazwa pliku
	 */
	public void writeObjectToFile(String fileName) {

		try {

			FileOutputStream fileOut = new FileOutputStream(fileName + ".txt");

			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for(FigureManagement figures: FigureManagement.getFigures()) {
				objectOut.writeObject(figures);
			}

			objectOut.close();
			fileOut.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *
	 * @param fileName - nazwa pliku
	 * @return - lista figur
	 */
	public List<FigureManagement> readObjectFromFile(String fileName) {
		List<FigureManagement> figures = new ArrayList<>();

		try {
			FileInputStream fi = new FileInputStream( fileName + ".txt");
			ObjectInputStream oi = new ObjectInputStream(fi);

			while(true) {
				figures.add((FigureManagement) oi.readObject());
			}

		} catch	(FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku!");
			return null;
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return figures;
	}
}