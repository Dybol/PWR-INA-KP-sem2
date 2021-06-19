import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SocketClient extends Frame implements ActionListener {
	/**
	 * output
	 */
	Label output;
	/**
	 * przycisk
	 */
	Button button;
	/**
	 * tekst inputowany
	 */
	TextField input;
	/**
	 * socket
	 */
	Socket socket = null;
	/**
	 * out - wysylanie tekstu na serwer
	 */
	PrintWriter out = null;
	/**
	 * in - odbieranie tekstu
	 */
	BufferedReader in = null;


	/**
	 * konstuktor - ustawiamy wiekszosc rzeczy
	 */
	SocketClient() {
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		input = new TextField(20);
		output = new Label();
		output.setBackground(Color.white);
		button = new Button("Send");
		button.addActionListener(this);

		setLayout(new GridLayout(3, 1));
		add(input);
		add(button);
		add(output);
	}

	/**
	 * metoda wywolywana automatycznie bo jakims wydarzeniu
	 *
	 * @param event wydarzenie zwiazane z klikaniem
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == button) {
			// Wysylanie do serwera
			out.println(input.getText());
			try {
				// Odbieranie z serwera
				output.setText(in.readLine());
			} catch (IOException e) {
				System.out.println("Read failed");
				System.exit(1);
			}
			input.setText("");
			input.requestFocus();
		}
	}

	/**
	 * nasluchiwanie - laczenie z serwerem
	 */
	public void listenSocket() {
		try {
			// Po³aczenie z socketem
			socket = new Socket("localhost", 4444);
			// Wysylanie do serwera
			out = new PrintWriter(socket.getOutputStream(), true);
			// Odbieranie z serwera
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: localhost");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
	}

	/**
	 * klasa glowna - uruchamiamy z niej program
	 *
	 * @param args - argumenty
	 */
	public static void main(String[] args) {
		SocketClient frame = new SocketClient();
		JOptionPane.showMessageDialog(frame, "Podaj typ danych w drzewie");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);
		frame.listenSocket();
	}
}
