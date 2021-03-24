public class Zadanie3 {

	public static void main(String[] args) {
		int n;

		for(int i = 0; i < args.length; i++) {
			try {
				n = Integer.parseInt(args[i]);

				if(n<=0) {
					System.out.println(n + " nie jest liczba naturalna");
					continue;
				}
				System.out.println(n + ": " + div(n));

			} catch (NumberFormatException ex) {
				System.out.println(args[i] + " nie jest liczba naturalna");
			}
		}

	}

	//jej najwiekszy podzielnik to ona sama?
	public static int div(int n) {

		for(int i = n/2; i > 0; i--) {
			if(n % i == 0)
				return i;
		}
		return n;
	}
}
