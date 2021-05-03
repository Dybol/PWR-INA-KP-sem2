package zadanie2;

import javax.swing.*;
import java.awt.*;

public class TrojkatPascala extends JFrame {

	JPanel mainPanel;
	JPanel titlePanel;
	JLabel titleLabel;
	JLabel contentLabel;

	public TrojkatPascala() {

		int nr_wiersza;
		final String letter = JOptionPane.showInputDialog("Podaj numer wiersza");
		try {
			nr_wiersza = Integer.parseInt(letter);
		} catch(NumberFormatException ex) {
			System.out.println("Wprowadz odpowiednia liczbe!");
			return;
		}
		if(nr_wiersza <= 0) {
			JOptionPane.showMessageDialog(this,"Nr wiersza musi byc wiekszy od 0!");
			return;
		}

		titleLabel = new JLabel();
		titleLabel.setText("Wyswietlanie trojkata pascala dla " + letter + " pierwszych wierszy");
		titleLabel.setForeground(new Color(10, 200, 100)); //kolor tekstu
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 35)); //czcionka, rozmiar
		titleLabel.setVerticalAlignment(JLabel.CENTER); //ustawia  pionowe wyrownanie do gory
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //ustawia poziome wyrownanie

		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(50, 50, 50));
		titlePanel.setPreferredSize(new Dimension(900, 80));
		titlePanel.add(titleLabel);


		StringBuilder s = new StringBuilder("<html>");
		for(int i = 0; i < nr_wiersza; i++) {
			s.append("<center>");
			for(int j: Kalkulacje.obliczWiersz(i)) {
				s.append(j).append("  ");
			}
			s.append("</center><br>");
		}
		s.append("</html>");

		contentLabel = new JLabel(s.toString());
		//contentLabel.setText("dadada\n3eeee");
		contentLabel.setForeground(new Color(20, 20, 20));
		contentLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 300/nr_wiersza));
		contentLabel.setHorizontalAlignment(JLabel.CENTER); //ustawia poziome wyrownanie
		contentLabel.setVerticalAlignment(JLabel.TOP); //ustawia  pionowe wyrownanie do gory

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(100, 100, 100));
		mainPanel.setPreferredSize(new Dimension(200, 1000));



		mainPanel.add(contentLabel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setLayout(new BorderLayout());
		this.setTitle("TrojkatPascala");

		this.add(titleLabel, BorderLayout.NORTH);
		this.add(contentLabel, BorderLayout.CENTER);

		//this.add(area);
		this.setVisible(true);
	}

}
