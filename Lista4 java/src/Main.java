import java.awt.*;

/**
 * Klasa glowna
 */
public class Main {

	/**
	 * Metoda sluzaca do odpalania programu
	 * @param args - argumenty
	 */
	public static void main(String[] args) {

		Frame f = new MenuDemo();
		f.setBounds(100,100,640,480);
		f.setTitle("Figury");
		f.setVisible(true);
	}
}
