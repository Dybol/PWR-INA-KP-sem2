
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

/**
 * Klasa odpowiadajaca za rozne modyfikacje zwiazane z figurami
 */
class Surface extends JPanel {


    /**
     * Rozmiar małych kwadracików w trójkącie i kwadracie, które służą do rozciągania / zmieniania kształtu figury
     */
    private final int SIZE = 10;

    /**
     * Menu wyswietlajace sie po kliknieciu na figure prawym przyciskiej
     * Slużące do zmiany kolorów
     */
    public JPopupMenu popup;

    /**
     * Zmienna pomocnicza sluzaca do przetrzymywania figury, ktorej kolor ma zostac zmieniony
     */
    private FigureManagement figureToChangeColor;


    /**
     * Konstruktor, see {@link #initUI()}
     */
    public Surface() {
        initUI();
    }

    /**
     * Metoda w ktorej inicjujemy zmienne, dodajemy listerenerow itp.
     */
    private void initUI() {

        ShapeTestAdapter ma = new ShapeTestAdapter();

        //tworzenie popup menu pod prawym przyciskiem menu - na kolor
        //--------------------------------
        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Color color;

                switch (event.getActionCommand()) {

                    case "Niebieski":
                        color = Color.BLUE;
                        break;
                    case "Czerwony":
                        color = Color.RED;
                        break;
                    case "Zielony":
                        color = Color.GREEN;
                        break;
                    case "Zolty":
                        color = Color.YELLOW;
                        break;
                    case "Czarny":
                        color = Color.BLACK;
                        break;
                    case "Bialy":
                        color = Color.WHITE;
                        break;
                    default:
                        color = Color.GRAY;
                        break;
                }

                if(figureToChangeColor != null) {
                    figureToChangeColor.setColor(color);
                    figureToChangeColor = null;
                }
            }
        };

        //-----------------------------------------------------------
        //kolory
        popup = new JPopupMenu();
        JMenuItem item;
        popup.add(item = new JMenuItem("Zmien kolor"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);
        popup.addSeparator();

        popup.add(item = new JMenuItem("Zielony"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);

        popup.add(item = new JMenuItem("Czerwony"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);

        popup.add(item = new JMenuItem("Niebieski"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);

        popup.add(item = new JMenuItem("Zolty"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);

        popup.add(item = new JMenuItem("Czarny"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);

        popup.add(item = new JMenuItem("Bialy"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        item.addActionListener(menuListener);
        //---------------------------------------------------------

        addMouseListener(ma);
        addMouseMotionListener(ma);

        addMouseWheelListener(new ScaleHandler());
    }

    /**
     * Metoda sluzaca do malowania figur, see {@link #paintComponent(Graphics)}
     * @param g - grafika
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    /**
     * Metoda odpowiadajaca za rysowanie figur
     * @param g - grafika
     */
    private void doDrawing(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;

        for(FigureManagement figureManagement: FigureManagement.getFigures()) {
            if(figureManagement.getShape() instanceof ZRectangle) {

                for (Point2D point : figureManagement.getPoints()) {
                    double x = point.getX() - SIZE / 2;
                    double y = point.getY() - SIZE / 2;
                    g2.setColor(Color.DARK_GRAY);
                    g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
                }

                ZRectangle zrect = (ZRectangle) figureManagement.getShape();

                //ustawiamy prostokat z punktow po przekatnej
                zrect.setFrameFromDiagonal(figureManagement.getPoints()[0], figureManagement.getPoints()[1]);

                if(figureManagement.getColor() != null)
                    g.setColor(figureManagement.getColor());

                ((Graphics2D) g).fill(zrect);
                repaint();

            } else if (figureManagement.getShape() instanceof ZEllipse) {

                ZEllipse zell = (ZEllipse) figureManagement.getShape();

                if(figureManagement.getColor() != null)
                    g.setColor(figureManagement.getColor());

                ((Graphics2D) g).fill(zell);
                repaint();

            } else if (figureManagement.getShape() instanceof PolyTest) {
                PolyTest triangle = (PolyTest) figureManagement.getShape();

                for (Point2D point : figureManagement.getPoints()) {
                    double x = point.getX() - SIZE / 2;
                    double y = point.getY() - SIZE / 2;
                    g2.setColor(Color.DARK_GRAY);
                    g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
                }

                if(figureManagement.getColor() != null)
                    g.setColor(figureManagement.getColor());

                ((Graphics2D) g).fill(triangle);
                repaint();
            }

        }
    }

    /**
     * Klasa obslugujaca zdarzenia zwiazane z myszka
     */
    private class ShapeTestAdapter extends MouseAdapter {

        /**
         * wspolrzedna x
         */
        private int x;
        /**
         * wspolrzedna y
         */
        private int y;

        /**
         * metoda wywolujaca sie po nacisnieciu dowolnego przycisku myszki
         * @param event - klik myszki
         */
        @Override
        public void mousePressed(MouseEvent event) {

            this.x = event.getX();
            this.y = event.getY();

            //otwieranie menu z kolorami
            if(event.getButton() == 3) {
                for(FigureManagement figureManagement: FigureManagement.getFigures()) {

                    if(figureManagement.getShape() != null && (figureManagement.getShape().contains(x,y) || figureManagement.getShape().getBounds2D().contains(x,y))) {
                        popup.show(Surface.this, event.getX(), event.getY());
                        figureToChangeColor = figureManagement;
                    }
                }
            }

            Point p = event.getPoint();

            for(FigureManagement figureManagement: FigureManagement.getFigures()) {

                if(figureManagement.getPoints() != null && figureManagement.getShape() instanceof ZRectangle) {
                    Point2D[] points = figureManagement.getPoints();

                    for (int i = 0; i < points.length; i++) {

                        // Pozyskiwanie wspolrzednych wierzcholkow
                        // malych kwadratow
                        double xx = points[i].getX() - SIZE / 2;
                        double yy = points[i].getY() - SIZE / 2;

                        Rectangle2D smallRect = new Rectangle2D.Double(xx, yy, SIZE, SIZE);


                        // Jesli punkt nasisniecia myszy zawiera sie w kwadracie malym
                        // to zwracany jest jego numer w tablicy i ustawiamy jego "pozycje"
                        // w figureManagement. Sluzy to nam potem do rozciagania / zwezania
                        // figury
                        if (smallRect.contains(p)) {
                            figureManagement.setPose(i);
                            return;
                        }
                    }

                } else if (figureManagement.getPoints() != null && figureManagement.getShape() instanceof PolyTest) {
                    Point2D[] points = figureManagement.getPoints();

                    for(int i = 0; i < points.length; i++) {
                        // Pozyskiwanie wspolrzednych wierzcholkow
                        // malych kwadratow
                        double xx = points[i].getX() - SIZE / 2;
                        double yy = points[i].getY() - SIZE / 2;

                        Rectangle2D smallRect = new Rectangle2D.Double(xx, yy, SIZE, SIZE);
                        repaint();

                        // Jesli punkt nasisniecia myszy zawiera sie w kwadracie malym
                        // to zwracany jest jego numer w tablicy i ustawiamy jego "pozycje"
                        // w figureManagement. Sluzy to nam potem do rozciagania / zwezania
                        // figury
                        if(smallRect.contains(p)) {
                            figureManagement.setPose(i);
                            return;
                        }
                    }
                }
            }
        }

        /**
         * Metoda wywolywana po puszczeniu myszki, sluzaca do
         * ustawiania "pozycji" na domyslna wartosc
         * @param event - wydarzenie puszczania myszki
         */
        @Override
        public void mouseReleased(MouseEvent event) {
            for(FigureManagement figureManagement : FigureManagement.getFigures()) {
                figureManagement.setPose(-1);
            }
        }

        /**
         * Metoda wywolywana podczas przeciagania myszka
         * Sluzy ona do rozciagania lub przesuwania (see {@link #doMove(MouseEvent)}) naszych figur
         * @param event - wydarzenie przeciagania myszki
         */
        @Override
        public void mouseDragged(MouseEvent event) {
            for(FigureManagement figureManagement : FigureManagement.getFigures()) {
                //jezeli "pozycja" jest ustawiona na -1 to znaczy, ze nie dotknelismy zadnego malego kwadratu
                //wiec przesuwamy po prostu cala figure
                if(figureManagement.getPose() == -1) {
                    doMove(event);
                    continue;
                }

                if(figureManagement.getPoints() != null && figureManagement.getShape() instanceof ZRectangle) {
                    figureManagement.getPoints()[figureManagement.getPose()] = event.getPoint();
                    repaint();

                } else if(figureManagement.getPoints() != null && figureManagement.getShape() instanceof PolyTest) {

                    figureManagement.getPoints()[figureManagement.getPose()] = event.getPoint();

                    PolyTest triangle = (PolyTest) figureManagement.getShape();

                    Point2D[] points = figureManagement.getPoints();

                    triangle.reset();

                    for(int i = 0; i < 3; i++)
                        triangle.addPoint((int) points[i].getX(), (int) points[i].getY());

                    figureManagement.setShape(triangle);

                    repaint();
                }
            }
        }

        /**
         * Metoda odpowiadajaca za przesuwanie figur
         * @param e - wydarzenie zwiazane z myszka
         */
        private void doMove(MouseEvent e) {

            int dx = e.getX() - x;
            int dy = e.getY() - y;

            for(FigureManagement figureManagement: FigureManagement.getFigures()) {
                if(figureManagement.getShape() instanceof ZRectangle) {
                    ZRectangle zrect = (ZRectangle) figureManagement.getShape();

                    if(zrect.isHit(x,y)) {
                        Point2D[] point2D = figureManagement.getPoints();

                        point2D[0].setLocation(point2D[0].getX() + dx, point2D[0].getY() + dy);
                        point2D[1].setLocation(point2D[1].getX() + dx, point2D[1].getY() + dy);
                        zrect.addX(dx);
                        zrect.addY(dy);

                        figureManagement.setPoints(point2D);
                    }

                } else if(figureManagement.getShape() instanceof ZEllipse) {
                    ZEllipse zell = (ZEllipse) figureManagement.getShape();

                    if(zell.isHit(x,y)) {
                        zell.addX(dx);
                        zell.addY(dy);
                    }

                } else if(figureManagement.getShape() instanceof PolyTest) {
                    PolyTest triangle = (PolyTest) figureManagement.getShape();

                    if(triangle.contains(x,y)) {
                        Point2D[] point2D = figureManagement.getPoints();

                        point2D[0].setLocation(point2D[0].getX() + dx, point2D[0].getY() + dy);
                        point2D[1].setLocation(point2D[1].getX() + dx, point2D[1].getY() + dy);
                        point2D[2].setLocation(point2D[2].getX() + dx, point2D[2].getY() + dy);

                        for(int i = 0; i < triangle.npoints; i++) {
                            triangle.xpoints[i] = triangle.xpoints[i] + dx;
                            triangle.ypoints[i] = triangle.ypoints[i] + dy;
                        }

                        int[] xpoints = triangle.xpoints;
                        int[] ypoints = triangle.ypoints;

                        //resetujemy, zeby odswiezylo wspolrzedne trojkata!
                        triangle.reset();

                        for(int i = 0; i < 3; i++)
                            triangle.addPoint(xpoints[i], ypoints[i]);

                        figureManagement.setShape(triangle);

                    }
                }
            }
            x += dx;
            y += dy;
        }
    }

    /**
     * Klasa zwiazana z prostokatem
     */
    static class ZRectangle extends Rectangle2D.Double {
        public ZRectangle() {
            super();
        }

        public ZRectangle(double x, double y, double width, double height) {
            setRect(x, y, width, height);
        }

        public boolean isHit(double x, double y) {
            return getBounds2D().contains(x, y);
        }

        // Zmiana wspolrzednej x prostakata
        public void addX(double x) {
            this.x += x;
        }

        // Zmiana wspolrzednej y prostakata
        public void addY(double y) {
            this.y += y;
        }

        // Zmiana szerokosci prostokata
        public void addWidth(double w) {
            this.width += w;
        }

        // Zmiana wysokosci prostokata
        public void addHeight(double h) {
            this.height += h;
        }
    }

    /**
     * Klasa zwiazana z okregiem
     */
    static class ZEllipse extends Ellipse2D.Float {

        public ZEllipse(float x, float y, float width, float height) {
            setFrame(x, y, width, height);
        }

        /// Metoda sprawdza czy najechalismy na figure
        public boolean isHit(float x, float y) {
            return getBounds2D().contains(x, y);
        }

        // Zmiana wspolrzednej x elipsy
        public void addX(float x) {
            this.x += x;
        }

        // Zmiana wspolrzednej y elipsy
        public void addY(float y) {
            this.y += y;
        }

        // Zmiana szerokosci elipsy
        public void addWidth(float w) {
            this.width += w;
        }

        // Zmiana wysokosci elipsy
        public void addHeight(float h) {
            this.height += h;
        }
    }

    /**
     * Klasa zwiazana z trojaktem
     */
    static class PolyTest extends Polygon {

        public PolyTest(int[] xpoints, int[] ypoints, int n) {
            this.xpoints = xpoints;
            this.ypoints = ypoints;
            this.npoints = n;
        }

        public boolean isHit(float x, float y) {
            return getBounds2D().contains(x,y);
        }
    }

    /**
     * Klasa obslugujaca zdarzenia ze scrollem
     */
    class ScaleHandler implements MouseWheelListener {

        /**
         * Metoda wywolywana podczas przesuwania scrolla
         * Wywolujemy {@link #doScale(MouseWheelEvent)}
         * @param e - wydarzenie zwiazane ze scrollem
         *
         */
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            doScale(e);
        }

        /**
         * Metoda odpowiadajaca za skalowanei figur
         * @param e - wydarzenie zwiazane ze scrollem
         */
        private void doScale(MouseWheelEvent e) {

            int x = e.getX();
            int y = e.getY();

            // Jesli krecimy kolkiem myszy to zmiania sie szerokosc i wysokosc
            // figury
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                for(FigureManagement figureManagement: FigureManagement.getFigures()) {
                    float amount = e.getWheelRotation() * -5f;

                    if(figureManagement.getShape() instanceof ZRectangle) {
                        Point2D[] points = figureManagement.getPoints();
                        ZRectangle zrect = (ZRectangle) figureManagement.getShape();

                        if (zrect.isHit(x, y)) {
                            double point0X = points[0].getX();
                            double point0Y = points[0].getY();

                            double point1X = points[1].getX();
                            double point1Y = points[1].getY();
                            if (point1X > point0X && point1Y > point0Y) {
                                points[0].setLocation(points[0].getX() - amount, points[0].getY() - amount);
                                points[1].setLocation(points[1].getX() + amount, points[1].getY() + amount);
                            } else if (point1X > point0X && point0Y > point1Y) {
                                points[0].setLocation(points[0].getX() - amount, points[0].getY() + amount);
                                points[1].setLocation(points[1].getX() + amount, points[1].getY() - amount);
                            } else if (point1X < point0X && point0Y < point1Y) {
                                points[0].setLocation(points[0].getX() + amount, points[0].getY() - amount);
                                points[1].setLocation(points[1].getX() - amount, points[1].getY() + amount);
                            } else {
                                points[0].setLocation(points[0].getX() + amount, points[0].getY() + amount);
                                points[1].setLocation(points[1].getX() - amount, points[1].getY() - amount);
                            }
                            repaint();
                        }
                    } else if (figureManagement.getShape() instanceof ZEllipse) {
                        ZEllipse zell = (ZEllipse) figureManagement.getShape();

                        if(zell.isHit(x,y)) {
                            zell.addWidth(amount);
                            zell.addHeight(amount);
                        }
                    }
                }
            }
        }
    }
}

