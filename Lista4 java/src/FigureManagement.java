import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiadaja za rejestr figur
 */
public class FigureManagement implements Serializable {

	/**
	 * Lista figur aktualnie widocznych na ekranie
	 */
	private static List<FigureManagement> figures = new ArrayList<>();


	/**
	 * Ksztalt figury - prostokat / trojkat / okrag
	 */
	private Shape shape;

	/**
	 * Punkty potrzebne do rysowania prostokata
	 */
	private Point2D[] points = null;

	/**
	 * Pozycja potrzebna do rozciagania figury
	 */
	Integer pose = -1;

	/**
	 * Kolor
	 */
	Color color;

	/**
	 * Konstruktor - dodawanie figur do rejestru.
	 * @param shape - ksztalt
	 */
	public FigureManagement(Shape shape) {
		this.shape = shape;

		figures.add(this);
	}

	/**
	 * @param shape - ksztalt
	 * @param points - punkty (potrzebne dla prostokata)
	 */
	public FigureManagement(Shape shape, Point2D[] points) {
		this(shape);
		this.points = points;
	}

	/**
	 * Pobiera kolor figury
	 * @return - Kolor danej figury
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Ustawia kolor figury
	 * @param color - Kolor
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Pobiera ksztalt danej figury
	 * @return - Ksztalt
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * Ustawia ksztalt danej figury
	 * @param shape - ksztalt
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Pobiera punkty danej figury (tylko w przypadku kwadratu, inaczej null)
	 * @return - punkty
	 */
	public Point2D[] getPoints() {
		return points;
	}

	/**
	 * Ustawia punkty danej figury
	 * @param points - punkty
	 */
	public void setPoints(Point2D[] points) {
		this.points = points;
	}

	/**
	 * Pobiega zmienna pomocnicza pozycji - do przesuwania figur
	 * @return - pozycja
	 */
	public Integer getPose() {
		return pose;
	}

	/**
	 * Ustawia zmienna pomocnicza pozycji - do przesuwania figur
	 * @param pose - pozycja
	 */
	public void setPose(Integer pose) {
		this.pose = pose;
	}

	/**
	 * Pobiera wszystkie figury z rejestru
	 * @return - figury
	 */
	public static List<FigureManagement> getFigures() {
		return figures;
	}

	/**
	 * Ustawia figury na nowo
	 * Metoda wywoływana podczas wczytywania z pliku, see {@link MenuDemo#readObjectFromFile(String)}
	 * @param newFigures - lista nowych figur
	 */
	public static void setFigures(List<FigureManagement> newFigures) {
		figures.clear();
		figures = newFigures;
	}
}
