import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Locale;


/**
 * Klasa serwera
 */
public class SocketServer {
	/**
	 * serwer
	 */
	ServerSocket server = null;
	/**
	 * klient
	 */
	Socket client = null;
	/**
	 * buffer
	 */
	BufferedReader in = null;
	/**
	 * writer
	 */
	PrintWriter out = null;
	/**
	 * line - kolejne argumenty
	 */
	String line = "";

	/**
	 * drzewo stringow
	 */
	Tree<String> stringTree = new Tree<>();
	/**
	 * drzewo double
	 */
	Tree<Double> doubleTree = new Tree<>();
	/**
	 * drzewo intow
	 */
	Tree<Integer> intTree = new Tree<>();

	/**
	 * typ danych w drzewie
	 */
	private static String TYPE;

	/**
	 * konstruktor
	 */
	SocketServer() {
		try {
			server = new ServerSocket(4444);
		} catch (IOException e) {
			System.out.println("Could not listen on port 4444");
			System.exit(-1);
		}
	}

	/**
	 * nasluchiwanie od po dane od klienta
	 */
	public void listenSocket() {
		try {
			client = server.accept();
		} catch (IOException e) {
			System.out.println("Accept failed: 4444");
			System.exit(-1);
		}
		try {
			// Odbieranie od socketa
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			// Wysylanie do socketa
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Accept failed: 4444");
			System.exit(-1);
		}
		while (line != null) {
			try {
				// Odbieranie od socketa
				line = in.readLine();

				String action = line.split(" ")[0];

				if (TYPE == null) {
					TYPE = action;
					out.println("Ustawiono typ: " + TYPE);
					continue;
				}

				String arg = "";

				if (line.split(" ").length > 1)
					arg = line.split(" ")[1];

				if (TYPE.equalsIgnoreCase("string"))
					stringTree.draw();
				else if (TYPE.equalsIgnoreCase("int"))
					intTree.draw();
				else if (TYPE.equalsIgnoreCase("double"))
					doubleTree.draw();

				switch (action) {
					case "insert":
						if (TYPE.equalsIgnoreCase("string"))
							stringTree.insert(arg);
						else if (TYPE.equalsIgnoreCase("int"))
							intTree.insert(Integer.parseInt(arg));
						else if (TYPE.equalsIgnoreCase("double"))
							doubleTree.insert(Double.parseDouble(arg));
						out.println("Dopisano: " + arg);
						break;
					case "draw":
						if (TYPE.equalsIgnoreCase("string"))
							out.println(stringTree.draw());
						else if (TYPE.equalsIgnoreCase("int"))
							out.println(intTree.draw());
						else if (TYPE.equalsIgnoreCase("double"))
							out.println(doubleTree.draw());
						break;
					case "remove":
						if (TYPE.equalsIgnoreCase("string"))
							stringTree.remove(arg);
						else if (TYPE.equalsIgnoreCase("int"))
							intTree.remove(Integer.parseInt(arg));
						else if (TYPE.equalsIgnoreCase("double"))
							doubleTree.remove(Double.parseDouble(arg));
						out.println("Usunieto: " + arg);
						break;
					case "search":
						boolean success = false;
						if (TYPE.equalsIgnoreCase("string"))
							success = stringTree.search(arg);
						else if (TYPE.equalsIgnoreCase("int"))
							success = intTree.search(Integer.parseInt(arg));
						else if (TYPE.equalsIgnoreCase("double"))
							success = doubleTree.search(Double.parseDouble(arg));
						out.println("Znaleziono element: " + arg + "? " + success);
				}

				if (TYPE.equalsIgnoreCase("string"))
					stringTree.draw();
				else if (TYPE.equalsIgnoreCase("int"))
					intTree.draw();
				else if (TYPE.equalsIgnoreCase("double"))
					doubleTree.draw();

			} catch (IOException ex) {
				System.out.println("Read failed");
				System.exit(-1);
			} catch (NumberFormatException ex) {
				out.println("Wprowadzono zly typ danej!");
			} catch (NullPointerException ex) {
				out.println("Prawdopodobnie zabraklo argumentow");
			}
		}
	}

	/**
	 * Metoda odpowiadajaca za konczenie polaczenia
	 */
	protected void finalize() {
		try {
			in.close();
			out.close();
			client.close();
			server.close();
		} catch (IOException e) {
			System.out.println("Could not close.");
			System.exit(-1);
		}
	}

	/**
	 * Glowna funkcja sluzaca do uruchamiania programu
	 *
	 * @param args argumenty
	 */
	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.listenSocket();
	}

}
