package zadanie2;

public class Kalkulacje {

	public static int[] obliczWiersz(int n) {
		int[] wiersze = new int[n+1];
		int licznik = 0;

		while(licznik < wiersze.length) {
			wiersze[licznik] = (int) (silnia(n) / (silnia(licznik) * silnia(n-licznik)));

			licznik++;
		}
		return wiersze;
	}

	private static long silnia(int n) {
		long s = 1;
		for(int i = 1; i <=n; i++)
			s*=i;
		return s;
	}
}
