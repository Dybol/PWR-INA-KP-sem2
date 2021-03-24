public class Test {
	public static void main(String[] args) {

		try {
			int n = Integer.parseInt(args[0]);
			if (n < 0 ) {
				System.out.println(n + " - Nieprawidlowy numer wiersza");
				return;
			}
			WierszTrojkataPascala wiersz = new WierszTrojkataPascala(n);

			for(int i = 1; i < args.length; i++) {
				try {
					int liczba = Integer.parseInt(args[i]);

					if(liczba < 0 || liczba > n) {
						System.out.println(liczba + " - liczba spoza zakresu");
						continue;
					}
					System.out.println(liczba + " - " + wiersz.wspolczynnik(liczba));

				} catch (NumberFormatException ex) {
					System.out.println(args[i] + " - nieprawidlowa dana");
				}

			}

		} catch (NumberFormatException ex) {
			System.out.println(args[0] + " -  nieprawidlowy numer wiersza");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Wprowadz jakies argumenty!");
		}

		//wypiszWszystkie(); //<- dla 15 sie juz zle wypisuje
	}

	public static void wypiszWszystkie() {
		for(int i = 0; i < 30; i++) {
			WierszTrojkataPascala wiersz = new WierszTrojkataPascala(i);
			System.out.println("Dla i = " + i);
			for(int j = 0; j <= i; j++) {
				System.out.print(wiersz.wspolczynnik(j) + " ");
			}

		}
	}
}
